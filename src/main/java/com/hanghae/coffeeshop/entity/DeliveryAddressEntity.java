package com.hanghae.coffeeshop.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "delivery_addresses")
public class DeliveryAddressEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100)
    private String city;
    @Column(nullable = false, length = 100)
    private String address;
    @Column(nullable = false, length = 100)
    private String postalCode;
    @OneToOne(fetch = FetchType.EAGER, mappedBy = "address")
    private CustomerEntity customer;

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
