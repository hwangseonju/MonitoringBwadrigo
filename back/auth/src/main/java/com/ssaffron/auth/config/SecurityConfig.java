package com.ssaffron.auth.config;

import com.ssaffron.auth.jwt.JwtRequestFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final JwtRequestFilter jwtRequestFilter;

    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    private final CustomAccessDeniedHandler customAccessDeniedHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Bcrypt 알고리즘으로 패스워드를 해시화 하거나 일치 여부를 확인
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()// rest api이므로 csrf 보안이 필요없으므로 disable처리
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // jwt 토큰으로 인증할 것이므로 세션이 필요없으므로 생성안함
                .and()
                .httpBasic()
                .authenticationEntryPoint(customAuthenticationEntryPoint) // 토큰 인증 예외 발생 시 이동
                .and()
                .exceptionHandling().accessDeniedHandler(customAccessDeniedHandler) // 권한 예외 발생 시 이동
                .and()
                .authorizeRequests() // 다음 리퀘스트에 대한 사용권한 체크
                .antMatchers("/v1/api/auth").permitAll()
                .antMatchers("/v1/api/auth/test").permitAll()
                .anyRequest().authenticated();

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class); // jwt 토큰 필터를 id / password 인증 필터 전에 넣어라
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
