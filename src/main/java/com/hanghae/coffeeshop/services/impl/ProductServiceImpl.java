package com.hanghae.coffeeshop.services.impl;

import com.hanghae.coffeeshop.converter.TempConverter;
import com.hanghae.coffeeshop.dto.ProductDto;
import com.hanghae.coffeeshop.entity.ProductEntity;
import com.hanghae.coffeeshop.exceptions.DuplicateException;
import com.hanghae.coffeeshop.exceptions.InstanceUndefinedException;
import com.hanghae.coffeeshop.repositories.ProductRepository;
import com.hanghae.coffeeshop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.parser.Entity;
import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
    private TempConverter tempConverter;

    @Autowired
    private void Initialise(ProductRepository productRepository, TempConverter tempConverter) {
        this.productRepository = productRepository;
        this.tempConverter = tempConverter;
    }

    @Transactional
    @Override
    public ProductDto createProduct(ProductDto productDto) {
        Optional<ProductEntity> existingProductOptional = productRepository.findByName(productDto.getName());
        if (existingProductOptional.isPresent()) {
            throw new DuplicateException("Product with name " + productDto.getName() + " already exists");
        }
        ProductEntity productEntity = tempConverter.productDtoToEntity(productDto);
        ProductEntity saveEntity = productRepository.save(productEntity);
        return tempConverter.productEntityToDto(saveEntity);

    }

    @Transactional
    @Override
    public ProductDto updateProduct(ProductDto productDto, Long productId) {
        ProductDto currentProduct = getProduct(productId);
        Optional<ProductEntity> existingProductOptional = productRepository.findByName(productDto.getName());
        if (existingProductOptional.isPresent()) {
            ProductEntity existingProductEntity = existingProductOptional.get();
            if (!Objects.equals(currentProduct.getId(), existingProductEntity.getId())) {
                throw new DuplicateException("Product with name " + productDto.getName() + " already exists");
            }

        }
        productDto.setId(currentProduct.getId());
        ProductEntity updateProduct = productRepository.save(tempConverter.productDtoToEntity(productDto));
        return tempConverter.productEntityToDto(updateProduct);
    }

    @Transactional
    @Override
    public void deleteProduct(Long productId) {
        getProduct(productId);
        productRepository.deleteById(productId);
    }

    @Transactional(readOnly = true)
    @Override
    public ProductDto getProduct(Long productId) {
        ProductDto returnValue = null;
        Optional<ProductEntity> existingProductOptional = productRepository.findById(productId);
        if (existingProductOptional.isPresent()) {
            returnValue = tempConverter.productEntityToDto(existingProductOptional.get());
        } else {
            throw new InstanceUndefinedException("Product with id:" + productId + " does not exist");
        }
        return returnValue;
    }

    @Transactional(readOnly = true)
    @Override
    public List<ProductDto> getProductList() {
        List<ProductEntity> productEntities = productRepository.findAll();
        List<ProductDto> returnValue = new ArrayList<>();
        for (Iterator<ProductEntity> iterator = productEntities.iterator(); iterator.hasNext();){
            returnValue.add(tempConverter.productEntityToDto(iterator.next()));
        }
        return returnValue;
    }
}
