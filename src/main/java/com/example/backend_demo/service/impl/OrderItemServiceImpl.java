package com.example.backend_demo.service.impl;

import com.example.backend_demo.model.OrderItem;
import com.example.backend_demo.repository.OrderItemRepository;
import com.example.backend_demo.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {
    private final OrderItemRepository orderItemRepository;

    @Override
    public OrderItem createOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }
}
