package com.pembo.store.controller;

import com.pembo.store.dto.OrderDto;
import com.pembo.store.dto.OrderRequestDto;
import com.pembo.store.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users/{userId}/orders")
public class UserOrderController {

    private final OrderService orderService;

    public UserOrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<OrderDto> getAllUserOrders(@PathVariable long userId) {
        return orderService.getAllUserOrders(userId);
    }


    @PostMapping
    public OrderDto createOrder(@PathVariable long userId, @RequestBody @Valid OrderRequestDto orderDto) {
        return orderService.saveUserOrder(userId, orderDto);
    }

}
