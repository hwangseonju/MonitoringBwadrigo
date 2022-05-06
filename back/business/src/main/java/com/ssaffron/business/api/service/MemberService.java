package com.ssaffron.business.api.service;

import com.ssaffron.business.api.config.UserRole;
import com.ssaffron.business.api.dto.MemberDto;
import com.ssaffron.business.api.entity.MemberEntity;
import com.ssaffron.business.api.entity.MemberStatus;

import com.ssaffron.business.api.exception.DuplicatedEmailException;
import com.ssaffron.business.api.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;

//    private PasswordEncoder passwordEncoder;


    private void saveMember(MemberDto memberDto, MemberEntity memberEntity) {
        memberEntity.setMemberName(memberDto.getMemberName());
        memberEntity.setMemberEmail(memberDto.getMemberEmail());
        memberEntity.setMemberPassword(memberDto.getMemberPassword());
        memberEntity.setMemberAddress(memberDto.getMemberAddress());
        memberEntity.setMemberAge(memberDto.getMemberAge());
        memberEntity.setMemberPhone(memberDto.getMemberPhone());
        memberEntity.setMemberGender(memberDto.isMemberGender());
        memberEntity.setMemberStatus(MemberStatus.ACTIVATE);
        memberEntity.setRole(UserRole.ROLE_USER);
        memberEntity.setMemberUpdateDate(LocalDateTime.now());
        memberRepository.save(memberEntity);
    }

    public void checkEmailDuplicate(String email){
        if(memberRepository.existsByMemberEmail(email)) {
            throw new DuplicatedEmailException(email);
        }
    }


    public void registerMember(MemberDto memberDto){
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setMemberCreateDate(LocalDateTime.now());
        saveMember(memberDto, memberEntity);
    }

    public MemberEntity getMember(String memberEmail){
        return memberRepository.findByMemberEmail(memberEmail);
    }

    public void updateMember(MemberDto memberDto){
        MemberEntity memberEntity = getMember(memberDto.getMemberEmail());
        saveMember(memberDto, memberEntity);
    }

    public void deleteMember(String memberEmail){
        MemberEntity memberEntity = getMember(memberEmail);
        memberEntity.setMemberStatus(MemberStatus.DEACTIVATE);
        memberRepository.save(memberEntity);
    }

    public Map<String, Object> login(String memberEmail, String memberPwd) {
        Map<String, Object> result = new HashMap<>();
        log.info("1111");
        MemberEntity memberEntity = memberRepository.findByMemberEmail(memberEmail);

        // 존재하는 회원인가?
        if(memberEntity == null)
            return null;

        // 비밀번호가 맞는지 비교
//        if(!passwordEncoder.matches(memberPwd ,memberEntity.getMemberPassword()))
//            return null;
        log.info("22222");
        // 사용자의 이메일과 비밀번호가 맞으면 Access 토큰과 Refresh토큰을 쿠키 값으로 준다.

        result.put("memberEmail: ", memberEmail);
        result.put("memberPassword: ", memberPwd);

        return result;
    }
    

}