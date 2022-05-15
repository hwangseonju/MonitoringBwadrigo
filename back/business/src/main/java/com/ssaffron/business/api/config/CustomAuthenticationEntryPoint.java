package com.ssaffron.business.api.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssaffron.business.api.entity.Response;
import com.ssaffron.business.api.exception.ErrorCode;
import com.ssaffron.business.api.exception.ErrorResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        ObjectMapper objectMapper = new ObjectMapper();

        httpServletResponse.setStatus(401);
        httpServletResponse.setContentType("application/json;charset=utf-8");
        PrintWriter out = httpServletResponse.getWriter();
        ErrorResponse response = ErrorResponse.of(ErrorCode.UNAUTHORIZATION);
        response.setDetatil("로그인 되지 않은 사용자입니다.");
        String jsonResponse = objectMapper.writeValueAsString(response);
        out.print(jsonResponse);
    }
}
