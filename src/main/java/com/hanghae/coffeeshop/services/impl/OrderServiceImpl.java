package com.hanghae.coffeeshop.services.impl;

import com.hanghae.coffeeshop.converter.TempConverter;
import com.hanghae.coffeeshop.dto.OrderDto;
import com.hanghae.coffeeshop.entity.OrderEntity;
import com.hanghae.coffeeshop.exceptions.InstanceUndefinedException;
import com.hanghae.coffeeshop.repositories.OrderRepository;
import com.hanghae.coffeeshop.services.MenuService;
import com.hanghae.coffeeshop.services.OrderService;
import com.hanghae.coffeeshop.services.UserService;
import jakarta.inject.Provider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final TempConverter tempConverter;
    private final UserService userService;
    private final Provider<MenuService> menuServiceProvider;

    @Override
    @Transactional
    public OrderDto addOrder(OrderDto orderDto) {
        menuServiceProvider.get().getMenu(orderDto.getMenuId());
        userService.getUser(orderDto.getUserId());
        OrderEntity storedOrder = orderRepository.save(tempConverter.orderDtoToEntity(orderDto));
        return tempConverter.orderEntityToDto(storedOrder);
    }

    @Override
    @Transactional
    public void deleteOrder(Long orderId) {
        getOrderById(orderId);
        orderRepository.deleteById(orderId);
    }

    @Override
    @Transactional
    public void deleteAllByMenuId(Long menuId) {
        orderRepository.deleteAllByMenuId(menuId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderDto> listAll() {
        List<OrderDto> returnValue = new ArrayList<>();
        List<OrderEntity> allOrders = orderRepository.findAll();
        if(allOrders.isEmpty()){
            return returnValue;
        }
        for (Iterator<OrderEntity> iterator = allOrders.iterator(); iterator.hasNext(); ) {
            OrderEntity orderEntity = iterator.next();
            returnValue.add(tempConverter.orderEntityToDto(orderEntity));
        }

        return returnValue;
    }

    @Override
    @Transactional(readOnly = true)
    public OrderDto getOrderById(Long orderId) {
        OrderDto returnValue = null;
        Optional<OrderEntity> orderEntityOptional = orderRepository.findById(orderId);
        if(orderEntityOptional.isPresent()){
            System.out.println("Order found");
            returnValue = tempConverter.orderEntityToDto(orderEntityOptional.get());
        } else{
            System.out.println("Order not found");
            throw new InstanceUndefinedException("Order with id:" + orderId + " does not exist");
        }
        return returnValue;
    }

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, TempConverter tempConverter, UserService userService, Provider<MenuService> menuServiceProvider) {
        this.orderRepository = orderRepository;
        this.tempConverter = tempConverter;
        this.userService = userService;
        this.menuServiceProvider = menuServiceProvider;
    }



}
