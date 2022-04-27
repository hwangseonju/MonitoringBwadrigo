package com.ssaffron.business.api.dto;

import com.ssaffron.business.api.entity.ApplyEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplyDto {

    private int applyIndex;
    private int applyWashCount;
    private int applyBeddingCount;
    private int applyDeliveryCount;
    private int applyCleaningCount;
    private int applyShirtCount;
    private LocalDateTime applyDate;
    private int applyChange;

    private int memberEmail;
    private int monthPlanIndex;

    // response : entity to dto
    public ApplyDto(ApplyEntity entity){
        this.applyIndex = entity.getApplyIndex();
        this.applyWashCount = entity.getApplyWashCount();
        this.applyBeddingCount = entity.getApplyBeddingCount();
        this.applyDeliveryCount = entity.getApplyDeliveryCount();
        this.applyCleaningCount = entity.getApplyCleaningCount();
        this.applyShirtCount = entity.getApplyShirtCount();
        this.applyChange = entity.getApplyChange();
        this.memberEmail = entity.getMemberEntity().getMemberIndex();
        this.monthPlanIndex =entity.getMonthPlanEntity().getMonthPlanIndex();
    }

}
