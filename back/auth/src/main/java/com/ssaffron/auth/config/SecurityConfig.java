package com.ssaffron.auth.config;

import com.ssaffron.auth.filter.RequestFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Bcrypt 알고리즘으로 패스워드를 해시화 하거나 일치 여부를 확인
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();// rest api이므로 csrf 보안이 필요없으므로 disable처리
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // jwt 토큰으로 인증할 것이므로 세션이 필요없으므로 생성안함
//                .and()
//                .httpBasic()
//                .and()
//                .authorizeRequests() // 다음 리퀘스트에 대한 사용권한 체크
//                .antMatchers("/v1/api/auth/test").permitAll()
//                .anyRequest().authenticated();
    }

}
