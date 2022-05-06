package com.ssaffron.business.api.service;

import com.ssaffron.business.api.config.JwtUtil;
import com.ssaffron.business.api.config.UserRole;
import com.ssaffron.business.api.dto.MemberDto;
import com.ssaffron.business.api.dto.MemberModifyDto;
import com.ssaffron.business.api.entity.MemberEntity;
import com.ssaffron.business.api.entity.MemberStatus;

import com.ssaffron.business.api.exception.DuplicatedEmailException;
import com.ssaffron.business.api.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;


@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;

    private final JwtUtil jwtUtil;

    private final CookieUtil cookieUtil;

    private final RedisUtil redisUtil;

    private final PasswordEncoder passwordEncoder;

    public void checkEmailDuplicate(String email){
        if(memberRepository.existsByMemberEmail(email)) {
            throw new DuplicatedEmailException(email);
        }
    }

    public void registerMember(MemberDto memberDto){
        MemberEntity memberEntity = MemberEntity.builder()
                .memberEmail(memberDto.getMemberEmail())
                .memberPassword(passwordEncoder.encode(memberDto.getMemberPassword()))
                .memberName(memberDto.getMemberName())
                .memberPhone(memberDto.getMemberPhone())
                .memberAddress(memberDto.getMemberAddress())
                .memberGender(memberDto.isMemberGender())
                .memberAge(memberDto.getMemberAge())
                .memberStatus(MemberStatus.ACTIVATE)
                .role(UserRole.ROLE_USER)
                .memberCreateDate(LocalDateTime.now())
                .memberUpdateDate(LocalDateTime.now())
                .build();
        memberRepository.save(memberEntity);
    }

    public MemberEntity getMember(String memberEmail){
        return memberRepository.findByMemberEmail(memberEmail);
    }

    public void updateMember(MemberModifyDto memberModifyDto){
        MemberEntity memberEntity = memberRepository.findByMemberEmail(memberModifyDto.getMemberEmail());
        if(!passwordEncoder.matches(memberModifyDto.getMemberPassword(), memberEntity.getMemberPassword())){
            throw new NullPointerException();
        }
        else{
            if(memberModifyDto.getModifiedPassword()==null){
                memberEntity.setMemberAddress(memberModifyDto.getMemberAddress());
                memberRepository.save(memberEntity);
            }
            else{
                memberEntity.setMemberPassword(passwordEncoder.encode(memberModifyDto.getModifiedPassword()));
                memberEntity.setMemberAddress(memberModifyDto.getMemberAddress());
                memberRepository.save(memberEntity);
            }
        }
    }

    public void deleteMember(String memberEmail){
        MemberEntity memberEntity = memberRepository.findByMemberEmail(memberEmail);
        memberEntity.setMemberStatus(MemberStatus.DEACTIVATE);
        memberRepository.save(memberEntity);
    }

    public Map<String, Object> login(String memberEmail, String memberPwd) {
        Map<String, Object> result = new HashMap<>();
        MemberEntity memberEntity = memberRepository.findByMemberEmail(memberEmail);

        // 존재하는 회원인가?
        if(memberEntity == null)
            return null;

        // 비밀번호가 맞는지 비교
        if(!passwordEncoder.matches(memberPwd ,memberEntity.getMemberPassword()))
            return null;

        // 사용자의 이메일과 비밀번호가 맞으면 Access 토큰과 Refresh토큰을 쿠키 값으로 준다.
        String token = jwtUtil.generateToken(memberEntity);
        String refreshJwt = jwtUtil.generateRefreshToken(memberEntity);
        Cookie accessToken = cookieUtil.createCookie(JwtUtil.ACCESS_TOKEN_NAME, token);
        Cookie refreshToken = cookieUtil.createCookie(JwtUtil.REFRESH_TOKEN_NAME, refreshJwt);
        redisUtil.setDataExpire(refreshJwt, memberEntity.getMemberEmail(), JwtUtil.REFRESH_TOKEN_VALIDATION_SECOND);
//        res.addCookie(accessToken);
//        res.addCookie(refreshToken);

        result.put("memberName", memberEntity.getMemberName());
        result.put("accessToken", accessToken);
        result.put("refreshToken", refreshToken);

        log.info(redisUtil.getData(refreshJwt));

        return result;
    }

    public String decodeJWT() {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String memberEmail = principal.getUsername();
//        String memberPassword = principal.getPassword();

        return memberEmail;
    }

}