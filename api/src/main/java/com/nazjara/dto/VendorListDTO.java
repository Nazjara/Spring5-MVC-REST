package com.nazjara.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(description = "Model which contains group of vendors")
public class VendorListDTO {

    @ApiModelProperty(notes = "List with vendors")
    private List<VendorDTO> vendors;
}
