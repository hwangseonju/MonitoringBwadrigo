package com.ssaffron.business.api.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="collect_for")
@Getter
@RequiredArgsConstructor
public class CollectForEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "collect_for_index")
    private long collectForIndex;

    @Column(name = "collect_for_type", nullable = false)
    private String collectForType;

    @Column(name = "collect_for_request_date", nullable = false)
    @CreatedDate
    private LocalDateTime collectForRequestDate;

    @Column(name = "collect_for_withdraw_date")
    @Setter
    private LocalDateTime collectForWithdrawDate;
    //수거 철회를 위한 컬럼 수거 철회신청이 있을 때 수거 신청 날로 갱신

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_index")
    private EmployeeEntity employeeEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_index")
    private MemberEntity memberEntity;

    @OneToMany(mappedBy = "collectForEntity")
    private List<PayForEntity> payForEntities = new ArrayList<>();

    public CollectForEntity(String collectForType, MemberEntity memberEntity){
        this.collectForType = collectForType;
        this.memberEntity = memberEntity;

    }
}
