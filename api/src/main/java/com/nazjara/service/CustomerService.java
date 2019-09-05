package com.nazjara.service;

import com.nazjara.model.CustomerDTO;
import com.nazjara.model.CustomerListDTO;

public interface CustomerService {
    CustomerListDTO getCustomers();
    CustomerDTO getCustomerById(Long id);
    CustomerDTO createCustomer(CustomerDTO customerDTO);
    CustomerDTO replaceCustomer(Long id, CustomerDTO customerDTO);
    CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO);
    void deleteCustomer(Long id);
}
