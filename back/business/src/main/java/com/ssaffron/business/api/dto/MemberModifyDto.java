package com.ssaffron.business.api.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberModifyDto {
    private String memberEmail;
    private String memberPassword;
    private String modifiedPassword;
    private String memberAddress;
}
