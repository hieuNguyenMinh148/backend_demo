package com.example.backend_demo.service;

import com.example.backend_demo.exeption.ProductException;
import com.example.backend_demo.model.Rating;
import com.example.backend_demo.model.User;
import com.example.backend_demo.request.RatingRequest;

import java.util.List;

public interface RatingService {
    public Rating createRating(RatingRequest request, User user) throws ProductException;

    public List<Rating> getProductsRating(Long productId);
}
