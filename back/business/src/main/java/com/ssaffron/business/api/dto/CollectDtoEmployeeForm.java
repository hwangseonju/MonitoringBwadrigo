package com.ssaffron.business.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CollectDtoEmployeeForm {
    //장애 대응팀 등 직원이 정보를 볼 수 있도록 서비스 하는 Dto
    private MemberDto memberDto;
    private CollectDto collectDto;
    private EmployeeDto employeeDto;

}
