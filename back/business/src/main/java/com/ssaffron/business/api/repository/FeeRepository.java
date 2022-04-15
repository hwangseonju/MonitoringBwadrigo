package com.ssaffron.business.api.repository;

import com.ssaffron.business.api.entity.FeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeeRepository extends JpaRepository<FeeEntity, Integer> {
}
