package com.hanghae.coffeeshop.utils;

import com.hanghae.coffeeshop.dto.CustomerDto;
import com.hanghae.coffeeshop.dto.DeliveryAddressDto;
import com.hanghae.coffeeshop.dto.UserDto;
import jakarta.validation.Valid;

import java.io.Serializable;

public class RegistrationForm implements Serializable {
    @Valid
    private UserDto user;
    @Valid
    private CustomerDto customer;
    @Valid
    private DeliveryAddressDto address;

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public CustomerDto getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDto customer) {
        this.customer = customer;
    }

    public DeliveryAddressDto getAddress() {
        return address;
    }

    public void setAddress(DeliveryAddressDto address) {
        this.address = address;
    }
}
