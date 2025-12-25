package com.hanghae.coffeeshop.services;


import com.hanghae.coffeeshop.dto.MenuDto;

import java.util.List;

public interface MenuService {

    MenuDto createMenu(MenuDto menuDto);

    MenuDto updateMenu(MenuDto menuDto, Long menuId);

    void deleteMenu(Long menuId);

    MenuDto getMenu(Long menuId);

    List<MenuDto> getMenuList();

    void clearMenu(Long menuId);

    //void refreshMenuState(Long menuId);

}
