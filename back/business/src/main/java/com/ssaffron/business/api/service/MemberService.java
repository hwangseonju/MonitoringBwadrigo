package com.ssaffron.business.api.service;

import com.ssaffron.business.api.dto.MemberDto;
import com.ssaffron.business.api.entity.MemberEntity;
import com.ssaffron.business.api.entity.MemberStatus;
import com.ssaffron.business.api.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    private void saveMember(MemberDto memberDto, MemberEntity memberEntity) {
        memberEntity.setMemberName(memberDto.getMemberName());
        memberEntity.setMemberEmail(memberDto.getMemberEmail());
        memberEntity.setMemberPassword(memberDto.getMemberPassword());
        memberEntity.setMemberAddress(memberDto.getMemberAddress());
        memberEntity.setMemberAge(memberDto.getMemberAge());
        memberEntity.setMemberPhone(memberDto.getMemberPhone());
        memberEntity.setMemberGender(memberDto.isMemberGender());
        memberEntity.setMemberStatus(memberDto.getMemberStatus());
        memberRepository.save(memberEntity);
    }

    public void registerMember(MemberDto memberDto){
        MemberEntity memberEntity = new MemberEntity();
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
    }
}