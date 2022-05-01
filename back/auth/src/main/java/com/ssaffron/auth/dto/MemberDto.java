package com.ssaffron.auth.dto;

import com.ssaffron.auth.config.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {

    private String memberEmail;
    private String memberName;
    private String memberPassword;
    private UserRole memberRole;

}
