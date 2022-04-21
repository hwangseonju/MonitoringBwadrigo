package com.ssaffron.business.api.controller;

import com.ssaffron.business.api.config.JwtUtil;
import com.ssaffron.business.api.config.UserRole;
import com.ssaffron.business.api.dto.LoginRequestDto;
import com.ssaffron.business.api.dto.MemberDto;
import com.ssaffron.business.api.entity.MemberEntity;
import com.ssaffron.business.api.entity.MemberStatus;
import com.ssaffron.business.api.service.CookieUtil;
import com.ssaffron.business.api.service.MemberService;
import com.ssaffron.business.api.service.RedisUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
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
    public ResponseEntity login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse res){
        //로그인 할 때, JWT를 헤더에 넣어서 반환

        Map<String, Object> result = memberService.login(loginRequestDto.getMemberEmail(), loginRequestDto.getMemberPassword());

        res.addCookie((Cookie) result.get("accessToken"));
        res.addCookie((Cookie) result.get("refreshToken"));
        result.remove("accessToken");
        result.remove("refreshToken");

        return new ResponseEntity<>(result, HttpStatus.OK);

    }

    @GetMapping("/{email}/exists")
    public boolean checkDuplication(@PathVariable("email") String email){
        log.info("check duplication in "+email);
        return memberService.checkEmailDuplicate(email);
    }

    @Secured("ROLE_ADMIN")
    @GetMapping()
    public ResponseEntity<MemberEntity> getMember(){
        //토큰 까서 이메일 적용
        String memberEmail = memberService.decodeJWT();
        MemberDto memberDto = new MemberDto();
        memberDto.setMemberEmail(memberEmail);
        log.info("getUser in "+memberEmail);
        MemberEntity memberEntity = memberService.getMember(memberEmail);
        return new ResponseEntity<>(memberEntity, HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<MemberEntity> updateMember(@RequestBody MemberDto memberDto){
        //토큰 까서 이메일 적용
        String memberEmail = memberService.decodeJWT();
        log.info("updateUser in "+memberEmail);
        memberService.updateMember(memberDto);
        MemberEntity response = memberService.getMember(memberEmail);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity deleteMember(){
        //토큰 까서 이메일 적용
        String memberEmail = memberService.decodeJWT();
        log.info("deleteUser in "+memberEmail);
        memberService.deleteMember(memberEmail);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/refresh")
    public ResponseEntity refreshToken(@PathVariable("useremail") String memberEmail){
        HttpHeaders headers = new HttpHeaders();
        log.info("refreshToken in "+memberEmail);
        return new ResponseEntity(headers, HttpStatus.OK);
    }


}
