package com.hanghae.coffeeshop.dto;

import java.io.Serializable;

public class MenuItemDto extends CartItemDto implements Serializable {
    private Long menuId;

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }
}
