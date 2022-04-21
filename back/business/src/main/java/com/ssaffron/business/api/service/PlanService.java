package com.ssaffron.business.api.service;

import com.ssaffron.business.api.dto.ApplyForDto;
import com.ssaffron.business.api.dto.MonthPlanDto;
import com.ssaffron.business.api.entity.ApplyForEntity;
import com.ssaffron.business.api.entity.MemberEntity;
import com.ssaffron.business.api.entity.MonthPlanEntity;
import com.ssaffron.business.api.repository.ApplyForRepository;
import com.ssaffron.business.api.repository.LaundryPlanRepository;
import com.ssaffron.business.api.repository.MemberRepository;
import com.ssaffron.business.api.repository.MonthPlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class PlanService {
    private final LaundryPlanRepository laundryPlanRepository;
    private final MonthPlanRepository monthPlanRepository;
    private final ApplyForRepository applyForRepository;
    private final MemberRepository memberRepository;

    // Monthplan 리스트-> stream api 사용해보고싶어서..
    public List<MonthPlanDto> getMonthPlanList(){
        List<MonthPlanEntity> entity = monthPlanRepository.findAll();
        return entity.stream().map(MonthPlanDto::new).collect(Collectors.toList());
    }
    // dd 한달요금제 1개조회
    public MonthPlanDto getMonthPlan(int monthPlanIndex){
        MonthPlanEntity entity = monthPlanRepository.findByMonthPlanIndex(monthPlanIndex);
        return new MonthPlanDto(entity);
    }
    //dd 사용중인 요금제 리스트 조회
    public List<ApplyForDto> getApplyList(){
        List<ApplyForEntity> entity = applyForRepository.findAll();
        return entity.stream().map(ApplyForDto::new).collect(Collectors.toList());
    }
    //dd 사용중인 요금제 조회
    public ApplyForDto getApplyFor(String memberEmail){
        MemberEntity member = memberRepository.findByMemberEmail(memberEmail);
        int index = member.getMemberIndex();
        ApplyForEntity entity = applyForRepository.findByMemberEntity_MemberIndex(index);
        return new ApplyForDto(entity);
    }

}
