package com.example.backend_demo.controller;

import com.example.backend_demo.annotation.Operation;
import com.example.backend_demo.annotation.Tag;
import com.example.backend_demo.exeption.ProductException;
import com.example.backend_demo.exeption.UserException;
import com.example.backend_demo.model.Cart;
import com.example.backend_demo.model.User;
import com.example.backend_demo.request.AddItemRequest;
import com.example.backend_demo.response.ApiResponse;
import com.example.backend_demo.service.CartService;
import com.example.backend_demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@Tag(name = "Cart Management", description = "find user cart, add item to cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    @Operation(description = "find cart by user jwt")
    public ResponseEntity<Cart> findUserCart(@RequestHeader("Authorization") String jwt) throws UserException {
        User user = userService.findUserProfileByJwt(jwt);
        Cart cart = cartService.findUserCart(user.getId());
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PutMapping("/add")
    @Operation(description = "add item to cart")
    public ResponseEntity<ApiResponse> addItemToCart(@RequestBody AddItemRequest request,
                                                     @RequestHeader("Authorization") String jwt)
            throws UserException, ProductException {
        User user = userService.findUserProfileByJwt(jwt);
        cartService.addCartItem(user.getId(), request);

        ApiResponse response = new ApiResponse("Item added to cart", true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
