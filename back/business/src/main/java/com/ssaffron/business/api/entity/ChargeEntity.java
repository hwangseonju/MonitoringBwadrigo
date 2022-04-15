package com.ssaffron.business.api.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="charge")
@RequiredArgsConstructor
@Getter
public class ChargeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "charge_idx")
    private long chargeIdx;

    @Column(name = "request_cnt", nullable = false)
    private long requestCnt;

    @Column(name = "responsed_at")
    private LocalDateTime responsedAt;

    @Column(name = "pick_date")
    private LocalDateTime pickDate;

//    @OneToOne(mappedBy = "optionalIdx")
//    private OptionalEntity optionalEntity;
//    관계를 끊어도 상관없고
//    관계가 있어도 charge에서는 optional을 참조할 필요가 없음

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "collection_idx")
    private CollectionEntity collectionEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_idx")
    private UserEntity userEntity;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fee_idx")
    private FeeEntity feeEntity;
}
