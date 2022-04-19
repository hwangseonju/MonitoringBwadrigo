package com.ssaffron.business.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MonthPlanDto {

    private int monthPlanIndex;
    private String monthPlanName;
    private int monthPlanPrice;
    private int monthPlanWashCount;
    private int monthPlanCleaningCount;
    private int monthPlanShirtCount;
    private int monthPlanBeddingCount;
    private int monthPlanDeliveryCount;

}
