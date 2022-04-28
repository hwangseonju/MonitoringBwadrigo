package com.ssaffron.business.api.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(name = "employee")
@Entity
@Getter
@RequiredArgsConstructor
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private int employeeId;

    @Column(name = "employee_name", nullable = false, length = 30)
    private String employeeName;

    @Column(name = "employee_phone", nullable = false, length = 30)
    private String employeePhone;

    @Column(name = "employee_create_date")
    @CreatedDate
    private LocalDateTime employeeCreateDate;

    @Column(name = "employee_update_date")
    @LastModifiedDate
    private LocalDateTime employeeUpdateDate;

    @OneToMany(mappedBy = "employeeEntity", cascade = CascadeType.ALL)
    private List<CollectEntity> collectEntities = new ArrayList<>();
}
