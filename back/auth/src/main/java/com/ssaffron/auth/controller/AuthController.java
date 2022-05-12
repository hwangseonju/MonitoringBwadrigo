package com.ssaffron.auth.controller;

import com.ssaffron.auth.dto.MemberDto;
import com.ssaffron.auth.entity.MemberEntity;
import com.ssaffron.auth.service.MemberService;
import com.ssaffron.auth.util.JwtUtil;
import com.ssaffron.auth.util.RedisUtil;
import kong.unirest.Unirest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
public class MemberController {

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

    @RequestMapping("*")
    public ResponseEntity returnMethode(HttpServletRequest request) throws IOException {
        String authorization = request.getHeader(HEADER_AUTHORIZATION);
        String requestUri = BUSINESS + request.getRequestURI();
        String requestMethod = request.getMethod();
        Map<String,String> header = new HashMap<>();
        header.put("Content-Type", "application/json;charset=UTF-8");
        header.put("Authorization",authorization);
        log.info("{}",requestUri);

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

        switch (requestMethod){
            case "GET":
                return (ResponseEntity) Unirest.get(requestUri).headers(header).asJson();
            case "POST":
                return (ResponseEntity) Unirest.post(requestUri).headers(header).body(fields).asJson();

            case "PUT":
                return (ResponseEntity) Unirest.put(requestUri).headers(header).body(fields).asJson();

            case "DELETE":
                return (ResponseEntity) Unirest.delete(requestUri).headers(header).asJson();

            default:
                return new ResponseEntity(HttpStatus.BAD_REQUEST);

        }
    }
}