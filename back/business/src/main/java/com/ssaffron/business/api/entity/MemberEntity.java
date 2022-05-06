package com.ssaffron.business.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ssaffron.business.api.config.UserRole;
import com.ssaffron.business.api.dto.MemberDto;
import com.ssaffron.business.api.dto.MemberModifyDto;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "member")
public class MemberEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    @Column(name = "member_id")
    private int memberId;

    @Column(name = "member_email", nullable = false, length = 30, unique = true)
    private String memberEmail;

    @Column(name = "member_password", nullable = false, length = 100)
    private String memberPassword;

    @Column(name = "member_name", nullable = false, length = 30)
    private String memberName;

    @Column(name = "member_phone", nullable = false, length = 30, unique = true)
    private String memberPhone;

    @Column(name = "member_address", length = 100)
    private String memberAddress;

    @Column(name = "member_gender")
    private boolean memberGender;

    @Column(name = "member_age")
    private int memberAge;

    @Column(name = "member_status")
    @Enumerated(EnumType.STRING)
    private MemberStatus memberStatus;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Column(name = "member_create_date")
    @CreatedDate
    private LocalDateTime memberCreateDate;

    @Column(name = "member_update_date")
    @LastModifiedDate
    private LocalDateTime memberUpdateDate;

    @Builder.Default
    @OneToMany(mappedBy = "memberEntity",cascade = CascadeType.ALL)
    private List<PayEntity> chargeEntities = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "memberEntity",cascade = CascadeType.ALL)
    private List<CollectEntity> collectionEntities = new ArrayList<>();

    @OneToOne(mappedBy = "memberEntity")
    private ApplyEntity applyForEntity;

    @Builder(builderMethodName = "MemberEntityBuilder")
    public static MemberEntityBuilder builder(MemberDto memberDto){
        return MemberEntityBuilder()
                .memberEmail(memberDto.getMemberEmail())
                .memberPassword(memberDto.getMemberPassword())
                .memberName(memberDto.getMemberName())
                .memberPhone(memberDto.getMemberPhone())
                .memberAddress(memberDto.getMemberAddress())
                .memberGender(memberDto.isMemberGender())
                .memberAge(memberDto.getMemberAge())
                .memberStatus(memberDto.getMemberStatus())
                .role(memberDto.getUserRole())
                .memberCreateDate(LocalDateTime.now())
                .memberUpdateDate(LocalDateTime.now());
    }

    public void updateWithPassword(MemberModifyDto memberModifyDto){
        this.memberPassword = memberModifyDto.getMemberPassword();
        this.memberAddress = memberModifyDto.getMemberAddress();
    }

    public void updateWithoutPassword(MemberModifyDto memberModifyDto){
        this.memberAddress = memberModifyDto.getMemberAddress();
    }


}
