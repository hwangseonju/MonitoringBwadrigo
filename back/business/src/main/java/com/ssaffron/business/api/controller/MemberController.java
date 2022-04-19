package com.ssaffron.business.api.controller;

import com.ssaffron.business.api.dto.LoginRequestDto;
import com.ssaffron.business.api.dto.MemberDto;
import com.ssaffron.business.api.service.MemberService;
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
    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity registerMember(@RequestBody MemberDto memberDto){
        log.info("registerUser in "+memberDto.getMemberEmail());
        memberService.registerMember(memberDto);
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
    public ResponseEntity<MemberDto> getMember(@PathVariable("useremail") String memberEmail){
        MemberDto memberDto = new MemberDto();
        memberDto.setMemberEmail(memberEmail);
        log.info("getUser in "+memberEmail);
        memberService.getMember(memberEmail);
        return new ResponseEntity<>(memberDto, HttpStatus.OK);
    }

    @PutMapping("{useremail}")
    public ResponseEntity updateMember(@PathVariable("useremail") String memberEmail, @RequestBody MemberDto memberDto){
        MemberDto responseDto = new MemberDto();
        responseDto.setMemberEmail(memberEmail);
        log.info("updateUser in "+memberEmail);
        log.info("updateUser in "+responseDto.toString());
        memberService.updateMember(memberDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/{useremail}")
    public ResponseEntity deleteMember(@PathVariable("useremail") String memberEmail){
        log.info("deleteUser in "+memberEmail);
        memberService.deleteMember(memberEmail);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/refresh/{useremail}")
    public ResponseEntity refreshToken(@PathVariable("useremail") String memberEmail){
        HttpHeaders headers = new HttpHeaders();
        log.info("refreshToken in "+memberEmail);
        return new ResponseEntity(headers, HttpStatus.OK);
    }


}
