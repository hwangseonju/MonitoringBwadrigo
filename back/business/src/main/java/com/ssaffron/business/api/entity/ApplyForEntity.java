package com.ssaffron.business.api.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "apply_for")
@RequiredArgsConstructor
public class ApplyForEntity {

    @Id
    @Column(name = "apply_for_index")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int applyForIndex;

    @Column(name = "apply_for_wash_count")
    private int applyForWashCount;

    @Column(name = "apply_for_bedding_count")
    private int applyForBeddingCount;

    @Column(name = "apply_for_delivery_count")
    private int applyForDeliveryCount;

    @Column(name = "apply_for_cleaning_count")
    private int applyForCleaningCount;

    @Column(name = "apply_for_shirt_count")
    private int applyForShirtCount;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_index")
    private MemberEntity memberEntity;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "month_plan_index")
    private MonthPlanEntity monthPlanIndex;

    @Builder
    public ApplyForEntity(int applyForIndex, int applyForWashCount, int applyForBeddingCount, int applyForDeliveryCount, int applyForCleaningCount, int applyForShirtCount/*, MemberEntity memberIndex, MonthPlanEntity monthPlanIndex*/) {
        this.applyForIndex = applyForIndex;
        this.applyForWashCount = applyForWashCount;
        this.applyForBeddingCount = applyForBeddingCount;
        this.applyForDeliveryCount = applyForDeliveryCount;
        this.applyForCleaningCount = applyForCleaningCount;
        this.applyForShirtCount = applyForShirtCount;
//        this.memberEntity = memberIndex;
//        this.monthPlanIndex = monthPlanIndex;
    }


    public void update(int applyForWashCount
            , int applyForBeddingCount, int applyForDeliveryCount
            , int applyForCleaningCount, int applyForShirtCount//, MonthPlanEntity monthPlanIndex
    )
    {
        this.applyForIndex = applyForIndex;
        this.applyForWashCount = applyForWashCount;
        this.applyForBeddingCount = applyForBeddingCount;
        this.applyForDeliveryCount = applyForDeliveryCount;
        this.applyForCleaningCount = applyForCleaningCount;
        this.applyForShirtCount = applyForShirtCount;
        //this.monthPlanIndex = monthPlanIndex;
    }
}
