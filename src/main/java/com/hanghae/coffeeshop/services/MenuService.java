package com.hanghae.coffeeshop.services;

import com.hanghae.coffeeshop.domain.menu.Menu;
import com.hanghae.coffeeshop.domain.menu.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {

    private final MenuRepository menuRepository;

    @Autowired
    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public List<Menu> getMenuList() {
        return menuRepository.findAll();
    }
}
