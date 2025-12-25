package com.hanghae.coffeeshop.controllers;

import com.hanghae.coffeeshop.dto.ProductDto;
import com.hanghae.coffeeshop.exceptions.DataNotValidatedException;
import com.hanghae.coffeeshop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<String> createProduct(@RequestBody @Validated ProductDto productDto, Errors errors) {
        if (errors.hasErrors()) {
            throw new DataNotValidatedException("Product data has not been validated");
        }
        productService.createProduct(productDto);
        return new ResponseEntity<>("The product created", HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable("id") Long productId) {
        return new ResponseEntity<>(productService.getProduct(productId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getProductList() {
        return new ResponseEntity<>(productService.getProductList(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateProduct(@RequestBody @Validated ProductDto productDto, @PathVariable("id") Long productId, Errors errors) {
        if (errors.hasErrors()) {
            throw new DataNotValidatedException("Product data has not been validated");
        }
        productService.updateProduct(productDto, productId);
        return new ResponseEntity<>("Product updated", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") Long productId) {
        productService.deleteProduct(productId);
        return new ResponseEntity<>("Product deleted", HttpStatus.OK);
    }

    @PutMapping("/{productId}/{menuId}")
    public ResponseEntity<String> addMenu(@PathVariable("productId") Long productId, @PathVariable("menuId") Long menuId){
        productService.addMenu(productId,menuId);
        return new ResponseEntity<>("Menu added to the product", HttpStatus.OK);
    }
}
