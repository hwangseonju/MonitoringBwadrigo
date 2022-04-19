package com.ssaffron.business.api.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@RequiredArgsConstructor
@Table(name = "month_plan")
public class MonthPlanEntity {

    @Id
    @Column(name = "month_plan_index")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int monthPlanIndex;
    @Column(name = "month_plan_name", nullable = false, length = 30)
    String monthPlanName;
    @Column(name = "month_plan_price", nullable = false)
    int monthPlanPrice;
    @Column(name = "month_plan_wash_count")
    int monthPlanWashCount;
    @Column(name = "month_plan_cleaning_count")
    int monthPlanCleaningCount;
    @Column(name = "month_plan_shirt_count")
    int monthPlanShirtCount;
    @Column(name = "month_plan_bedding_count")
    int monthPlanBeddingCount;
    @Column(name = "month_plan_delivery_count")
    int monthPlanDeliveryCount;
}