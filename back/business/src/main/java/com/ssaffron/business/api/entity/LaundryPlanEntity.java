package com.ssaffron.business.api.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "laundry_plan")
@Getter
@RequiredArgsConstructor
public class LaundryPlanEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "laundry_plan_id")
    private int laundryPlanId;

    @Column(name = "laundry_plan_type_kor", nullable = false, length = 20)
    private String laundryPlanTypeKor;

    @Column(name = "laundry_plan_type_eng", nullable = false, length = 20)
    private String laundryPlanTypeEng;

    @Column(name = "laundry_plan_details", nullable = false, length = 50)
    private String laundryPlanDetails;

    @Column(name = "laundry_plan_price", nullable = false)
    private int laundryPlanPrice;

    @Column(name = "laundry_plan_description", nullable = false, length = 1000)
    private String laundryPlanDescription;

    @Column(name = "laundry_plan_create_date")
    @CreatedDate
    private LocalDateTime laundryCreateDate;

    @Column(name = "laundry_plan_update_date")
    @LastModifiedDate
    private LocalDateTime laundryUpdateDate;

//    @OneToOne(mappedBy = "feeEntity")
//    private ChargeEntity chargeEntity;
}
