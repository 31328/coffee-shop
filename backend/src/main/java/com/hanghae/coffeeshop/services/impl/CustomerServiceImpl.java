package com.hanghae.coffeeshop.services.impl;

import com.hanghae.coffeeshop.converter.TempConverter;
import com.hanghae.coffeeshop.dto.CartDto;
import com.hanghae.coffeeshop.dto.CustomerDto;
import com.hanghae.coffeeshop.dto.DeliveryAddressDto;
import com.hanghae.coffeeshop.dto.UserDto;
import com.hanghae.coffeeshop.entity.CustomerEntity;
import com.hanghae.coffeeshop.exceptions.InstanceUndefinedException;
import com.hanghae.coffeeshop.repositories.CustomerRepository;
import com.hanghae.coffeeshop.services.CartService;
import com.hanghae.coffeeshop.services.CustomerService;
import com.hanghae.coffeeshop.services.DeliveryAddressService;
import com.hanghae.coffeeshop.services.UserService;
import com.hanghae.coffeeshop.utils.RegistrationForm;
import com.hanghae.coffeeshop.utils.TimeConversionUtils;
import jakarta.inject.Provider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Service
public class CustomerServiceImpl implements CustomerService {
    private final TempConverter tempConverter;
    private final CustomerRepository customerRepository;
    private final DeliveryAddressService deliveryAddressService;
    private final Provider<UserService> userServiceProvider;
    private final CartService cartService;
    private final TimeConversionUtils timeConversionUtils;

    @Autowired
    public CustomerServiceImpl(TempConverter tempConverter, CustomerRepository customerRepository, DeliveryAddressService deliveryAddressService, Provider<UserService> userServiceProvider, CartService cartService, TimeConversionUtils timeConversionUtils) {
        this.tempConverter = tempConverter;
        this.customerRepository = customerRepository;
        this.deliveryAddressService = deliveryAddressService;
        this.userServiceProvider = userServiceProvider;

        this.cartService = cartService;
        this.timeConversionUtils = timeConversionUtils;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomerDto> listCustomers() {
        List<CustomerDto> returnValue = new ArrayList<>();
        List<CustomerEntity> allCustomers = customerRepository.findAll();
        if (!allCustomers.isEmpty()) {
            for (Iterator<CustomerEntity> iterator = allCustomers.iterator(); iterator.hasNext(); ) {
                CustomerEntity customer = iterator.next();
                returnValue.add(tempConverter.customerEntityToDto(customer));
            }
        }

        return returnValue;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomerDto> cutoffDate() {
        List<CustomerDto> returnValue = new ArrayList<>();
        List<CustomerEntity> customerEntities = customerRepository
                .findInactiveCustomersForOneYear(timeConversionUtils
                        .getOneYearAgo(), timeConversionUtils.getOneYearAgo());
        if (customerEntities.isEmpty()) {
            return returnValue;
        }
        for (Iterator<CustomerEntity> iterator = customerEntities.iterator(); iterator.hasNext(); ) {
            CustomerEntity customerEntity = iterator.next();
            returnValue.add(tempConverter.customerEntityToDto(customerEntity));
        }
        return returnValue;
    }

    @Override
    @Transactional
    public CustomerDto addCustomer(RegistrationForm form) {
        DeliveryAddressDto storedAddress = deliveryAddressService.addAddress(form.getAddress());
        UserDto storeUser = userServiceProvider.get().addUser(form.getUser());
        CartDto cartDto = new CartDto();
        CartDto storeCart = cartService.saveCart(cartDto);
        CustomerDto customerDto = form.getCustomer();
        customerDto.setPoints(10);
        customerDto.setAddressId(storedAddress.getId());
        customerDto.setCartId(storeCart.getId());
        customerDto.setUserId(storeUser.getId());
        CustomerEntity customerEntity = customerRepository.save(tempConverter.customerDtoToEntity(customerDto));
        return tempConverter.customerEntityToDto(customerEntity);
    }

    @Override
    @Transactional
    public void deleteCustomer(Long customerId) {
        customerRepository.deleteById(customerId);
    }

    @Override
    @Transactional
    public void pruneBulkInactiveCustomers() {
        List<CustomerDto> customers = cutoffDate();
        if (customers.isEmpty()) {
            return;
        }
        for (Iterator<CustomerDto> iterator = customers.iterator(); iterator.hasNext(); ) {
            CustomerDto customer = iterator.next();
            deleteCustomer(customer.getId());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerDto getCustomerById(Long customerId) {
        CustomerEntity customerEntity = customerRepository.findById(customerId)
                .orElseThrow(() -> new InstanceUndefinedException("Customer not found for customerId: " + customerId));
        return tempConverter.customerEntityToDto(customerEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerDto getCurrentCustomer() {
        UserDto userDto = userServiceProvider.get().getCurrentUser();
        return getCustomerByUserId(userDto.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerDto getCustomerByUserId(Long userId) {
        return customerRepository.findByUserId(userId)
                .map(tempConverter::customerEntityToDto)
                .orElseThrow(() -> new InstanceUndefinedException("Customer not found for userId: " + userId));
    }

    @Override
    @Transactional
    public void banCustomer(Long customerId) {
        CustomerDto customerDto = getCustomerById(customerId);
        userServiceProvider.get().banUser(customerDto.getUserId());
    }

    @Override
    @Transactional
    public void unbanCustomer(Long customerId) {
        CustomerDto customer = getCustomerById(customerId);
        userServiceProvider.get().unbanUser(customer.getUserId());
    }

    @Override
    @Transactional
    public void banGroup(List<Long> customerIdes) {
        if (customerIdes == null || customerIdes.isEmpty()) {
            return;
        }
        for (Iterator<Long> iterator = customerIdes.iterator(); iterator.hasNext(); ) {
            banCustomer(iterator.next());
        }
    }

    @Override
    @Transactional
    public void unbanGroup(List<Long> customerIdes) {
        if (customerIdes == null || customerIdes.isEmpty()) {
            return;
        }
        for (Iterator<Long> iterator = customerIdes.iterator(); iterator.hasNext(); ) {
            unbanCustomer(iterator.next());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerDto getCustomerByPhone(String customerPhone) {
        CustomerEntity customerEntity = customerRepository.findByPhone(customerPhone)
                .orElseThrow(()->new InstanceUndefinedException("Customer had not being found"));
        return tempConverter.customerEntityToDto(customerEntity);
    }


}
