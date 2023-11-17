package com.codesmachine.springbootrestapi.services.impl;

import com.codesmachine.springbootrestapi.domain.Category;
import com.codesmachine.springbootrestapi.dtos.CategoryDto;
import com.codesmachine.springbootrestapi.exceptions.ResourceNotFoundException;
import com.codesmachine.springbootrestapi.repositories.CategoryRepository;
import com.codesmachine.springbootrestapi.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {


    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public CategoryDto addCategory(CategoryDto categoryDto) {
        Category category = modelMapper.map(categoryDto,Category.class);
        Category savedCategory = categoryRepository.save(category);
        return modelMapper.map(savedCategory,CategoryDto.class);
    }

    @Override
    public CategoryDto getCategoryById(String id) {
        Category category =categoryRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Category","id",id));
        return modelMapper.map(category,CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map((category)-> modelMapper.map(category,CategoryDto.class)).collect(Collectors.toList());
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, String id) {
        Category category =categoryRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Category","id",id));
        category.setId(categoryDto.getId());
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        Category updatedCategory = categoryRepository.save(category);
        return modelMapper.map(updatedCategory,CategoryDto.class);
    }

    @Override
    public String deleteCategory(String id) {
        Category category = categoryRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Category","id",id));
        categoryRepository.delete(category);
        return "Category is deleted successfully";
    }
}
