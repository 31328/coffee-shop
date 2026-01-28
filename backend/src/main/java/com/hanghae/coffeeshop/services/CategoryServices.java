package com.hanghae.coffeeshop.services;

import com.hanghae.coffeeshop.dto.CategoryDto;

import java.util.List;

public interface CategoryServices {
    // add, delete, update, getCategory, listAllCategories
    CategoryDto addCategory(CategoryDto categoryDto);

    void deleteCategory(Long id);

    CategoryDto updateCategory(Long id, CategoryDto productCategoryDto);

    CategoryDto getCategory(Long id);

    List<CategoryDto> listAllCategories();
}
