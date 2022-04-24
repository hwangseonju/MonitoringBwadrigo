package com.ssaffron.business.api.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "member")
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_index")
    private int memberIndex;

    @Column(name = "member_email", nullable = false, length = 30)
    private String memberEmail;

    @Column(name = "member_password", nullable = false, length = 20)
    private String memberPassword;

    @Column(name = "member_name", nullable = false, length = 30)
    private String memberName;

    @Column(name = "member_phone", nullable = false, length = 30)
    private String memberPhone;

    @Column(name = "member_address", length = 100)
    private String memberAddress;

    @Column(name = "member_gender")
    private boolean memberGender;

    @Column(name = "member_age")
    private int memberAge;

    @Column(name = "member_status")
    private MemberStatus memberStatus;

    @OneToMany(mappedBy = "memberEntity",cascade = CascadeType.ALL)
    private List<PayForEntity> chargeEntities = new ArrayList<>();

    @OneToMany(mappedBy = "memberEntity",cascade = CascadeType.ALL)
    private List<CollectForEntity> collectionEntities = new ArrayList<>();

    @OneToOne(mappedBy = "memberEntity")
    private ApplyForEntity applyForEntity;
}
