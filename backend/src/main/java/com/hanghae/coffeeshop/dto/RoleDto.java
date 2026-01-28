package com.hanghae.coffeeshop.dto;

import java.io.Serializable;
import java.util.List;

public class RoleDto implements Serializable {
    private Long id;
    private String role;
    private List<Long> usersIds;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Long> getUsersIds() {
        return usersIds;
    }

    public void setUsersIds(List<Long> usersIds) {
        this.usersIds = usersIds;
    }
}
