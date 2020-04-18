package com.developia.bookstore.repository;

import com.developia.bookstore.model.Cart;
import com.developia.bookstore.model.User;
import org.springframework.data.repository.CrudRepository;

public interface CartRepository extends CrudRepository<Cart, Long> {

    Cart findByUser(User user);
}
