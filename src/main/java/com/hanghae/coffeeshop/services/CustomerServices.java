package com.hanghae.coffeeshop.services;

import com.hanghae.coffeeshop.dto.CustomerDto;
import com.hanghae.coffeeshop.utils.RegistrationForm;

import java.util.List;

public interface CustomerServices {
    List<CustomerDto> listCustomers();

    CustomerDto addCustomer(RegistrationForm form);

    void deleteCustomer(Long customerId);

    void pruneBulkInactiveCustomers();

    CustomerDto getCustomerById(Long customerId);

    CustomerDto getCurrentCustomer();

    CustomerDto getCustomerByUserId(Long userId);

    CustomerDto banCustomer(Long customerId);

    CustomerDto unbanCustomer(Long customerId);

    List<CustomerDto> banGroup(List<Long> customerId);

    List<CustomerDto> unbanGroup(List<Long> customerId);


}
