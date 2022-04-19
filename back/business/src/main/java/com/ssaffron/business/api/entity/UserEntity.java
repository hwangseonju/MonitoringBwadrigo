package com.ssaffron.business.api.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@RequiredArgsConstructor
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_idx")
    private int userIdx;

    @Column(name = "user_email", nullable = false, length = 30)
    private String userEmail;

    @Column(name = "user_pwd", nullable = false, length = 20)
    private String userPwd;

    @Column(name = "user_name", nullable = false, length = 30)
    private String userName;

    @Column(name = "user_phone", nullable = false, length = 30)
    private String userPhone;

    @Column(name = "user_address", length = 100)
    private String userAddress;

    @Column(name = "user_gender")
    private boolean userGender;

    @Column(name = "user_age")
    private int userAge;

    @OneToMany(mappedBy = "userEntity",cascade = CascadeType.ALL)
    private List<PayForEntity> chargeEntities = new ArrayList<>();

    @OneToMany(mappedBy = "userEntity",cascade = CascadeType.ALL)
    private List<CollectionEntity> collectionEntities = new ArrayList<>();
}
