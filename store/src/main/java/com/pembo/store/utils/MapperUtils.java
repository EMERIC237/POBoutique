package com.pembo.store.utils;

import com.pembo.store.dto.CategoryDto;
import com.pembo.store.model.CartItem;
import com.pembo.store.model.Category;
import com.pembo.store.model.Review;

import java.math.BigDecimal;
import java.util.Set;

public class MapperUtils {

    public static Double calculateAverageRating(Set<Review> reviews) {
        return reviews.stream().mapToDouble(Review::getRating).average().orElse(0.0);
    }
    public static int calculateReviewCount(Set<Review> reviews) {
        return reviews.size();
    }
    public static CategoryDto map(Category category) {
        return new CategoryDto(category.getId(),category.getName());
    }

    public static BigDecimal calculateTotalPrice(Set<CartItem> cartItems) {
        return cartItems.stream()
                .map(cartItem -> cartItem.getProduct().getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }
}


