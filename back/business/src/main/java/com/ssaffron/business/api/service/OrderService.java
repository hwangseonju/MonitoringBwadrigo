package com.ssaffron.business.api.service;

import com.ssaffron.business.api.dto.*;
import com.ssaffron.business.api.entity.*;
import com.ssaffron.business.api.exception.NullAddressException;
import com.ssaffron.business.api.exception.NullApplyForException;
import com.ssaffron.business.api.repository.*;
import com.sun.mail.handlers.text_html;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final MemberRepository memberRepository;
    private final EmployeeRepository employeeRepository;
    private final CollectForRepository collectForRepository;
    private final PayForRepository payForRepository;
    private final LaundryPlanRepository laundryPlanRepository;
    private final ApplyForRepository applyForRepository;

    public boolean collectionRequest(String memberEmail, List<CollectForDto> collectForDtoList){
        //수거 신청 성공/실패
        MemberEntity memberEntity = memberRepository.findByMemberEmail(memberEmail);
        if(memberEntity.getMemberAddress() == null)
            throw new NullAddressException();
        ApplyForEntity applyForEntity = applyForRepository.findByMemberEntityAndApplyForDeliveryCountIsNotNull(memberEntity).orElse(null);
        if(applyForEntity == null){
            throw new NullApplyForException(memberEntity.getMemberName());
        }
        List<CollectForEntity> collectForEntityList = collectForDtoList.
                stream().map(collectForDto -> new CollectForEntity(
                        collectForDto.getCollectFortype(), memberEntity)).collect(Collectors.toList());
        collectForRepository.saveAll(collectForEntityList);
        return true;
    }

    public List<CollectForDto> collectionRequestInquiry(String memberEmail){
        //한 사용자의 수거 요청 목록을 조회 할 수 있다.
        //수거한 직원의 정보를 볼 수 없다. -> 사용자용
        MemberEntity memberEntity = memberRepository.findByMemberEmail(memberEmail);
        return collectForRepository.findAllByMemberEntity(memberEntity).
                stream().map(collectForEntity -> CollectForDto.builder().
                    collectForIndex(collectForEntity.getCollectForIndex()).
                    collectForRequestDate(collectForEntity.getCollectForRequestDate()).
                    collectForWithdrawDate(collectForEntity.getCollectForWithdrawDate()).
                    collectFortype(collectForEntity.getCollectForType()).
                    build()).collect(Collectors.toList());
                    //collectForIndex를 넣는 이유 -> 접수번호 확인
    }

    public List<CollectForDtoEmployeeForm> fetchAllCollectionRequest(){
        //모든 사용자의 수거 요청 목록이 보여짐. -> 수거한 직원 정보를 볼 수 있다.
        //JWT에서 Role이 직원에 해당 할 때 접근 가능하도록 설정 필요
        return collectForRepository.findAll().
                stream().map(collectForEntity -> CollectForDtoEmployeeForm.builder().
                    memberDto(MemberDto.builder().
                                memberEmail(collectForEntity.getMemberEntity().getMemberEmail()).
                                memberName(collectForEntity.getMemberEntity().getMemberName()).build()).
                    collectForDto(CollectForDto.builder().
                            collectForIndex(collectForEntity.getCollectForIndex()).
                            collectFortype(collectForEntity.getCollectForType()).
                            collectForRequestDate(collectForEntity.getCollectForRequestDate()).
                            collectForWithdrawDate(collectForEntity.getCollectForWithdrawDate()).build()).
                    employeeDto(EmployeeDto.builder().
                            employeeIndex(collectForEntity.getEmployeeEntity().getEmployeeIndex()).
                            employeeName(collectForEntity.getEmployeeEntity().getEmployeeName()).
                            employeePhone(collectForEntity.getEmployeeEntity().getEmployeePhone()).build()).build()
                ).collect(Collectors.toList());

    }

    public List<CollectForDtoEmployeeForm> fetchCollectionRequestByMember(String memberEmail){
        //특정 사용자의 수거 요청 목록이 보여짐. -> 수거한 직원 정보를 볼 수 있다.
        //JWT에서 Role이 직원에 해당 할 때 접근 가능하도록 설정 필요
        MemberEntity memberEntity = memberRepository.findByMemberEmail(memberEmail);
        return collectForRepository.findAllByMemberEntity(memberEntity).
                stream().map(collectForEntity -> CollectForDtoEmployeeForm.builder().
                        memberDto(MemberDto.builder().
                                memberEmail(collectForEntity.getMemberEntity().getMemberEmail()).
                                memberName(collectForEntity.getMemberEntity().getMemberName()).build()).
                        collectForDto(CollectForDto.builder().
                                collectForIndex(collectForEntity.getCollectForIndex()).
                                collectFortype(collectForEntity.getCollectForType()).
                                collectForRequestDate(collectForEntity.getCollectForRequestDate()).
                                collectForWithdrawDate(collectForEntity.getCollectForWithdrawDate()).build()).
                        employeeDto(EmployeeDto.builder().
                                employeeIndex(collectForEntity.getEmployeeEntity().getEmployeeIndex()).
                                employeeName(collectForEntity.getEmployeeEntity().getEmployeeName()).
                                employeePhone(collectForEntity.getEmployeeEntity().getEmployeePhone()).build()).build()
                ).collect(Collectors.toList());
    }

    public List<CollectForDtoEmployeeForm> fetchCollectionRequestByEmployee(int employeeIndex){
        //직원이 수거한 수거 요청을 확인 할 수 있다.
        //JWT에서 Role이 직원에 해당 할 때 접근 가능하도록 설정 필요
        EmployeeEntity employeeEntity = employeeRepository.findById(employeeIndex).get();
        return collectForRepository.findAllByEmployeeEntity(employeeEntity).
                stream().map(collectForEntity -> CollectForDtoEmployeeForm.builder().
                        memberDto(MemberDto.builder().
                                memberEmail(collectForEntity.getMemberEntity().getMemberEmail()).
                                memberName(collectForEntity.getMemberEntity().getMemberName()).build()).
                        collectForDto(CollectForDto.builder().
                                collectForIndex(collectForEntity.getCollectForIndex()).
                                collectFortype(collectForEntity.getCollectForType()).
                                collectForRequestDate(collectForEntity.getCollectForRequestDate()).
                                collectForWithdrawDate(collectForEntity.getCollectForWithdrawDate()).build()).
                        employeeDto(EmployeeDto.builder().
                                employeeIndex(collectForEntity.getEmployeeEntity().getEmployeeIndex()).
                                employeeName(collectForEntity.getEmployeeEntity().getEmployeeName()).
                                employeePhone(collectForEntity.getEmployeeEntity().getEmployeePhone()).build()).build()
                ).collect(Collectors.toList());
    }

    public boolean collectionApproval(List<CollectForDto> collectForDtoList, EmployeeDto employeeDto){
        //수거를 할 직원을 할당할 수 있다.
        EmployeeEntity employeeEntity = employeeRepository.findById(employeeDto.getEmployeeIndex()).get();
        //사원번호호 사원 엔티티 생성
        List<CollectForEntity> collectForEntityList = new ArrayList<>();
        collectForDtoList.forEach(collectForDto -> {
            CollectForEntity collectForEntity = collectForRepository.findById(collectForDto.getCollectForIndex()).get();
            collectForEntity.setEmployeeEntity(employeeEntity);
            collectForEntityList.add(collectForEntity);
        });
        collectForRepository.saveAll(collectForEntityList);
        return true;
    }

    public boolean withdrawalOfCollection(long collectionForIndex){
        //사용자 인증은 JWT를 통해 본인만 진행이 되고, collectionForIndex자체는 PK이기 때문에 중복이 없음
        //직원은 접근이 가능함.
        CollectForEntity collectForEntity = collectForRepository.findById(collectionForIndex).get();
        collectForEntity.setCollectForWithdrawDate(LocalDateTime.now());
        collectForRepository.save(collectForEntity);
        return true;

    }

    public boolean registBill(String memberEmail, long collectForIndex, int laudryPlanIndex, PayForDto payForDto){
        //collectDto로 빨래 수거 맡긴 날 찾아오기
        //regist 후에 ApplyForEntity도 수정하기
        //collectDto에서의 Type은 laundryPlan의 Type에 해당 되고 세부 타입에 따라 여러 row가 나올 수 있다.
        MemberEntity memberEntity = memberRepository.findByMemberEmail(memberEmail);
        CollectForEntity collectForEntity = collectForRepository.findById(collectForIndex).get();
        LaundryPlanEntity laundryPlanEntity = laundryPlanRepository.findById(laudryPlanIndex).get();

        PayForEntity payForEntity = new PayForEntity(
                                            payForDto.getPayForRequestCount(),
                                            LocalDateTime.now(),
                                            collectForEntity.getCollectForRequestDate(),
                                            collectForEntity,
                                            memberEntity,
                                            laundryPlanEntity
                                    );
        payForRepository.save(payForEntity);
        ApplyForEntity applyForEntity = applyForRepository.findByMemberEntityAndApplyForDeliveryCountIsNotNull(memberEntity).get();
        //신청한 요금제로 상태관리
        if(applyForEntity.getMonthPlanEntity().getMonthPlanIndex() == 1){
            return true;
            //저장할 필요가 없음 1일 때는 자유요금제

        }
        int requestCount = payForEntity.getPayForRequestCount();
        switch (laundryPlanEntity.getLaundryPlanType()) {
            case "wash": {
                int count = Math.max(applyForEntity.getApplyForWashCount() - requestCount, 0);
                applyForEntity.setApplyForWashCount(count);
                break;
            }
            case "bedding": {
                int count = Math.max(applyForEntity.getApplyForBeddingCount() - requestCount, 0);
                applyForEntity.setApplyForBeddingCount(count);
                break;
            }
            case "cleaning": {
                int count = Math.max(applyForEntity.getApplyForCleaningCount() - requestCount, 0);
                applyForEntity.setApplyForCleaningCount(count);
                break;
            }
            case "shirt": {
                int count = Math.max(applyForEntity.getApplyForShirtCount() - requestCount, 0);
                applyForEntity.setApplyForShirtCount(count);
                break;
            }
            case "delivery": {
                int count = Math.max(applyForEntity.getApplyForDeliveryCount() - requestCount, 0);
                applyForEntity.setApplyForDeliveryCount(count);
                break;
            }
        }

        applyForRepository.save(applyForEntity);
        return true;
    }

    public List<PayForDto> getBill(String memberEmail){
        MemberEntity memberEntity = memberRepository.findByMemberEmail(memberEmail);
        return payForRepository.findAllByMemberEntity(memberEntity).
                stream().map(payForEntity -> PayForDto.builder().
                    payForRequestCount(payForEntity.getPayForRequestCount()).
                    payForResponseDate(payForEntity.getPayForResponseDate()).
                    payForPickDate(payForEntity.getPayForPickDate()).
                    laundryPlanDto(LaundryPlanDto.builder()
                            .laundryPlanDescription(payForEntity.getLaundryPlanEntity().getLaundryPlanDescription())
                            .laundryPlanType(payForEntity.getLaundryPlanEntity().getLaundryPlanType())
                            .laundryPlanDetails(payForEntity.getLaundryPlanEntity().getLaundryPlanDetails())
                            .laundryPlanPrice(payForEntity.getLaundryPlanEntity().getLaundryPlanPrice())
                            .build()
                    ).build()
        ).collect(Collectors.toList());
    }


}
