package com.nazjara.service;

import com.nazjara.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {
    List<CategoryDTO> getCategories();
    CategoryDTO getCategoryByName(String name);
}
