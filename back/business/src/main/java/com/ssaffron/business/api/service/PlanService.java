package com.ssaffron.business.api.service;

import com.ssaffron.business.api.dto.ApplyForDto;
import com.ssaffron.business.api.dto.LaundryPlanDto;
import com.ssaffron.business.api.entity.ApplyForEntity;
import com.ssaffron.business.api.entity.LaundryPlanEntity;
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

    public List<LaundryPlanDto> findAllLaundryPlan(){
        return laundryPlanRepository.findAll()
                .stream().map(laundryPlanEntity -> new LaundryPlanDto().builder()
                        .laundryPlanIndex(laundryPlanEntity.getLaundryPlanIndex())
                        .laundryPlanType(laundryPlanEntity.getLaundryPlanType())
                        .laundryPlanDetails(laundryPlanEntity.getLaundryPlanDetails())
                        .laundryPlanPrice(laundryPlanEntity.getLaundryPlanPrice())
                        .laundryPlanDescription(laundryPlanEntity.getLaundryPlanDescription()).build()).collect(Collectors.toList());
    }

    public LaundryPlanDto findOneLaundryPlan(int laundryPlanIndex){
        LaundryPlanEntity laundryPlanEntity =  laundryPlanRepository.findByLaundryPlanIndex(laundryPlanIndex);
        return new LaundryPlanDto().builder()
                .laundryPlanIndex(laundryPlanEntity.getLaundryPlanIndex())
                .laundryPlanType(laundryPlanEntity.getLaundryPlanType())
                .laundryPlanDetails(laundryPlanEntity.getLaundryPlanDetails())
                .laundryPlanPrice(laundryPlanEntity.getLaundryPlanPrice())
                .build();
    }

    // 요금제 신청
    public void insertApplyFor(ApplyForDto applyForDto, int monthPlanIndex, String memberEmail){
        MemberEntity memberEntity = memberRepository.findByMemberEmail(memberEmail);
        MonthPlanEntity monthPlanEntity = monthPlanRepository.findByMonthPlanIndex(monthPlanIndex);

        ApplyForEntity applyForEntity = new ApplyForEntity(
                applyForDto.getApplyForWashCount(),
                applyForDto.getApplyForBeddingCount(),
                applyForDto.getApplyForDeliveryCount(),
                applyForDto.getApplyForCleaningCount(),
                applyForDto.getApplyForShirtCount(),
                memberEntity,
                monthPlanEntity
        );
        if(applyForRepository.findByMemberEntity(memberEntity).orElse(null) == null) {
            applyForRepository.save(applyForEntity);
        }else{
            throw new RuntimeException();
        }
    }

    // 요금제 삭제
    public void deleteApplyFor(String memberEmail){
        MemberEntity memberEntity = memberRepository.findByMemberEmail(memberEmail);
        ApplyForEntity applyForEntity = applyForRepository.findByMemberEntity(memberEntity).get();
        applyForRepository.delete(applyForEntity);
    }

}
