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

    @Builder(builderMethodName = "MemberDtoBuilder")
    public static MemberDtoBuilder builder(MemberEntity memberEntity){
        return MemberDtoBuilder()
                .memberEmail(memberEntity.getMemberEmail())
                .memberPassword(memberEntity.getMemberPassword())
                .memberName(memberEntity.getMemberName())
                .memberPhone(memberEntity.getMemberPhone())
                .memberAddress(memberEntity.getMemberAddress())
                .memberGender(memberEntity.isMemberGender())
                .memberAge(memberEntity.getMemberAge())
                .memberStatus(memberEntity.getMemberStatus())
                .userRole(memberEntity.getRole());
    }
}
