package com.ssaffron.business.api.controller;


import com.ssaffron.business.api.dto.MemberDto;
import com.ssaffron.business.api.dto.MemberModifyDto;
import com.ssaffron.business.api.entity.MemberEntity;
import com.ssaffron.business.api.exception.DuplicatedEmailException;
import com.ssaffron.business.api.service.HeaderUtil;
import com.ssaffron.business.api.service.MemberService;
import com.ssaffron.business.api.service.RedisUtil;
import com.ssaffron.business.api.success.SuccessCode;
import com.ssaffron.business.api.success.SuccessHandler;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/v1/api/member")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class MemberController {

    private final MemberService memberService;
    private final RedisUtil redisUtil;
    private final SuccessHandler successHandler;

    @Operation(summary = "회원가입")
    @PostMapping("/signup")
    public ResponseEntity registerMember(@RequestBody MemberDto memberDto){
        memberService.registerMember(memberDto);
        successHandler.sendSuccessLog(SuccessCode.REGISTER_MEMBER,"POST v1/api/member/signup");
        return new ResponseEntity(HttpStatus.OK);
    }

    @Operation(summary = "로그아웃")
    @DeleteMapping("/logout")
    public ResponseEntity doLogout(HttpServletRequest httpServletRequest){
        String refreshToken = HeaderUtil.getRefreshToken(httpServletRequest);
        redisUtil.deleteRefreshToken(refreshToken);
        MDC.clear();
        return new ResponseEntity(HttpStatus.OK);
    }

    @Operation(summary = "{email}통한 이메일 중복 여부 체크")
    @GetMapping("/check/{email}")
    public ResponseEntity checkDuplication(@PathVariable("email") String email){
        memberService.checkEmailDuplicate(email);

        return new ResponseEntity(HttpStatus.OK);
    }

    @Operation(summary = "회원정보 조회")
    @GetMapping()
    public ResponseEntity<MemberDto> getMember(){
        String memberEmail = memberService.decodeJWT();
        MemberDto memberDto = memberService.getMemberDto(memberEmail);

        return new ResponseEntity<>(memberDto, HttpStatus.OK);
    }

    @Operation(summary = "회원정보 수정")
    @PutMapping()
    public ResponseEntity<String> updateMember(@RequestBody MemberModifyDto memberModifyDto){
        String memberEmail = memberService.decodeJWT();
        memberService.updateMember(memberModifyDto);
        MemberEntity response = memberService.getMember(memberEmail);
        successHandler.sendSuccessLog(SuccessCode.UPDATE_MEMBER,"PUT v1/api/member");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "회원탈퇴")
    @DeleteMapping()
    public ResponseEntity<String> deleteMember(){
        String memberEmail = memberService.decodeJWT();
        memberService.deleteMember(memberEmail);
        successHandler.sendSuccessLog(SuccessCode.DELETE_MEMBER,"DELETE v1/api/member");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "JWT토큰 갱신")
    @GetMapping("/refresh")
    public ResponseEntity refreshToken(){
        String memberEmail = memberService.decodeJWT();

        return new ResponseEntity(HttpStatus.OK);
    }

}
