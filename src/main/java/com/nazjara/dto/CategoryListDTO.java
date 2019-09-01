package com.nazjara.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(description = "Model which contains group of categories")
public class CategoryListDTO {

    @ApiModelProperty(notes = "List with categories")
    private List<CategoryDTO> categories;
}
