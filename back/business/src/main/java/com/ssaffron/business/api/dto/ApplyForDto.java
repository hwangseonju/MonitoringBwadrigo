package com.ssaffron.business.api.dto;

import com.ssaffron.business.api.entity.ApplyForEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalDateTime;

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
    private LocalDateTime applyForDate;
    private int applyForChange;

    private int memberEmail;
    private int monthPlanIndex;

    // response : entity to dto
    public ApplyForDto(ApplyForEntity entity){
        this.applyForIndex = entity.getApplyForIndex();
        this.applyForWashCount = entity.getApplyForWashCount();
        this.applyForBeddingCount = entity.getApplyForBeddingCount();
        this.applyForDeliveryCount = entity.getApplyForDeliveryCount();
        this.applyForCleaningCount = entity.getApplyForCleaningCount();
        this.applyForShirtCount = entity.getApplyForShirtCount();
        this.memberEmail = entity.getMemberEntity().getMemberIndex();
        this.monthPlanIndex =entity.getMonthPlanEntity().getMonthPlanIndex();
    }

}
