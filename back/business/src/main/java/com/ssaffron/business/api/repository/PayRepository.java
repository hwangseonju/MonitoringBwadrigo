package com.ssaffron.business.api.repository;

import com.ssaffron.business.api.entity.MemberEntity;
import com.ssaffron.business.api.entity.PayEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PayRepository extends JpaRepository<PayEntity, Long> {
    List<PayEntity> findAllByMemberEntity(MemberEntity memberEntity);
}
