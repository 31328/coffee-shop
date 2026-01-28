package com.hanghae.coffeeshop.services.impl;

import com.hanghae.coffeeshop.converter.TempConverter;
import com.hanghae.coffeeshop.dto.DeliveryAddressDto;
import com.hanghae.coffeeshop.entity.DeliveryAddressEntity;
import com.hanghae.coffeeshop.repositories.DeliveryAddressRepository;
import com.hanghae.coffeeshop.services.DeliveryAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeliveryAddressServiceImpl implements DeliveryAddressService {
    private final TempConverter tempConverter;
    private final DeliveryAddressRepository deliveryAddressRepository;


    @Autowired
    public DeliveryAddressServiceImpl(TempConverter tempConverter, DeliveryAddressRepository deliveryAddressRepository) {
        this.tempConverter = tempConverter;
        this.deliveryAddressRepository = deliveryAddressRepository;
    }

    @Override
    @Transactional
    public DeliveryAddressDto addAddress(DeliveryAddressDto addressDto) {
        DeliveryAddressEntity storedAddress = deliveryAddressRepository.save(tempConverter.deliveryAddressDtoToEntity(addressDto));
        return tempConverter.deliveryAddressEntityToDto(storedAddress);
    }
}
