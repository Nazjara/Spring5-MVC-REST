package com.nazjara.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Model to create a new vendor")
public class VendorDTO {

    @ApiModelProperty(notes = "Id of vendor", example = "1")
    private Long id;

    @ApiModelProperty(notes = "Name of vendor", example = "Vendor1")
    private String name;
}
