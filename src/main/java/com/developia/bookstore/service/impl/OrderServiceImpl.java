package com.developia.bookstore.service.impl;

import com.developia.bookstore.model.Order;
import com.developia.bookstore.model.User;
import com.developia.bookstore.repository.OrderRepository;
import com.developia.bookstore.service.OrderService;
import com.developia.bookstore.service.SessionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final SessionService sessionService;

    public OrderServiceImpl(OrderRepository orderRepository,
                            SessionService sessionService) {
        this.orderRepository = orderRepository;
        this.sessionService = sessionService;
    }

    @Override
    public List<Order> findAll() {
        User user = sessionService.find().getUser();
        return orderRepository.findByUser(user);
    }
}
