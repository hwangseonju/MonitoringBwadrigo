package com.ssaffron.auth.util;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import java.util.List;

public class SecurityMember extends User {
    private static final long serialVersionUid = 1L;

    public SecurityMember(String memberEmail, String memberPassword, List<GrantedAuthority> role) {
        super(memberEmail, memberPassword, role);




//        super(memberEntity.getMemberEmail(),"{noop}"+ memberEntity.getMemberPassword());
    }

}
