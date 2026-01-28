package com.hanghae.coffeeshop.dto;


import java.io.Serializable;

public class ProductOrderItemDto extends OrderItemDto implements Serializable {

    private String productName;

    private Double productPrice;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }
}
