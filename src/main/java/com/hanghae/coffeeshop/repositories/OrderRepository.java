package com.hanghae.coffeeshop.repositories;

import com.hanghae.coffeeshop.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    @Modifying
    @Query(value = "delete from orders where menu_id = :menuId", nativeQuery = true)
    void deleteAllByMenuId(@Param("menuId") Long menuId);
}
