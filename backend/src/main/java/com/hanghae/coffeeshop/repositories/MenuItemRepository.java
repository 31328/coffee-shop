package com.hanghae.coffeeshop.repositories;

import com.hanghae.coffeeshop.entity.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuEntity, Long> {
}
