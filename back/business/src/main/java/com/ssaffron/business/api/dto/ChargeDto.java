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
public class ChargeDto {

    private long chargeIdx;

    private int requestCnt;

    private LocalDateTime responsedAt;

    private LocalDateTime pickDate;
}
