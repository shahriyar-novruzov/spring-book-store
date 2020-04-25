package com.developia.bookstore.service;

import com.developia.bookstore.model.Cart;
import com.developia.bookstore.model.Order;

import java.math.BigDecimal;
import java.util.List;

public interface OrderService {
    List<Order> findAll();

}
