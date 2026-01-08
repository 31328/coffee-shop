package com.hanghae.coffeeshop.services.impl;

import com.hanghae.coffeeshop.converter.TempConverter;
import com.hanghae.coffeeshop.dto.CustomerDto;
import com.hanghae.coffeeshop.entity.CustomerEntity;
import com.hanghae.coffeeshop.repositories.CustomerRepository;
import com.hanghae.coffeeshop.services.CustomerServices;
import com.hanghae.coffeeshop.utils.RegistrationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Service
public class CustomerServiceImpl implements CustomerServices {
    private final TempConverter tempConverter;
    private final CustomerRepository customerRepository;


    @Autowired
    public CustomerServiceImpl(TempConverter tempConverter, CustomerRepository customerRepository) {
        this.tempConverter = tempConverter;
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomerDto> listCustomers() {
        List<CustomerDto> returnValue = new ArrayList<>();
        List<CustomerEntity> allCustomers = customerRepository.findAll();
        if (!allCustomers.isEmpty()) {
            for (Iterator<CustomerEntity> iterator = allCustomers.iterator(); iterator.hasNext();) {
                CustomerEntity customer = iterator.next();
                returnValue.add(tempConverter.customerEntityToDto(customer));
            }
        }

        return returnValue;
    }

    @Override
    @Transactional
    public CustomerDto addCustomer(RegistrationForm form) {

        return null;
    }

    @Override
    @Transactional
    public void deleteCustomer(Long customerId) {

    }

    @Override
    @Transactional
    public void pruneBulkInactiveCustomers() {

    }

    @Override
    @Transactional(readOnly = true)
    public CustomerDto getCustomerById(Long customerId) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerDto getCurrentCustomer() {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerDto getCustomerByUserId(Long userId) {
        return null;
    }

    @Override
    @Transactional
    public CustomerDto banCustomer(Long customerId) {
        return null;
    }

    @Override
    @Transactional
    public CustomerDto unbanCustomer(Long customerId) {
        return null;
    }

    @Override
    @Transactional
    public List<CustomerDto> banGroup(List<Long> customerId) {
        return List.of();
    }

    @Override
    @Transactional
    public List<CustomerDto> unbanGroup(List<Long> customerId) {
        return List.of();
    }


}
