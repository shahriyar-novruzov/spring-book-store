package com.developia.bookstore.service;

import com.developia.bookstore.model.Review;

public interface ReviewService {
    void create(String isbn, Review review);
}
