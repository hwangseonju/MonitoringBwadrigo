package com.ssaffron.business.api.dto;



import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class CollectForDto {
    private long collectForIndex;
    private String collectFortype;
    private LocalDateTime collectForRequestDate;
    private LocalDateTime collectForWithdrawDate;

}
