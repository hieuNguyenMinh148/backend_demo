package com.example.backend_demo.service;

import com.example.backend_demo.exeption.CartItemException;
import com.example.backend_demo.exeption.UserException;
import com.example.backend_demo.model.Cart;
import com.example.backend_demo.model.CartItem;
import com.example.backend_demo.model.Product;
import com.example.backend_demo.request.UpdateCartItemRequest;

public interface CartItemService {
    public CartItem createCartItem(CartItem cartItem);

    public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws CartItemException, UserException;

    public CartItem isCartItemExist(Cart cart, Product product, String size, Long userId);

    public void removeCartItem(Long userId, Long cartItemId) throws CartItemException, UserException;

    public CartItem findCartItemById(Long cartItemId) throws CartItemException;

}
