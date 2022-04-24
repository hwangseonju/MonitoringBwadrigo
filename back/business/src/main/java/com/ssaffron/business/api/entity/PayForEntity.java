package com.ssaffron.business.api.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="pay_for")
@RequiredArgsConstructor
@Getter
public class PayForEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pay_for_index")
    private long payForIndex;

    @Column(name = "pay_for_request_count", nullable = false)
    private int payForRequestCount;

    @Column(name = "pay_for_response_date")
    private LocalDateTime payForResponseDate;

    @Column(name = "pay_for_pick_date")
    private LocalDateTime payForPickDate;

//    @OneToOne(mappedBy = "optionalIdx")
//    private OptionalEntity optionalEntity;
//    관계를 끊어도 상관없고
//    관계가 있어도 charge에서는 optional을 참조할 필요가 없음

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "collect_for_index")
    private CollectForEntity collectForEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_index")
    private MemberEntity memberEntity;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "laundry_plan_index")
    private LaundryPlanEntity laundryPlanEntity;

    public PayForEntity(int payForRequestCount, LocalDateTime payForResponseDate, LocalDateTime payForPickDate,
                        CollectForEntity collectForEntity, MemberEntity memberEntity, LaundryPlanEntity laundryPlanEntity) {
        this.payForRequestCount = payForRequestCount;
        this.payForResponseDate = payForResponseDate;
        this.payForPickDate = payForPickDate;
        this.collectForEntity = collectForEntity;
        this.memberEntity = memberEntity;
        this.laundryPlanEntity = laundryPlanEntity;
    }
}
