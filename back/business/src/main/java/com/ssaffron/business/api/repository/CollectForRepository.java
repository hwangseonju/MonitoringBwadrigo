package com.ssaffron.business.api.repository;

import com.ssaffron.business.api.entity.CollectForEntity;
import com.ssaffron.business.api.entity.EmployeeEntity;
import com.ssaffron.business.api.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollectForRepository extends JpaRepository<CollectForEntity, Long> {
    List<CollectForEntity> findAllByMemberEntity(MemberEntity memberEntity);
    List<CollectForEntity> findAllByEmployeeEntity(EmployeeEntity employeeEntity);
}
