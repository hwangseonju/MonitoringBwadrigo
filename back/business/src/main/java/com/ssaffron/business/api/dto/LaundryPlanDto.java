package com.ssaffron.business.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LaundryPlanDto {

    private int laundryPlanId;

    private String laundryPlanType;

    private String laundryPlanDetails;

    private int laundryPlanPrice;

    private String laundryPlanDescription;

}
