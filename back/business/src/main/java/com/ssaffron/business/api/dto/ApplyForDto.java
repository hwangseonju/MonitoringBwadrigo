package com.ssaffron.business.api.dto;

import com.ssaffron.business.api.entity.ApplyForEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplyForDto {

    private int applyForIndex;
    private int applyForWashCount;
    private int applyForBeddingCount;
    private int applyForDeliveryCount;
    private int applyForCleaningCount;
    private int applyForShirtCount;

    private int memberIndex;
    private int monthPlanIndex;

    // response : entity to dto
    public ApplyForDto(ApplyForEntity entity){
        this.applyForIndex = entity.getApplyForIndex();
        this.applyForWashCount = entity.getApplyForWashCount();
        this.applyForBeddingCount = entity.getApplyForBeddingCount();
        this.applyForDeliveryCount = entity.getApplyForDeliveryCount();
        this.applyForCleaningCount = entity.getApplyForCleaningCount();
        this.applyForShirtCount = entity.getApplyForShirtCount();
        this.memberIndex = entity.getMemberEntity().getMemberIndex();
        this.monthPlanIndex =entity.getMonthPlanEntity().getMonthPlanIndex();
    }

}
