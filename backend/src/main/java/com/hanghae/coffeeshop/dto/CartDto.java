package com.hanghae.coffeeshop.dto;

import java.io.Serializable;
import java.util.List;

public class CartDto implements Serializable {

    private Long id;
    private List<Long> cartItemsIds;
    private Double cartPrice;
    private Long customerId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Long> getCartItemsIds() {
        return cartItemsIds;
    }

    public void setCartItemsIds(List<Long> cartItemsIds) {
        this.cartItemsIds = cartItemsIds;
    }

    public Double getCartPrice() {
        return cartPrice;
    }

    public void setCartPrice(Double cartPrice) {
        this.cartPrice = cartPrice;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
