package com.ssaffron.business.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private int userIdx;
    private String userEmail;
    private String userPwd;
    private String userName;
    private String userPhone;
    private String userAddress;
    private boolean userGender;
    private int userAge;
}
