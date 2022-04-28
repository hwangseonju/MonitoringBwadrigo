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
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


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
                        .laundryPlanType(laundryPlanEntity.getLaundryPlanType())
                        .laundryPlanDetails(laundryPlanEntity.getLaundryPlanDetails())
                        .laundryPlanPrice(laundryPlanEntity.getLaundryPlanPrice())
                        .laundryPlanDescription(laundryPlanEntity.getLaundryPlanDescription()).build()).collect(Collectors.toList());
    }

    public LaundryPlanDto findOneLaundryPlan(int laundryPlanId){
        LaundryPlanEntity laundryPlanEntity =  laundryPlanRepository.findByLaundryPlanId(laundryPlanId);
        return new LaundryPlanDto().builder()
                .laundryPlanId(laundryPlanEntity.getLaundryPlanId())
                .laundryPlanType(laundryPlanEntity.getLaundryPlanType())
                .laundryPlanDetails(laundryPlanEntity.getLaundryPlanDetails())
                .laundryPlanPrice(laundryPlanEntity.getLaundryPlanPrice())
                .build();
    }

    // 요금제 신청
    public void insertApply(ApplyDto applyDto, int monthPlanId, String memberEmail){
        MemberEntity memberEntity = memberRepository.findByMemberEmail(memberEmail);
        MonthPlanEntity monthPlanEntity = monthPlanRepository.findByMonthPlanId(monthPlanId);

        ApplyEntity applyEntity = new ApplyEntity(
                applyDto.getApplyWashCount(),
                applyDto.getApplyBeddingCount(),
                applyDto.getApplyDeliveryCount(),
                applyDto.getApplyCleaningCount(),
                applyDto.getApplyShirtCount(),
                applyDto.getApplyDate().now(),
                applyDto.getApplyChange(),
                memberEntity,
                monthPlanEntity
        );
        if(applyRepository.findByMemberEntity(memberEntity).orElse(null) == null) {
            applyRepository.save(applyEntity);
        }else{
            throw new DuplicatedApplyException(memberEntity.getMemberName());
        }
    }

    // 요금제 수정 - 변경 요금제 신청하기
    public void updateApply(ApplyDto applyDto, int monthPlanId, String memberEmail){
        MemberEntity memberEntity = memberRepository.findByMemberEmail(memberEmail);
        int memberId = memberEntity.getMemberId();
        MonthPlanEntity monthPlanEntity = monthPlanRepository.findByMonthPlanId(monthPlanId);
        ApplyEntity applyEntity = applyRepository.findByMemberEntity_MemberId(memberId);
        if(monthPlanId != applyDto.getApplyChange() && applyEntity != null) {
            applyEntity.setApplyChange(applyDto.getApplyChange());
            applyRepository.save(applyEntity);
        }else if(monthPlanId == applyDto.getApplyChange()){
            throw new ExistedApplyException(memberEntity.getMemberName());
        }else{
            throw new NoSuchApplyException(memberEntity.getMemberName());
        }
    }

    // 요금제 삭제
    public void deleteApply(String memberEmail) {
        MemberEntity memberEntity = memberRepository.findByMemberEmail(memberEmail);
        ApplyEntity applyEntity = applyRepository.findByMemberEntity(memberEntity).orElse(null);
        if(applyEntity != null) {
            applyRepository.delete(applyEntity);
        }else{
            throw new DeleteApplyException(memberEntity.getMemberName());
        }
    }

    // Monthplan 리스트-> stream api 사용해보고싶어서..
    public List<MonthPlanDto> getMonthPlanList(){
        List<MonthPlanEntity> entity = monthPlanRepository.findAll();
        return entity.stream().map(MonthPlanDto::new).collect(Collectors.toList());
    }
    // dd 한달요금제 1개조회
    public MonthPlanDto getMonthPlan(int monthPlanId){
        MonthPlanEntity entity = monthPlanRepository.findByMonthPlanId(monthPlanId);
        return new MonthPlanDto(entity);
    }
    //dd 사용중인 요금제 리스트 조회
    public List<ApplyDto> getApplyList(){
        List<ApplyEntity> entity = applyRepository.findAll();
        return entity.stream().map(ApplyDto::new).collect(Collectors.toList());
    }
    //dd 사용중인 요금제 조회
    public ApplyDto getApply(String memberEmail){
        MemberEntity member = memberRepository.findByMemberEmail(memberEmail);
        int Id = member.getMemberId();
        ApplyEntity entity = applyRepository.findByMemberEntity_MemberId(Id);
//        if(entity != null){
//            return new ApplyDto(entity);
//        }else{
//            throw new RuntimeException();
//        }
        return new ApplyDto(entity);

    }

}
