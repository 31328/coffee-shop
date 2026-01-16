package com.hanghae.coffeeshop.controllers;

import com.hanghae.coffeeshop.dto.MenuDto;
import com.hanghae.coffeeshop.exceptions.DataNotValidatedException;
import com.hanghae.coffeeshop.services.MenuService;
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

    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuDto> getMenu(@PathVariable("id") Long menuId) {
        return new ResponseEntity<>(menuService.getMenu(menuId), HttpStatus.OK);
    }

    @GetMapping("/menuDetails/{id}")
    public ResponseEntity<List<MenuDto>> getMenuList() {
        return new ResponseEntity<>(menuService.getMenuList(), HttpStatus.OK);
    }

    @PostMapping("/listAll")
    @PreAuthorize(value = "hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> createMenu(@RequestBody @Validated MenuDto menuDto, Errors errors) {
        if (errors.hasErrors()) {
            throw new DataNotValidatedException("Menu data has not been validated");
        }
        menuService.createMenu(menuDto);
        return new ResponseEntity<>("The menu created", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize(value = "hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> updateMenu(@RequestBody @Validated MenuDto menuDto, @PathVariable("id") Long menuId, Errors errors) {
        if (errors.hasErrors()) {
            throw new DataNotValidatedException("Menu data has not been validated");
        }
        menuService.updateMenu(menuDto, menuId);
        return new ResponseEntity<>("Menu updated", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize(value = "hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> deleteMenu(@PathVariable("id") Long menuId) {
        menuService.deleteMenu(menuId);
        return new ResponseEntity<>("Menu deleted", HttpStatus.OK);
    }


}
