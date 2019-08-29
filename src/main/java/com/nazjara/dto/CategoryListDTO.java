package com.nazjara.dto;

import lombok.Data;

import java.util.List;

@Data
public class CategoryListDTO {
    private List<CategoryDTO> categories;
}
