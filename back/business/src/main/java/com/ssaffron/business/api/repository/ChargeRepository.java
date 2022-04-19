package com.ssaffron.business.api.repository;

import com.ssaffron.business.api.entity.PayForEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChargeRepository extends JpaRepository<PayForEntity, Long> {
}
