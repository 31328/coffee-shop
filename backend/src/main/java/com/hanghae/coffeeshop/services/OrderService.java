package com.hanghae.coffeeshop.services;

import com.hanghae.coffeeshop.dto.OrderDto;

import java.util.List;

public interface OrderService {
    OrderDto addOrder(OrderDto orderDto);

    void deleteOrder(Long orderId);

    void deleteAllByMenuId(Long menuId);

    List<OrderDto> listAll();

    OrderDto getOrderById(Long orderId);

    // Name of the drink, price and adding points ALSO the item quantity
}
