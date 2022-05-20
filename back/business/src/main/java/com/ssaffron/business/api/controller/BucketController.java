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
        // 10개의 클라이언트를 3분에 10개의 요청을 처리할 수 있는 Bucket 생성
        Bandwidth limit = Bandwidth.classic(10, Refill.intervally(10, Duration.ofMinutes(3)));
        this.bucket = Bucket.builder()
                .addLimit(limit)
                .build();
    }

    @GetMapping("")
    public ResponseEntity bucketTest(){

        if (bucket.tryConsume(1)) {
            return ResponseEntity.status(HttpStatus.OK).build();
        }else {
            throw new TooManyException("Too Many Requests");
        }

    }
}
