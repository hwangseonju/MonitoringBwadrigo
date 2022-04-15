package com.ssaffron.business.api.controller;

import com.ssaffron.business.api.entity.FeeEntity;
import com.ssaffron.business.api.entity.PaymentEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/pay")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PayController {

    @GetMapping("/paylist")
    public ResponseEntity<List<PaymentEntity>> payAllList() {
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/{payIdx}")
    public ResponseEntity<PaymentEntity> payOneList(@PathVariable int payIdx) {
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/feelist")
    public ResponseEntity<List<FeeEntity>> feeAllList() {
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/fee/{feeIdx}")
    public ResponseEntity<FeeEntity> feeOneList(@PathVariable int feeIdx) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

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
