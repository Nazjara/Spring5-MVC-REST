package com.nazjara.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Model to create a new category")
public class CategoryDTO {

    @ApiModelProperty(notes = "Id of category", example = "1")
    private Long id;

    @ApiModelProperty(notes = "Name of category", example = "Fruits")
    private String name;
}
