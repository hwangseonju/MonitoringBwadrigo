package com.ssaffron.business.api.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssaffron.business.api.entity.Response;
import com.ssaffron.business.api.entity.SecurityMember;
import com.ssaffron.business.api.exception.ErrorCode;
import com.ssaffron.business.api.exception.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

@Slf4j
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        httpServletResponse.setStatus(401);
        httpServletResponse.setContentType("application/json;charset=utf-8");
        PrintWriter out = httpServletResponse.getWriter();
        ErrorResponse response = ErrorResponse.of(ErrorCode.UNAUTHORIZATION);
        response.setDetail("권한이 없는 사용자입니다.");
        MDC.put("Code",response.getCode());
        MDC.put("MSG",response.getMessage());
        log.warn(e.getMessage());
        String jsonResponse = objectMapper.writeValueAsString(response);
        out.print(jsonResponse);

    }

}
