package com.nazjara.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(description = "Model which contains group of customers")
public class CustomerListDTO {

    @ApiModelProperty(notes = "List with customers")
    private List<CustomerDTO> customers;
}
