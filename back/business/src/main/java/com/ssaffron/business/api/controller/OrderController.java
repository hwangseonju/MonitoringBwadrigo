package com.ssaffron.business.api.controller;

import com.ssaffron.business.api.dto.*;
import com.ssaffron.business.api.exception.NullAddressException;
import com.ssaffron.business.api.exception.NullApplyException;
import com.ssaffron.business.api.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/v1/api/order")
public class OrderController {

    private final OrderService orderService;

    private final String memberEmail = "ssafy@ssafy.com";

    @PostMapping("")
    public ResponseEntity collectionRequest(@RequestBody List<CollectDto> collectDtoList){
        try {
            orderService.collectionRequest(memberEmail, collectDtoList);

        }catch (NullAddressException e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }catch (NullApplyException e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
            //NullAddressException과 NullApplyException이 다른 Status를 가지면 좋을 것 같다.
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity collectionRequestInquiry(){
        List<CollectDto> collectDtoList = orderService.collectionRequestInquiry(memberEmail);
        if(collectDtoList.size() == 0){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(collectDtoList, HttpStatus.OK);
    }



    @DeleteMapping("")
    public ResponseEntity withdrawalOfCollection(@RequestBody long collectionId){
        orderService.withdrawalOfCollection(collectionId);
        return new ResponseEntity(HttpStatus.OK);
    }



    @GetMapping("/bill")
    public ResponseEntity getBill(){
        List<PayDto> payDtoList = orderService.getBill(memberEmail);
        return new ResponseEntity(payDtoList, HttpStatus.OK);
    }
}
