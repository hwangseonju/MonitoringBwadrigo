package com.ssaffron.business.api.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "laundry_plan")
@Getter
@RequiredArgsConstructor
public class LaundryPlanEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "laundry_plan_index")
    private int laundryPlanIndex;

    @Column(name = "laundry_plan_type", nullable = false, length = 20)
    private String laundryPlanType;

    @Column(name = "laundry_plan_details", nullable = false, length = 20)
    private String laundryPlanDetails;

    @Column(name = "laundry_plan_price", nullable = false)
    private int laundryPlanPrice;

    @Column(name = "laundry_plan_description", nullable = false, length = 20)
    private String laundryPlanDescription;

//    @OneToOne(mappedBy = "feeEntity")
//    private ChargeEntity chargeEntity;
}
