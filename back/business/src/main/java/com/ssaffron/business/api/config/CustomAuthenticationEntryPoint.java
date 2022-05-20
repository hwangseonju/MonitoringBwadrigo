package com.ssaffron.business.api.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssaffron.business.api.entity.Response;
import com.ssaffron.business.api.exception.ErrorCode;
import com.ssaffron.business.api.exception.ErrorResponse;
import com.ssaffron.business.api.service.HeaderUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        ObjectMapper objectMapper = new ObjectMapper();

        httpServletResponse.setStatus(401);
        httpServletResponse.setContentType("application/json;charset=utf-8");
        PrintWriter out = httpServletResponse.getWriter();
        ErrorResponse response = ErrorResponse.of(ErrorCode.UNAUTHORIZATION);
        response.setDetail("로그인 되지 않은 사용자입니다.");
        MDC.put("Code",response.getCode());
        MDC.put("MSG",response.getMessage());
        log.warn(e.getMessage());
        String jsonResponse = objectMapper.writeValueAsString(response);
        out.print(jsonResponse);
    }
}
