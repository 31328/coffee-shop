package com.hanghae.coffeeshop.repositories;

import com.hanghae.coffeeshop.entity.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MenuRepository extends JpaRepository<MenuEntity, Long> {
    Optional<MenuEntity> findByName(String name);
}
