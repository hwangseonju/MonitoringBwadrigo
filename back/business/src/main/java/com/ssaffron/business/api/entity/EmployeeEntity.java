package com.ssaffron.business.api.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "employee")
@Entity
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_idx")
    private int employeeIdx;

    @Column(name = "employee_name", nullable = false, length = 30)
    private String employeeName;

    @Column(name = "employee_phone", nullable = false, length = 30)
    private String employeePhone;

    @OneToMany(mappedBy = "employeeEntity", cascade = CascadeType.ALL)
    private List<CollectionEntity> collectionEntities = new ArrayList<>();
}
