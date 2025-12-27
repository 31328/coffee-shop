package com.hanghae.coffeeshop.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import java.io.Serializable;
@Entity
@Table(name = "cart_items")
public class CartItemEntity implements Serializable {
    private Long id;
    private Integer quantity;
    private Double price;
    @OneToOne
    private ProductEntity product;

}
