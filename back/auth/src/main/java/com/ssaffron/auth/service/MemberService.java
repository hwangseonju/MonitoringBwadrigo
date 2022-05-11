package com.ssaffron.auth.service;

import com.ssaffron.auth.entity.MemberEntity;
import com.ssaffron.auth.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberEntity getMember(String memberEmail, String memberPassword) {
        MemberEntity memberEntity = memberRepository.findByMemberEmail(memberEmail).orElseThrow(()->new NullPointerException());

        if (passwordEncoder.matches(memberPassword, memberEntity.getMemberPassword()))
            return memberEntity;
        else
            throw new NullPointerException();
    }
}
