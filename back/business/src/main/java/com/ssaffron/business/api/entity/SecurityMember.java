package com.ssaffron.business.api.entity;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

public class SecurityMember extends User {
    private static final long serialVersionUid = 1L;

    public SecurityMember(MemberEntity memberEntity) {
        super(memberEntity.getMemberEmail(),"{noop}"+ memberEntity.getMemberPassword(), AuthorityUtils.createAuthorityList(memberEntity.getRole().toString()));
//        super(memberEntity.getMemberEmail(),"{noop}"+ memberEntity.getMemberPassword());
    }
}
