package com.ssaffron.business.api.repository;

import com.ssaffron.business.api.entity.OptionalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionalRepository extends JpaRepository<OptionalEntity, Integer> {
}
