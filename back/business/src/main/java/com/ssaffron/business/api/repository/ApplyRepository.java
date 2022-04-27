package com.ssaffron.business.api.repository;

import com.ssaffron.business.api.entity.ApplyEntity;
import com.ssaffron.business.api.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ApplyRepository extends JpaRepository<ApplyEntity, Integer> {
    Optional<ApplyEntity> findByMemberEntityAndApplyDeliveryCountIsNotNull(MemberEntity memberEntity);
    Optional<ApplyEntity> findByMemberEntity(MemberEntity memberIndex);
    ApplyEntity findByMemberEntity_MemberIndex(int index);
}

