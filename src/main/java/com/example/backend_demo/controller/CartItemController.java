package com.example.backend_demo.controller;

import com.example.backend_demo.exeption.CartItemException;
import com.example.backend_demo.exeption.UserException;
import com.example.backend_demo.model.CartItem;
import com.example.backend_demo.model.User;
import com.example.backend_demo.request.UpdateCartItemRequest;
import com.example.backend_demo.response.ApiResponse;
import com.example.backend_demo.service.CartItemService;
import com.example.backend_demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart_item")
public class CartItemController {
    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private UserService userService;

    @DeleteMapping("/{cartItemId}")
    public ResponseEntity<ApiResponse> deleteCartItem(@PathVariable Long cartItemId,
                                                      @RequestHeader("Authorization") String jwt)
            throws UserException, CartItemException {
        User user = userService.findUserProfileByJwt(jwt);
        cartItemService.removeCartItem(user.getId(), cartItemId);

        ApiResponse response = new ApiResponse("Item has removed from cart", true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{cartItemId}")
    public ResponseEntity<CartItem> updateCartItem(@RequestBody CartItem cartItem, @PathVariable Long cartItemId,
                                                   @RequestHeader("Authorization") String jwt)
            throws UserException, CartItemException {
        User user = userService.findUserProfileByJwt(jwt);
        CartItem updatedCartItem = cartItemService.updateCartItem(user.getId(), cartItemId, cartItem);
        return new ResponseEntity<>(updatedCartItem, HttpStatus.OK);
    }
}
