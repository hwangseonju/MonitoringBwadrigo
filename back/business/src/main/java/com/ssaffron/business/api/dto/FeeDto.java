package com.ssaffron.business.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FeeDto {

    private int feeIdx;

    private String type;

    private String details;

    private int price;

    private String description;

}
