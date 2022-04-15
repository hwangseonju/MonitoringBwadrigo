package com.ssaffron.business.api.entity;

import lombok.Cleanup;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@RequiredArgsConstructor
@Table(name = "payment")
public class PaymentEntity {

    @Id
    @Column(name = "payment_idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int paymentIdx;
    @Column(name = "payment_name", nullable = false, length = 30)
    String paymentName;
    @Column(name = "payment_price", nullable = false)
    int paymentPrice;
    @Column(name = "wash_cnt")
    int washCnt;
    @Column(name = "cleaning_cnt")
    int cleaningCnt;
    @Column(name = "shirt_cnt")
    int shirtCnt;
    @Column(name = "bedding_cnt")
    int beddingCnt;
    @Column(name = "delivery_cnt")
    int deliveryCnt;
}
