package com.ssaffron.business.api.repository;

import com.ssaffron.business.api.entity.MemberEntity;
import com.ssaffron.business.api.entity.PayForEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PayForRepository extends JpaRepository<PayForEntity, Long> {
    List<PayForEntity> findAllByMemberEntity(MemberEntity memberEntity);
}
