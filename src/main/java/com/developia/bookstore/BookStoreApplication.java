package com.developia.bookstore;

import com.developia.bookstore.model.Book;
import com.developia.bookstore.model.Cart;
import com.developia.bookstore.model.Review;
import com.developia.bookstore.model.User;
import com.developia.bookstore.repository.BookRepository;
import com.developia.bookstore.repository.CartRepository;
import com.developia.bookstore.repository.ReviewRepository;
import com.developia.bookstore.service.BookService;
import com.developia.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@SpringBootApplication
public class BookStoreApplication implements CommandLineRunner {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private CartRepository cartRepository;

    public static void main(String[] args) {
        SpringApplication.run(BookStoreApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

//        Book book = bookService.find("12");
//
//        Cart cart = cartRepository.findById(34L).get();
//
//        List<Book> bookList = cart.getBooks();
//        bookList.add(book);
//
//        cart.setBooks(bookList);
//
//        cartRepository.save(cart);

////
//        Review review = reviewRepository.findById(26L).get();
//
//        review.setBook(book);
//
//        reviewRepository.save(review);

//
//        book.setDescription("It is about true life story");
//        book.setPageSize(950);
//        book.setPublishDate(LocalDate.now());
//
//        bookRepository.save(book);
//        User user = userService.find(20L);
//
//        Review review = Review.builder().comment(
//                "Optional is not really intended for the purpose of dealing with exceptions, it was intended to deal with potential nulls without breaking the flow of your program. For example:")
//                .rate(new BigDecimal(5)).user(user).book(book).build();
//
//        reviewRepository.save(review);
    }
}
