//package com.ssaffron.auth.util;
//
//import com.ssaffron.auth.dto.MemberDto;
//import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.security.core.userdetails.User;
//
//public class SecurityMember extends User {
//    private static final long serialVersionUid = 1L;
//
//    public SecurityMember(MemberDto memberDto) {
//        super(memberDto.getMemberEmail(),"{noop}"+ memberDto.getMemberPassword(), AuthorityUtils.createAuthorityList(memberDto.getMemberRole().toString()));
////        super(memberEntity.getMemberEmail(),"{noop}"+ memberEntity.getMemberPassword());
//    }
//
//}
