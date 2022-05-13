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

@RestController
@RequestMapping("/v1/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class AuthController {

    private final static String HEADER_AUTHORIZATION = "Authorization";
    private final static String BUSINESS = "http://localhost:8081";
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
    public ResponseEntity refreshToken(@PathVariable("useremail") String memberEmail){
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity(headers, HttpStatus.OK);
    }

    @RequestMapping(value = {"/member/**", "/order/**", "/plan/**"}, method = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.POST, RequestMethod.POST})
//    @GetMapping("/member")
    public Object returnMethode(HttpServletRequest request) throws IOException {
        String authorization = HeaderUtil.getAccessToken(request);
        String requestUri = BUSINESS + request.getRequestURI();
        String requestMethod = request.getMethod();
        HttpHeaders headers = new HttpHeaders();
        Map<String,String> header = new HashMap<>();
        header.put("Content-Type", "application/json;charset=UTF-8");
        header.put("Authorization",authorization);
//        headers.add("Content-Type", "application/json;charset=UTF-8");
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(authorization);
//        headers.add("Authorization",authorization);
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
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity httpEntity = new HttpEntity(fields, headers);

        switch (requestMethod){
            case "GET":
//                return  Unirest.get(requestUri).headers(header).asJson();
                return restTemplate.exchange(requestUri, HttpMethod.GET, httpEntity, Object.class);
            case "POST":
                return restTemplate.exchange(requestUri, HttpMethod.POST, httpEntity, Object.class);

            case "PUT":
                return restTemplate.exchange(requestUri, HttpMethod.PUT, httpEntity, Object.class);

            case "DELETE":
                return restTemplate.exchange(requestUri, HttpMethod.DELETE, httpEntity, Object.class);

            default:
                return new ResponseEntity(HttpStatus.BAD_REQUEST);

        }
    }

}