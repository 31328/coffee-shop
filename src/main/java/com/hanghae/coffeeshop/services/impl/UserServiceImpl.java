package com.hanghae.coffeeshop.services.impl;

import com.hanghae.coffeeshop.converter.TempConverter;
import com.hanghae.coffeeshop.dto.RoleDto;
import com.hanghae.coffeeshop.dto.UserDto;
import com.hanghae.coffeeshop.entity.UserEntity;
import com.hanghae.coffeeshop.exceptions.InstanceUndefinedException;
import com.hanghae.coffeeshop.repositories.UserRepository;
import com.hanghae.coffeeshop.services.RoleService;
import com.hanghae.coffeeshop.services.UserService;
import jakarta.inject.Provider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final TempConverter tempConverter;
    private final RoleService roleService;
    private final Provider<AuthenticationManager> authenticationManagerProvider;

    @Autowired
    public UserServiceImpl(Provider<AuthenticationManager> authenticationManagerProvider, RoleService roleService, TempConverter tempConverter, UserRepository userRepository) {
        this.authenticationManagerProvider = authenticationManagerProvider;
        this.roleService = roleService;
        this.tempConverter = tempConverter;
        this.userRepository = userRepository;
    }


    @Override
    @Transactional(readOnly = true)
    public UserDto getUser(Long userId) {
        UserDto returnValue = null;
        Optional<UserEntity> userEntityOptional = userRepository.findById(userId);
        if (userEntityOptional.isPresent()) {
            UserEntity userEntity = userEntityOptional.get();
            returnValue = tempConverter.userEntityToDto(userEntity);
        } else {
            throw new InstanceUndefinedException(
                    "The user has not being found with id: " + userId
            );
        }
        return returnValue;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDto getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated()) {
            String currentUserName = authentication.getName();
            return getUserByEmail(currentUserName);
        } else {
            throw new InstanceUndefinedException("Invalid user");
        }

    }


    @Override
    @Transactional(readOnly = true)
    public UserDto getUserByEmail(String email) {
        Optional<UserEntity> getUserByEmailOptional = userRepository.findByEmail(email);
        if (getUserByEmailOptional.isPresent()) {
            UserEntity getUserByEmail = getUserByEmailOptional.get();
            return tempConverter.userEntityToDto(getUserByEmail);
        } else {
            throw new InstanceUndefinedException("User has not being found");
        }
    }

    @Override
    @Transactional
    public void deleteUser(Long userId) {
        getUser(userId);
        userRepository.deleteById(userId);
    }

    @Override
    @Transactional
    public void banUser(Long userId) {
        UserDto user = getUser(userId);
        user.setEnabled((byte) 0);
        userRepository.save(tempConverter.userDtoToEntity(user));
    }

    @Override
    @Transactional
    public void unbanUser(Long userId) {
        UserDto user = getUser(userId);
        user.setEnabled((byte) 1);
        userRepository.save(tempConverter.userDtoToEntity(user));
    }

    @Override
    @Transactional
    public UserDto addUser(UserDto userDto) {
        userDto.setEnabled((byte) 1);
        RoleDto roleUser = roleService.getRoleByRoleName("ROLE_USER");
        List<Long> rolesIdes = new ArrayList<>();
        rolesIdes.add(roleUser.getId());
        userDto.setRolesIdes(rolesIdes);
        UserEntity addUserEntity = userRepository.save(tempConverter.userDtoToEntity(userDto));
        return tempConverter.userEntityToDto(addUserEntity);
    }

    @Override
    @Transactional
    public UserDto updateUser(UserDto userDto, Long userId) {
        UserDto currentUserDto = getUser(userId);
        userDto.setId(currentUserDto.getId());
        UserEntity saveUserEntity = userRepository
                .save(tempConverter.userDtoToEntity(userDto));
        return tempConverter.userEntityToDto(saveUserEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDto> listAllUsers() {
        List<UserDto> returnValue = new ArrayList<>();
        List<UserEntity> userEntityList = userRepository.findAll();
        for (UserEntity userEntity : userEntityList) {
            returnValue.add(tempConverter.userEntityToDto(userEntity));
        }
        return returnValue;
    }

    @Override
    public Optional<Authentication> authenticateUser(String userName, String password) {
        // Validate inputs
        if (userName == null || userName.trim().isEmpty() ||
                password == null || password.trim().isEmpty()) {
            return Optional.empty();
        }

        // Use functional programming style
        return userRepository.findByEmail(userName)
                .map(user -> {
                    try {
                        UsernamePasswordAuthenticationToken authRequest =
                                new UsernamePasswordAuthenticationToken(userName, password);
                        return Optional.of(authenticationManagerProvider.get().authenticate(authRequest));
                    } catch (AuthenticationException e) {
                        return Optional.<Authentication>empty();
                    }
                })
                .orElse(Optional.empty());
    }


}
