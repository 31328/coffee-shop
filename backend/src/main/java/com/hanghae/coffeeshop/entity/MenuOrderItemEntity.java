package com.hanghae.coffeeshop.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

import java.io.Serializable;
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class MenuOrderItemEntity extends OrderItemEntity implements Serializable {
    @Column(name = "menu_name", nullable = false)
    private String menuName;
    @Column(name = "menu_price", nullable = false)
    private Double menuPrice;

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Double getMenuPrice() {
        return menuPrice;
    }

    public void setMenuPrice(Double menuPrice) {
        this.menuPrice = menuPrice;
    }
}
