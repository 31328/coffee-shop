package com.hanghae.coffeeshop.controllers;

import com.hanghae.coffeeshop.dto.OrderDto;
import com.hanghae.coffeeshop.exceptions.DataNotValidatedException;
import com.hanghae.coffeeshop.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private OrderService orderService;

    @Autowired
    private void initialize(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    @PreAuthorize(value = "hasAuthority('ROLE_USER')")
    public ResponseEntity<String> addOrder(@RequestBody @Validated OrderDto orderDto,  Errors errors){
        if (errors.hasErrors()){
            throw new DataNotValidatedException("Order data has not been validated");
        }
        orderService.addOrder(orderDto);
        return new ResponseEntity<>("Order created", HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @PreAuthorize(value = "hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable("id")Long orderId){
        return new ResponseEntity<>(orderService.getOrderById(orderId), HttpStatus.OK);
    }

    @GetMapping
    @PreAuthorize(value = "hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<OrderDto>> listAll (){
        return new ResponseEntity<>(orderService.listAll(),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize(value = "hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> deleteOrder(@PathVariable("id")Long orderId){
        orderService.deleteOrder(orderId);
        return new ResponseEntity<>("Order deleted", HttpStatus.OK);
    }

}
