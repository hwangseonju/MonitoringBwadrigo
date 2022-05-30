package com.ssaffron.business.api.service;

import com.ssaffron.business.api.dto.CollectDto;
import com.ssaffron.business.api.dto.LaundryPlanDto;
import com.ssaffron.business.api.dto.PayDto;
import com.ssaffron.business.api.entity.ApplyEntity;
import com.ssaffron.business.api.entity.CollectEntity;
import com.ssaffron.business.api.entity.MemberEntity;
import com.ssaffron.business.api.exception.NotFoundAddressException;
import com.ssaffron.business.api.exception.NotFoundApplyException;
import com.ssaffron.business.api.repository.CollectRepository;
import com.ssaffron.business.api.repository.MemberRepository;
import com.ssaffron.business.api.repository.PayRepository;
import com.ssaffron.business.api.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class OrderTest {

    @Autowired
    private CollectRepository collectRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PayRepository payRepository;

    MemberEntity memberEntity;

    @BeforeEach
    void getMember(){
        memberEntity = memberRepository.findByMemberEmail("abc@abc.com");

    }

    @Test
    @DisplayName("수거 신청 테스트")
    public void collectionRequestTest(){
        List<CollectDto> collectDtoList = new ArrayList<>();
        collectDtoList.add(new CollectDto(1, "wash", LocalDateTime.now(), LocalDateTime.now()));

        List<CollectEntity> collectEntityList = collectDtoList.
                stream().map(collectDto -> new CollectEntity(
                        collectDto.getCollectType(),LocalDateTime.now(), memberEntity)).collect(Collectors.toList());

        List<CollectEntity> saveCollectEntityList =  collectRepository.saveAll(collectEntityList);
        assertEquals(collectDtoList.size(), saveCollectEntityList.size());
    }

    @Test
    @DisplayName("수거 신청 여부 확인 테스트")
    public void isCollect(){
        List<CollectDto> collectDtoList = collectRepository.findAllByMemberEntityAndCollectWithdrawDateIsNullAndEmployeeEntityIsNull(memberEntity)
                .stream().map(collectEntity -> CollectDto.builder().
                        collectId(collectEntity.getCollectId()).
                        collectRequestDate(collectEntity.getCollectRequestDate()).
                        collectWithdrawDate(collectEntity.getCollectWithdrawDate()).
                        collectType(collectEntity.getCollectType()).
                        build()).collect(Collectors.toList());
        assertTrue(collectDtoList.size() <= 4);
    }

    @Test
    @DisplayName("수거 철회 테스트")
    public void withdrawalOfCollection(){
        //사용자 인증은 JWT를 통해 본인만 진행이 되고, collectionId자체는 PK이기 때문에 중복이 없음
        CollectEntity collectEntity = collectRepository.findById(1L).get();
        collectEntity.setCollectWithdrawDate(LocalDateTime.now());
        assertTrue(collectRepository.save(collectEntity).getCollectWithdrawDate() != null);
    }

    @Test
    @DisplayName("영수증 요청 테스트")
    public void getBill(){
        LocalDateTime end = LocalDateTime.now();
        LocalDateTime start = LocalDateTime.now().minusMonths(1);
        List<PayDto> payDtoList = payRepository.findAllByMemberEntityAndPayResponseDateBetweenOrderByPayResponseDateDesc(memberEntity, start, end).
                stream().map(payEntity -> PayDto.builder().
                        payId(payEntity.getPayId()).
                        payRequestCount(payEntity.getPayRequestCount()).
                        payResponseDate(payEntity.getPayResponseDate()).
                        payPickDate(payEntity.getPayPickDate()).
                        laundryPlanDto(LaundryPlanDto.builder()
                                .laundryPlanDescription(payEntity.getLaundryPlanEntity().getLaundryPlanDescription())
                                .laundryPlanTypeKor(payEntity.getLaundryPlanEntity().getLaundryPlanTypeKor())
                                .laundryPlanTypeEng(payEntity.getLaundryPlanEntity().getLaundryPlanTypeEng())
                                .laundryPlanDetails(payEntity.getLaundryPlanEntity().getLaundryPlanDetails())
                                .laundryPlanPrice(payEntity.getLaundryPlanEntity().getLaundryPlanPrice())
                                .build()
                        ).build()
                ).collect(Collectors.toList());
        assertEquals(1, payDtoList.size());
    }

}
