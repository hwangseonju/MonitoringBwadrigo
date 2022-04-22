package com.ssaffron.business.api.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
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

    @Column(name = "apply_for_date")
    private LocalDateTime applyForDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_index")
    private MemberEntity memberEntity;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "month_plan_index")
    private MonthPlanEntity monthPlanEntity;

    public ApplyForEntity(int applyForWashCount, int applyForBeddingCount,
                          int applyForDeliveryCount, int applyForCleaningCount,
                          int applyForShirtCount, LocalDateTime applyForDate, MemberEntity memberEntity, MonthPlanEntity monthPlanEntity) {
        this.applyForWashCount = applyForWashCount;
        this.applyForBeddingCount = applyForBeddingCount;
        this.applyForDeliveryCount = applyForDeliveryCount;
        this.applyForCleaningCount = applyForCleaningCount;
        this.applyForShirtCount = applyForShirtCount;
        this.applyForDate = applyForDate;
        this.memberEntity = memberEntity;
        this.monthPlanEntity = monthPlanEntity;
    }

}
