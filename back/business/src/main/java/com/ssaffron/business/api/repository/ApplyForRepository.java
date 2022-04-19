package com.ssaffron.business.api.repository;

import com.ssaffron.business.api.entity.ApplyForEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplyForRepository extends JpaRepository<ApplyForEntity, Integer> {
}
