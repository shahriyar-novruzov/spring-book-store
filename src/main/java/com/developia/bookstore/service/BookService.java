package com.developia.bookstore.service;

import com.developia.bookstore.model.Book;
import com.developia.bookstore.model.Response;

import java.util.List;

public interface BookService {
    Response create(Book book);

    Response update(Book book);

    Response delete(String isbn);

    Book find(long id);

    Book find(String isbn);

    List<Book> findAll();

    void addToCart(String isbn);

    void removeFromCart(String isbn);

}
