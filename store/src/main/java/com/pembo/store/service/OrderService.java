package com.pembo.store.service;

import com.pembo.store.dto.*;
import com.pembo.store.exception.InvalidArgumentException;
import com.pembo.store.exception.ResourceNotFoundException;
import com.pembo.store.mapper.OrderItemMapper;
import com.pembo.store.mapper.OrderMapper;
import com.pembo.store.model.Address;
import com.pembo.store.model.Order;
import com.pembo.store.model.OrderStatus;
import com.pembo.store.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    private final AddressService addressService;
    private final OrderItemMapper orderItemMapper;
    private final UserService userService;
    private final OrderItemService orderItemService;

    @Autowired
    public OrderService(OrderRepository orderRepository,
                        OrderMapper orderMapper,
                        UserService userService,
                        OrderItemService orderItemService,
                        OrderItemMapper orderItemMapper,
                        AddressService addressService) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.userService = userService;
        this.orderItemService = orderItemService;
        this.orderItemMapper = orderItemMapper;
        this.addressService = addressService;
    }

    public List<OrderDto> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(orderMapper::toDto)
                .collect(Collectors.toList());
    }

    public OrderDto getOrderById(Long orderId) {
        Order order = findOrderById(orderId);
        return orderMapper.toDto(order);
    }

    @Transactional
    public OrderDto saveUserOrder(long userId, OrderRequestDto orderRequestDto) {
        Order newOrder = new Order();
        newOrder.setUser(userService.findUserById(userId));
        newOrder.setDateCreated(orderRequestDto.dateCreated());
        newOrder.setStatus(OrderStatus.valueOf(orderRequestDto.status()));

        Address address = addressService
                .getAddressFromUserAddresses(userId, orderRequestDto.addressId())
                .orElseThrow(() -> new InvalidArgumentException("The given address does not belong to the user"));
        newOrder.setAddress(address); // set the address to the order

        Order savedOrder = orderRepository.save(newOrder);
        Set<OrderItemDto> orderItemDtos = orderItemService.createOrderItems(savedOrder, orderRequestDto.orderItems());

        savedOrder.setOrderItems(orderItemDtos.stream().map(orderItemMapper::toEntity).collect(Collectors.toSet()));
        savedOrder = orderRepository.save(savedOrder);

        return orderMapper.toDto(savedOrder);
    }

    @Transactional
    public OrderDto updateOrder(long orderId, OrderRequestDto orderRequestDto) {
        Order orderToUpdate = findOrderById(orderId);
        orderToUpdate.setStatus(OrderStatus.valueOf(orderRequestDto.status()));
        Order updatedOrder = orderRepository.save(orderToUpdate);

        return orderMapper.toDto(updatedOrder);
    }

    public void deleteOrder(Long id) {
        Order order = findOrderById(id);
        orderRepository.delete(order);
    }

    private Order findOrderById(long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order", orderId));
    }

    public List<OrderDto> getAllUserOrders(Long userId) {
        return orderRepository.findByUserId(userId).stream()
                .map(orderMapper::toDto)
                .collect(Collectors.toList());
    }
}
