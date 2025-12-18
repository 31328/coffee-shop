package com.hanghae.coffeeshop.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public class UserDto implements Serializable {

    private Long id;
    @NotEmpty
    @Size(min = 5, max = 20)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private Integer points;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public UserDto(String password, Integer points) {
        this.password = password;
        this.points = points;
    }

    public UserDto() {
    }
}
