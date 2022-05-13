package com.ssaffron.business.api.controller;

import com.ssaffron.business.api.entity.Response;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;

@Slf4j
@RestController
@RequestMapping(value = "/v1/api/bucket")
public class BucketController {

    private Bucket bucket;

    public BucketController() {
        //10분에 10개의 요청을 처리할 수 있는 Bucket 생성
//        Bandwidth limit = Bandwidth.classic(10, Refill.intervally(10, Duration.ofMinutes(10)));
//        this.bucket = Bucket.builder()
//                .addLimit(limit)
//                .build();
    }

    @GetMapping("")
    public ResponseEntity<ResponseEntity> bucketTest() {

        if (bucket.tryConsume(1)) {
            log.info("Success");
            return ResponseEntity.status(HttpStatus.OK).build();
        }

        log.error("TOO MANY REQUEST");
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
    }
}
