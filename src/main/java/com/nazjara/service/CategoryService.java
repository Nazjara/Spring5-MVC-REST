package com.nazjara.service;

import com.nazjara.dto.CategoryDTO;
import com.nazjara.dto.CategoryListDTO;

public interface CategoryService {
    CategoryListDTO getCategories();
    CategoryDTO getCategoryByName(String name);
}
