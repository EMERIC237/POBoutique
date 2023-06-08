package com.pembo.store.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.pembo.store.dto.OrderDto;
import com.pembo.store.mapper.OrderMapper;
import com.pembo.store.model.Order;
import com.pembo.store.repository.OrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Autowired
    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    /**
     * get all orders
     * 
     * @return List of all orders
     */
    public List<OrderDto> getAllOrders() {
        return orderRepository.findAll().stream().map(orderMapper::toDto).collect(Collectors.toList());
    }

    /**
     * get order by id
     * 
     * @param id
     * @return order by id
     */
    public OrderDto getOrderById(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        return orderMapper.toDto(order.orElseThrow(() -> new RuntimeException("Order not found")));
    }

    /**
     * save order
     * 
     * @param orderDto
     * @return saved order
     */
    public OrderDto saveOrder(OrderDto orderDto) {
        Order newOrder = orderMapper.toEntity(orderDto);
        Order savedOrder = orderRepository.save(newOrder);
        return orderMapper.toDto(savedOrder);
    }


    public OrderDto updateOrder(Long orderId, OrderDto orderDto) {
        Order toUpdateOrder = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
        orderMapper.partialUpdate(orderDto, toUpdateOrder);
        Order savedOrder = orderRepository.save(toUpdateOrder);
        return orderMapper.toDto(savedOrder);
    }
    /**
     * delete order by id
     * 
     * @param id
     */
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

}
