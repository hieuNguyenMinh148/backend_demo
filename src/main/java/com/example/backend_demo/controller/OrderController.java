package com.example.backend_demo.controller;

import com.example.backend_demo.exeption.OrderException;
import com.example.backend_demo.exeption.UserException;
import com.example.backend_demo.model.Address;
import com.example.backend_demo.model.Order;
import com.example.backend_demo.model.User;
import com.example.backend_demo.service.OrderService;
import com.example.backend_demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @PostMapping("/")
    public ResponseEntity<Order> createOrder(@RequestBody Address shippingAddress,
                                             @RequestHeader("Authorization") String jwt) throws UserException {
        User user = userService.findUserProfileByJwt(jwt);
        Order order = orderService.createOrder(user, shippingAddress);
        return new ResponseEntity<Order>(order, HttpStatus.CREATED);
    }

    @GetMapping("/user")
    public ResponseEntity<List<Order>> userOrderHistory(@RequestHeader("Authorization") String jwt)
            throws UserException {
        User user = userService.findUserProfileByJwt(jwt);

        List<Order> orderList = orderService.usersOrderHistory(user.getId());
        return new ResponseEntity<>(orderList, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> findOrderById(@PathVariable("id") Long orderId,
                                               @RequestHeader("Authorization") String jwt)
            throws UserException, OrderException {
        User user = userService.findUserProfileByJwt(jwt);
        Order order = orderService.findOrderById(orderId);
        return new ResponseEntity<>(order, HttpStatus.ACCEPTED);
    }

}
