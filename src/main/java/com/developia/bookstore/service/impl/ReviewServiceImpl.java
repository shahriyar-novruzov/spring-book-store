package com.developia.bookstore.service.impl;

import com.developia.bookstore.model.Book;
import com.developia.bookstore.model.Review;
import com.developia.bookstore.model.User;
import com.developia.bookstore.repository.BookRepository;
import com.developia.bookstore.repository.ReviewRepository;
import com.developia.bookstore.service.ReviewService;
import com.developia.bookstore.service.SessionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final BookRepository bookRepository;
    private final SessionService sessionService;

    public ReviewServiceImpl(ReviewRepository reviewRepository,
                             BookRepository bookRepository,
                             SessionService sessionService) {
        this.reviewRepository = reviewRepository;
        this.bookRepository = bookRepository;
        this.sessionService = sessionService;
    }

    @Override
    public void create(String isbn, Review review) {

        Book book = bookRepository.findByIsbn(isbn);
        User user = sessionService.find().getUser();

        review.setBook(book);
        review.setUser(user);

        reviewRepository.save(review);
    }
}
