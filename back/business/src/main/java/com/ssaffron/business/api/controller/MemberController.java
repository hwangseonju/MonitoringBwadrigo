package com.ssaffron.business.api.controller;


import com.ssaffron.business.api.dto.MemberDto;
import com.ssaffron.business.api.dto.MemberModifyDto;
import com.ssaffron.business.api.entity.MemberEntity;
import com.ssaffron.business.api.exception.DuplicatedEmailException;
import com.ssaffron.business.api.service.HeaderUtil;
import com.ssaffron.business.api.service.MemberService;
import com.ssaffron.business.api.service.RedisUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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

    @PostMapping("/signup")
    public ResponseEntity registerMember(@RequestBody MemberDto memberDto){
        memberService.registerMember(memberDto);

        return new ResponseEntity(HttpStatus.OK);
    }


    @DeleteMapping("/logout")
    public ResponseEntity doLogout(HttpServletRequest httpServletRequest){
        String refreshToken = HeaderUtil.getRefreshToken(httpServletRequest);
        redisUtil.deleteRefreshToken(refreshToken);

        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/check/{email}")
    public ResponseEntity checkDuplication(@PathVariable("email") String email) throws DuplicatedEmailException{
        log.info("check duplication in "+email);
        memberService.checkEmailDuplicate(email);

        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<MemberDto> getMember(){
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
        log.info("in - data {}",memberDto.toString());

        return new ResponseEntity<>(memberDto, HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<String> updateMember(@RequestBody MemberModifyDto memberModifyDto){
        String memberEmail = memberService.decodeJWT();
        memberService.updateMember(memberModifyDto);
        MemberEntity response = memberService.getMember(memberEmail);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity<String> deleteMember(){
        String memberEmail = memberService.decodeJWT();
        memberService.deleteMember(memberEmail);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/refresh")
    public ResponseEntity refreshToken(){
        String memberEmail = memberService.decodeJWT();
        log.info("refreshToken in "+memberEmail);

        return new ResponseEntity(HttpStatus.OK);
    }

}
