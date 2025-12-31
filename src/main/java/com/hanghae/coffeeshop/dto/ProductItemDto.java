package com.hanghae.coffeeshop.dto;

import java.io.Serializable;

public class ProductItemDto extends CartItemDto implements Serializable {
    private Long productId;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
