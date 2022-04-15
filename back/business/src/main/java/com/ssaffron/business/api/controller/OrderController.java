package com.ssaffron.business.api.controller;

import com.ssaffron.business.api.entity.ChargeEntity;
import com.ssaffron.business.api.entity.CollectionEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/v1/api/order")
public class OrderController {
    @PostMapping("/pick")
    public ResponseEntity pick(@RequestBody String test){
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/pickresponse")
    public ResponseEntity<ChargeEntity> pickResponse(){
        return new ResponseEntity(HttpStatus.OK);
    }
}
