package com.pembo.store.service;

import com.pembo.store.dto.CartItemDto;
import com.pembo.store.dto.CartItemRequestDto;
import com.pembo.store.exception.ResourceNotFoundException;
import com.pembo.store.mapper.CartItemMapper;
import com.pembo.store.model.Cart;
import com.pembo.store.model.CartItem;
import com.pembo.store.model.Product;
import com.pembo.store.model.User;
import com.pembo.store.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartItemService {

    private final CartItemRepository cartItemRepository;
    private final CartItemMapper cartItemMapper;
    private final ProductService productService;
    private final CartService cartService;

    @Autowired
    public CartItemService(CartItemRepository cartItemRepository,
                           CartItemMapper cartItemMapper,
                           ProductService productService,
                           CartService cartService) {
        this.cartItemRepository = cartItemRepository;
        this.cartItemMapper = cartItemMapper;
        this.productService = productService;
        this.cartService = cartService;
    }

    public CartItemDto createOrUpdateCartItem(Long userId, Long productId, int quantity, boolean update) {
        Cart userCart = cartService.findUserCartById(userId);
        Product product = productService.findProductById(productId);

        CartItem cartItem = cartItemRepository.findByCartAndProduct(userCart, product).orElseGet(() -> {
            CartItem newCartItem = new CartItem();
            newCartItem.setCart(userCart);
            newCartItem.setProduct(product);
            return newCartItem;
        });

        if (update && cartItem.getId() == null) {
            throw new ResourceNotFoundException("CartItem not found");
        }

        cartItem.setQuantity(update ? quantity : cartItem.getQuantity() + quantity);

        return cartItemMapper.toDto(cartItemRepository.save(cartItem));
    }

    public CartItemDto createCartItem(Long userId, Long productId, int quantity) {
        return createOrUpdateCartItem(userId, productId, quantity, false);
    }

    public CartItemDto updateCartItem(Long userId, Long productId, int quantity) {
        return createOrUpdateCartItem(userId, productId, quantity, true);
    }

    public void deleteCartItem(Long userId, Long productId) {
        Cart userCart = cartService.findUserCartById(userId);
        Product product = productService.findProductById(productId);

        CartItem cartItem = cartItemRepository.findByCartAndProduct(userCart, product)
                .orElseThrow(() -> new ResourceNotFoundException("CartItem not found"));

        cartItemRepository.delete(cartItem);
    }

    @Transactional
    public List<CartItemDto> createCartItems(Long userId, List<CartItemRequestDto> items) {
        Cart userCart = cartService.findUserCartById(userId);

        List<CartItem> cartItems = new ArrayList<>();
        for (CartItemRequestDto item : items) {
            Product product = productService.findProductById(item.productId());
            CartItem cartItem = cartItemRepository.findByCartAndProduct(userCart, product)
                    .orElseGet(() -> {
                        CartItem newCartItem = new CartItem();
                        newCartItem.setCart(userCart);
                        newCartItem.setProduct(product);
                        return newCartItem;
                    });

            cartItem.setQuantity(cartItem.getQuantity() + item.quantity());
            cartItems.add(cartItem);
        }

        cartItemRepository.saveAll(cartItems);
        return cartItems.stream().map(cartItemMapper::toDto).collect(Collectors.toList());
    }
}
