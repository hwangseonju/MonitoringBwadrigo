package com.ssaffron.business.api.controller;

import com.ssaffron.business.api.dto.LoginRequestDto;
import com.ssaffron.business.api.dto.MemberDto;
import com.ssaffron.business.api.dto.MemberModifyDto;
import com.ssaffron.business.api.entity.MemberEntity;
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
import java.util.Map;

@RestController
@RequestMapping("/v1/api/member")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity registerMember(@RequestBody MemberDto memberDto){

        memberService.registerMember(memberDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity doLogin(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse res){
        //로그인 할 때, JWT를 헤더에 넣어서 반환

        Map<String, Object> result = memberService.login(loginRequestDto.getMemberEmail(), loginRequestDto.getMemberPassword());

        //로그인, 이름
        res.addCookie((Cookie) result.get("accessToken"));
        res.addCookie((Cookie) result.get("refreshToken"));
        result.remove("accessToken");
        result.remove("refreshToken");

        return new ResponseEntity<>(result, HttpStatus.OK);

    }

    @GetMapping("/check/{email}")
    public ResponseEntity checkDuplication(@PathVariable("email") String email){
        log.info("check duplication in "+email);
        try{
            memberService.checkEmailDuplicate(email);
            return new ResponseEntity(HttpStatus.OK);
        }
        catch (DuplicatedEmailException e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping()
    public ResponseEntity<MemberDto> getMember(){
        //토큰 까서 이메일 적용
        String memberEmail = memberService.decodeJWT();
        log.info("getUser in "+memberEmail);
        MemberEntity memberEntity = memberService.getMember(memberEmail);
        MemberDto memberDto = MemberDto.builder(memberEntity).build();
        return new ResponseEntity<>(memberDto, HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<String> updateMember(@RequestBody MemberModifyDto memberModifyDto){
        //토큰 까서 이메일 적용
        String memberEmail = memberService.decodeJWT();
        log.info("updateUser in "+memberEmail);
        memberService.updateMember(memberModifyDto);
        MemberEntity response = memberService.getMember(memberEmail);
        return new ResponseEntity<>("수정 완료", HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity<String> deleteMember(){
        //토큰 까서 이메일 적용
        String memberEmail = memberService.decodeJWT();
        log.info("deleteUser in "+memberEmail);
        memberService.deleteMember(memberEmail);
        return new ResponseEntity<>("삭제 완료" ,HttpStatus.OK);
    }

    @GetMapping("/refresh")
    public ResponseEntity refreshToken(@PathVariable("useremail") String memberEmail){
        HttpHeaders headers = new HttpHeaders();
        log.info("refreshToken in "+memberEmail);
        return new ResponseEntity(headers, HttpStatus.OK);
    }

}
