package com.ssaffron.business.api.service;

import com.ssaffron.business.api.dto.ApplyDto;
import com.ssaffron.business.api.dto.LaundryPlanDto;
import com.ssaffron.business.api.entity.ApplyEntity;
import com.ssaffron.business.api.entity.LaundryPlanEntity;
import com.ssaffron.business.api.dto.MonthPlanDto;
import com.ssaffron.business.api.entity.MemberEntity;
import com.ssaffron.business.api.entity.MonthPlanEntity;
import com.ssaffron.business.api.exception.*;
import com.ssaffron.business.api.repository.ApplyRepository;
import com.ssaffron.business.api.repository.LaundryPlanRepository;
import com.ssaffron.business.api.repository.MemberRepository;
import com.ssaffron.business.api.repository.MonthPlanRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlanService {

    private final LaundryPlanRepository laundryPlanRepository;
    private final MonthPlanRepository monthPlanRepository;
    private final ApplyRepository applyRepository;
    private final MemberRepository memberRepository;


    public List<LaundryPlanDto> findAllLaundryPlan(){
        return laundryPlanRepository.findAll()
                .stream().map(laundryPlanEntity -> new LaundryPlanDto().builder()
                        .laundryPlanId(laundryPlanEntity.getLaundryPlanId())
                        .laundryPlanTypeKor(laundryPlanEntity.getLaundryPlanTypeKor())
                        .laundryPlanDetails(laundryPlanEntity.getLaundryPlanDetails())
                        .laundryPlanPrice(laundryPlanEntity.getLaundryPlanPrice())
                        .laundryPlanDescription(laundryPlanEntity.getLaundryPlanDescription()).build()).collect(Collectors.toList());
    }

    public LaundryPlanDto findOneLaundryPlan(int laundryPlanId){
        LaundryPlanEntity laundryPlanEntity =  laundryPlanRepository.findByLaundryPlanId(laundryPlanId);
        return new LaundryPlanDto().builder()
                .laundryPlanId(laundryPlanEntity.getLaundryPlanId())
                .laundryPlanTypeKor(laundryPlanEntity.getLaundryPlanTypeKor())
                .laundryPlanDetails(laundryPlanEntity.getLaundryPlanDetails())
                .laundryPlanPrice(laundryPlanEntity.getLaundryPlanPrice())
                .build();
    }

    // 요금제 신청
    @Transactional
    public void insertApply(int monthPlanId, String memberEmail){
        MemberEntity memberEntity = memberRepository.findByMemberEmail(memberEmail);
        if(memberEntity.getApplyEntity() != null) {
            throw new DuplicatedApplyException("Duplicated Apply");
        }
        MonthPlanEntity monthPlanEntity = monthPlanRepository.findByMonthPlanId(monthPlanId);
        ApplyEntity applyEntity = new ApplyEntity(
                monthPlanEntity.getMonthPlanWashCount(),
                monthPlanEntity.getMonthPlanBeddingCount(),
                monthPlanEntity.getMonthPlanDeliveryCount(),
                monthPlanEntity.getMonthPlanCleaningCount(),
                monthPlanEntity.getMonthPlanShirtCount(),
                LocalDateTime.now(),
                null,
                memberEntity,
                monthPlanEntity
        );
        applyRepository.save(applyEntity);
    }

    // 요금제 수정 - 변경 요금제 신청하기
    @Transactional
    public void updateApply(int monthPlanId, String memberEmail)  {
        MemberEntity memberEntity = memberRepository.findByMemberEmail(memberEmail);
        ApplyEntity applyEntity = applyRepository.findByMemberEntity(memberEntity).orElseThrow(() ->
            new NotFoundApplyException("Not Found Apply"));
        applyEntity.setApplyChange(monthPlanId);
    }

    // 요금제 삭제
    @Transactional
    public void deleteApply(String memberEmail) {
        MemberEntity memberEntity = memberRepository.findByMemberEmail(memberEmail);
        ApplyEntity applyEntity = applyRepository.findByMemberEntity(memberEntity).orElseThrow(() ->
                new NotFoundApplyException("Not Found Apply"));
        applyRepository.delete(applyEntity);
    }

    // Monthplan 리스트-> stream api 사용해보고싶어서..
    public List<MonthPlanDto> getMonthPlanList(){
        List<MonthPlanEntity> entity = monthPlanRepository.findAll();
        return entity.stream().map(MonthPlanDto::new).collect(Collectors.toList());
    }
    // dd 한달요금제 1개조회
    public MonthPlanDto getMonthPlan(int monthPlanId){
        MonthPlanEntity monthPlanEntity = monthPlanRepository.findByMonthPlanId(monthPlanId);
        return new MonthPlanDto(monthPlanEntity);
    }
    //dd 사용중인 요금제 리스트 조회
    public List<ApplyDto> getApplyList(){
        List<ApplyEntity> entity = applyRepository.findAll();
        return entity.stream().map(ApplyDto::new).collect(Collectors.toList());
    }
    //dd 사용중인 요금제 조회
    @Transactional
    public ApplyDto getApply(String memberEmail){
        MemberEntity memberEntity = memberRepository.findByMemberEmail(memberEmail);
        ApplyEntity applyEntity = memberEntity.getApplyEntity();

        if(applyEntity != null){
            return new ApplyDto(applyEntity);
        }else{
            throw new NotFoundApplyException("Not Found Apply");
        }

    }

}
