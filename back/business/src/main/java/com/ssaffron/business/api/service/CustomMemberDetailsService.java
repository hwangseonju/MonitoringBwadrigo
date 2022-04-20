package com.ssaffron.business.api.service;

import com.ssaffron.business.api.entity.MemberEntity;
import com.ssaffron.business.api.entity.MemberStatus;
import com.ssaffron.business.api.repository.MemberRepository;
import org.elasticsearch.client.ccr.FollowInfoResponse;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component("memberDetailsService")
public class CustomMemberDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;

    public CustomMemberDetailsService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String memberEmail) {
        return memberRepository.findOneWithAuthoritiesByMemberEmail(memberEmail)
                .map(memberEntity -> createUser(memberEmail, memberEntity))
                .orElseThrow(() -> new UsernameNotFoundException(memberEmail + " -> 데이터베이스에서 찾을 수 없습니다."));
    }

    private org.springframework.security.core.userdetails.User createUser(String memberEmail, MemberEntity memberEntity) {
        if (!memberEntity.getMemberStatus().equals(MemberStatus.ACTIVATE)) {
            throw new RuntimeException(memberEmail + " -> 활성화되어 있지 않습니다.");
        }
        List<GrantedAuthority> grantedAuthorities = memberEntity.getAuthorities().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getAuthorityName()))
                .collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(memberEntity.getMemberEmail(),
                memberEntity.getMemberPassword(),
                grantedAuthorities);
    }
}
