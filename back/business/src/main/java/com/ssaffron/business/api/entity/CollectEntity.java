package com.ssaffron.business.api.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="collect")
@Getter
@RequiredArgsConstructor
public class CollectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "collect_id")
    private long collectId;

    @Column(name = "collect_type", nullable = false)
    private String collectType;

    @Column(name = "collect_request_date", nullable = false)
    @CreatedDate
    private LocalDateTime collectRequestDate;

    @Column(name = "collect_withdraw_date")
    @Setter
    private LocalDateTime collectWithdrawDate;
    //수거 철회를 위한 컬럼 수거 철회신청이 있을 때 수거 신청 날로 갱신

    @Column(name = "collect_create_date")
    @CreatedDate
    private LocalDateTime collectCreateDate;

    @Column(name = "collect_update_date")
    @LastModifiedDate
    private LocalDateTime collectUpdateDate;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private EmployeeEntity employeeEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;

    @OneToMany(mappedBy = "collectEntity")
    private List<PayEntity> payEntities = new ArrayList<>();

    public CollectEntity(String collectType, LocalDateTime collectRequestDate, MemberEntity memberEntity){
        this.collectType = collectType;
        this.collectRequestDate = collectRequestDate;
        this.memberEntity = memberEntity;

    }
}
