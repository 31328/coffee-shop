package com.hanghae.coffeeshop.entity.embedded;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
@Embeddable
public class OrderAddressValue implements Serializable {
    @Column(nullable = false, length = 100)
    private String city;
    @Column(nullable = false, length = 100)
    private String address;
    @Column(nullable = false, length = 100)
    private String postalCode;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
