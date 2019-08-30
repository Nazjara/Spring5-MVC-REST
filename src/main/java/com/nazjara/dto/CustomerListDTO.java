package com.nazjara.dto;

import lombok.Data;

import java.util.List;

@Data
public class CustomerListDTO {
    private List<CustomerDTO> customers;
}
