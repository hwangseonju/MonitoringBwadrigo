package com.ssaffron.business.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDto {

    private int paymentIdx;
    private String paymentName;
    private int paymentPrice;
    private int washCnt;
    private int cleaningCnt;
    private int shirtCnt;
    private int beddingCnt;
    private int deliveryCnt;

}
