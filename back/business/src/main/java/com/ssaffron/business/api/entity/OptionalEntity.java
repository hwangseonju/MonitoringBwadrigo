package com.ssaffron.business.api.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "optional")
public class OptionalEntity {

    @Id
    @Column(name = "optional_idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int optionalIdx;

    @Column(name = "wash_cnt")
    private int washCnt;

    @Column(name = "bedding_cnt")
    private int beddingCnt;

    @Column(name = "delivery_cnt")
    private int deliveryCnt;

    @Column(name = "cleaning_cnt")
    private int cleaningCnt;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_idx")
    private UserEntity userIdx;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_idx")
    private PaymentEntity paymentIdx;

}
