package com.pembo.store.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;
import com.pembo.store.dto.ReviewDto;
import com.pembo.store.service.ReviewService;

@RestController
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping(value = "api/v1/products/{productId}/reviews")
    public List<ReviewDto> getAllReviews(@PathVariable Long productId) {
        return reviewService.getAllProductReviews(productId);
    }

    @PostMapping(value = "api/v1/products/{productId}/reviews/user/{userId}")
    public ReviewDto createReview(@PathVariable Long productId, @PathVariable Long userId, @RequestBody ReviewDto reviewDto) {
        return reviewService.saveReview(userId, productId, reviewDto);
    }

    @PutMapping("api/v1/reviews/{reviewId}")
    public ReviewDto updateReview(@PathVariable Long reviewId, @RequestBody ReviewDto reviewDto) {
        return reviewService.updateReview(reviewId, reviewDto);
    }

    @DeleteMapping("api/v1/reviews/{reviewId}")
    public void deleteReview(@PathVariable Long reviewId) {
        reviewService.deleteReview(reviewId);
    }

}
