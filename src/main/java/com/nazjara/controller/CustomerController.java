package com.nazjara.controller;

import com.nazjara.dto.CustomerDTO;
import com.nazjara.dto.CustomerListDTO;
import com.nazjara.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customers")
@Api(description = "Operations pertaining to customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    @ApiOperation(value = "View a list of available customers", response = CustomerListDTO.class)
    public ResponseEntity<CustomerListDTO> list() {
        return new ResponseEntity<>(customerService.getCustomers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get customer by id", response = CustomerDTO.class)
    public ResponseEntity<CustomerDTO> getById(@PathVariable Long id) {
        return new ResponseEntity<>(customerService.getCustomerById(id), HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation(value = "Create new customer", response = CustomerDTO.class)
    public ResponseEntity<CustomerDTO> create(@RequestBody CustomerDTO customerDTO) {
        return new ResponseEntity<>(customerService.createCustomer(customerDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Replace existing customer", response = CustomerDTO.class)
    public ResponseEntity<CustomerDTO> replace(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
        return new ResponseEntity<>(customerService.replaceCustomer(id, customerDTO), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    @ApiOperation(value = "Update existing customer", response = CustomerDTO.class)
    public ResponseEntity<CustomerDTO> update(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
        return new ResponseEntity<>(customerService.updateCustomer(id, customerDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete existing customer")
    public ResponseEntity delete(@PathVariable Long id) {
        customerService.deleteCustomer(id);

        return ResponseEntity.ok().build();
    }
}