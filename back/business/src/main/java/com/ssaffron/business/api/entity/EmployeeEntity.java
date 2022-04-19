package com.ssaffron.business.api.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "employee")
@Entity
@Getter
@RequiredArgsConstructor
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_index")
    private int employeeIndex;

    @Column(name = "employee_name", nullable = false, length = 30)
    private String employeeName;

    @Column(name = "employee_phone", nullable = false, length = 30)
    private String employeePhone;

    @OneToMany(mappedBy = "employeeEntity", cascade = CascadeType.ALL)
    private List<CollectForEntity> collectforEntities = new ArrayList<>();
}
