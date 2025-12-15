package com.hanghae.coffeeshop.repositories;

import com.hanghae.coffeeshop.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

}
