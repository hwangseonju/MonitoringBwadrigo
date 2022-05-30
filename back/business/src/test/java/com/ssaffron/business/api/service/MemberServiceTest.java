package com.ssaffron.business.api.service;

import com.ssaffron.business.api.config.UserRole;
import com.ssaffron.business.api.entity.MemberEntity;
import com.ssaffron.business.api.entity.MemberStatus;
import com.ssaffron.business.api.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("MemberService 테스트")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MemberServiceTest {

    @Autowired
    private MemberRepository memberRepository;

    private final String testEmail = "abc@abc.com";

    @Test
    @DisplayName("회원가입 테스트")
    void registerMember() {
        MemberEntity memberEntity = MemberEntity.builder()
                .memberEmail("hello@hello.com")
                .memberPassword("hello")
                .memberName("황선주")
                .memberPhone("010-1234-5678")
                .memberAddress("광주")
                .memberAge(25)
                .memberGender(false)
                .memberStatus(MemberStatus.ACTIVATE)
                .role(UserRole.ROLE_USER)
                .build();
        memberRepository.save(memberEntity);

        final MemberEntity savedMember = memberRepository.findByMemberEmail(memberEntity.getMemberEmail());

        assertEquals(memberEntity.getMemberEmail(),savedMember.getMemberEmail());
    }

    @Test
    @DisplayName("회원조회 테스트")
    void getMember() {
        final MemberEntity savedMember = memberRepository.findByMemberEmail(testEmail);

        assertEquals(testEmail,savedMember.getMemberEmail());
    }

    @Test
    @DisplayName("회원정보 수정 테스트")
    void updateMember() {
        String testAddress = "수정된 광주";
        final MemberEntity savedMember = memberRepository.findByMemberEmail(testEmail);

        savedMember.setMemberAddress("수정된 광주");
        memberRepository.save(savedMember);

        assertEquals(testAddress,savedMember.getMemberAddress());
    }

    @Test
    @DisplayName("회원탈퇴 테스트")
    void deleteMember() {
        final MemberEntity savedMember = memberRepository.findByMemberEmail(testEmail);

        savedMember.setMemberStatus(MemberStatus.DEACTIVATE);
        memberRepository.save(savedMember);

        assertEquals(MemberStatus.DEACTIVATE,savedMember.getMemberStatus());
    }
}