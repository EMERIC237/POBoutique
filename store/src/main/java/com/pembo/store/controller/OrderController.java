package com.pembo.store.controller;

import java.util.List;

import com.pembo.store.dto.OrderDto;
import com.pembo.store.dto.OrderRequestDto;
import com.pembo.store.service.OrderService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<OrderDto> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public OrderDto getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }


    @PutMapping("/{id}")
    public OrderDto updateOrder(@PathVariable Long id, @RequestBody OrderRequestDto orderDto) {
        return orderService.updateOrder(id, orderDto);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
    }

}
