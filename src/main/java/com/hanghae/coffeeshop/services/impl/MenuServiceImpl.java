package com.hanghae.coffeeshop.services.impl;

import com.hanghae.coffeeshop.converter.TempConverter;
import com.hanghae.coffeeshop.dto.MenuDto;
import com.hanghae.coffeeshop.entity.MenuEntity;
import com.hanghae.coffeeshop.exceptions.DuplicateException;
import com.hanghae.coffeeshop.exceptions.InstanceUndefinedException;
import com.hanghae.coffeeshop.repositories.MenuRepository;
import com.hanghae.coffeeshop.services.MenuService;
import com.hanghae.coffeeshop.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class MenuServiceImpl implements MenuService {
    private MenuRepository menuRepository;
    private TempConverter tempConverter;
    private OrderService orderService;

    //setter injection
    @Autowired
    private void Initialise(MenuRepository menuRepository, TempConverter tempConverter, OrderService orderService) {
        this.menuRepository = menuRepository;
        this.tempConverter = tempConverter;
        this.orderService = orderService;
    }

    @Transactional
    @Override
    public MenuDto addMenu(MenuDto menuDto) {
        menuDto.setPrice(0.0);
        menuDto.setPoints(0);
        Optional<MenuEntity> existingMenuOptional = menuRepository.findByName(menuDto.getName());
        if (existingMenuOptional.isPresent()) {
            throw new DuplicateException("Menu with name \"" + menuDto.getName() + "\" already exists");
        }
        MenuEntity menuEntity = tempConverter.menuDtoToEntity(menuDto);
        MenuEntity savedEntity = menuRepository.save(menuEntity);
        return tempConverter.menuEntityToDto(savedEntity);
    }

    @Transactional
    @Override
    public MenuDto updateMenu(MenuDto menuDto, Long menuId) {
        MenuDto currentMenu = getMenu(menuId);
        Optional<MenuEntity> existingMenuOptional = menuRepository.findByName(menuDto.getName());
        if (existingMenuOptional.isPresent()) {
            MenuEntity existingMenuEntity = existingMenuOptional.get();
            if (!Objects.equals(existingMenuEntity.getId(), currentMenu.getId())) {
                throw new DuplicateException("Menu with name \"" + menuDto.getName() + "\" already exists");
            }
        }
        menuDto.setId(currentMenu.getId());
        menuDto.setPoints(currentMenu.getPoints());
        menuDto.setPrice(currentMenu.getPrice());
        menuDto.setProductIdes(currentMenu.getProductIdes());
        MenuEntity updatedMenu = menuRepository.saveAndFlush(tempConverter.menuDtoToEntity(menuDto));
        return tempConverter.menuEntityToDto(updatedMenu);
    }

    @Transactional
    @Override
    public void deleteMenu(Long menuId) {
        getMenu(menuId);
        orderService.deleteAllByMenuId(menuId);
        menuRepository.deleteById(menuId);
        menuRepository.flush();
    }

    @Transactional(readOnly = true)
    @Override
    public MenuDto getMenu(Long menuId) {
        MenuDto returnValue = null;
        Optional<MenuEntity> menuEntityOptional = menuRepository.findById(menuId);
        if (menuEntityOptional.isPresent()) {
            returnValue = tempConverter.menuEntityToDto(menuEntityOptional.get());
        } else {
            throw new InstanceUndefinedException("Menu with id:" + menuId + " does not exist");
        }
        return returnValue;
    }

    @Transactional(readOnly = true)
    @Override
    public List<MenuDto> getMenuList() {
        List<MenuEntity> menuEntities = menuRepository.findAll();
        List<MenuDto> returnValue = new ArrayList<>();
        for (Iterator<MenuEntity> iterator = menuEntities.iterator(); iterator.hasNext(); ) {
            returnValue.add(tempConverter.menuEntityToDto(iterator.next()));
        }
        return returnValue;
    }

    @Transactional
    @Override
    public void clearMenu(Long menuId) {
        System.out.println("Attempt to clear the menu");
        MenuDto menuDto = getMenu(menuId);
        menuRepository.clearMenu(menuId);
        menuDto.setPoints(0);
        menuDto.setPrice(0.0);
        menuRepository.save(tempConverter.menuDtoToEntity(menuDto));
        System.out.println("Menu cleared");
    }

   /* @Override
    public void refreshMenuState(Long menuId) {
        MenuDto menuDto = getMenu(menuId);
        point
    }*/


}
