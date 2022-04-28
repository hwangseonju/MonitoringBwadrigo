package com.ssaffron.business.api.dto;

import com.ssaffron.business.api.entity.MemberEntity;
import com.ssaffron.business.api.entity.MonthPlanEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MonthPlanDto {

    private int monthPlanId;
    private String monthPlanName;
    private int monthPlanPrice;
    private int monthPlanWashCount;
    private int monthPlanCleaningCount;
    private int monthPlanShirtCount;
    private int monthPlanBeddingCount;
    private int monthPlanDeliveryCount;


    //entity -> dto
    public MonthPlanDto(MonthPlanEntity entity) {
        this.monthPlanId = entity.getMonthPlanId();
        this.monthPlanName = entity.getMonthPlanName();
        this.monthPlanPrice = entity.getMonthPlanPrice();
        this.monthPlanWashCount = entity.getMonthPlanWashCount();
        this.monthPlanCleaningCount = entity.getMonthPlanCleaningCount();
        this.monthPlanShirtCount = entity.getMonthPlanShirtCount();
        this.monthPlanBeddingCount = entity.getMonthPlanBeddingCount();
        this.monthPlanDeliveryCount = entity.getMonthPlanDeliveryCount();
    }
}
