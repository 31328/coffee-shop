package com.hanghae.coffeeshop.dto;

import java.io.Serializable;

import java.util.List;

public class OrderDto implements Serializable {

    private Long id;

    private Long cartId;

    private Double price;

    private String createTimeStr;

    private OrderAddressDto address;

    private List<Long> orderedItemsIdes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCreateTimeStr() {
        return createTimeStr;
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
    }

    public OrderAddressDto getAddress() {
        return address;
    }

    public void setAddress(OrderAddressDto address) {
        this.address = address;
    }

    public List<Long> getOrderedItemsIdes() {
        return orderedItemsIdes;
    }

    public void setOrderedItemsIdes(List<Long> orderedItemsIdes) {
        this.orderedItemsIdes = orderedItemsIdes;
    }
}
