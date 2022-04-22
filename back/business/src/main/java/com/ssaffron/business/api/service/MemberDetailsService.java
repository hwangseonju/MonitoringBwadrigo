package com.ssaffron.business.api.service;

import com.ssaffron.business.api.entity.MemberEntity;
import com.ssaffron.business.api.entity.SecurityMember;
import com.ssaffron.business.api.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberDetailsService implements UserDetailsService{

    private final MemberRepository memberRepository;
    public UserDetails loadUserByUsername(String memberEmail) throws UsernameNotFoundException {

        MemberEntity memberEntity = memberRepository.findByMemberEmail(memberEmail);
        if(memberEntity == null){
            throw new UsernameNotFoundException(memberEmail + " : 사용자 존재하지 않음");
        }

        return new SecurityMember(memberEntity);
    }

}
