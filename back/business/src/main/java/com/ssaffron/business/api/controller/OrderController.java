package com.ssaffron.business.api.controller;

import com.ssaffron.business.api.dto.*;
import com.ssaffron.business.api.service.MemberService;
import com.ssaffron.business.api.service.OrderService;
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

    @PostMapping("")
    public ResponseEntity collectionRequest(@RequestBody List<CollectDto> collectDtoList, @RequestParam String redirect){
        String memberEmaill = memberService.decodeJWT();
//        try {
            orderService.collectionRequest(memberEmaill, collectDtoList);
//        }catch (NotFoundAddressException e){
//            return new ResponseEntity(HttpStatus.BAD_REQUEST);
//        }catch (NullApplyException e){
//            return new ResponseEntity(HttpStatus.BAD_REQUEST);
//            //NullAddressException과 NullApplyException이 다른 Status를 가지면 좋을 것 같다.
//        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(redirect));
        return new ResponseEntity(headers, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity collectionRequestInquiry(@RequestParam String redirect){
        String memberEmail = memberService.decodeJWT();

        List<CollectDto> collectDtoList = orderService.collectionRequestInquiry(memberEmail);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(redirect));
        if(collectDtoList.size() == 0){
            return new ResponseEntity(headers, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(collectDtoList, headers, HttpStatus.OK);
    }

    @DeleteMapping("/{collectId}")
    public ResponseEntity withdrawalOfCollection(@PathVariable("collectId") long collectId, @RequestParam String redirect){
        orderService.withdrawalOfCollection(collectId);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(redirect));
        return new ResponseEntity(headers, HttpStatus.OK);
    }



    @GetMapping("/bill/{month}")
    public ResponseEntity getBill(@PathVariable("month") int month, @RequestParam String redirect){
        String memberEmail = memberService.decodeJWT();

        List<PayDto> payDtoList = orderService.getBill(memberEmail, month);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(redirect));
        return new ResponseEntity(payDtoList, headers, HttpStatus.OK);
    }
}
