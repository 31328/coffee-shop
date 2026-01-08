package com.hanghae.coffeeshop.dto;

import jakarta.persistence.Column;

import java.io.Serializable;

public class OrderAddressDto implements Serializable {

    private String city;

    private String address;

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
