package com.ssaffron.auth.filter;

import com.ssaffron.auth.util.HeaderUtil;
import kong.unirest.Unirest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

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
        if(token != null){
            Map<String,String> header = new HashMap<>();
            header.put("Content-Type", "application/json;charset=UTF-8");
            header.put("Authorization",token);
            requestUnirest(request,header);
        }
        else
            filterChain.doFilter(request, response);
    }

    private void requestUnirest(HttpServletRequest request, Map<String,String> header) throws IOException {
        String requestURI = request.getRequestURI();
        String requestMethod  = request.getMethod();
        InputStream inputStream = request.getInputStream();
        String body = null;
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;
        if (inputStream != null) {
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            char[] charBuffer = new char[128];
            int bytesRead = -1;
            while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                stringBuilder.append(charBuffer, 0, bytesRead);
            }
        }
        body = stringBuilder.toString();
        body = body.replaceAll("[\\t\\s]","").replaceAll("\"","").replaceAll(" ","").replace("{","");
        String[] bodyArr = body.split(":|,|}");

        Map<String, Object> fields = new HashMap<>();
        for(int i=1;i< bodyArr.length;i+=2){
            fields.put(bodyArr[i-1],bodyArr[i]);
        }

        switch (requestMethod){
            case "GET":
                log.info("겟임.");
                Unirest.get(requestURI).headers(header);
                break;
            case "POST":
                log.info("포스트임.");
                Unirest.post(requestURI).headers(header).body(fields).asJson();
                log.info("URL: {}, HEADER: {}, BODY: {}",requestURI,header,fields);
                break;
            case "PUT":
                log.info("풋임.");
                Unirest.put(requestURI).headers(header).body(fields).asJson();
                break;
            case "DELETE":
                log.info("삭제임");
                Unirest.delete(requestURI).headers(header);
                break;
            default:
                log.info("??");
                break;
        }
    }
}
