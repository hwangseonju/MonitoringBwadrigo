package com.ssaffron.business.api.controller;

import com.ssaffron.business.api.dto.ApplyDto;
import com.ssaffron.business.api.dto.LaundryPlanDto;
import com.ssaffron.business.api.dto.RequestApplyDto;
import com.ssaffron.business.api.dto.MonthPlanDto;
import com.ssaffron.business.api.exception.*;
import com.ssaffron.business.api.service.MemberService;
import com.ssaffron.business.api.service.PlanService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/api/plan")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class PlanController {

    private final PlanService planService;
    private final MemberService memberService;

    // tt Laundry Plan 요금제 전체 조회
    @GetMapping("/laundry/list")
    public ResponseEntity<List<LaundryPlanDto>> allListLaundry(@RequestParam String redirect) {
        List<LaundryPlanDto> laundryPlanDtos = planService.findAllLaundryPlan();

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(redirect));

        return new ResponseEntity<>(laundryPlanDtos, headers, HttpStatus.OK);
    }

    // tt Laundry Plan 요금제 단일 조회
    @GetMapping("/laundry/{laundry-plan-Id}")
    public ResponseEntity<LaundryPlanDto> getListLaundry(@PathVariable("laundry-plan-Id") int laundryplanId, @RequestParam String redirect) {
        LaundryPlanDto laundryPlanDto = planService.findOneLaundryPlan(laundryplanId);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(redirect));

        return new ResponseEntity<>(laundryPlanDto, headers, HttpStatus.OK);
    }

    // tt 요금제 신청 header로 불러오는 법 ->
    @PostMapping("")
    public ResponseEntity createApplyFor(@RequestBody RequestApplyDto requestApplyDto, @RequestParam String redirect) throws DuplicatedApplyException{
        String memberEmail = memberService.decodeJWT();
        int monthPlanId = requestApplyDto.getMonthPlanId();
        planService.insertApply(monthPlanId, memberEmail);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(redirect));

        return new ResponseEntity<>(headers, HttpStatus.OK);
    }

    // tt 요금제 수정
    @PutMapping("")
    public ResponseEntity updateApplyFor(@RequestBody RequestApplyDto requestApplyDto, @RequestParam String redirect) throws NotFoundApplyException{
        String memberEmail = memberService.decodeJWT();
        int monthPlanId = requestApplyDto.getMonthPlanId();
        planService.updateApply(monthPlanId, memberEmail);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(redirect));
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }

    // tt 요금제 삭제
    @DeleteMapping("")
    public ResponseEntity deleteApplyFor(@RequestParam String redirect) throws DeleteApplyException{
        String memberEmail = memberService.decodeJWT();
        planService.deleteApply(memberEmail);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(redirect));
        return new ResponseEntity<>(headers, HttpStatus.OK);

    }

    // dd 한달 요금제 리스트 조회
    @GetMapping("/month/list")
    public ResponseEntity<List<MonthPlanDto>> allListMonth(@RequestParam String redirect) {
        List<MonthPlanDto> monthlist = planService.getMonthPlanList();

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(redirect));
        return new ResponseEntity<>(monthlist, headers, HttpStatus.OK);
    }
    // dd 한달요금제 1개조회
    @GetMapping("/month/{month_plan_Id}")
    public ResponseEntity<MonthPlanDto> getListMonth(@PathVariable int month_plan_Id, @RequestParam String redirect) {
        MonthPlanDto monthone = planService.getMonthPlan(month_plan_Id);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(redirect));
        return new ResponseEntity<>(monthone, headers, HttpStatus.OK);
    }

    //dd 사용중인 요금제 list 조회
    @GetMapping("/admin/using-plan")
    public ResponseEntity<List<ApplyDto>> allListApplyFor(@RequestParam String redirect) {
        List<ApplyDto> applylist = planService.getApplyList();

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(redirect));
        return new ResponseEntity<>(applylist, headers, HttpStatus.OK);
    }

    //dd 사용중인 요금제 조회
    @GetMapping("")
    public ResponseEntity<ApplyDto> getApplyFor(@RequestParam String redirect) throws NotFoundApplyException{
        String memberEmail = memberService.decodeJWT();
//        try {
        ApplyDto applyone = planService.getApply(memberEmail);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(redirect));
            return new ResponseEntity(applyone, headers, HttpStatus.OK);
//        }catch (NotFoundApplyException e){
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
    }

    // dd Test : 다음달 요금제 변경------------
    @GetMapping("/change/{memberId}")
    public ResponseEntity nextMonthPlan(@PathVariable("memberId") int memberId, @RequestBody MonthPlanDto dto, @RequestParam String redirect) { // 수정

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(redirect));
        return new ResponseEntity(headers, HttpStatus.OK);
    }

}
