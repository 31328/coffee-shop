package com.hanghae.coffeeshop.dto;


import jakarta.validation.constraints.*;

import java.io.Serializable;

public class MenuDto implements Serializable {

    private Long id;
    @NotEmpty
    @Size(min = 4, max = 100)
    private String name;
    @NotNull
    @DecimalMax(value = "1000")
    @DecimalMin(value = "0")
    private Double price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
