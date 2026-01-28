package com.hanghae.coffeeshop.dto;


import java.io.Serializable;

public class MenuOrderItemDto extends OrderItemDto implements Serializable {

    private String menuName;

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
