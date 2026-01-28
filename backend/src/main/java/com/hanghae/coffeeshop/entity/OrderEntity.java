package com.hanghae.coffeeshop.entity;

import com.hanghae.coffeeshop.entity.embedded.OrderAddressValue;
import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "orders")
public class OrderEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cart_id", nullable = false, updatable = false)
    private CartEntity cart;
    @Column(nullable = false)
    private Double price;
    @Column(nullable = false, name = "create_time")
    private Timestamp createTime;
    @Embedded
    private OrderAddressValue address;
    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "order")
    private List<OrderItemEntity> orderedItems;

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public OrderAddressValue getAddress() {
        return address;
    }

    public void setAddress(OrderAddressValue address) {
        this.address = address;
    }

    public List<OrderItemEntity> getOrderedItems() {
        return orderedItems;
    }

    public void setOrderedItems(List<OrderItemEntity> orderedItems) {
        this.orderedItems = orderedItems;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CartEntity getCart() {
        return cart;
    }

    public void setCart(CartEntity cart) {
        this.cart = cart;
    }
}



