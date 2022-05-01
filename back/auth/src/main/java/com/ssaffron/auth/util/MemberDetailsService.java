package com.ssaffron.auth.util;

import com.ssaffron.auth.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class MemberDetailsService implements UserDetailsService{

//    public UserDetails loadUserByUsername(MemberDto memberDto) throws UsernameNotFoundException {
//
//        return new SecurityMember(memberDto);
//    }


    private PasswordEncoder encoder;

//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }


    public UserDetails loadUserByUsername(String memberEmail) throws UsernameNotFoundException {
        log.info("들어오니?");
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails)principal;
        String password = userDetails.getPassword();
        log.info("pw: {}",password);
        return new User(memberEmail, encoder.encode(password), AuthorityUtils.createAuthorityList());
    }
}
