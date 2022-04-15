package com.ssaffron.business.api.controller;

import com.ssaffron.business.api.dto.FeeDto;
import com.ssaffron.business.api.dto.PaymentDto;
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

}
