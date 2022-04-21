package com.ssaffron.business.api.controller;

import com.ssaffron.business.api.dto.ApplyForDto;
import com.ssaffron.business.api.dto.LaundryPlanDto;
import com.ssaffron.business.api.dto.RequestApplyForDto;
import com.ssaffron.business.api.service.PlanService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/plan")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class PlanController {

    private final PlanService planService;

    // tt Laundry Plan 요금제 전체 조회
    @GetMapping("/laundry/list")
    public ResponseEntity<List<LaundryPlanDto>> allListLaundry() {
        List<LaundryPlanDto> laundryPlanDtos = planService.findAllLaundryPlan();
        return new ResponseEntity<>(laundryPlanDtos, HttpStatus.OK);
    }

    // tt Laundry Plan 요금제 단일 조회
    @GetMapping("/laundry/{laundry-plan-index}")
    public ResponseEntity<LaundryPlanDto> getListLaundry(@PathVariable("laundry-plan-index") int laundryplanindex) {
        LaundryPlanDto laundryPlanDto = planService.findOneLaundryPlan(laundryplanindex);
        return new ResponseEntity<>(laundryPlanDto, HttpStatus.OK);
    }

    // tt 요금제 신청 header로 불러오는 법 ->
    @PostMapping("")
    public ResponseEntity createApplyFor(@RequestBody RequestApplyForDto requestApplyForDto) {
//        int monthPlanIndex = (int) map.get("monthPlanIndex");
//        String memberEmail = (String) map.get("memberEmail");
//        ApplyForDto applyForDto = (ApplyForDto) map.get("applyForDto");
//        planService.insertApplyFor(applyForDto, monthPlanIndex, memberEmail);
//        log.info(map.toString());
        int monthPlanIndex = requestApplyForDto.getMonthPlanIndex();
        String memberEmail = requestApplyForDto.getMemberEmail();
        ApplyForDto applyForDto = requestApplyForDto.getApplyForDto();
        try {
            planService.insertApplyFor(applyForDto, monthPlanIndex, memberEmail);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(RuntimeException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
//        Map<String, Object> result = new HashMap<>();
//        result.put("monthPlanIndex", monthPlanIndex);
//        result.put("memberEmail", memberEmail);
//        result.put("applyForDto", applyForDto);
    }


    // tt 요금제 삭제
    @DeleteMapping("/{memberemail}")
    public ResponseEntity deleteApplyFor(@PathVariable("memberemail") String memberEmail) {
        planService.deleteApplyFor(memberEmail);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
