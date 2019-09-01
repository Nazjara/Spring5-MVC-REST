package com.nazjara.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Model to create a new customer")
public class CustomerDTO {

    @ApiModelProperty(notes = "Id of customer", example = "1")
    private Long id;

    @ApiModelProperty(notes = "First name of customer", example = "Joe")
    private String firstName;

    @ApiModelProperty(notes = "Last name of customer", example = "Newman")
    private String lastName;
}
