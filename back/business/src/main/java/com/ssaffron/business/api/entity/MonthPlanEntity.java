package com.ssaffron.business.api.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "month_plan")
public class MonthPlanEntity {

    @Id
    @Column(name = "month_plan_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int monthPlanId;
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
    @Column(name = "month_plan_create_date")
    @CreatedDate
    private LocalDateTime monthPlanCreateDate;

    @Column(name = "month_plan_update_date")
    @LastModifiedDate
    private LocalDateTime monthPlanUpdateDate;

}
