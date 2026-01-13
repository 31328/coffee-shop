package com.hanghae.coffeeshop.services.impl;

import com.hanghae.coffeeshop.converter.TempConverter;
import com.hanghae.coffeeshop.dto.RoleDto;
import com.hanghae.coffeeshop.entity.RoleEntity;
import com.hanghae.coffeeshop.exceptions.InstanceUndefinedException;
import com.hanghae.coffeeshop.repositories.RoleRepository;
import com.hanghae.coffeeshop.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final TempConverter tempConverter;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository, TempConverter tempConverter) {
        this.roleRepository = roleRepository;
        this.tempConverter = tempConverter;
    }

    @Override
    @Transactional(readOnly = true)
    public RoleDto getRoleByRoleName(String role) {
       RoleEntity roleEntity = roleRepository.findByRole(role)
               .orElseThrow(() -> new InstanceUndefinedException("Role has not being found"));
        return tempConverter.roleEntityToDto(roleEntity);
    }
}
