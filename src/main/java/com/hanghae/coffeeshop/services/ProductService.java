package com.hanghae.coffeeshop.services;

import com.hanghae.coffeeshop.dto.ProductDto;

import java.util.List;

public interface ProductService {
    ProductDto createProduct(ProductDto productDto);

    ProductDto updateProduct(ProductDto productDto, Long productId);

    void deleteProduct(Long productId);

    ProductDto getProduct(Long productId);

    List<ProductDto> getProductList();
}
