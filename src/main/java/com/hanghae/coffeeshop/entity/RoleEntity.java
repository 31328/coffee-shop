package com.hanghae.coffeeshop.entity;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "roles")
public class RoleEntity implements Serializable, GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true, length = 50)
    private String role;
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "roles")
    private List<UserEntity> users;

    public RoleEntity(String role) {
        this.role = role;
    }

    public RoleEntity() {

    }

    @Override
    public String getAuthority() {
        return role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
