package com.ssaffron.auth.controller;

import com.ssaffron.auth.dto.MemberDto;
import com.ssaffron.auth.util.CookieUtil;
import com.ssaffron.auth.util.JwtUtil;

import com.ssaffron.auth.util.RedisUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class MemberController {

    private final JwtUtil jwtUtil;
    private final CookieUtil cookieUtil;
    private final RedisUtil redisUtil;

    @PostMapping()
    public ResponseEntity doLogin(@RequestBody MemberDto memberDto, HttpServletResponse res){
        log.info("여기는?");
        //로그인 할 때, JWT를 헤더에 넣어서 반환
        Map<String, Object> result = new HashMap<>();
        String token = jwtUtil.generateToken(memberDto);
        String refreshJwt = jwtUtil.generateRefreshToken(memberDto);
        Cookie accessToken = cookieUtil.createCookie(JwtUtil.ACCESS_TOKEN_NAME, token);
        Cookie refreshToken = cookieUtil.createCookie(JwtUtil.REFRESH_TOKEN_NAME, refreshJwt);
        String role = String.valueOf(memberDto.getMemberRole());
        log.info("중간은?");
        redisUtil.setDataExpire(refreshJwt, memberDto, JwtUtil.REFRESH_TOKEN_VALIDATION_SECOND);
        log.info("여기는?");
        result.put("memberName", memberDto.getMemberName());
        result.put("accessToken", accessToken);
        result.put("refreshToken", refreshToken);

        //로그인, 이름
        res.addCookie((Cookie) result.get("accessToken"));
        res.addCookie((Cookie) result.get("refreshToken"));
        result.remove("accessToken");
        result.remove("refreshToken");
        return new ResponseEntity<>(result, HttpStatus.OK);

    }

    @GetMapping("/refresh")
    public ResponseEntity refreshToken(@PathVariable("useremail") String memberEmail){
        HttpHeaders headers = new HttpHeaders();
        log.info("refreshToken in "+memberEmail);
        return new ResponseEntity(headers, HttpStatus.OK);
    }


    @GetMapping("/test")
    public void test(){
        log.info("test");
    }
}
