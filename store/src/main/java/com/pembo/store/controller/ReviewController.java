package com.pembo.store.controller;
import java.util.List;

import org.springframework.web.bind.annotation.*;
import com.pembo.store.dto.ReviewDto;
import com.pembo.store.service.ReviewService;

@RestController
@RequestMapping("api/v1/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService){
        this.reviewService = reviewService;
    }

    @GetMapping
    public List<ReviewDto> getAllReviews(){
        return reviewService.getAllReviews();
    }

    @GetMapping("/{id}")
    public ReviewDto getReviewById(@PathVariable Long id){
        return reviewService.getReviewById(id);
    }

    @PostMapping
    public ReviewDto createReview(@RequestBody ReviewDto reviewDto){
        return reviewService.saveReview(reviewDto);
    }

    @PutMapping("/{id}")
    public ReviewDto updateReview(@PathVariable Long id, @RequestBody ReviewDto reviewDto){
        return reviewService.updateReview(id, reviewDto);
    }

    @DeleteMapping("/{id}")
    public void deleteReview(@PathVariable Long id){
        reviewService.deleteReview(id);
    }
    
}
