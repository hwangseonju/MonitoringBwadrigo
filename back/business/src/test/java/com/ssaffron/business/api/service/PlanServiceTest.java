package com.ssaffron.business.api.service;

import com.ssaffron.business.api.entity.ApplyEntity;
import com.ssaffron.business.api.entity.LaundryPlanEntity;
import com.ssaffron.business.api.entity.MemberEntity;
import com.ssaffron.business.api.entity.MonthPlanEntity;
import com.ssaffron.business.api.repository.ApplyRepository;
import com.ssaffron.business.api.repository.LaundryPlanRepository;
import com.ssaffron.business.api.repository.MemberRepository;
import com.ssaffron.business.api.repository.MonthPlanRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PlanServiceTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ApplyRepository applyRepository;

    @Autowired
    private MonthPlanRepository monthPlanRepository;

    @Autowired
    private LaundryPlanRepository laundryPlanRepository;

    @Test
    @DisplayName("요금제 조회 테스트")
    void selectApply(){
        MemberEntity memberEntity = memberRepository.findByMemberEmail("abc@abc.com");
        ApplyEntity applyEntity = applyRepository.findByMemberEntity(memberEntity).get();

        assertEquals(1, applyEntity.getApplyId());
    }

    @Test
    @DisplayName("요금제 신청 테스트")
    void insertApply(){
        MemberEntity memberEntity = memberRepository.findByMemberEmail("ssaffy@ssaffy.com");
        MonthPlanEntity monthPlanEntity = monthPlanRepository.findByMonthPlanId(2);
        ApplyEntity applyEntity = new ApplyEntity(
                3,
                0,
                3,
                0,
                20,
                null,
                null,
                memberEntity,
                monthPlanEntity
        );

        assertEquals(applyEntity, applyRepository.save(applyEntity));
    }

    @Test
    @DisplayName("요금제 삭제 테스트")
    void updateApply(){
        MemberEntity memberEntity = memberRepository.findByMemberEmail("abc@abc.com");
        ApplyEntity applyEntity = applyRepository.findByMemberEntity(memberEntity).get();
        assertTrue(applyEntity.getApplyChange() == null);
        //1. 변경할 요금제가 없는지 확인.
        applyEntity.setApplyChange(3);

        ApplyEntity changeApplyEntity = applyRepository.findByMemberEntity(memberEntity).get();
        assertEquals(3, changeApplyEntity.getApplyChange());
        //2. 변경할 요금제가 들어갔는지 확인.
    }

    @Test
    @DisplayName("요금제 삭제 테스트")
    void deleteApply(){
        MemberEntity memberEntity = memberRepository.findByMemberEmail("abc@abc.com");
        ApplyEntity applyEntity = applyRepository.findByMemberEntity(memberEntity).get();
        int applyId = applyEntity.getApplyId();

        applyRepository.delete(applyEntity);

        assertEquals(null, applyRepository.findById(applyId).orElse(null));
    }

    @Test
    @DisplayName("안심 정찰제 조회 테스트")
    void getAllListLaundryPlan(){
        List<LaundryPlanEntity> laundryPlanEntities = laundryPlanRepository.findAll();
        assertEquals(45, laundryPlanEntities.size());
    }

    @Test
    @DisplayName("안심 정찰제 단일 조회 테스트")
     void getOneLaundryPlan(){
        LaundryPlanEntity laundryPlanEntity = laundryPlanRepository.findByLaundryPlanId(1);
        String laundryPlanDetails = laundryPlanEntity.getLaundryPlanDetails();
        assertEquals("생활빨래3kg까지", laundryPlanDetails);
    }

    @Test
    @DisplayName("정액 요금제 조회 테스트")
    void getAllListMonthPlan(){
        List<MonthPlanEntity> monthPlanEntities = monthPlanRepository.findAll();
        assertEquals(7, monthPlanEntities.size());
    }

    @Test
    @DisplayName("정액 요금제 단일 조회 테스트")
    void getOneMonthPlan(){
        MonthPlanEntity monthPlanEntity = monthPlanRepository.findByMonthPlanId(2);
        String monthPlanName = monthPlanEntity.getMonthPlanName();
        assertEquals("올인원", monthPlanName);
    }
}
