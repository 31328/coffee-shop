package com.hanghae.coffeeshop.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;

import java.io.Serializable;
import java.util.List;

public class MenuDto implements Serializable {

    private Long id;
    @NotEmpty
    @Size(min = 4, max = 100)
    private String name;
    //@NotNull
    //@DecimalMax(value = "1000")
    //@DecimalMin(value = "0")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Double price;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer points;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<Long> productIdes;


    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public List<Long> getProductIdes() {
        return productIdes;
    }

    public void setProductIdes(List<Long> productIdes) {
        this.productIdes = productIdes;
    }

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
