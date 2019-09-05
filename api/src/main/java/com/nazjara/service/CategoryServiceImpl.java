package com.nazjara.service;

import com.nazjara.dto.CategoryDTO;
import com.nazjara.dto.CategoryListDTO;
import com.nazjara.exception.ResourceNotFoundException;
import com.nazjara.mapper.CategoryMapper;
import com.nazjara.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryMapper categoryMapper;
    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryMapper categoryMapper, CategoryRepository categoryRepository) {
        this.categoryMapper = categoryMapper;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryListDTO getCategories() {
        CategoryListDTO categories = new CategoryListDTO();
        categories.setCategories(categoryRepository.findAll().stream()
                .map(category -> categoryMapper.categoryToCategoryDTO(category)).collect(Collectors.toList()));

        return categories;
    }

    @Override
    public CategoryDTO getCategoryByName(String name) {
        return categoryMapper.categoryToCategoryDTO(categoryRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Category with name = %s not found", name))));
    }
}
