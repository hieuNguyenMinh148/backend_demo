package com.example.backend_demo.service;

import com.example.backend_demo.exeption.ProductException;
import com.example.backend_demo.model.Cart;
import com.example.backend_demo.model.User;
import com.example.backend_demo.request.AddItemRequest;

public interface CartService {
    public Cart createCart(User user);

    public String addCartItem(Long userId, AddItemRequest request) throws ProductException;

    public Cart findUserCart(Long userId);
}
