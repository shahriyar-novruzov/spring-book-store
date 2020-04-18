package com.developia.bookstore.service.impl;

import com.developia.bookstore.model.Cart;
import com.developia.bookstore.model.Response;
import com.developia.bookstore.model.Session;
import com.developia.bookstore.model.User;
import com.developia.bookstore.repository.CartRepository;
import com.developia.bookstore.repository.SessionRepository;
import com.developia.bookstore.repository.UserRepository;
import com.developia.bookstore.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private SessionRepository sessionRepository;
    private CartRepository cartRepository;

    UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder,
                    SessionRepository sessionRepository, CartRepository cartRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.sessionRepository = sessionRepository;
        this.cartRepository = cartRepository;
    }

    @Override
    public Response register(User user) {

        User existed = userRepository.findByUserName(user.getUserName());

        if (existed != null) {
            return new Response(false, "Username already exists");
        }

        if (!user.getPassword().equals(user.getConfirmPassword()))
            return new Response(false, "Passwords not equal");

        String encode = passwordEncoder.encode(user.getPassword());

        user.setConfirmPassword(encode);
        user.setPassword(encode);
        user.setRole("USER");

        Cart cart = Cart
                .builder()
                .user(user)
                .build();

        try {
            userRepository.save(user);
            cartRepository.save(cart);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response(false, e.getMessage());
        }

        return new Response(true, "Success");
    }

    @Override
    public User login(String username, String password) {
        User user = userRepository.findByUserName(username);

        if (user == null)
            return null;

        Session session = Session
                .builder()
                .user(user)
                .build();

        sessionRepository.save(session);

        return user;
    }

    @Override
    public User find(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found!"));
    }
}
