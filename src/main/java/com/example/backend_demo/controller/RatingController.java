package com.example.backend_demo.controller;

import com.example.backend_demo.exeption.ProductException;
import com.example.backend_demo.exeption.UserException;
import com.example.backend_demo.model.Rating;
import com.example.backend_demo.model.User;
import com.example.backend_demo.request.RatingRequest;
import com.example.backend_demo.service.RatingService;
import com.example.backend_demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ratings")
public class RatingController {
    @Autowired
    private UserService userService;

    @Autowired
    private RatingService ratingService;

    @PostMapping("/create")
    public ResponseEntity<Rating> createRating(@RequestBody RatingRequest request,
                                               @RequestHeader("Authorization") String jwt)
            throws UserException, ProductException {
        User user = userService.findUserProfileByJwt(jwt);
        Rating rating = ratingService.createRating(request, user);
        return new ResponseEntity<>(rating, HttpStatus.CREATED);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Rating>> getProductsRating(@PathVariable Long productId,
                                                          @RequestHeader("Authorization") String jwt)
            throws UserException, ProductException {
        User user = userService.findUserProfileByJwt(jwt);
        List<Rating> ratingList = ratingService.getProductsRating(productId);
        return new ResponseEntity<>(ratingList, HttpStatus.CREATED);
    }


}
