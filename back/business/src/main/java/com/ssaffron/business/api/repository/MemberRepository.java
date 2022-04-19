package com.ssaffron.business.api.repository;

import com.ssaffron.business.api.entity.MemberEntity;
import com.ssaffron.business.api.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Integer> {
}
