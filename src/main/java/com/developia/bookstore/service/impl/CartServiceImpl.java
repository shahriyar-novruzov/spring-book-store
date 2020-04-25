package com.developia.bookstore.service.impl;

import com.developia.bookstore.model.Book;
import com.developia.bookstore.model.Cart;
import com.developia.bookstore.model.Order;
import com.developia.bookstore.model.User;
import com.developia.bookstore.repository.CartRepository;
import com.developia.bookstore.repository.OrderRepository;
import com.developia.bookstore.service.CartService;
import com.developia.bookstore.service.SessionService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final SessionService sessionService;
    private final OrderRepository orderRepository;

    public CartServiceImpl(CartRepository cartRepository,
                           SessionService sessionService,
                           OrderRepository orderRepository) {
        this.cartRepository = cartRepository;
        this.sessionService = sessionService;
        this.orderRepository = orderRepository;
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

        List<Book> bookList = new ArrayList<>(cart.getBooks());

        Order order = Order.builder()
                .orderNumber(UUID.randomUUID().toString().substring(0, 10))
                .dateTime(LocalDateTime.now())
                .books(bookList)
                .user(cart.getUser())
                .build();

        orderRepository.save(order);

        cart.setBooks(new ArrayList<>());
        save(cart);
    }
}
