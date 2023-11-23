package com.codesmachine.springbootrestapi.controllers;

import com.codesmachine.springbootrestapi.dtos.CategoryDto;
import com.codesmachine.springbootrestapi.services.CategoryService;
import com.codesmachine.springbootrestapi.services.impl.CategoryServiceImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {


    @Autowired
    private CategoryServiceImpl categoryService;


    // Build REST API to create category and give ONLY Users with ADMIN Role to create this
    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto){
        CategoryDto savedCategoryDto = categoryService.addCategory(categoryDto);
        return new ResponseEntity<>(savedCategoryDto, HttpStatus.CREATED);
    }


    // Build REST API to create category and give ONLY Users with ADMIN Role to create this
    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @PutMapping("/{id}/update")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryDto> updateCategory(
            @RequestBody CategoryDto categoryDto,
            @PathVariable(name = "id") String id){

        CategoryDto updatedCategoryDto = categoryService.updateCategory(categoryDto,id);
        return ResponseEntity.ok(updatedCategoryDto);
    }

    // Build REST API to get category by id
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> findCategoryById(@PathVariable(name = "id") String id){
        CategoryDto categoryDto = categoryService.getCategoryById(id);
        return ResponseEntity.ok(categoryDto);
    }


    // Build REST API to get all categories
    @GetMapping("")
    public ResponseEntity<List<CategoryDto>> findAllCategoryies(){
        List<CategoryDto> categoryDtoList = categoryService.getAllCategories();
        return ResponseEntity.ok(categoryDtoList);
    }

    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @DeleteMapping("/{id}/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteById(@PathVariable(name = "id") String id){
        return ResponseEntity.ok(categoryService.deleteCategory(id));
    }



}
