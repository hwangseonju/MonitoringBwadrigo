package com.ssaffron.business.api.controller;

import com.ssaffron.business.api.entity.LaundryPlanEntity;
import com.ssaffron.business.api.entity.MonthPlanEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/pay")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PlanController {

    // dd
    @GetMapping("/paylist")
    public ResponseEntity<List<MonthPlanEntity>> payAllList() {
        return new ResponseEntity(HttpStatus.OK);
    }

    // dd
    @GetMapping("/{payIdx}")
    public ResponseEntity<MonthPlanEntity> payOneList(@PathVariable int payIdx) {
        return new ResponseEntity(HttpStatus.OK);
    }

    // tt
    @GetMapping("/feelist")
    public ResponseEntity<List<LaundryPlanEntity>> feeAllList() {
        return new ResponseEntity(HttpStatus.OK);
    }

    // tt
    @GetMapping("/fee/{feeIdx}")
    public ResponseEntity<LaundryPlanEntity> feeOneList(@PathVariable int feeIdx) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // tt
    @PostMapping("/{payId}")
    public ResponseEntity applicationPayment(@PathVariable("payId") int payId) { //신청
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // dd
    @PutMapping("/{payId}")
    public ResponseEntity updatePayment(@PathVariable("payId") int payId) { // 수정
        return new ResponseEntity(HttpStatus.OK);
    }

    // tt
    @DeleteMapping("/{payId}")
    public ResponseEntity deletePayment(@PathVariable("payId") int payId) { // 삭제
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
