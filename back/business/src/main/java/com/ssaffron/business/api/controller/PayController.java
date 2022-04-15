package com.ssaffron.business.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/pay")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PayController {

    @PostMapping("/{payId}")
    public ResponseEntity applicationPayment(@PathVariable("payId") int payId) { //신청
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{payId}")
    public ResponseEntity updatePayment(@PathVariable("payId") int payId) { // 수정
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/{payId}")
    public ResponseEntity deletePayment(@PathVariable("payId") int payId) { // 삭제
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
