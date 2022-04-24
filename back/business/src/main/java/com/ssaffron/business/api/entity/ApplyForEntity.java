package com.ssaffron.business.api.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "apply_for")
@RequiredArgsConstructor
public class ApplyForEntity {

    @Id
    @Column(name = "apply_for_index")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int applyForIndex;

    @Setter
    @Column(name = "apply_for_wash_count")
    private int applyForWashCount;

    @Setter
    @Column(name = "apply_for_bedding_count")
    private int applyForBeddingCount;

    @Setter
    @Column(name = "apply_for_delivery_count")
    private int applyForDeliveryCount;

    @Setter
    @Column(name = "apply_for_cleaning_count")
    private int applyForCleaningCount;

    @Setter
    @Column(name = "apply_for_shirt_count")
    private int applyForShirtCount;

    @Column(name = "apply_for_date")
    private LocalDateTime applyForDate;

    @Setter
    @Column(name = "apply_for_change")
    private int applyForChange;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_index", unique = true)
    private MemberEntity memberEntity;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "month_plan_index")
    private MonthPlanEntity monthPlanEntity;

    public ApplyForEntity(int applyForWashCount, int applyForBeddingCount,
                          int applyForDeliveryCount, int applyForCleaningCount,
                          int applyForShirtCount, LocalDateTime applyForDate, int applyForChange, MemberEntity memberEntity, MonthPlanEntity monthPlanEntity) {
        this.applyForWashCount = applyForWashCount;
        this.applyForBeddingCount = applyForBeddingCount;
        this.applyForDeliveryCount = applyForDeliveryCount;
        this.applyForCleaningCount = applyForCleaningCount;
        this.applyForShirtCount = applyForShirtCount;
        this.applyForDate = applyForDate;
        this.applyForChange = applyForChange;
        this.memberEntity = memberEntity;
        this.monthPlanEntity = monthPlanEntity;
    }

}
