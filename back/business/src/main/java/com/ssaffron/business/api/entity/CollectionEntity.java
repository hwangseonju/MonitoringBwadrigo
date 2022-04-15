package com.ssaffron.business.api.entity;

import org.apache.catalina.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="collection")
public class CollectionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "collection_idx")
    private long collectionIdx;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "requested_at", nullable = false)
    private LocalDateTime requestedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_idx")
    private EmployeeEntity employeeIdx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_idx")
    private UserEntity userIdx;

    @OneToMany(mappedBy = "chargeIdx")
    private List<ChargeEntity> chargeEntity;
}
