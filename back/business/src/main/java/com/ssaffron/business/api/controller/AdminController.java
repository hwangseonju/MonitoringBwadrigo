package com.ssaffron.business.api.controller;

import com.ssaffron.business.api.dto.*;
import com.ssaffron.business.api.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/api/manager")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class AdminController {
    private final AdminService adminService;
    @GetMapping("/list")
    public ResponseEntity fetchAllCollectionRequest(){
        //직원만 접근 가능한 URI
        List<CollectDtoEmployeeForm> collectDtoEmployeeFormList = adminService.fetchAllCollectionRequest();

        return new ResponseEntity(collectDtoEmployeeFormList, HttpStatus.OK);
    }

    @GetMapping("/find")
    public ResponseEntity fetchCollectionRequestByMember(@RequestBody String findMemberEmail){
        //직원만 접근 가능한 URI
        List<CollectDtoEmployeeForm> collectDtoEmployeeFormList = adminService.fetchCollectionRequestByMember(findMemberEmail);

        return new ResponseEntity(collectDtoEmployeeFormList, HttpStatus.OK);
    }

    @GetMapping("/find/employee")
    public ResponseEntity fetchCollectionRequestByEmployee(@RequestBody int employeeId){
        List<CollectDtoEmployeeForm> collectDtoEmployeeFormList = adminService.fetchCollectionRequestByEmployee(employeeId);

        return new ResponseEntity(collectDtoEmployeeFormList, HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity collectionApproval(@RequestBody CollectionApprovalDto collectionApprovalDto){
        List<CollectDto> collectDtoList = collectionApprovalDto.getCollectDtoList();
        EmployeeDto employeeDto = collectionApprovalDto.getEmployeeDto();

        if(collectDtoList.size() == 0){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        if(employeeDto.getEmployeeName() == null){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        adminService.collectionApproval(collectDtoList, employeeDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/bill")
    public ResponseEntity registBill(@RequestBody PayDtoEmployeeForm payDtoEmployeeForm){
        MemberDto memberDto = payDtoEmployeeForm.getMemberDto();
        CollectDto collectDto = payDtoEmployeeForm.getCollectDto();
        LaundryPlanDto laundryPlanDto = payDtoEmployeeForm.getPayDto().getLaundryPlanDto();
        PayDto payDto = payDtoEmployeeForm.getPayDto();
        adminService.registBill(memberDto.getMemberEmail(), collectDto.getCollectId(), laundryPlanDto.getLaundryPlanId(), payDto);

        return new ResponseEntity(HttpStatus.OK);
    }
}
