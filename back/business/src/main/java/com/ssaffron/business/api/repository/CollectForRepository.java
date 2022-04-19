package com.ssaffron.business.api.repository;

import com.ssaffron.business.api.entity.CollectForEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollectForRepository extends JpaRepository<CollectForEntity, Long> {
}
