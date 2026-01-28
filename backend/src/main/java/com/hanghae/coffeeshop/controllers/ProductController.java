package com.hanghae.coffeeshop.controllers;

import com.hanghae.coffeeshop.dto.ProductDto;
import com.hanghae.coffeeshop.exceptions.DataNotValidatedException;
import com.hanghae.coffeeshop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @PreAuthorize(value = "hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> addProduct(@RequestBody @Validated ProductDto productDto, Errors errors) {
        if (errors.hasErrors()) {
            throw new DataNotValidatedException("Product data has not been validated");
        }
        ProductDto savedProduct = productService.addProduct(productDto);
        return new ResponseEntity<>("The product with id: " + savedProduct.getId() + " created", HttpStatus.CREATED);
    }

    @GetMapping("/productDetails/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable("id") Long productId) {
        return new ResponseEntity<>(productService.getProduct(productId), HttpStatus.OK);
    }

    @GetMapping("/listAll")
    public ResponseEntity<List<ProductDto>> getProductList() {
        return new ResponseEntity<>(productService.getProductList(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @PreAuthorize(value = "hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> updateProduct(@RequestBody @Validated ProductDto productDto, @PathVariable("id") Long productId, Errors errors) {
        if (errors.hasErrors()) {
            throw new DataNotValidatedException("Product data has not been validated");
        }
        productService.updateProduct(productDto, productId);
        return new ResponseEntity<>("Product with id: " + productId + " has been updated", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize(value = "hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") Long productId) {
        productService.deleteProduct(productId);
        return new ResponseEntity<>("Product with id: " + productId + " has been deleted", HttpStatus.OK);
    }
}