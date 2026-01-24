package com.hanghae.coffeeshop.services.impl;

import com.hanghae.coffeeshop.converter.TempConverter;
import com.hanghae.coffeeshop.dto.CategoryDto;
import com.hanghae.coffeeshop.entity.CategoryEntity;
import com.hanghae.coffeeshop.exceptions.DuplicateException;
import com.hanghae.coffeeshop.exceptions.InstanceUndefinedException;
import com.hanghae.coffeeshop.repositories.CategoryRepository;
import com.hanghae.coffeeshop.services.CategoryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryServices {


    private final TempConverter tempConverter;
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(TempConverter tempConverter,CategoryRepository categoryRepository) {
        this.tempConverter = tempConverter;
        this.categoryRepository = categoryRepository;

    }

    @Override
    @Transactional
    public CategoryDto addCategory(CategoryDto categoryDto) {
        Optional<CategoryEntity> categoryEntityOptional = categoryRepository.findByName(categoryDto.getName());
        if (categoryEntityOptional.isPresent()) {
            throw new DuplicateException("Category with name: " + categoryDto.getName() + " already exists");
        }
        CategoryEntity saveCategory = categoryRepository.save(tempConverter.categoryDtoToEntity(categoryDto));
        return tempConverter.categoryEntityToDto(saveCategory);
    }

    @Override
    @Transactional
    public void deleteCategory(Long id) {
        getCategory(id);
        categoryRepository.deleteById(id);
        categoryRepository.flush();
    }

    @Override
    @Transactional
    public CategoryDto updateCategory(Long id, CategoryDto categoryDto) {
        CategoryDto currentCategory = getCategory(id);
        Optional<CategoryEntity> existingCategoryOptional = categoryRepository.findByName(categoryDto.getName());
        if (existingCategoryOptional.isPresent()) {
            CategoryEntity existingCategory = existingCategoryOptional.get();
            if (existingCategory.getId() != currentCategory.getId()) {
                throw new DuplicateException("Category already exists");
            }
        }
        categoryDto.setId(currentCategory.getId());
        categoryDto.setProductsIdes(currentCategory.getProductsIdes());
        CategoryEntity updateCategory = categoryRepository.save(tempConverter.categoryDtoToEntity(categoryDto));
        return tempConverter.categoryEntityToDto(updateCategory);
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryDto getCategory(Long id) {
        CategoryDto returnValue = null;
        Optional<CategoryEntity> productCategoryOptional = categoryRepository.findById(id);
        if (productCategoryOptional.isPresent()){
            returnValue = tempConverter.categoryEntityToDto(productCategoryOptional.get());
        } else {
            throw new InstanceUndefinedException("Category with id: " + id + " does not exist");
        }
        return returnValue;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryDto> listAllCategories() {
        List<CategoryEntity> productCategoryEntities = categoryRepository.findAll();
        List<CategoryDto> returnValue = new ArrayList<>();
        for (Iterator<CategoryEntity> iterator = productCategoryEntities.iterator(); iterator.hasNext();){
            returnValue.add(tempConverter.categoryEntityToDto(iterator.next()));
        }
        return returnValue;
    }
}
