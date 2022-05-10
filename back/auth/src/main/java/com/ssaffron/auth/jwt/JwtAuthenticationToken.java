//package com.ssaffron.auth.jwt;
//
//import org.springframework.security.authentication.AbstractAuthenticationToken;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
//
//public class JwtAuthenticationToken extends AbstractAuthenticationToken {
//
//    private Object principal;
//
//    private String credentials;
//
//
//    public JwtAuthenticationToken(Object principal, String credentials) {
//        super(null);
//        super.setAuthenticated(false);
//        this.principal = principal;
//        this.credentials = credentials;
//    }
//
//    public JwtAuthenticationToken(Object principal, String credentials, List<GrantedAuthority> authorities) {
//        super(authorities);
//        super.setAuthenticated(true);
//
//        this.principal = principal;
//        this.credentials = credentials;
//    }
//
//    @Override
//    public Object getCredentials() {
//        return this.credentials = credentials;
//    }
//
//    @Override
//    public Object getPrincipal() {
//        return this.principal = principal;
//    }
//}
