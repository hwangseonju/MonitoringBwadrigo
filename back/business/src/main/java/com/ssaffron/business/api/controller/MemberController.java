package com.ssaffron.business.api.controller;

import com.ssaffron.business.api.dto.LoginRequestDto;
import com.ssaffron.business.api.dto.MemberDto;
import com.ssaffron.business.api.dto.MemberModifyDto;
import com.ssaffron.business.api.entity.MemberEntity;
import com.ssaffron.business.api.exception.BadRequestException;
import com.ssaffron.business.api.exception.NullMemberException;
import com.ssaffron.business.api.exception.DuplicatedEmailException;
import com.ssaffron.business.api.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;

@RestController
@RequestMapping("/v1/api/member")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity registerMember(@RequestBody MemberDto memberDto, @RequestParam String redirect){
        memberService.registerMember(memberDto);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(redirect));
        return new ResponseEntity(headers, HttpStatus.OK);
    }


    @DeleteMapping("/logout")
    public ResponseEntity doLogout(HttpServletResponse response, @RequestParam String redirect){

        Cookie cookie1 = new Cookie("accessToken",null);
        cookie1.setMaxAge(0);
        cookie1.setPath("/");
        Cookie cookie2 = new Cookie("refreshToken",null);
        cookie2.setMaxAge(0);
        cookie2.setPath("/");
        response.addCookie(cookie1);
        response.addCookie(cookie2);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(redirect));
        return new ResponseEntity(headers, HttpStatus.OK);
    }

    @GetMapping("/check/{email}")
    public ResponseEntity checkDuplication(@PathVariable("email") String email, @RequestParam String redirect) throws DuplicatedEmailException{
        log.info("check duplication in "+email);
        memberService.checkEmailDuplicate(email);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(redirect));
        return new ResponseEntity(headers, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<MemberDto> getMember(@RequestParam String redirect){
        String memberEmail = memberService.decodeJWT();
        MemberEntity memberEntity = memberService.getMember(memberEmail);
        MemberDto memberDto = MemberDto.builder()
                .memberEmail(memberEntity.getMemberEmail())
                .memberName(memberEntity.getMemberName())
                .memberPhone(memberEntity.getMemberPhone())
                .memberAddress(memberEntity.getMemberAddress())
                .memberGender(memberEntity.isMemberGender())
                .memberAge(memberEntity.getMemberAge())
                .memberStatus(memberEntity.getMemberStatus())
                .userRole(memberEntity.getRole())
                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(redirect));
        return new ResponseEntity<>(memberDto, headers, HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<String> updateMember(@RequestBody MemberModifyDto memberModifyDto, @RequestParam String redirect){
        String memberEmail = memberService.decodeJWT();
        memberService.updateMember(memberModifyDto);
        MemberEntity response = memberService.getMember(memberEmail);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(redirect));
        return new ResponseEntity<>("수정 완료", headers, HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity<String> deleteMember(@RequestParam String redirect){
        String memberEmail = memberService.decodeJWT();
        memberService.deleteMember(memberEmail);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(redirect));
        return new ResponseEntity<>("삭제 완료" , headers, HttpStatus.OK);
    }

    @GetMapping("/refresh")
    public ResponseEntity refreshToken(@RequestParam String redirect){
        String memberEmail = memberService.decodeJWT();
        log.info("refreshToken in "+memberEmail);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(redirect));
        return new ResponseEntity(headers, HttpStatus.OK);
    }

}
