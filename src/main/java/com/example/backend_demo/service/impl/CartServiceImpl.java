package com.example.backend_demo.service.impl;

import com.example.backend_demo.exeption.ProductException;
import com.example.backend_demo.model.Cart;
import com.example.backend_demo.model.CartItem;
import com.example.backend_demo.model.Product;
import com.example.backend_demo.model.User;
import com.example.backend_demo.repository.CartRepository;
import com.example.backend_demo.request.AddItemRequest;
import com.example.backend_demo.service.CartItemService;
import com.example.backend_demo.service.CartService;
import com.example.backend_demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final CartItemService cartItemService;
    private final ProductService productService;

    @Override
    public Cart createCart(User user) {
        Cart cart = new Cart();
        cart.setUser(user);
        return cartRepository.save(cart);
    }

    @Override
    public String addCartItem(Long userId, AddItemRequest request) throws ProductException {
        Cart cart = cartRepository.findByUserId(userId);
        Product product = productService.findProductById(request.getProductId());

        CartItem isPresent = cartItemService.isCartItemExist(cart, product, request.getSize(), userId);
        if (isPresent == null) {
            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setCart(cart);
            cartItem.setQuantity(request.getQuantity());
            cartItem.setUserId(userId);

            int price = request.getQuantity() * product.getDiscountedPrice();
            cartItem.setPrice(price);
            cartItem.setSize(request.getSize());

            CartItem createdCartItem = cartItemService.createCartItem(cartItem);
            cart.getCartItems().add(createdCartItem);
            System.out.println(cart);
            cartRepository.save(cart);
        }
        return "Item Added to Cart";
    }

    @Override
    public Cart findUserCart(Long userId) {
        Cart cart = cartRepository.findByUserId(userId);

        int totalPrice = 0;
        int totalDiscountedPrice = 0;
        int totalItem = 0;

        for (CartItem cartItem : cart.getCartItems()) {
            totalDiscountedPrice = totalDiscountedPrice + cartItem.getDiscountedPrice();
            totalPrice = totalPrice + cartItem.getPrice();
            totalItem = totalItem + cartItem.getQuantity();
        }

        cart.setTotalItem(totalItem);
        cart.setTotalDiscountedPrice(totalDiscountedPrice);
        cart.setTotalPrice(totalPrice);
        cart.setTotalDiscountedPrice(totalPrice - totalDiscountedPrice);

        return cartRepository.save(cart);
    }
}
