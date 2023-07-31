package com.pembo.store.service;

import java.util.List;

import com.pembo.store.dto.ReviewDto;
import com.pembo.store.exception.ResourceNotFoundException;
import com.pembo.store.mapper.ReviewMapper;
import com.pembo.store.model.Product;
import com.pembo.store.model.Review;
import com.pembo.store.model.User;
import com.pembo.store.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;

    private final ProductService productService;

    private final UserService userService;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository,
                         ReviewMapper reviewMapper,
                         ProductService productService,
                         UserService userService) {
        this.reviewRepository = reviewRepository;
        this.reviewMapper = reviewMapper;
        this.productService = productService;
        this.userService = userService;
    }

    /**
     * get all reviews
     *
     * @return List of all reviews
     */
    public List<ReviewDto> getAllProductReviews(long productId) {
        return reviewRepository.findByProduct_Id(productId).stream().map(reviewMapper::toDto)
                .collect(java.util.stream.Collectors.toList());
    }

    /**
     * get review by id
     *
     * @param reviewId
     * @return review
     */
    public ReviewDto getReviewById(Long reviewId) {
        return reviewMapper
                .toDto(reviewRepository.findById(reviewId).orElseThrow(() -> new ResourceNotFoundException("Review", reviewId)));
    }

    public ReviewDto updateReview(Long reviewId, ReviewDto reviewDto) {
        Review toUpdateReview = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ResourceNotFoundException("Review", reviewId));
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
    public ReviewDto saveReview(Long userId, Long productId, ReviewDto review) {
        // Get product and user.
        User user = userService.findUserById(userId);
        Product product = productService.findProductById(productId);
        Review newReview = reviewMapper.toEntity(review);
        newReview.setUser(user);
        newReview.setProduct(product);
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
