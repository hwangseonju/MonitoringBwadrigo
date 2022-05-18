package com.ssaffron.business.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssaffron.business.api.exception.ErrorCode;
import com.ssaffron.business.api.exception.ErrorResponse;
import com.ssaffron.business.api.exception.NotFoundAddressException;
import com.ssaffron.business.api.exception.TooManyException;
import com.ssaffron.business.api.success.SuccessCode;
import com.ssaffron.business.api.success.SuccessHandler;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Duration;

@Slf4j
@RestController
@RequestMapping(value = "/v1/api/bucket")
public class BucketController {

    private final SuccessHandler successHandler;

    private Bucket bucket;

    public BucketController(SuccessHandler successHandler) {
        this.successHandler = successHandler;
        //10분에 10개의 요청을 처리할 수 있는 Bucket 생성
        Bandwidth limit = Bandwidth.classic(10, Refill.intervally(10, Duration.ofMinutes(10)));
        this.bucket = Bucket.builder()
                .addLimit(limit)
                .build();
    }

    @GetMapping("")
    public ResponseEntity bucketTest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        if (bucket.tryConsume(1)) {
//            successHandler.sendSuccessLog(SuccessCode.TOO_MANY,"GET v1/api/bucket");
            return ResponseEntity.status(HttpStatus.OK).build();
        }else {
            throw new TooManyException("Too Many Requests");
        }

//        httpServletResponse.setStatus(429);
//        httpServletResponse.setContentType("application/json;charset=utf-8");
//        PrintWriter out = httpServletResponse.getWriter();
//        ErrorResponse response = ErrorResponse.of(ErrorCode.TOO_MANY_REQEUSTS);
//        response.setDetail("너무 많은 요청입니다.");
//        String jsonResponse = objectMapper.writeValueAsString(response);
//        out.print(jsonResponse);
//        log.error("TOO MANY REQUEST");
//        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
    }
}
