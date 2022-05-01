package com.ssaffron.auth.jwt;

import com.ssaffron.auth.dto.MemberDto;
import com.ssaffron.auth.util.*;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.springframework.security.config.Elements.JWT;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private final MemberDetailsService memberDetailsService;

    private final JwtUtil jwtUtil;

    private final CookieUtil cookieUtil;

    private final RedisUtil redisUtil;


    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        log.info("거침?");
        final Cookie jwtToken = cookieUtil.getCookie(httpServletRequest,JwtUtil.ACCESS_TOKEN_NAME);
        log.info(String.valueOf(jwtToken));
        String memberEmail = null;
        String jwt = null;
        String refreshJwt = null;
        String refreshUname = null;

        try{
            if(jwtToken != null){
                log.info("거침?2");
                jwt = jwtToken.getValue();
                log.info("jwt: {}",jwt);
                log.info("거침?2");
                log.info("memberEmail: {}",jwtUtil.getUsername(jwt));
                memberEmail = jwtUtil.getUsername(jwt);


            }
            if(memberEmail!=null){
                log.info("거침?3");
                UserDetails userDetails = memberDetailsService.loadUserByUsername(memberEmail);
                log.info("거침?33");

//                SecurityMember userDetails = new SecurityMember(memberDto);


                if(jwtUtil.validateToken(jwt,userDetails)){
                    log.info("거침?4");
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
//                    JwtAuthenticationToken authenticationToken = new JwtAuthenticationToken(new JwtAuthenticationToken(jwt, memberEmail), null);
//                    authenticationToken.setDetails((new WebAuthenticationDetailsSource().buildDetails(httpServletRequest)));

                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
        }catch (ExpiredJwtException e){
            log.info("거침?5");
            Cookie refreshToken = cookieUtil.getCookie(httpServletRequest,JwtUtil.REFRESH_TOKEN_NAME);
            if(refreshToken!=null){
                log.info("거침?6");
                refreshJwt = refreshToken.getValue();
            }
        }catch(NullPointerException e){
            log.info("왔니?");
        }

        try{
            if(refreshJwt != null){
                refreshUname = String.valueOf(redisUtil.getData(refreshJwt));

                if(refreshUname != null && refreshUname.equals(jwtUtil.getUsername(refreshJwt))){
                    UserDetails userDetails = memberDetailsService.loadUserByUsername(refreshUname);
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));

//                    JwtAuthenticationToken authenticationToken = new JwtAuthenticationToken(new JwtAuthenticationToken(jwt, memberEmail), null);
//                    authenticationToken.setDetails((new WebAuthenticationDetailsSource().buildDetails(httpServletRequest)));

                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

//                    MemberEntity memberEntity = new MemberEntity();
//                    memberEntity.setMemberEmail(refreshUname);

                    MemberDto memberDto = new MemberDto();
                    memberDto.setMemberEmail(refreshUname);

                    String newToken =jwtUtil.generateToken(memberDto);

                    Cookie newAccessToken = cookieUtil.createCookie(JwtUtil.ACCESS_TOKEN_NAME,newToken);
                    httpServletResponse.addCookie(newAccessToken);
                }
            }
        }catch(ExpiredJwtException e){
        }

        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }

}
