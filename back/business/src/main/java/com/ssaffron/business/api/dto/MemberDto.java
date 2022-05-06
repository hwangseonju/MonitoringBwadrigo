package com.ssaffron.business.api.dto;

import com.ssaffron.business.api.config.UserRole;
import com.ssaffron.business.api.entity.MemberEntity;
import com.ssaffron.business.api.entity.MemberStatus;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {
    private int memberId;
    private String memberEmail;
    private String memberPassword;
    private String memberName;
    private String memberPhone;
    private String memberAddress;
    private boolean memberGender;
    private int memberAge;
    private MemberStatus memberStatus;
    private UserRole userRole;
}
