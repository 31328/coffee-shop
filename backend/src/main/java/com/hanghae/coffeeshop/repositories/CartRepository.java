package com.hanghae.coffeeshop.repositories;

import com.hanghae.coffeeshop.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository <CartEntity, Long>{

}
