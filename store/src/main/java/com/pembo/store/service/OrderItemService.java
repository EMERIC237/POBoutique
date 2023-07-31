package com.pembo.store.service;

import com.pembo.store.dto.OrderItemDto;
import com.pembo.store.dto.OrderItemRequestDto;
import com.pembo.store.dto.ProductDto;
import com.pembo.store.mapper.OrderItemMapper;
import com.pembo.store.model.Order;
import com.pembo.store.model.OrderItem;
import com.pembo.store.model.Product;
import com.pembo.store.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final ProductService productService;
    private final OrderItemMapper orderItemMapper;

    @Autowired
    public OrderItemService(OrderItemRepository orderItemRepository,
                            ProductService productService,
                            OrderItemMapper orderItemMapper) {
        this.orderItemRepository = orderItemRepository;
        this.productService = productService;
        this.orderItemMapper = orderItemMapper;
    }

    @Transactional
    public Set<OrderItemDto> createOrderItems(Order order, Set<OrderItemRequestDto> orderItemRequestDtos) {
        return orderItemRequestDtos.stream()
                .map(itemDto -> createOrderItem(order, itemDto))
                .collect(Collectors.toSet());
    }

    @Transactional
    public OrderItemDto createOrderItem(Order order, OrderItemRequestDto orderItemRequestDto) {
        Product product = productService.findProductById(orderItemRequestDto.productId());
        OrderItem orderItem = new OrderItem();
        orderItem.setOrder(order);
        orderItem.setProduct(product);
        orderItem.setQuantity(orderItemRequestDto.quantity());
        orderItem.setUnitPrice(product.getPrice());
        OrderItem savedOrderItem = orderItemRepository.save(orderItem);

        return orderItemMapper.toDto(savedOrderItem);
    }

    @Transactional
    public OrderItemDto updateOrderItem(Long id, OrderItemRequestDto orderItemRequestDto) {
        OrderItem existingOrderItem = orderItemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid order item ID: " + id));
        existingOrderItem.setQuantity(orderItemRequestDto.quantity());
        Product product = productService.findProductById(orderItemRequestDto.productId());
        existingOrderItem.setProduct(product);
        orderItemRepository.save(existingOrderItem);
        return orderItemMapper.toDto(existingOrderItem);
    }

    @Transactional
    public void deleteOrderItem(Long id) {
        OrderItem orderItem = orderItemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid order item ID: " + id));
        orderItemRepository.delete(orderItem);
    }

    private OrderItemDto createOrderItemFromRequestDto(OrderItemRequestDto orderItemRequestDto) {
        // Get the specific product
        Product product = productService.findProductById(orderItemRequestDto.productId());
        ProductDto productDto = productService.getProductDtoFromProduct(product);
        // Create OrderItemDto from the product and requestDto
        return new OrderItemDto(null, product.getPrice(), orderItemRequestDto.quantity(), productDto);
    }
}
