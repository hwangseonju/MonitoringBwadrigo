package com.ssaffron.business.api.controller;

import com.ssaffron.business.api.dto.ApplyForDto;
import com.ssaffron.business.api.dto.MonthPlanDto;
import com.ssaffron.business.api.entity.ApplyForEntity;
import com.ssaffron.business.api.entity.LaundryPlanEntity;
import com.ssaffron.business.api.entity.MonthPlanEntity;
import com.ssaffron.business.api.service.PlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/plan")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PlanController {
    private final PlanService planService;
    // dd 한달 요금제 리스트 조회
    @GetMapping("/month/list")
    public ResponseEntity<List<MonthPlanDto>> allListMonth() {
        List<MonthPlanDto> monthlist = planService.getMonthPlanList();
        return new ResponseEntity<>(monthlist,HttpStatus.OK);
    }
    // dd 한달요금제 1개조회
    @GetMapping("/month/{month_plan_index}")
    public ResponseEntity<MonthPlanDto> getListMonth(@PathVariable int month_plan_index) {
        MonthPlanDto monthone = planService.getMonthPlan(month_plan_index);
        return new ResponseEntity<>(monthone,HttpStatus.OK);
    }

    //dd 사용중인 요금제 list 조회
    @GetMapping("/admin/using-plan")
    public ResponseEntity<List<ApplyForDto>> allListApplyFor() {
        List<ApplyForDto> applylist = planService.getApplyList();
        return new ResponseEntity<>(applylist,HttpStatus.OK);
    }

    //dd 사용중인 요금제 조회
    @GetMapping("/{memberemail}")
    public ResponseEntity<ApplyForDto> getApplyFor(@PathVariable String memberemail) {
        ApplyForDto applyone = planService.getApplyFor(memberemail);
        return new ResponseEntity(applyone,HttpStatus.OK);
    }

    // dd Test : 다음달 요금제 변경------------
    @GetMapping("/change/{memberindex}")
    public ResponseEntity nextMonthPlan(@PathVariable("memberindex") int memberId, @RequestBody MonthPlanDto dto) { // 수정
        return new ResponseEntity(HttpStatus.OK);
    }

}