package com.hanghae.coffeeshop.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.List;

public class CategoryDto implements Serializable {
    private Long id;
    @NotEmpty
    @Size(min = 4, max = 50)
    private String name;
    private List<Long> productsIdes;

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

    public List<Long> getProductsIdes() {
        return productsIdes;
    }

    public void setProductsIdes(List<Long> productsIdes) {
        this.productsIdes = productsIdes;
    }
}
