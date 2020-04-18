package com.developia.bookstore.service;

import com.developia.bookstore.model.Response;
import com.developia.bookstore.model.User;

public interface UserService {

    Response register(User user);

    User login(String username, String password);

    User find(Long id);
}
