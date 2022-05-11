package com.ssaffron.auth.filter;

import com.ssaffron.auth.util.HeaderUtil;
import kong.unirest.HttpRequest;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class RequestFilter extends OncePerRequestFilter {

    @Override
    public void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws IOException, ServletException {
        //헤더에 토큰이 있으면 검사, 없으면 로그인
        String token = HeaderUtil.getAccessToken(request);
        Unirest.config().defaultBaseUrl("http://localhost:8081");
        log.info("{}",token);
        if(token == null){
            String requestURI = request.getRequestURI();
            String requestMethod  = request.getMethod();
            log.info("test: "+requestMethod);
            HttpResponse tttest = Unirest.get("/v1/api/member/test").asString();
            log.info("response: {}",tttest.getBody());
        }

        filterChain.doFilter(request, response);
    }
}
