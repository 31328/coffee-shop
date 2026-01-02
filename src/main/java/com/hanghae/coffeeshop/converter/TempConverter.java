package com.hanghae.coffeeshop.converter;

import com.hanghae.coffeeshop.dto.*;
import com.hanghae.coffeeshop.entity.*;
import com.hanghae.coffeeshop.enumerated.TransactionType;
import com.hanghae.coffeeshop.repositories.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Component
public class TempConverter {


    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final MenuRepository menuRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;
    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;
    private final DeliveryAddressRepository deliveryAddressRepository;

    @Autowired
    public TempConverter(ModelMapper modelMapper, UserRepository userRepository, MenuRepository menuRepository, CustomerRepository customerRepository, ProductRepository productRepository, CartItemRepository cartItemRepository, CartRepository cartRepository, DeliveryAddressRepository deliveryAddressRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.menuRepository = menuRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.cartItemRepository = cartItemRepository;
        this.cartRepository = cartRepository;
        this.deliveryAddressRepository = deliveryAddressRepository;
    }

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

    public CartEntity cartDtoToEntity(CartDto cartDto) {
        CartEntity returnValue = modelMapper.map(cartDto, CartEntity.class);
        Optional<Long> customerIdOptional = Optional.ofNullable(cartDto.getCustomerId());
        if (customerIdOptional.isPresent()) {
            Long customerId = customerIdOptional.get();
            Optional<CustomerEntity> customerEntityOptional = customerRepository.findById(customerId);
            if (customerEntityOptional.isPresent()) {
                returnValue.setCustomer(customerEntityOptional.get());
            }
        }
        Optional<List<Long>> cartItemsIdsOptional = Optional.ofNullable(cartDto.getCartItemsIds());
        List<CartItemEntity> cartItemsEntity = new ArrayList<>();
        if (cartItemsIdsOptional.isPresent()) {
            List<Long> cartItemsIdes = cartItemsIdsOptional.get();
            if (!cartItemsIdes.isEmpty()) {
                for (Iterator<Long> iterator = cartItemsIdes.iterator(); iterator.hasNext(); ) {
                    Long itemId = iterator.next();
                    Optional<CartItemEntity> cartItemOptional = cartItemRepository.findById(itemId);
                    if (cartItemOptional.isPresent()) {
                    }
                    cartItemsEntity.add(cartItemOptional.get());
                }
            }
        }
        returnValue.setCartItems(cartItemsEntity);
        return returnValue;
    }

    public CartDto cartEntityToDto(CartEntity cartEntity) {
        CartDto returnValue = modelMapper.map(cartEntity, CartDto.class);
        Optional<CustomerEntity> customerOptional = Optional.ofNullable(cartEntity.getCustomer());
        if (customerOptional.isPresent()) {
            CustomerEntity customer = customerOptional.get();
            returnValue.setCustomerId(customer.getId());
        }
        Optional<List<CartItemEntity>> cartItemsOptional = Optional.ofNullable(cartEntity.getCartItems());
        List<Long> cartItemsIds = new ArrayList<>();
        if (cartItemsOptional.isPresent()) {
            List<CartItemEntity> cartItems = cartItemsOptional.get();
            if (!cartItems.isEmpty()) {
                for (Iterator<CartItemEntity> iterator = cartItems.iterator(); iterator.hasNext(); ) {
                    CartItemEntity cartItem = iterator.next();
                    cartItemsIds.add(cartItem.getId());
                }
            }
        }
        returnValue.setCartItemsIds(cartItemsIds);
        return returnValue;
    }

    public CartItemDto cartItemEntityToDto(CartItemEntity cartItemEntity) {
        CartItemDto returnValue = modelMapper.map(cartItemEntity, CartItemDto.class);
        Optional<CartEntity> cartOptional = Optional.ofNullable(cartItemEntity.getCart());
        if (cartOptional.isPresent()) {
            CartEntity cart = cartOptional.get();
            returnValue.setCartId(cart.getId());
        }
        return returnValue;
    }

    public CartItemEntity cartItemDtoToEntity(CartItemDto cartItemDto) {
        CartItemEntity returnValue = modelMapper.map(cartItemDto, CartItemEntity.class);
        Optional<Long> cartIdOptional = Optional.ofNullable(cartItemDto.getCartId());
        if (cartIdOptional.isPresent()) {
            Long cartId = cartIdOptional.get();
            Optional<CartEntity> cartOptional = cartRepository.findById(cartId);
            cartOptional.ifPresent(returnValue::setCart);
        }
        return returnValue;
    }

    public CustomerDto customerEntityToDto(CustomerEntity customerEntity) {
        CustomerDto returnValue = modelMapper.map(customerEntity, CustomerDto.class);
        Optional<UserEntity> userOptional = Optional.ofNullable(customerEntity.getUser());
        if (userOptional.isPresent()) {
            UserEntity userEntity = userOptional.get();
            returnValue.setUserId(userEntity.getId());
        }
        Optional<CartEntity> cartOptional = Optional.ofNullable(customerEntity.getCart());
        if (cartOptional.isPresent()) {
            CartEntity cart = cartOptional.get();
            returnValue.setCartId(cart.getId());
        }
        Optional<DeliveryAddressEntity> addressOptional = Optional.ofNullable(customerEntity.getAddress());
        if (addressOptional.isPresent()) {
            DeliveryAddressEntity address = addressOptional.get();
            returnValue.setAddressId(address.getId());
        }
        return returnValue;
    }

    public CustomerEntity customerDtoToEntity(CustomerDto customerDto) {
        CustomerEntity returnValue = modelMapper.map(customerDto, CustomerEntity.class);
        Optional<Long> userIdOptional = Optional.ofNullable(customerDto.getUserId());
        if (userIdOptional.isPresent()) {
            Long userId = userIdOptional.get();
            Optional<UserEntity> userOptional = userRepository.findById(userId);
            if (userOptional.isPresent()) {
                UserEntity userEntity = userOptional.get();
                returnValue.setUser(userEntity);
            }
        }
        Optional<Long> cartIdOptional = Optional.ofNullable(customerDto.getCartId());
        if (cartIdOptional.isPresent()) {
            Long cartId = cartIdOptional.get();
            Optional<CartEntity> cartOptional = cartRepository.findById(cartId);
            if (cartOptional.isPresent()) {
                CartEntity cartEntity = cartOptional.get();
                returnValue.setCart(cartEntity);
            }
        }
        Optional<Long> addressIdOptional = Optional.ofNullable(customerDto.getAddressId());
        if (addressIdOptional.isPresent()) {
            Long addressId = addressIdOptional.get();
            Optional<DeliveryAddressEntity> addressOptional = deliveryAddressRepository.findById(addressId);
            if (addressOptional.isPresent()) {
                DeliveryAddressEntity address = addressOptional.get();
                returnValue.setAddress(address);
            }
        }
        return returnValue;
    }

    public DeliveryAddressDto DeliveryAddressEntityToDto(DeliveryAddressEntity addressEntity) {
        DeliveryAddressDto returnValue = modelMapper.map(addressEntity, DeliveryAddressDto.class);
        Optional<CustomerEntity> customerOptional = Optional.ofNullable(addressEntity.getCustomer());
        if (customerOptional.isPresent()) {
            CustomerEntity customerEntity = customerOptional.get();
            returnValue.setCustomerId(customerEntity.getId());
        }
        return returnValue;
    }

    public DeliveryAddressEntity DeliveryAddressDtoToEntity(DeliveryAddressDto addressDto) {
        DeliveryAddressEntity returnValue = modelMapper.map(addressDto, DeliveryAddressEntity.class);
        Optional<Long> customerIdOptional = Optional.ofNullable(addressDto.getCustomerId());
        if (customerIdOptional.isPresent()) {
            Long customerId = customerIdOptional.get();
            Optional<CustomerEntity> customerOptional = customerRepository.findById(customerId);
            if (customerOptional.isPresent()) {
                CustomerEntity customerEntity = customerOptional.get();
                returnValue.setCustomer(customerEntity);
            }
        }
        return returnValue;
    }



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

    public MenuItemDto MenuItemEntityToDto(MenuItemEntity menuItemEntity){
        MenuItemDto returnValue = new MenuItemDto();
        Optional<MenuEntity> menuOptional = Optional.ofNullable(menuItemEntity.getMenu());
        if (menuOptional.isPresent()){
            MenuEntity menuEntity = menuOptional.get();
            returnValue.setMenuId(menuEntity.getId());
        }
        return returnValue;
    }

    public MenuItemEntity menuItemDtoToEntity(MenuItemDto menuItemDto){
        MenuItemEntity returnValue = new MenuItemEntity();
        Optional<Long> menuIdOptional = Optional.ofNullable(menuItemDto.getMenuId());
        if(menuIdOptional.isPresent()){
            Long menuIdDto = menuIdOptional.get();
            Optional<MenuEntity> menuOptional = menuRepository.findById(menuItemDto.getMenuId());
            if (menuOptional.isPresent()){
                MenuEntity menuEntity = menuOptional.get();
                returnValue.setMenu(menuEntity);
            }
        }
        return returnValue;
    }

    public OrderDto orderEntityToDto(OrderEntity orderEntity) {
        OrderDto returnValue = modelMapper.map(orderEntity, OrderDto.class);

        return returnValue;
    }

    public OrderEntity orderDtoToEntity(OrderDto orderDto) {
        OrderEntity returnValue = modelMapper.map(orderDto, OrderEntity.class);

        return returnValue;
    }

    // order, points, product, user

    public UserDto userEntityToDto(UserEntity userEntity) {
        return modelMapper.map(userEntity, UserDto.class);
    }

    public UserEntity userDtoToEntity(UserDto userDto) {
        return modelMapper.map(userDto, UserEntity.class);
    }

    public PointTransactionDto pointsEntityToDto(PointTransactionEntity pointTransactionEntity) {
        PointTransactionDto returnValue = modelMapper.map(pointTransactionEntity, PointTransactionDto.class);


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
            while (iterator.hasNext()) {
                Long menuIde = iterator.next();
                Optional<MenuEntity> menuEntityOptional = menuRepository.findById(menuIde);
                if (menuEntityOptional.isPresent()) {
                    menus.add(menuEntityOptional.get());
                }
            }
        }
        returnValue.setMenus(menus);
        return returnValue;
    }

}
