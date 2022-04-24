package com.ssaffron.business.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestApplyForDto {
    private int monthPlanIndex;
    private String memberEmail;
    private ApplyForDto applyForDto;
}
