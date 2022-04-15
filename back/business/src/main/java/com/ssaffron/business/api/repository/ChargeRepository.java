package com.ssaffron.business.api.repository;

import com.ssaffron.business.api.entity.ChargeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChargeRepository extends JpaRepository<ChargeEntity, Long> {
}
