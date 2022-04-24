package com.ssaffron.business.api.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "month_plan")
public class MonthPlanEntity {

    @Id
    @Column(name = "month_plan_index")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int monthPlanIndex;
    @Column(name = "month_plan_name", nullable = false, length = 30)
    private String monthPlanName;
    @Column(name = "month_plan_price", nullable = false)
    private int monthPlanPrice;
    @Column(name = "month_plan_wash_count")
    private int monthPlanWashCount;
    @Column(name = "month_plan_cleaning_count")
    private int monthPlanCleaningCount;
    @Column(name = "month_plan_shirt_count")
    private int monthPlanShirtCount;
    @Column(name = "month_plan_bedding_count")
    private int monthPlanBeddingCount;
    @Column(name = "month_plan_delivery_count")
    private int monthPlanDeliveryCount;
    @OneToOne(mappedBy = "monthPlanEntity")
    private ApplyForEntity applyForEntity;

}
