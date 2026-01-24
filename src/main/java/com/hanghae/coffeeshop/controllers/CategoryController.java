package com.hanghae.coffeeshop.controllers;

import com.hanghae.coffeeshop.dto.CategoryDto;
import com.hanghae.coffeeshop.exceptions.DataNotValidatedException;
import com.hanghae.coffeeshop.services.CategoryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/categories")
public class CategoryController {
    private final CategoryServices categoryServices;

    @Autowired
    public CategoryController(CategoryServices categoryServices) {
        this.categoryServices = categoryServices;
    }

    @PostMapping
    @PreAuthorize(value = "hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> addCategory(@RequestBody @Validated CategoryDto categoryDto, Errors errors) {
        if (errors.hasErrors()){
            throw new DataNotValidatedException("Category data has not being validated");
        }
        categoryServices.addCategory(categoryDto);
        return new ResponseEntity<>("Category has been created", HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize(value = "hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") Long id){
        categoryServices.deleteCategory(id);
        return new ResponseEntity<>("Category with id: " + id + " deleted", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @PreAuthorize(value = "hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> updateCategory(@RequestBody @Validated Long id,CategoryDto categoryDto, Errors errors){
        if (errors.hasErrors()){
            throw new DataNotValidatedException("Category data has not been validated");
        }
        categoryServices.updateCategory(id, categoryDto);
        return new ResponseEntity<>("Category with id: " + id + " has been updated.", HttpStatus.OK);
    }

    @GetMapping("/categoryDetails/{id}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable("id") Long id){
        return new ResponseEntity<>(categoryServices.getCategory(id), HttpStatus.OK);
    }

    @GetMapping("/listAll")
    public ResponseEntity<List<CategoryDto>> listAllCategories(){
        return  new ResponseEntity<>(categoryServices.listAllCategories(), HttpStatus.OK);
    }
}
