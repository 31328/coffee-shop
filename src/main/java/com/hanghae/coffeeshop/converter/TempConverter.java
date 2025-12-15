package com.hanghae.coffeeshop.converter;

import com.hanghae.coffeeshop.dto.MenuDto;
import com.hanghae.coffeeshop.dto.OrderDto;
import com.hanghae.coffeeshop.dto.PointTransactionDto;
import com.hanghae.coffeeshop.dto.UserDto;
import com.hanghae.coffeeshop.entity.MenuEntity;
import com.hanghae.coffeeshop.entity.OrderEntity;
import com.hanghae.coffeeshop.entity.PointTransactionEntity;
import com.hanghae.coffeeshop.entity.UserEntity;
import com.hanghae.coffeeshop.enumerated.TransactionType;
import com.hanghae.coffeeshop.repositories.MenuRepository;
import com.hanghae.coffeeshop.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.Optional;

@Component
public class TempConverter {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MenuRepository menuRepository;
   /* @Autowired
    public TempConverter(ModelMapper modelMapper, UserRepository userRepository, MenuRepository menuRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.menuRepository = menuRepository;
    }

    @Autowired
    private void Initialise(ModelMapper modelMapper, UserRepository userRepository, MenuRepository menuRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.menuRepository = menuRepository;
    }*/

    public MenuDto MenuEntityToDto(MenuEntity menu) {
        return modelMapper.map(menu, MenuDto.class);
    }

    public MenuEntity MenuDtoToEntity(MenuDto menu) {
        return modelMapper.map(menu, MenuEntity.class);
    }

    public UserDto userEntityToDto(UserEntity userEntity) {
        return modelMapper.map(userEntity, UserDto.class);
    }

    public UserEntity userDtoToEntity(UserDto userDto) {
        return modelMapper.map(userDto, UserEntity.class);
    }

    public PointTransactionDto pointsEntityToDto(PointTransactionEntity pointTransactionEntity) {
        PointTransactionDto returnValue = modelMapper.map(pointTransactionEntity, PointTransactionDto.class);
        Optional<UserEntity> userOptional = Optional.ofNullable(pointTransactionEntity.getUser());
        if (userOptional.isPresent()) {
            UserEntity userEntity = userOptional.get();
            returnValue.setUserId(userEntity.getId());
        }

        Optional<TransactionType> typeOptional = Optional.ofNullable(pointTransactionEntity.getType());
        if (typeOptional.isPresent()) {
            TransactionType transactionType = typeOptional.get();
            String typeStr = transactionType.name();
            returnValue.setType(typeStr);
        }
        return returnValue;
    }

    public PointTransactionEntity pointsDtoToEntity(PointTransactionDto pointTransactionDto) {
        PointTransactionEntity returnValue = modelMapper.map(pointTransactionDto, PointTransactionEntity.class);
        Optional<Long> userIdOptional = Optional.ofNullable(pointTransactionDto.getUserId());
        if (userIdOptional.isPresent()) {
            Long userId = userIdOptional.get();
            Optional<UserEntity> userOptional = userRepository.findById(userId);
            if (userOptional.isPresent()) {
                UserEntity userEntity = userOptional.get();
                returnValue.setUser(userEntity);
            }
        }

        Optional<String> typeOptional = Optional.ofNullable(pointTransactionDto.getType());
        if (typeOptional.isPresent()) {
            try {
                TransactionType transactionType = TransactionType.valueOf(typeOptional.get());
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Invalid transaction type: " + typeOptional.get());
            }
        }
        return returnValue;
    }

    public OrderDto orderEntityToDto(OrderEntity orderEntity) {
        OrderDto returnValue = modelMapper.map(orderEntity, OrderDto.class);
        Optional<UserEntity> userOptional = Optional.ofNullable(orderEntity.getUser());
        if (userOptional.isPresent()) {
            UserEntity userEntity = userOptional.get();
            returnValue.setUserId(userEntity.getId());
        }
        Optional<MenuEntity> menuOptional = Optional.ofNullable(orderEntity.getMenu());
        if (menuOptional.isPresent()) {
            MenuEntity menuEntity = menuOptional.get();
            returnValue.setMenuId(menuEntity.getId());
        }
        return returnValue;
    }

    public OrderEntity orderDtoToEntity(OrderDto orderDto) {
        OrderEntity returnValue = modelMapper.map(orderDto, OrderEntity.class);
        Optional<Long> userIdOptional = Optional.ofNullable(orderDto.getUserId());
        if (userIdOptional.isPresent()) {
            Long userId = userIdOptional.get();
            Optional<UserEntity> userOptional = userRepository.findById(userId);
            if (userOptional.isPresent()) {
                UserEntity userEntity = userOptional.get();
                returnValue.setUser(userEntity);
            }
        }
        Optional<Long> menuIdOptional = Optional.ofNullable(orderDto.getMenuId());
        if (menuIdOptional.isPresent()) {
            Long menuId = menuIdOptional.get();
            Optional<MenuEntity> menuOptional = menuRepository.findById(menuId);
            if (menuOptional.isPresent()) {
                MenuEntity menuEntity = menuOptional.get();
                returnValue.setMenu(menuEntity);
            }
        }
        return returnValue;
    }


    //   private Long userId; <-->  private UserEntity user;
    //      private Long menuId; <-->     private MenuEntity menu;
}
