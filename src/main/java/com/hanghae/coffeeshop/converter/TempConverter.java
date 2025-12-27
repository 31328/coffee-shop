package com.hanghae.coffeeshop.converter;

import com.hanghae.coffeeshop.dto.*;
import com.hanghae.coffeeshop.entity.*;
import com.hanghae.coffeeshop.enumerated.TransactionType;
import com.hanghae.coffeeshop.repositories.MenuRepository;
import com.hanghae.coffeeshop.repositories.ProductRepository;
import com.hanghae.coffeeshop.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Component
public class TempConverter {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private ProductRepository productRepository;
   /* @Autowired
    public TempConverter(ModelMapper modelMapper, UserRepository userRepository, MenuRepository menuRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.menuRepository = menuRepository;
    }

    @Autowired
    private void Initialise(ModelMapper modelMapper, UserRepository userRepository, MenuRepository menuRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.menuRepository = menuRepository;
    }*/

    public MenuDto MenuEntityToDto(MenuEntity menuEntity) {
        MenuDto returnValue = modelMapper.map(menuEntity, MenuDto.class);
        Optional<List<ProductEntity>> productsOptional = Optional.ofNullable(menuEntity.getProducts());
        List<Long> productsIdes = new ArrayList<>();
        if (productsOptional.isPresent()) {
            List<ProductEntity> products = productsOptional.get();
            for (ProductEntity product : products) {
                productsIdes.add(product.getId());
            }
        }
        returnValue.setProductIdes(productsIdes);
        return returnValue;
        //products ProductEntity, List<Long>productIdes Dto
    }

    public MenuEntity MenuDtoToEntity(MenuDto menuDto) {
        MenuEntity returnValue = modelMapper.map(menuDto, MenuEntity.class);
        Optional<List<Long>> productIdesOptional = Optional.ofNullable(menuDto.getProductIdes());
        List<ProductEntity> products = new ArrayList<>();
        if (productIdesOptional.isPresent()) {
            List<Long> productsIdes = productIdesOptional.get();
            for (Long productId : productsIdes) {
                Optional<ProductEntity> productEntityOptional = productRepository.findById(productId);
                if (productEntityOptional.isPresent()) {
                    ProductEntity productEntity = productEntityOptional.get();
                    products.add(productEntity);
                }
            }
        }
        returnValue.setProducts(products);
        return returnValue;
    }

    public UserDto userEntityToDto(UserEntity userEntity) {
        return modelMapper.map(userEntity, UserDto.class);
    }

    public UserEntity userDtoToEntity(UserDto userDto) {
        return modelMapper.map(userDto, UserEntity.class);
    }

    public PointTransactionDto pointsEntityToDto(PointTransactionEntity pointTransactionEntity) {
        PointTransactionDto returnValue = modelMapper.map(pointTransactionEntity, PointTransactionDto.class);
        Optional<UserEntity> userOptional = Optional.ofNullable(pointTransactionEntity.getUser());
        if (userOptional.isPresent()) {
            UserEntity userEntity = userOptional.get();
            returnValue.setUserId(userEntity.getId());
        }

        Optional<TransactionType> typeOptional = Optional.ofNullable(pointTransactionEntity.getType());
        if (typeOptional.isPresent()) {
            TransactionType transactionType = typeOptional.get();
            String typeStr = transactionType.name();
            returnValue.setType(typeStr);
        }
        return returnValue;
    }

    public PointTransactionEntity pointsDtoToEntity(PointTransactionDto pointTransactionDto) {
        PointTransactionEntity returnValue = modelMapper.map(pointTransactionDto, PointTransactionEntity.class);
        Optional<Long> userIdOptional = Optional.ofNullable(pointTransactionDto.getUserId());
        if (userIdOptional.isPresent()) {
            Long userId = userIdOptional.get();
            Optional<UserEntity> userOptional = userRepository.findById(userId);
            if (userOptional.isPresent()) {
                UserEntity userEntity = userOptional.get();
                returnValue.setUser(UserEntity);
            }
        }

        Optional<String> typeOptional = Optional.ofNullable(pointTransactionDto.getType());
        if (typeOptional.isPresent()) {
            try {
                TransactionType transactionType = TransactionType.valueOf(typeOptional.get());
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Invalid transaction type: " + typeOptional.get());
            }
        }
        return returnValue;
    }

    public OrderDto orderEntityToDto(OrderEntity orderEntity) {
        OrderDto returnValue = modelMapper.map(orderEntity, OrderDto.class);
        Optional<UserEntity> userOptional = Optional.ofNullable(orderEntity.getUser());
        if (userOptional.isPresent()) {
            UserEntity userEntity = userOptional.get();
            returnValue.setUserId(userEntity.getId());
        }
        Optional<MenuEntity> menuOptional = Optional.ofNullable(orderEntity.getMenu());
        if (menuOptional.isPresent()) {
            MenuEntity menuEntity = menuOptional.get();
            returnValue.setMenuId(menuEntity.getId());
        }
        return returnValue;
    }

    public OrderEntity orderDtoToEntity(OrderDto orderDto) {
        OrderEntity returnValue = modelMapper.map(orderDto, OrderEntity.class);
        Optional<Long> userIdOptional = Optional.ofNullable(orderDto.getUserId());
        if (userIdOptional.isPresent()) {
            Long userId = userIdOptional.get();
            Optional<UserEntity> userOptional = userRepository.findById(userId);
            if (userOptional.isPresent()) {
                UserEntity userEntity = userOptional.get();
                returnValue.setUser(userEntity);
            }
        }
        Optional<Long> menuIdOptional = Optional.ofNullable(orderDto.getMenuId());
        if (menuIdOptional.isPresent()) {
            Long menuId = menuIdOptional.get();
            Optional<MenuEntity> menuOptional = menuRepository.findById(menuId);
            if (menuOptional.isPresent()) {
                MenuEntity menuEntity = menuOptional.get();
                returnValue.setMenu(menuEntity);
            }
        }
        return returnValue;
    }

    public ProductDto productEntityToDto(ProductEntity productEntity) {
        ProductDto returnValue = modelMapper.map(productEntity, ProductDto.class);
        Optional<List<MenuEntity>> menusOptional = Optional.ofNullable(productEntity.getMenus());
        List<Long> menusIdes = new ArrayList<>();
        if (menusOptional.isPresent()) {
            List<MenuEntity> menus = menusOptional.get();
            menus.forEach(tempMenu -> {
                menusIdes.add(tempMenu.getId());
            });
        }
        returnValue.setMenusIdes(menusIdes);
        return returnValue;
    }

    // List<MenuEntity> menus / Entity , List<Long> menusIdes / Dto
    public ProductEntity productDtoToEntity(ProductDto productDto) {
        ProductEntity returnValue = modelMapper.map(productDto, ProductEntity.class);
        Optional<List<Long>> menusIdesOptional = Optional.ofNullable(productDto.getMenusIdes());
        List<MenuEntity> menus = new ArrayList<>();
        if (menusIdesOptional.isPresent()) {
            List<Long> menusIdes = menusIdesOptional.get();
            Iterator<Long> iterator = menusIdes.iterator();
            while (iterator.hasNext()){
                Long menuIde = iterator.next();
                Optional<MenuEntity> menuEntityOptional = menuRepository.findById(menuIde);
                if(menuEntityOptional.isPresent()){
                    menus.add(menuEntityOptional.get());
                }
            }
        }
        returnValue.setMenus(menus);
        return returnValue;
    }

}
