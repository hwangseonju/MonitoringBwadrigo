package com.ssaffron.business.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OptionalDto {

    private int optionalIdx;

    private int washCnt;

    private int beddingCnt;

    private int deliveryCnt;

    private int cleaningCnt;

    private int userIdx;

    private int paymentIdx;

}
