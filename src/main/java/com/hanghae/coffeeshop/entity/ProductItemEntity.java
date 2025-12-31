package com.hanghae.coffeeshop.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("Product")
public class ProductItemEntity extends CartItemEntity implements Serializable {
    @JoinColumn(name = "product_id")
    @OneToOne(fetch = FetchType.EAGER)
    private ProductEntity product;

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }
}
