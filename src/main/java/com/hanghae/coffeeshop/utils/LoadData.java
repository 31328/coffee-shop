package com.hanghae.coffeeshop.utils;

import com.hanghae.coffeeshop.entity.RoleEntity;
import com.hanghae.coffeeshop.entity.UserEntity;
import com.hanghae.coffeeshop.repositories.RoleRepository;
import com.hanghae.coffeeshop.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LoadData {


    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public LoadData(RoleRepository roleRepository, UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        addRolesData();
        addAdminData();
    }

    public void addRolesData() {
        RoleEntity adminRole = roleRepository.findByRole("ROLE_ADMIN").orElse(null);
        RoleEntity userRole = roleRepository.findByRole("ROLE_USER").orElse(null);
        if (adminRole == null) {
            roleRepository.save(new RoleEntity("ROLE_ADMIN"));
        }
        if (userRole == null) {
            roleRepository.save(new RoleEntity("ROLE_USER"));
        }
    }

    public void addAdminData() {
        RoleEntity adminRole = roleRepository.findByRole("ROLE_ADMIN").orElse(null);
        if (adminRole != null) {
            try {
                List<RoleEntity> roles = new ArrayList<>();
                UserEntity userEntity = new UserEntity((byte) 1, "Admin123", "user@hanghae.coffeeshop.kr", "Dipas", "John");
                userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
                roles.add(adminRole);
                userEntity.setRoles(roles);
                userRepository.save(userEntity);
            } catch (Exception ex) {
                System.out.println("Admin already added in database");
            }
        }
    }


}
