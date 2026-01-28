package com.hanghae.coffeeshop.controllers;

import com.hanghae.coffeeshop.dto.MenuDto;
import com.hanghae.coffeeshop.dto.ProductDto;
import com.hanghae.coffeeshop.exceptions.DataNotValidatedException;
import com.hanghae.coffeeshop.services.MenuService;
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
@RequestMapping("/menus")
public class MenuController {

    private final MenuService menuService;
    private final ProductService productService;

    @Autowired
    public MenuController(MenuService menuService, ProductService productService) {
        this.menuService = menuService;
        this.productService = productService;
    }

    @GetMapping("/menuDetails/{id}")
    public ResponseEntity<MenuDto> getMenu(@PathVariable("id") Long menuId) {
        return new ResponseEntity<>(menuService.getMenu(menuId), HttpStatus.OK);
    }

    @GetMapping("/listAll")
    public ResponseEntity<List<MenuDto>> getMenuList() {
        return new ResponseEntity<>(menuService.getMenuList(), HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize(value = "hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> addMenu(@RequestBody @Validated MenuDto menuDto, Errors errors) {
        if (errors.hasErrors()) {
            throw new DataNotValidatedException("Menu data has not been validated");
        }
        MenuDto savedMenu = menuService.addMenu(menuDto);
        return new ResponseEntity<>("The menu with id: " + savedMenu.getId() + " has been created", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize(value = "hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> updateMenu(@RequestBody @Validated MenuDto menuDto, @PathVariable("id") Long menuId, Errors errors) {
        if (errors.hasErrors()) {
            throw new DataNotValidatedException("Menu data has not been validated");
        }
        menuService.updateMenu(menuDto, menuId);
        return new ResponseEntity<>("Menu with id: " + menuId + " updated", HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    @PreAuthorize(value = "hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> deleteMenu(@PathVariable("id") Long menuId) {
        menuService.deleteMenu(menuId);
        return new ResponseEntity<>("Menu with id: " + menuId + " deleted", HttpStatus.OK);
    }

    @PutMapping("/clearMenu/{id}")
    @PreAuthorize(value = "hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> clearMenu(@PathVariable("id") Long menuId) {
        menuService.clearMenu(menuId);
        return new ResponseEntity<>("Menu with id: " + menuId + " cleared", HttpStatus.OK);
    }

    @PutMapping("/{productId}/{menuId}")
    @PreAuthorize(value = "hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> addProductToMenu(@PathVariable("productId") Long productId, @PathVariable("menuId") Long menuId) {
        MenuDto providedMenu = menuService.getMenu(menuId);
        ProductDto providedProduct = productService.getProduct(productId);
        productService.addProductToMenu(productId, menuId);
        return new ResponseEntity<>("Product " + providedProduct.getName() + " id: " + productId + " has been added to " + providedMenu.getName() + " id: " + menuId, HttpStatus.OK);
    }

}
