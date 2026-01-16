package com.hanghae.coffeeshop.services;

import com.hanghae.coffeeshop.dto.CustomerDto;
import com.hanghae.coffeeshop.utils.RegistrationForm;

import java.util.List;

public interface CustomerService {
    List<CustomerDto> listCustomers();

    List<CustomerDto> cutoffDate();

    CustomerDto addCustomer(RegistrationForm form);

    void deleteCustomer(Long customerId);

    void pruneBulkInactiveCustomers();

    CustomerDto getCustomerById(Long customerId);

    CustomerDto getCurrentCustomer();

    CustomerDto getCustomerByUserId(Long userId);

    void banCustomer(Long customerId);

    void unbanCustomer(Long customerId);

    void banGroup(List<Long> customerIdes);

    void unbanGroup(List<Long> customerIdes);

    CustomerDto getCustomerByPhone(String customerPhone);

}
