package com.ssaffron.business.api.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserEntity {

    @Id
    private int userIdx;
}
