package com.hanghae.coffeeshop.repositories;

import com.hanghae.coffeeshop.entity.PointTransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointTransactionRepository extends JpaRepository<PointTransactionEntity, Long> {
}
