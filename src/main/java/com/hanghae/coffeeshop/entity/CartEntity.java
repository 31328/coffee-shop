package com.hanghae.coffeeshop.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "cart")
public class CartEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "cart", orphanRemoval = true)
    private List<CartItemEntity> cartItems;
    @Column(nullable = false)
    private Double cartPrice;
    @OneToOne(fetch = FetchType.EAGER, mappedBy = "cart")
    private CustomerEntity customer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    public Double getCartPrice() {
        return cartPrice;
    }

    public void setCartPrice(Double cartPrice) {
        this.cartPrice = cartPrice;
    }

    public List<CartItemEntity> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItemEntity> cartItems) {
        this.cartItems = cartItems;
    }
}
