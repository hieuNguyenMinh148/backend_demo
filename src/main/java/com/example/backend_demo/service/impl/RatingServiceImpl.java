package com.example.backend_demo.service.impl;

import com.example.backend_demo.exeption.ProductException;
import com.example.backend_demo.model.Product;
import com.example.backend_demo.model.Rating;
import com.example.backend_demo.model.User;
import com.example.backend_demo.repository.RatingRepository;
import com.example.backend_demo.request.RatingRequest;
import com.example.backend_demo.service.ProductService;
import com.example.backend_demo.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {
    private final ProductService productService;
    private final RatingRepository ratingRepository;

    @Override
    public Rating createRating(RatingRequest request, User user) throws ProductException {
        Product product = productService.findProductById(request.getProductId());

        Rating rating = new Rating();
        rating.setUser(user);
        rating.setProduct(product);
        rating.setRating(request.getRating());
        rating.setCreatedAt(LocalDateTime.now());
        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getProductsRating(Long productId) {

        return ratingRepository.findAllByProductId(productId);
    }
}
