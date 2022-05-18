package com.ssaffron.business.api.controller;

import com.ssaffron.business.api.dto.*;
import com.ssaffron.business.api.service.MemberService;
import com.ssaffron.business.api.service.OrderService;
import com.ssaffron.business.api.success.SuccessCode;
import com.ssaffron.business.api.success.SuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/v1/api/order")
public class OrderController {

    private final OrderService orderService;
    private final MemberService memberService;
    private final SuccessHandler successHandler;

    @PostMapping("")
    public ResponseEntity collectionRequest(@RequestBody List<CollectDto> collectDtoList){
        String memberEmaill = memberService.decodeJWT();

        orderService.collectionRequest(memberEmaill, collectDtoList);
        successHandler.sendSuccessLog(SuccessCode.COLLECTION_REQUEST,"POST v1/api/order");
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity collectionRequestInquiry(){
        String memberEmail = memberService.decodeJWT();

        List<CollectDto> collectDtoList = orderService.collectionRequestInquiry(memberEmail);

        if(collectDtoList.size() == 0){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(collectDtoList, HttpStatus.OK);
    }

    @DeleteMapping("/{collectId}")
    public ResponseEntity withdrawalOfCollection(@PathVariable("collectId") long collectId){
        orderService.withdrawalOfCollection(collectId);

        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/bill/{month}")
    public ResponseEntity getBill(@PathVariable("month") int month){
        String memberEmail = memberService.decodeJWT();

        List<PayDto> payDtoList = orderService.getBill(memberEmail, month);

        return new ResponseEntity(payDtoList, HttpStatus.OK);
    }

    @GetMapping("/collect/check")
    public ResponseEntity isCollect(){
        String memberEmail = memberService.decodeJWT();

        List<CollectDto> collectDtoList = orderService.isCollect(memberEmail);
        if(collectDtoList.size() == 0){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(collectDtoList, HttpStatus.OK);
    }

}
