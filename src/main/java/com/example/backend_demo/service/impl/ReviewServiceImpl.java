package com.example.backend_demo.service.impl;

import com.example.backend_demo.exeption.ProductException;
import com.example.backend_demo.model.Product;
import com.example.backend_demo.model.Review;
import com.example.backend_demo.model.User;
import com.example.backend_demo.repository.ProductRepository;
import com.example.backend_demo.repository.ReviewRepository;
import com.example.backend_demo.request.ReviewRequest;
import com.example.backend_demo.service.ProductService;
import com.example.backend_demo.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final ProductService productService;
    private final ProductRepository productRepository;

    @Override
    public Review createReview(ReviewRequest request, User user) throws ProductException {
        Product product = productService.findProductById(request.getProductId());

        Review review = new Review();
        review.setUser(user);
        review.setProduct(product);
        review.setReview(request.getReview());
        review.setCreatedAt(LocalDateTime.now());

        return reviewRepository.save(review);
    }

    @Override
    public List<Review> getAllReview(Long productId) {
        return reviewRepository.findAllByProductId(productId);
    }
}
