package com.hanghae.coffeeshop.repositories;

import com.hanghae.coffeeshop.entity.DeliveryAddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryAddressRepository extends JpaRepository <DeliveryAddressEntity, Long>{
}
