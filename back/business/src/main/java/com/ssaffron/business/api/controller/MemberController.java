package com.ssaffron.business.api.controller;

import com.ssaffron.business.api.dto.LoginRequestDto;
import com.ssaffron.business.api.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/user")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class MemberController {

    @PostMapping("/signup")
    public ResponseEntity registerUser(@RequestBody MemberDto memberDto){
        log.info("registerUser in "+memberDto.getMemberEmail());
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<MemberDto> login(@RequestBody LoginRequestDto loginRequestDto){
        //로그인 할 때, JWT를 헤더에 넣어서 반환
        log.info("login in "+loginRequestDto.getUserEmail());
        MemberDto memberDto = new MemberDto();
        HttpHeaders headers = new HttpHeaders();
//        headers.add(JWT에 관한 내용)
        return new ResponseEntity<>(memberDto, headers, HttpStatus.OK);
    }

    @GetMapping("/{useremail}")
    public ResponseEntity<MemberDto> getUser(@PathVariable("useremail") String userEmail){
        MemberDto memberDto = new MemberDto();
        memberDto.setMemberEmail(userEmail);
        log.info("getUser in "+userEmail);
        return new ResponseEntity<>(memberDto, HttpStatus.OK);
    }

    @PutMapping("{useremail}")
    public ResponseEntity updateUser(@PathVariable("useremail") String userEmail, @RequestBody MemberDto memberDto){
        MemberDto responseDto = new MemberDto();
        responseDto.setMemberEmail(userEmail);
        log.info("updateUser in "+userEmail);
        log.info("updateUser in "+responseDto.toString());
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/{useremail}")
    public ResponseEntity deleteUser(@PathVariable("useremail") String userEmail){
        log.info("deleteUser in "+userEmail);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/refresh/{useremail}")
    public ResponseEntity refreshToken(@PathVariable("useremail") String userEmail){
        HttpHeaders headers = new HttpHeaders();
        log.info("refreshToken in "+userEmail);
        return new ResponseEntity(headers, HttpStatus.OK);
    }


}
