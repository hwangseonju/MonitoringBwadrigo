package com.ssaffron.business.api.controller;

import com.ssaffron.business.api.dto.*;
import com.ssaffron.business.api.service.MemberService;
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
    private final MemberService memberService;

    @PostMapping("")
    public ResponseEntity collectionRequest(@RequestBody List<CollectDto> collectDtoList){
        String memberEmaill = memberService.decodeJWT();
//        try {
            orderService.collectionRequest(memberEmaill, collectDtoList);
//        }catch (NotFoundAddressException e){
//            return new ResponseEntity(HttpStatus.BAD_REQUEST);
//        }catch (NullApplyException e){
//            return new ResponseEntity(HttpStatus.BAD_REQUEST);
//            //NullAddressException과 NullApplyException이 다른 Status를 가지면 좋을 것 같다.
//        }
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
}
