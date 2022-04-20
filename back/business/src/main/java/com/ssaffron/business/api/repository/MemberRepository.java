package com.ssaffron.business.api.repository;

import com.ssaffron.business.api.entity.MemberEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Integer> {
    MemberEntity findByMemberEmail(String memberEmail);

    @EntityGraph(attributePaths = "authorities")
    Optional<MemberEntity> findOneWithAuthoritiesByMemberEmail(String memberEmail);
}
