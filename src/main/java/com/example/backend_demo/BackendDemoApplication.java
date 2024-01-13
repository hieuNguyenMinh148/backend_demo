package com.example.backend_demo;

import com.example.backend_demo.model.Cart;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendDemoApplication.class, args);
        Cart cart = new Cart();
        System.out.println(cart.getCartItems());
    }

}
