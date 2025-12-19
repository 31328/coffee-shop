package com.hanghae.coffeeshop.controllers;

import com.hanghae.coffeeshop.dto.MenuDto;
import com.hanghae.coffeeshop.exceptions.DataNotValidatedException;
import com.hanghae.coffeeshop.services.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/menu")
public class MenuController {

    private final MenuService menuService;

    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuDto> getMenu(@PathVariable("id") Long menuId) {
        return new ResponseEntity<>(menuService.getMenu(menuId), HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<MenuDto>> getMenuList() {
        return new ResponseEntity<>(menuService.getMenuList(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createMenu(@RequestBody @Validated MenuDto menuDto, Errors error) {
        if (error.hasErrors()) {
            throw new DataNotValidatedException("Menu data has not been validated");
        }
        menuService.createMenu(menuDto);
        return new ResponseEntity<>("The menu created", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateMenu(MenuDto menuDto, @PathVariable("id") Long menuId) {
        menuService.updateMenu(menuDto, menuId);
        return new ResponseEntity<>("Menu updated", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMenu(@PathVariable("id") Long menuId) {
        menuService.deleteMenu(menuId);
        return new ResponseEntity<>("Menu deleted", HttpStatus.OK);
    }


}
