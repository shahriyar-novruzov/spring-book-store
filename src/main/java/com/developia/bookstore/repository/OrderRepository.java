package com.developia.bookstore.repository;

import com.developia.bookstore.model.Cart;
import com.developia.bookstore.model.Order;
import com.developia.bookstore.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {

    List<Order> findByUser(User user);
}
