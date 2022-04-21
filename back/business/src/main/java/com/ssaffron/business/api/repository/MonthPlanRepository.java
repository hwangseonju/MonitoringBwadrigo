package com.ssaffron.business.api.repository;

import com.ssaffron.business.api.entity.MonthPlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface MonthPlanRepository extends JpaRepository<MonthPlanEntity, Integer> {
    MonthPlanEntity findByMonthPlanIndex(int monthPlanIndex);
}
