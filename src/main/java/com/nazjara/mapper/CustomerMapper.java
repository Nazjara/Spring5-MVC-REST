package com.nazjara.mapper;

import com.nazjara.dto.CategoryDTO;
import com.nazjara.dto.CustomerDTO;
import com.nazjara.model.Category;
import com.nazjara.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {
    CustomerMapper MAPPER = Mappers.getMapper(CustomerMapper.class);

    CustomerDTO customerToCustomerDTO(Customer customer);
}
