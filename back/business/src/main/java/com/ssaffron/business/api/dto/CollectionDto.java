package com.ssaffron.business.api.dto;


import com.ssaffron.business.api.entity.EmployeeEntity;
import com.ssaffron.business.api.entity.UserEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
class CollectionDto {
    private long collectionIdx;
    private String type;
    private LocalDateTime requestedAt;
    private EmployeeEntity employeeIdx;
    private UserEntity userIdx;
}
