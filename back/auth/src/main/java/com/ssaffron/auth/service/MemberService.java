package com.ssaffron.auth.service;

import com.ssaffron.auth.config.UserRole;
import com.ssaffron.auth.entity.MemberEntity;
import com.ssaffron.auth.entity.MemberStatus;
import com.ssaffron.auth.repository.MemberRepository;
import com.ssaffron.auth.util.HeaderUtil;
import com.ssaffron.auth.util.JwtUtil;
import com.ssaffron.auth.util.RedisUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final RedisUtil redisUtil;
    private final JwtUtil jwtUtil;

    public MemberEntity getMember(String memberEmail, String memberPassword) {
        MemberEntity memberEntity = memberRepository.findByMemberEmail(memberEmail).orElseThrow(()->new NullPointerException());
        MemberStatus status = memberEntity.getMemberStatus();

        if (passwordEncoder.matches(memberPassword, memberEntity.getMemberPassword()) && status.equals(MemberStatus.ACTIVATE))
            return memberEntity;
        else
            throw new NullPointerException();
    }

    public String refreshAccessToken(HttpServletRequest httpServletRequest) {

        String refreshToken = HeaderUtil.getRefreshToken(httpServletRequest);
        String refreshMemberEmail = String.valueOf(redisUtil.getData(refreshToken).get("memberEmail"));
        String refreshMemberRole = (String) redisUtil.getData(refreshToken).get("memberRole");

        if(refreshMemberEmail != null && refreshMemberEmail.equals(jwtUtil.getMemberEmail(refreshToken))) {
            MemberEntity memberEntity = new MemberEntity();
            memberEntity.setMemberEmail(refreshMemberEmail);
            memberEntity.setRole(UserRole.valueOf(refreshMemberRole));

            String accessToken = jwtUtil.generateToken(memberEntity);

            return accessToken;
        }
        else {
            return "오류 넣어야함";
        }

    }
}
