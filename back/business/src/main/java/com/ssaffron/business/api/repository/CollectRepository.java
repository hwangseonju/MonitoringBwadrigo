package com.ssaffron.business.api.repository;

import com.ssaffron.business.api.entity.CollectEntity;
import com.ssaffron.business.api.entity.EmployeeEntity;
import com.ssaffron.business.api.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollectRepository extends JpaRepository<CollectEntity, Long> {
    List<CollectEntity> findAllByMemberEntity(MemberEntity memberEntity);
    List<CollectEntity> findAllByMemberEntityOrderByCollectRequestDateDesc(MemberEntity memberEntity);
    List<CollectEntity> findAllByMemberEntityAndCollectWithdrawDateIsNullAndEmployeeEntityIsNull(MemberEntity memberEntity);
    List<CollectEntity> findAllByEmployeeEntity(EmployeeEntity employeeEntity);
}
