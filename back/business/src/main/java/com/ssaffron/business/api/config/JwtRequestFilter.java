package com.ssaffron.business.api.config;

import com.ssaffron.business.api.entity.MemberEntity;
import com.ssaffron.business.api.service.*;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private final MemberDetailsService memberDetailsService;

    private final JwtUtil jwtUtil;

    private final RedisUtil redisUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        String jwtToken = HeaderUtil.getAccessToken(httpServletRequest);

        String memberEmail = null;
        String jwt = null;
        String refreshJwt = null;
        String refreshmemberEmail = null;

        try{
            if(jwtToken != null){
                jwt = jwtToken;
                memberEmail = jwtUtil.getUsername(jwt);
            }
            if(memberEmail!=null){
                UserDetails userDetails = memberDetailsService.loadUserByUsername(memberEmail);

                if(jwtUtil.validateToken(jwt,userDetails)){
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
        }catch (ExpiredJwtException e){
            String refreshToken = HeaderUtil.getRefreshToken(httpServletRequest);
            if(refreshToken!=null){
                refreshJwt = refreshToken;
            }
        }catch(Exception e){

        }

        try{
            if(refreshJwt != null){
                refreshmemberEmail = String.valueOf(redisUtil.getData(refreshJwt).get("memberEmail"));

                if(refreshmemberEmail != null && refreshmemberEmail.equals(jwtUtil.getUsername(refreshJwt))){
                    UserDetails userDetails = memberDetailsService.loadUserByUsername(refreshmemberEmail);
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
        }catch(ExpiredJwtException e){

        }

        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
