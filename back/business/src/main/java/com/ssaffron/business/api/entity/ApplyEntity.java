package com.ssaffron.business.api.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "apply")
@RequiredArgsConstructor
public class ApplyEntity {

    @Id
    @Column(name = "apply_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int applyId;

    @Setter
    @Column(name = "apply_wash_count")
    private int applyWashCount;

    @Setter
    @Column(name = "apply_bedding_count")
    private int applyBeddingCount;

    @Setter
    @Column(name = "apply_delivery_count")
    private int applyDeliveryCount;

    @Setter
    @Column(name = "apply_cleaning_count")
    private int applyCleaningCount;

    @Setter
    @Column(name = "apply_shirt_count")
    private int applyShirtCount;

    @Column(name = "apply_date")
    private LocalDateTime applyDate;

    @Setter
    @Column(name = "apply_change")
    private int applyChange;

    @Column(name = "apply_create_date")
    @CreatedDate
    private LocalDateTime apllyCreateDate;

    @Column(name = "apply_update_date")
    @LastModifiedDate
    private LocalDateTime applyUpdateDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", unique = true)
    private MemberEntity memberEntity;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "month_plan_id")
    private MonthPlanEntity monthPlanEntity;


    public ApplyEntity(int applyWashCount, int applyBeddingCount,
                       int applyDeliveryCount, int applyCleaningCount,
                       int applyShirtCount, LocalDateTime applyDate, int applyChange, MemberEntity memberEntity, MonthPlanEntity monthPlanEntity) {
        this.applyWashCount = applyWashCount;
        this.applyBeddingCount = applyBeddingCount;
        this.applyDeliveryCount = applyDeliveryCount;
        this.applyCleaningCount = applyCleaningCount;
        this.applyShirtCount = applyShirtCount;
        this.applyDate = applyDate;
        this.applyChange = applyChange;
        this.memberEntity = memberEntity;
        this.monthPlanEntity = monthPlanEntity;
    }

}
