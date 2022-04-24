package com.ssaffron.business.api.repository;

import com.ssaffron.business.api.entity.ApplyForEntity;
import com.ssaffron.business.api.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface ApplyForRepository extends JpaRepository<ApplyForEntity, Integer> {
    Optional<ApplyForEntity> findByMemberEntityAndApplyForDeliveryCountIsNotNull(MemberEntity memberEntity);
    Optional<ApplyForEntity> findByMemberEntity(MemberEntity memberIndex);
    ApplyForEntity findByMemberEntity_MemberIndex(int index);
}

