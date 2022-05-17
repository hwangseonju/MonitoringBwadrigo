package com.ssaffron.business.api.service;

import com.ssaffron.business.api.config.JwtUtil;
import com.ssaffron.business.api.config.UserRole;
import com.ssaffron.business.api.dto.MemberDto;
import com.ssaffron.business.api.dto.MemberModifyDto;
import com.ssaffron.business.api.entity.MemberEntity;
import com.ssaffron.business.api.entity.MemberStatus;

import com.ssaffron.business.api.exception.*;
import com.ssaffron.business.api.repository.MemberRepository;
import com.ssaffron.business.api.success.SuccessCode;
import com.ssaffron.business.api.success.SuccessResponse;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
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
    private final PasswordEncoder passwordEncoder;

    public void checkEmailDuplicate(String email){
        if(memberRepository.existsByMemberEmail(email)) {
            throw new DuplicatedEmailException("Duplicated Email");
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
                .build();
        memberRepository.save(memberEntity);
    }

    public MemberEntity getMember(String memberEmail){
        return memberRepository.findByMemberEmail(memberEmail);
    }

    public MemberDto getMemberDto(String memberEmail){
        MemberEntity memberEntity = getMember(memberEmail);
        MemberDto memberDto = MemberDto.builder()
                .memberEmail(memberEntity.getMemberEmail())
                .memberName(memberEntity.getMemberName())
                .memberPhone(memberEntity.getMemberPhone())
                .memberAddress(memberEntity.getMemberAddress())
                .memberGender(memberEntity.isMemberGender())
                .memberAge(memberEntity.getMemberAge())
                .build();
        return memberDto;
    }

    public void updateMember(MemberModifyDto memberModifyDto){
        MemberEntity memberEntity = memberRepository.findByMemberEmail(memberModifyDto.getMemberEmail());
        if(!passwordEncoder.matches(memberModifyDto.getMemberPassword(), memberEntity.getMemberPassword())){
            throw new NotMatchedPasswordException("Not Matched Password");
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


    public String decodeJWT() {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String memberEmail = principal.getUsername();

        return memberEmail;
    }

}