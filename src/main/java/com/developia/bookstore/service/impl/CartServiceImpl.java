package com.developia.bookstore.service.impl;

import com.developia.bookstore.model.Book;
import com.developia.bookstore.model.Cart;
import com.developia.bookstore.model.User;
import com.developia.bookstore.repository.CartRepository;
import com.developia.bookstore.service.CartService;
import com.developia.bookstore.service.SessionService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final SessionService sessionService;

    public CartServiceImpl(CartRepository cartRepository,
                           SessionService sessionService) {
        this.cartRepository = cartRepository;
        this.sessionService = sessionService;
    }

    @Override
    public Cart find() {
        User user = sessionService.find().getUser();
        return cartRepository.findByUser(user);
    }

    @Override
    public void save(Cart cart) {
        cartRepository.save(cart);
    }

    @Override
    public BigDecimal total() {
        Cart cart = find();
        BigDecimal total = BigDecimal.ZERO;
        for (Book book : cart.getBooks())
            total = total.add(book.getPrice());

        return total;
    }

    @Override
    public void checkout() {
        Cart cart = find();
        cart.setBooks(new ArrayList<>());
        save(cart);
    }
}
