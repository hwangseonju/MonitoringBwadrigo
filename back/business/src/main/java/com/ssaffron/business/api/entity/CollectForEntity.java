package com.ssaffron.business.api.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

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
    private LocalDateTime collectForRequestDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_index")
    private EmployeeEntity employeeEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_index")
    private MemberEntity memberEntity;

    @OneToMany(mappedBy = "collectForEntity")
    private List<PayForEntity> payForEntities = new ArrayList<>();
}
