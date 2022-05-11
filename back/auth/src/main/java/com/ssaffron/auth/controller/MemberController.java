package com.ssaffron.auth.controller;

import com.ssaffron.auth.dto.MemberDto;
import com.ssaffron.auth.entity.MemberEntity;
import com.ssaffron.auth.service.MemberService;
import com.ssaffron.auth.util.JwtUtil;
import com.ssaffron.auth.util.RedisUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class MemberController {

    private final JwtUtil jwtUtil;
    private final RedisUtil redisUtil;
    private final MemberService memberService;

    @PostMapping()
    public ResponseEntity doLogin(@RequestBody MemberDto memberDto){
        MemberEntity memberEntity = memberService.getMember(memberDto.getMemberEmail(),memberDto.getMemberPassword());
        String memberEmail = memberEntity.getMemberEmail();
        String role = memberEntity.getRole().toString();

        String token = jwtUtil.generateToken(memberEntity);
        String refreshToken = jwtUtil.generateRefreshToken(memberEntity);
        redisUtil.setDataExpire(refreshToken, memberEmail, role ,JwtUtil.REFRESH_TOKEN_VALIDATION_SECOND);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer " + token);
        httpHeaders.add("RefreshToken", "Bearer " + refreshToken);
        return new ResponseEntity<>(httpHeaders, HttpStatus.OK);
    }

    @GetMapping("/refresh")
    public ResponseEntity refreshToken(@PathVariable("useremail") String memberEmail){
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity(headers, HttpStatus.OK);
    }
//-
}