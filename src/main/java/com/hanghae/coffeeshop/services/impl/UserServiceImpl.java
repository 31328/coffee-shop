package com.hanghae.coffeeshop.services.impl;

import com.hanghae.coffeeshop.converter.TempConverter;
import com.hanghae.coffeeshop.dto.PointTransactionDto;
import com.hanghae.coffeeshop.dto.UserDto;
import com.hanghae.coffeeshop.entity.PointTransactionEntity;
import com.hanghae.coffeeshop.entity.UserEntity;
import com.hanghae.coffeeshop.enumerated.TransactionType;
import com.hanghae.coffeeshop.exceptions.InstanceUndefinedException;
import com.hanghae.coffeeshop.repositories.PointTransactionRepository;
import com.hanghae.coffeeshop.repositories.UserRepository;
import com.hanghae.coffeeshop.services.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TempConverter tempConverter;
    @Autowired
    private PointTransactionRepository pointTransactionRepository;

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
    @Transactional
    public void deleteUser(Long userId) {
        getUser(userId);
        userRepository.deleteById(userId);
    }

    @Override
    @Transactional
    public UserDto addUser(UserDto userDto) {
        UserEntity addUserEntity = userRepository.save(tempConverter.userDtoToEntity(userDto));
        return tempConverter.userEntityToDto(addUserEntity);
    }

    @Override
    @Transactional
    public UserDto updateUser(UserDto userDto, Long userId) {
        UserDto currentUserDto = getUser(userId);
        userDto.setId(currentUserDto.getId());
        UserEntity saveUserEntity = userRepository.saveAndFlush(tempConverter.userDtoToEntity(userDto));
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
}
