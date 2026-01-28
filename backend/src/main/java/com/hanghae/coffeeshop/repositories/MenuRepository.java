package com.hanghae.coffeeshop.repositories;

import com.hanghae.coffeeshop.entity.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface MenuRepository extends JpaRepository<MenuEntity, Long> {
    Optional<MenuEntity> findByName(String name);
    @Modifying
    @Query(value = "delete from product_menus where menu_id = :menuId", nativeQuery = true)
    void clearMenu(@Param("menuId") Long menuId);

}
