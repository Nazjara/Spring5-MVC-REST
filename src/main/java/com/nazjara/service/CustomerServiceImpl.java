package com.nazjara.service;

import com.nazjara.dto.CustomerDTO;
import com.nazjara.dto.CustomerListDTO;
import com.nazjara.mapper.CustomerMapper;
import com.nazjara.model.Customer;
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

    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        Customer customer = customerMapper.customerDTOToCustomer(customerDTO);
        return customerMapper.customerToCustomerDTO(customerRepository.save(customer));
    }

    @Override
    public CustomerDTO replaceCustomer(Long id, CustomerDTO customerDTO) {
        Customer customer = customerMapper.customerDTOToCustomer(customerDTO);
        customer.setId(id);

        return customerMapper.customerToCustomerDTO(customerRepository.save(customer));
    }

    @Override
    public CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO) {
        return customerRepository.findById(id).map(customer -> {
            if (customerDTO.getFirstName() != null) {
                customer.setFirstName(customerDTO.getFirstName());
            }

            if (customerDTO.getLastName() != null) {
                customer.setLastName(customerDTO.getLastName());
            }

            return customerMapper.customerToCustomerDTO(customerRepository.save(customer));
        }).orElse(null);
    }
}
