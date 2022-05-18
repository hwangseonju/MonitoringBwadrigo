package com.ssaffron.business.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PayDtoEmployeeForm {

    private PayDto payDto;

    private MemberDto memberDto;

    private CollectDto collectDto;

}
