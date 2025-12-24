package com.hanghae.coffeeshop.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hanghae.coffeeshop.entity.MenuEntity;
import jakarta.validation.constraints.*;

import java.io.Serializable;
import java.util.List;

public class ProductDto implements Serializable {
    // validation rules
    private Long id;
    @NotEmpty
    @Size(min = 4, max = 50)
    private String name;
    @NotNull
    @PositiveOrZero
    @DecimalMax(value = "1000")
    @DecimalMin(value = "0")
    private Double price;
    @NotNull
    @PositiveOrZero
    private Integer points;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<Long> menusIdes;
    @NotEmpty
    @Size(min = 4, max = 255)
    private String imageUrl;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public List<Long> getMenusIdes() {
        return menusIdes;
    }

    public void setMenusIdes(List<Long> menusIdes) {
        this.menusIdes = menusIdes;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
