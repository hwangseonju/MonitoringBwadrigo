package com.ssaffron.business.api.service;

import com.ssaffron.business.api.dto.ApplyForDto;
import com.ssaffron.business.api.dto.LaundryPlanDto;
import com.ssaffron.business.api.entity.ApplyForEntity;
import com.ssaffron.business.api.entity.LaundryPlanEntity;
import com.ssaffron.business.api.dto.MonthPlanDto;
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
                applyForDto.getApplyForDate().now(),
                applyForDto.getApplyForChange(),
                memberEntity,
                monthPlanEntity
        );
        if(applyForRepository.findByMemberEntity(memberEntity).orElse(null) == null) {
            applyForRepository.save(applyForEntity);
        }else{
            throw new RuntimeException();
        }
    }

    // 요금제 수정 - 변경 요금제 신청하기
    public void updateApplyFor(ApplyForDto applyForDto, int monthPlanIndex, String memberEmail){
        MemberEntity memberEntity = memberRepository.findByMemberEmail(memberEmail);
        int memberIndex = memberEntity.getMemberIndex();
        MonthPlanEntity monthPlanEntity = monthPlanRepository.findByMonthPlanIndex(monthPlanIndex);
        ApplyForEntity applyForEntity = applyForRepository.findByMemberEntity_MemberIndex(memberIndex);
        if(monthPlanIndex != applyForDto.getApplyForChange()) {
            applyForEntity.setApplyForChange(applyForDto.getApplyForChange());
            applyForRepository.save(applyForEntity);
        }else {
            throw new RuntimeException();
        }
    }

    // 요금제 삭제
    public void deleteApplyFor(String memberEmail) {
        MemberEntity memberEntity = memberRepository.findByMemberEmail(memberEmail);
        ApplyForEntity applyForEntity = applyForRepository.findByMemberEntity(memberEntity).get();
        applyForRepository.delete(applyForEntity);
    }

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
//        if(entity != null){
//            return new ApplyForDto(entity);
//        }else{
//            throw new RuntimeException();
//        }
        return new ApplyForDto(entity);

    }

}
