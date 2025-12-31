package com.hanghae.coffeeshop.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.List;

public class UserDto implements Serializable {

    private Long id;
    @NotEmpty
    @Size(max = 40, min = 3)
    private String firstName;
    @NotEmpty
    @Size(max = 40, min = 3)
    private String lastName;
    @NotEmpty
    @Size(max = 100)
    @Email(regexp = ".+[@].+[\\.].+")
    private String email;
    @NotEmpty
    @Size(min = 8, max = 20)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Byte enabled;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    List<Long> rolesIdes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Byte getEnabled() {
        return enabled;
    }

    public void setEnabled(Byte enabled) {
        this.enabled = enabled;
    }

    public List<Long> getRolesIdes() {
        return rolesIdes;
    }

    public void setRolesIdes(List<Long> rolesIdes) {
        this.rolesIdes = rolesIdes;
    }
}
