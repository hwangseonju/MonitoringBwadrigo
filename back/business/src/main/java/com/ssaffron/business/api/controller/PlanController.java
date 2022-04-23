package com.ssaffron.business.api.controller;

import com.ssaffron.business.api.dto.ApplyForDto;
import com.ssaffron.business.api.dto.LaundryPlanDto;
import com.ssaffron.business.api.dto.RequestApplyForDto;
import com.ssaffron.business.api.dto.MonthPlanDto;
import com.ssaffron.business.api.service.MemberService;
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
    private final MemberService memberService;

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

    // 아직 완성 x
    @PutMapping("")
    public ResponseEntity updateApplyFor(@RequestBody RequestApplyForDto requestApplyForDto){
//        String memberEmail = memberService.decodeJWT();
        int monthPlanIndex = requestApplyForDto.getMonthPlanIndex();
        String memberEmail = requestApplyForDto.getMemberEmail();
        ApplyForDto applyForDto = requestApplyForDto.getApplyForDto();
        planService.updateApplyFor(applyForDto, monthPlanIndex, memberEmail);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    // tt 요금제 삭제
    @DeleteMapping("/{memberemail}")
    public ResponseEntity deleteApplyFor(@PathVariable("memberemail") String memberEmail) {
        planService.deleteApplyFor(memberEmail);
        return new ResponseEntity<>(HttpStatus.OK);
    }

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
