package com.ssaffron.business.api.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_index")
    private MemberEntity memberEntity;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "month_plan_index")
    private MonthPlanEntity monthPlanEntity;

}
