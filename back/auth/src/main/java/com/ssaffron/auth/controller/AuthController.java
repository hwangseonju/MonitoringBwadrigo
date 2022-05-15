package com.ssaffron.auth.controller;

import com.ssaffron.auth.dto.MemberDto;
import com.ssaffron.auth.entity.MemberEntity;
import com.ssaffron.auth.service.MemberService;
import com.ssaffron.auth.util.HeaderUtil;
import com.ssaffron.auth.util.JwtUtil;
import com.ssaffron.auth.util.RedisUtil;
import kong.unirest.Unirest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

@RestController
@RequestMapping("/v1/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class AuthController {

    private final static String BUSINESS = "http://k6s104.p.ssafy.io:8081";
    private final JwtUtil jwtUtil;
    private final RedisUtil redisUtil;
    private final MemberService memberService;

    @PostMapping("/auth")
    public ResponseEntity doLogin(@RequestBody MemberDto memberDto){
        MemberEntity memberEntity = memberService.getMember(memberDto.getMemberEmail(),memberDto.getMemberPassword());
        String memberEmail = memberEntity.getMemberEmail();
        String role = memberEntity.getRole().toString();

        String token = jwtUtil.generateToken(memberEntity);
        String refreshToken = jwtUtil.generateRefreshToken(memberEntity);
        redisUtil.setDataExpire(refreshToken, memberEmail, role ,JwtUtil.REFRESH_TOKEN_VALIDATION_SECOND);
        log.info("{}님이 로그인을 시도", memberDto.getMemberEmail());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer " + token);
        httpHeaders.add("RefreshToken", "Bearer " + refreshToken);

        return new ResponseEntity<>(memberEntity.getMemberName(), httpHeaders, HttpStatus.OK);
    }

    @GetMapping("/auth/refresh")
    public ResponseEntity refreshToken(HttpServletRequest httpServletRequest){
        String accessToken = memberService.refreshAccessToken(httpServletRequest);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer " + accessToken);

        return new ResponseEntity(httpHeaders, HttpStatus.OK);
    }

    @RequestMapping(value = {"/member/**", "/order/**", "/plan/**", "/manager/**"}, method = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.POST, RequestMethod.POST})
    public Object returnMethode(HttpServletRequest request) throws IOException {
        String authorization = HeaderUtil.getAccessToken(request);
        String RefreshToken = HeaderUtil.getRefreshToken(request);
        String requestUri = BUSINESS + request.getRequestURI();
        String requestMethod = request.getMethod();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(authorization);
        headers.add("RefreshToken", RefreshToken);

        log.info("auth 그 외의 요청 {}, 너의 헤더는? {}",requestUri,authorization);

        InputStream inputStream = request.getInputStream();
        String body = null;
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;
        if (inputStream != null) {
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            char[] charBuffer = new char[128];
            int bytesRead = -1;
            while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                stringBuilder.append(charBuffer, 0, bytesRead);
            }
        }
        body = stringBuilder.toString();
        body = body.replaceAll("[\\t\\s]","").replaceAll("\"","").replaceAll(" ","").replace("{","");
        String[] bodyArr = body.split(":|,|}");

        Map<String, Object> fields = new HashMap<>();
        for(int i=1;i< bodyArr.length;i+=2){
            fields.put(bodyArr[i-1],bodyArr[i]);
        }

        HttpEntity httpEntity = new HttpEntity(fields, headers);
        ResponseEntity response = null;
        switch (requestMethod){
            case "GET":
                response = getResponse(requestUri, HttpMethod.GET, httpEntity);
                break;
            case "POST":
                response = getResponse(requestUri, HttpMethod.POST, httpEntity);
                break;
            case "PUT":
                response =  getResponse(requestUri, HttpMethod.PUT, httpEntity);
                break;
            case "DELETE":
                response = getResponse(requestUri, HttpMethod.DELETE, httpEntity);
                break;
            default:
                response = new ResponseEntity(HttpStatus.BAD_REQUEST);

        }
        log.info(response.getBody().toString());
        return response;
    }

    public ResponseEntity getResponse(String requestUri, HttpMethod method, HttpEntity httpEntity){

        ResponseEntity responseEntity = null;
        RestTemplate restTemplate = new RestTemplate();
        try{
            responseEntity = restTemplate.exchange(requestUri, method, httpEntity, ResponseEntity.class);

        }catch (Exception e){
            log.info(e.getMessage());
            StringTokenizer st = new StringTokenizer(e.getMessage(),":");
            HttpStatus status = HttpStatus.valueOf(Integer.parseInt(st.nextToken().trim()));
            log.info("{} 에러 코드", status);
            String message = null;
            while(st.hasMoreTokens()){
                message = st.nextToken();
            }
            st = new StringTokenizer(message, "}");
            message = st.nextToken();
            log.info(message);
            responseEntity = new ResponseEntity(message, status);
        }
        return responseEntity;
    }

}