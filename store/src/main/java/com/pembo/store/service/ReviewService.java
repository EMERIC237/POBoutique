package com.pembo.store.service;

import java.util.List;

import com.pembo.store.dto.ReviewDto;
import com.pembo.store.mapper.ReviewMapper;
import com.pembo.store.model.Review;
import com.pembo.store.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository, ReviewMapper reviewMapper) {
        this.reviewRepository = reviewRepository;
        this.reviewMapper = reviewMapper;
    }

    /**
     * get all reviews
     * 
     * @return List of all reviews
     */
    public List<ReviewDto> getAllReviews() {
        return reviewRepository.findAll().stream().map(reviewMapper::toDto)
                .collect(java.util.stream.Collectors.toList());
    }

    /**
     * get review by id
     * 
     * @param id
     * @return review
     */
    public ReviewDto getReviewById(Long id) {
        return reviewMapper
                .toDto(reviewRepository.findById(id).orElseThrow(() -> new RuntimeException("Review Not found")));
    }

    public ReviewDto updateReview(Long id, ReviewDto reviewDto) {
        Review toUpdateReview = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found"));
        reviewMapper.partialUpdate(reviewDto, toUpdateReview);
        Review savedReview = reviewRepository.save(toUpdateReview);
        return reviewMapper.toDto(savedReview);
    }

    /**
     * save review
     * 
     * @param review
     * @return saved review
     */
    public ReviewDto saveReview(ReviewDto review) {
        Review newReview = reviewMapper.toEntity(review);
        Review savedReview = reviewRepository.save(newReview);
        return reviewMapper.toDto(savedReview);
    }

    /**
     * delete review by id
     * 
     * @param id
     */
    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }

}
