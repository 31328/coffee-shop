package com.hanghae.coffeeshop.repositories;

import com.hanghae.coffeeshop.entity.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<MenuEntity, Long> { }
