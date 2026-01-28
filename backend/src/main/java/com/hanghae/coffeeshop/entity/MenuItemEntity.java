package com.hanghae.coffeeshop.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("Menu")
public class MenuItemEntity extends CartItemEntity implements Serializable {
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "menu_id")
    private MenuEntity menu;

    public MenuEntity getMenu() {
        return menu;
    }

    public void setMenu(MenuEntity menu) {
        this.menu = menu;
    }
}
