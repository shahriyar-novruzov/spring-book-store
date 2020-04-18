package com.developia.bookstore.service;

import com.developia.bookstore.model.Cart;

import java.math.BigDecimal;

public interface CartService {
    Cart find();

    void save(Cart cart);

    BigDecimal total();

    void checkout();
}
