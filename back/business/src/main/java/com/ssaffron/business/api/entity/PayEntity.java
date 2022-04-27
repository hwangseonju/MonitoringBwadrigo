package com.ssaffron.business.api.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="pay")
@RequiredArgsConstructor
@Getter
public class PayEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pay_id")
    private long payId;

    @Column(name = "pay_request_count", nullable = false)
    private int payRequestCount;

    @Column(name = "pay_response_date")
    private LocalDateTime payResponseDate;

    @Column(name = "pay_pick_date")
    private LocalDateTime payPickDate;

//    @OneToOne(mappedBy = "optionalIdx")
//    private OptionalEntity optionalEntity;
//    관계를 끊어도 상관없고
//    관계가 있어도 charge에서는 optional을 참조할 필요가 없음

    @Column(name = "pay_create_date")
    @CreatedDate
    private LocalDateTime payCreateDate;

    @Column(name = "pay_update_date")
    @LastModifiedDate
    private LocalDateTime payUpdateDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "collect_id")
    private CollectEntity collectEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "laundry_plan_id")
    private LaundryPlanEntity laundryPlanEntity;

    public PayEntity(int payRequestCount, LocalDateTime payResponseDate, LocalDateTime payPickDate,
                     CollectEntity collectEntity, MemberEntity memberEntity, LaundryPlanEntity laundryPlanEntity) {
        this.payRequestCount = payRequestCount;
        this.payResponseDate = payResponseDate;
        this.payPickDate = payPickDate;
        this.collectEntity = collectEntity;
        this.memberEntity = memberEntity;
        this.laundryPlanEntity = laundryPlanEntity;
    }
}
