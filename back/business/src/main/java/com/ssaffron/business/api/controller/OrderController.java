package com.ssaffron.business.api.controller;

import com.ssaffron.business.api.dto.*;
import com.ssaffron.business.api.entity.PayForEntity;
import com.ssaffron.business.api.exception.NullAddressException;
import com.ssaffron.business.api.exception.NullApplyForException;
import com.ssaffron.business.api.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/v1/api/order")
public class OrderController {

    private final OrderService orderService;

    private final String memberEmail = "ssafy@ssafy.com";

    @PostMapping("")
    public ResponseEntity collectionRequest(@RequestBody List<CollectForDto> collectForDtoList){
        try {
            orderService.collectionRequest(memberEmail, collectForDtoList);

        }catch (NullAddressException e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }catch (NullApplyForException e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
            //NullAddressException과 NullApplyForException이 다른 Status를 가지면 좋을 것 같다.
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity collectionRequestInquiry(){
        List<CollectForDto> collectForDtoList = orderService.collectionRequestInquiry(memberEmail);
        if(collectForDtoList.size() == 0){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(collectForDtoList, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity fetchAllCollectionRequest(){
        //직원만 접근 가능한 URI
        List<CollectForDtoEmployeeForm> collectForDtoEmployeeFormList = orderService.fetchAllCollectionRequest();
        return new ResponseEntity(collectForDtoEmployeeFormList, HttpStatus.OK);
    }

    @GetMapping("/find")
    public ResponseEntity fetchCollectionRequestByMember(@RequestBody String findMemberEmail){
        //직원만 접근 가능한 URI
        List<CollectForDtoEmployeeForm> collectForDtoEmployeeFormList = orderService.fetchCollectionRequestByMember(findMemberEmail);
        return new ResponseEntity(collectForDtoEmployeeFormList, HttpStatus.OK);
    }

    @GetMapping("/find/employee")
    public ResponseEntity fetchCollectionRequestByEmployee(@RequestBody int employeeIndex){
        List<CollectForDtoEmployeeForm> collectForDtoEmployeeFormList = orderService.fetchCollectionRequestByEmployee(employeeIndex);
        return new ResponseEntity(collectForDtoEmployeeFormList, HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity collectionApproval(@RequestBody CollectionApprovalDto collectionApprovalDto){
        List<CollectForDto> collectForDtoList = collectionApprovalDto.getCollectForDtoList();
        EmployeeDto employeeDto = collectionApprovalDto.getEmployeeDto();
        if(collectForDtoList.size() == 0){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        if(employeeDto.getEmployeeName() == null){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        orderService.collectionApproval(collectForDtoList, employeeDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("")
    public ResponseEntity withdrawalOfCollection(@RequestBody long collectionForIndex){
        orderService.withdrawalOfCollection(collectionForIndex);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/bill")
    public ResponseEntity registBill(@RequestBody PayForDtoEmployeeForm payForDtoEmployeeForm){
        MemberDto memberDto = payForDtoEmployeeForm.getMemberDto();
        CollectForDto collectForDto = payForDtoEmployeeForm.getCollectForDto();
        LaundryPlanDto laundryPlanDto = payForDtoEmployeeForm.getPayForDto().getLaundryPlanDto();
        PayForDto payForDto = payForDtoEmployeeForm.getPayForDto();
        orderService.registBill(memberDto.getMemberEmail(), collectForDto.getCollectForIndex(), laundryPlanDto.getLaundryPlanIndex(), payForDto);

        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/bill")
    public ResponseEntity getBill(){
        List<PayForDto> payForDtoList = orderService.getBill(memberEmail);
        return new ResponseEntity(payForDtoList, HttpStatus.OK);
    }
}
