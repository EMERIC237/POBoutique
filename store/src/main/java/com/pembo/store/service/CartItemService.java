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
import com.pembo.store.repository.CartRepository;
import com.pembo.store.repository.ProductRepository;
import com.pembo.store.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartItemService {

    private final CartItemRepository cartItemRepository;

    private final UserRepository userRepository;

    private final ProductRepository productRepository;

    private final CartRepository cartRepository;

    private final CartItemMapper cartItemMapper;

    @Autowired
    public CartItemService(CartItemRepository cartItemRepository,
                           CartItemMapper cartItemMapper,
                           CartRepository cartRepository,
                           UserRepository userRepository,
                           ProductRepository productRepository
    ) {
        this.cartItemRepository = cartItemRepository;
        this.cartItemMapper = cartItemMapper;
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public CartItemDto createCartItem(Long userId, Long itemId, int quantity) {
        // Get the User
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        // Get the user's CART
        Cart userCart = cartRepository.findByUser(user).orElseThrow(() -> new ResourceNotFoundException("User not found"));

        // Get the product
        Product product = productRepository.findById(itemId).orElseThrow(() -> new ResourceNotFoundException("Product Not found"));

        // Check if the cart already contains an item with the given product
        CartItem cartItem = cartItemRepository.findByCartAndProduct(userCart, product).orElse(null);

        if (cartItem != null) {
            // If the item already exists, increase its quantity
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        } else {
            // If the item does not exist, create a new Cart Item
            cartItem = new CartItem();
            cartItem.setCart(userCart);
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
        }

        // Save the cart item
        return cartItemMapper.toDto(cartItemRepository.save(cartItem));
    }


    public CartItemDto updateCartItem(Long userId, Long itemId, int quantity) {
        // Get the User
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        // Get the user's CART
        Cart userCart = cartRepository.findByUser(user).orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Product product = productRepository.findById(itemId).orElseThrow(() -> new ResourceNotFoundException("Product Not found"));

        // Get the cart item
        CartItem cartItem = cartItemRepository.findByCartAndProduct(userCart, product)
                .orElseThrow(() -> new ResourceNotFoundException("CartItem not found"));

        cartItem.setQuantity(quantity);
        // Update the cart item
        return cartItemMapper.toDto(cartItemRepository.save(cartItem));
    }

    public void deleteCartItem(Long userId, Long itemId) {
        // Get the User
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        // Get the user's CART
        Cart userCart = cartRepository.findByUser(user).orElseThrow(() -> new ResourceNotFoundException("User not found"));

        // Get the product
        Product product = productRepository.findById(itemId).orElseThrow(() -> new ResourceNotFoundException("Product Not found"));

        // Get the cart item
        CartItem cartItem = cartItemRepository.findByCartAndProduct(userCart, product)
                .orElseThrow(() -> new ResourceNotFoundException("CartItem not found"));

        // Delete the cart item
        cartItemRepository.delete(cartItem);
    }

    @Transactional
    public List<CartItemDto> createCartItems(Long userId, List<CartItemRequestDto> items) {
        // Get the User
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        // Get the user's CART
        Cart userCart = cartRepository.findByUser(user).orElseThrow(() -> new ResourceNotFoundException("Cart not found"));

        List<CartItem> cartItems = new ArrayList<>();

        for (CartItemRequestDto item : items) {
            // Get the product
            Product product = productRepository.findById(item.productId()).orElseThrow(() -> new ResourceNotFoundException("Product Not found"));

            // Check if the cart item already exists
            Optional<CartItem> existingCartItemOpt = cartItemRepository.findByCartAndProduct(userCart, product);
            if (existingCartItemOpt.isPresent()) {
                // update the quantity
                CartItem existingCartItem = existingCartItemOpt.get();
                existingCartItem.setQuantity(existingCartItem.getQuantity() + item.quantity());
                cartItems.add(existingCartItem);
            } else {
                // create a new Cart Item
                CartItem newCartItem = new CartItem();
                newCartItem.setCart(userCart);
                newCartItem.setProduct(product);
                newCartItem.setQuantity(item.quantity());
                cartItems.add(newCartItem);
            }
        }

        // Save the cart's Items
        cartItemRepository.saveAll(cartItems);
        return cartItems.stream().map(cartItemMapper::toDto).collect(Collectors.toList());
    }
}
