package com.codesmachine.springbootrestapi.services;

import com.codesmachine.springbootrestapi.domain.Category;
import com.codesmachine.springbootrestapi.dtos.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto addCategory(CategoryDto categoryDto);

    CategoryDto getCategoryById(String id);

    List<CategoryDto> getAllCategories();

    CategoryDto updateCategory(CategoryDto categoryDto, String id);

    String deleteCategory(String id);
}
