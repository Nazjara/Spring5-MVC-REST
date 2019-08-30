package com.nazjara.service;

import com.nazjara.dto.CategoryDTO;
import com.nazjara.dto.CategoryListDTO;
import com.nazjara.dto.CustomerDTO;
import com.nazjara.dto.CustomerListDTO;

public interface CustomerService {
    CustomerListDTO getCustomers();
    CustomerDTO getCustomerById(Long id);
}
