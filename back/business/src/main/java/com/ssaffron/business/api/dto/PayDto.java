package com.ssaffron.business.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PayDto {

    private long payIndex;

    private int payRequestCount;

    private LocalDateTime payResponseDate;

    private LocalDateTime payPickDate;

    private LaundryPlanDto laundryPlanDto;

}
