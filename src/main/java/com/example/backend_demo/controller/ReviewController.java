package com.example.backend_demo.controller;

import com.example.backend_demo.exeption.ProductException;
import com.example.backend_demo.exeption.UserException;
import com.example.backend_demo.model.Review;
import com.example.backend_demo.model.User;
import com.example.backend_demo.request.ReviewRequest;
import com.example.backend_demo.service.ReviewService;
import com.example.backend_demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<Review> createReview(@RequestBody ReviewRequest request,
                                               @RequestHeader("Authorization") String jwt)
            throws UserException, ProductException {
        User user = userService.findUserProfileByJwt(jwt);
        Review review = reviewService.createReview(request, user);
        return new ResponseEntity<>(review, HttpStatus.CREATED);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Review>> getProductReview(@PathVariable Long productId)
            throws UserException, ProductException {
        List<Review> reviewList = reviewService.getAllReview(productId);
        return new ResponseEntity<>(reviewList, HttpStatus.CREATED);
    }


}
