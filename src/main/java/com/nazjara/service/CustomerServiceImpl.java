package com.nazjara.service;

import com.nazjara.dto.CustomerDTO;
import com.nazjara.dto.CustomerListDTO;
import com.nazjara.mapper.CustomerMapper;
import com.nazjara.mapper.CustomerMapper;
import com.nazjara.repositories.CustomerRepository;
import com.nazjara.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerMapper customerMapper;
    private CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerMapper customerMapper, CustomerRepository customerRepository) {
        this.customerMapper = customerMapper;
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerListDTO getCustomers() {
        CustomerListDTO customers = new CustomerListDTO();
        customers.setCustomers(customerRepository.findAll().stream()
                .map(customer -> customerMapper.customerToCustomerDTO(customer)).collect(Collectors.toList()));

        return customers;
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {
        return customerMapper.customerToCustomerDTO(customerRepository.findById(id).orElse(null));
    }
}