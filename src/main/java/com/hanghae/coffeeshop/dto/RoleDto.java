package com.hanghae.coffeeshop.dto;

import java.io.Serializable;
import java.util.List;

public class RoleDto implements Serializable {
    private Long id;
    private String role;
    private List<Long> usersIds;
}
