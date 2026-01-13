package com.hanghae.coffeeshop.services.impl;

import com.hanghae.coffeeshop.converter.TempConverter;
import com.hanghae.coffeeshop.dto.CartDto;
import com.hanghae.coffeeshop.entity.CartEntity;
import com.hanghae.coffeeshop.repositories.CartRepository;
import com.hanghae.coffeeshop.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final TempConverter tempConverter;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository, TempConverter tempConverter) {
        this.cartRepository = cartRepository;
        this.tempConverter = tempConverter;
    }

    @Override
    @Transactional
    public CartDto saveCart(CartDto cartDto) {
        cartDto.setCartPrice(0.00);
        CartEntity cart = cartRepository.save(tempConverter.cartDtoToEntity(cartDto));
        return tempConverter.cartEntityToDto(cart);
    }


}
