package com.ssaffron.business.api.repository;

import com.ssaffron.business.api.entity.ApplyForEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApplyForRepository extends JpaRepository<ApplyForEntity, Integer> {
    ApplyForEntity findByMemberEntity_MemberIndex(int index);
}
