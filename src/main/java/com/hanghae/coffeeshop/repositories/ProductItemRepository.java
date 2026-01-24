package com.hanghae.coffeeshop.repositories;

import com.hanghae.coffeeshop.entity.ProductItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductItemRepository extends JpaRepository<ProductItemEntity, Long> {

}
