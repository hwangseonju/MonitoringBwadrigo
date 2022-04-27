package com.ssaffron.business.api.dto;



import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class CollectDto {
    private long collectId;
    private String collecttype;
    private LocalDateTime collectRequestDate;
    private LocalDateTime collectWithdrawDate;

}
