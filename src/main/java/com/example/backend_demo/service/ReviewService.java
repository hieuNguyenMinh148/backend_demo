package com.example.backend_demo.service;

import com.example.backend_demo.exeption.ProductException;
import com.example.backend_demo.model.Review;
import com.example.backend_demo.model.User;
import com.example.backend_demo.request.ReviewRequest;

import java.util.List;

public interface ReviewService {
    public Review createReview(ReviewRequest request, User user) throws ProductException;

    public List<Review> getAllReview(Long productId);

}
