package com.ssaffron.business.api.service;

import com.ssaffron.business.api.dto.*;
import com.ssaffron.business.api.entity.*;
import com.ssaffron.business.api.exception.NullAddressException;
import com.ssaffron.business.api.exception.NullApplyException;
import com.ssaffron.business.api.repository.*;
import lombok.RequiredArgsConstructor;
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
    private final CollectRepository collectRepository;
    private final PayRepository payRepository;
    private final LaundryPlanRepository laundryPlanRepository;
    private final ApplyRepository applyRepository;

    public boolean collectionRequest(String memberEmail, List<CollectDto> collectDtoList){
        //수거 신청 성공/실패
        MemberEntity memberEntity = memberRepository.findByMemberEmail(memberEmail);
        if(memberEntity.getMemberAddress() == null)
            throw new NullAddressException();
        ApplyEntity applyEntity = applyRepository.findByMemberEntityAndApplyDeliveryCountIsNotNull(memberEntity).orElse(null);
        if(applyEntity == null){
            throw new NullApplyException(memberEntity.getMemberName());
        }
        List<CollectEntity> collectEntityList = collectDtoList.
                stream().map(collectDto -> new CollectEntity(
                        collectDto.getCollecttype(), memberEntity)).collect(Collectors.toList());
        collectRepository.saveAll(collectEntityList);
        return true;
    }

    public List<CollectDto> collectionRequestInquiry(String memberEmail){
        //한 사용자의 수거 요청 목록을 조회 할 수 있다.
        //수거한 직원의 정보를 볼 수 없다. -> 사용자용
        MemberEntity memberEntity = memberRepository.findByMemberEmail(memberEmail);
        return collectRepository.findAllByMemberEntity(memberEntity).
                stream().map(collectEntity -> CollectDto.builder().
                    collectId(collectEntity.getCollectId()).
                    collectRequestDate(collectEntity.getCollectRequestDate()).
                    collectWithdrawDate(collectEntity.getCollectWithdrawDate()).
                    collecttype(collectEntity.getCollectType()).
                    build()).collect(Collectors.toList());
                    //collectId를 넣는 이유 -> 접수번호 확인
    }

    public List<CollectDtoEmployeeForm> fetchAllCollectionRequest(){
        //모든 사용자의 수거 요청 목록이 보여짐. -> 수거한 직원 정보를 볼 수 있다.
        //JWT에서 Role이 직원에 해당 할 때 접근 가능하도록 설정 필요
        return collectRepository.findAll().
                stream().map(collectEntity -> CollectDtoEmployeeForm.builder().
                    memberDto(MemberDto.builder().
                                memberEmail(collectEntity.getMemberEntity().getMemberEmail()).
                                memberName(collectEntity.getMemberEntity().getMemberName()).build()).
                        collectDto(CollectDto.builder().
                            collectId(collectEntity.getCollectId()).
                            collecttype(collectEntity.getCollectType()).
                            collectRequestDate(collectEntity.getCollectRequestDate()).
                            collectWithdrawDate(collectEntity.getCollectWithdrawDate()).build()).
                    employeeDto(EmployeeDto.builder().
                            employeeId(collectEntity.getEmployeeEntity().getEmployeeId()).
                            employeeName(collectEntity.getEmployeeEntity().getEmployeeName()).
                            employeePhone(collectEntity.getEmployeeEntity().getEmployeePhone()).build()).build()
                ).collect(Collectors.toList());

    }

    public List<CollectDtoEmployeeForm> fetchCollectionRequestByMember(String memberEmail){
        //특정 사용자의 수거 요청 목록이 보여짐. -> 수거한 직원 정보를 볼 수 있다.
        //JWT에서 Role이 직원에 해당 할 때 접근 가능하도록 설정 필요
        MemberEntity memberEntity = memberRepository.findByMemberEmail(memberEmail);
        return collectRepository.findAllByMemberEntity(memberEntity).
                stream().map(collectEntity -> CollectDtoEmployeeForm.builder().
                        memberDto(MemberDto.builder().
                                memberEmail(collectEntity.getMemberEntity().getMemberEmail()).
                                memberName(collectEntity.getMemberEntity().getMemberName()).build()).
                        collectDto(CollectDto.builder().
                                collectId(collectEntity.getCollectId()).
                                collecttype(collectEntity.getCollectType()).
                                collectRequestDate(collectEntity.getCollectRequestDate()).
                                collectWithdrawDate(collectEntity.getCollectWithdrawDate()).build()).
                        employeeDto(EmployeeDto.builder().
                                employeeId(collectEntity.getEmployeeEntity().getEmployeeId()).
                                employeeName(collectEntity.getEmployeeEntity().getEmployeeName()).
                                employeePhone(collectEntity.getEmployeeEntity().getEmployeePhone()).build()).build()
                ).collect(Collectors.toList());
    }

    public List<CollectDtoEmployeeForm> fetchCollectionRequestByEmployee(int employeeId){
        //직원이 수거한 수거 요청을 확인 할 수 있다.
        //JWT에서 Role이 직원에 해당 할 때 접근 가능하도록 설정 필요
        EmployeeEntity employeeEntity = employeeRepository.findById(employeeId).get();
        return collectRepository.findAllByEmployeeEntity(employeeEntity).
                stream().map(collectEntity -> CollectDtoEmployeeForm.builder().
                        memberDto(MemberDto.builder().
                                memberEmail(collectEntity.getMemberEntity().getMemberEmail()).
                                memberName(collectEntity.getMemberEntity().getMemberName()).build()).
                        collectDto(CollectDto.builder().
                                collectId(collectEntity.getCollectId()).
                                collecttype(collectEntity.getCollectType()).
                                collectRequestDate(collectEntity.getCollectRequestDate()).
                                collectWithdrawDate(collectEntity.getCollectWithdrawDate()).build()).
                        employeeDto(EmployeeDto.builder().
                                employeeId(collectEntity.getEmployeeEntity().getEmployeeId()).
                                employeeName(collectEntity.getEmployeeEntity().getEmployeeName()).
                                employeePhone(collectEntity.getEmployeeEntity().getEmployeePhone()).build()).build()
                ).collect(Collectors.toList());
    }

    public boolean collectionApproval(List<CollectDto> collectDtoList, EmployeeDto employeeDto){
        //수거를 할 직원을 할당할 수 있다.
        EmployeeEntity employeeEntity = employeeRepository.findById(employeeDto.getEmployeeId()).get();
        //사원번호호 사원 엔티티 생성
        List<CollectEntity> collectEntityList = new ArrayList<>();
        collectDtoList.forEach(collectDto -> {
            CollectEntity collectEntity = collectRepository.findById(collectDto.getCollectId()).get();
            collectEntity.setEmployeeEntity(employeeEntity);
            collectEntityList.add(collectEntity);
        });
        collectRepository.saveAll(collectEntityList);
        return true;
    }

    public boolean withdrawalOfCollection(long collectionId){
        //사용자 인증은 JWT를 통해 본인만 진행이 되고, collectionId자체는 PK이기 때문에 중복이 없음
        //직원은 접근이 가능함.
        CollectEntity collectEntity = collectRepository.findById(collectionId).get();
        collectEntity.setCollectWithdrawDate(LocalDateTime.now());
        collectRepository.save(collectEntity);
        return true;

    }

    public boolean registBill(String memberEmail, long collectId, int laudryPlanId, PayDto payDto){
        //collectDto로 빨래 수거 맡긴 날 찾아오기
        //regist 후에 ApplyEntity도 수정하기
        //collectDto에서의 Type은 laundryPlan의 Type에 해당 되고 세부 타입에 따라 여러 row가 나올 수 있다.
        MemberEntity memberEntity = memberRepository.findByMemberEmail(memberEmail);
        CollectEntity collectEntity = collectRepository.findById(collectId).get();
        LaundryPlanEntity laundryPlanEntity = laundryPlanRepository.findById(laudryPlanId).get();

        PayEntity payEntity = new PayEntity(
                                            payDto.getPayRequestCount(),
                                            LocalDateTime.now(),
                                            collectEntity.getCollectRequestDate(),
                collectEntity,
                                            memberEntity,
                                            laundryPlanEntity
                                    );
        payRepository.save(payEntity);
        ApplyEntity applyEntity = applyRepository.findByMemberEntityAndApplyDeliveryCountIsNotNull(memberEntity).get();
        //신청한 요금제로 상태관리
        if(applyEntity.getMonthPlanEntity().getMonthPlanId() == 1){
            return true;
            //저장할 필요가 없음 1일 때는 자유요금제

        }
        int requestCount = payEntity.getPayRequestCount();
        switch (laundryPlanEntity.getLaundryPlanType()) {
            case "wash": {
                int count = Math.max(applyEntity.getApplyWashCount() - requestCount, 0);
                applyEntity.setApplyWashCount(count);
                break;
            }
            case "bedding": {
                int count = Math.max(applyEntity.getApplyBeddingCount() - requestCount, 0);
                applyEntity.setApplyBeddingCount(count);
                break;
            }
            case "cleaning": {
                int count = Math.max(applyEntity.getApplyCleaningCount() - requestCount, 0);
                applyEntity.setApplyCleaningCount(count);
                break;
            }
            case "shirt": {
                int count = Math.max(applyEntity.getApplyShirtCount() - requestCount, 0);
                applyEntity.setApplyShirtCount(count);
                break;
            }
            case "delivery": {
                int count = Math.max(applyEntity.getApplyDeliveryCount() - requestCount, 0);
                applyEntity.setApplyDeliveryCount(count);
                break;
            }
        }

        applyRepository.save(applyEntity);
        return true;
    }

    public List<PayDto> getBill(String memberEmail){
        MemberEntity memberEntity = memberRepository.findByMemberEmail(memberEmail);
        return payRepository.findAllByMemberEntity(memberEntity).
                stream().map(payEntity -> PayDto.builder().
                    payRequestCount(payEntity.getPayRequestCount()).
                    payResponseDate(payEntity.getPayResponseDate()).
                    payPickDate(payEntity.getPayPickDate()).
                    laundryPlanDto(LaundryPlanDto.builder()
                            .laundryPlanDescription(payEntity.getLaundryPlanEntity().getLaundryPlanDescription())
                            .laundryPlanType(payEntity.getLaundryPlanEntity().getLaundryPlanType())
                            .laundryPlanDetails(payEntity.getLaundryPlanEntity().getLaundryPlanDetails())
                            .laundryPlanPrice(payEntity.getLaundryPlanEntity().getLaundryPlanPrice())
                            .build()
                    ).build()
        ).collect(Collectors.toList());
    }


}
