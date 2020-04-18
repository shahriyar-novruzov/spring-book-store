package com.developia.bookstore.service.impl;

import com.developia.bookstore.model.Book;
import com.developia.bookstore.model.Cart;
import com.developia.bookstore.model.Response;
import com.developia.bookstore.repository.BookRepository;
import com.developia.bookstore.repository.CartRepository;
import com.developia.bookstore.service.BookService;
import com.developia.bookstore.service.CartService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;
    private CartService cartService;
    private CartRepository cartRepository;

    BookServiceImpl(BookRepository bookRepository,
                    CartService cartService,
                    CartRepository cartRepository) {
        this.bookRepository = bookRepository;
        this.cartService = cartService;
        this.cartRepository = cartRepository;
    }

    @Override
    public Response create(Book book) {

        try {
            bookRepository.save(book);
        } catch (Exception e) {
            return new Response(false, e.getMessage());
        }

        return new Response(true, "Book created successfully");
    }

    @Override
    public Response update(Book book) {
        Book oldBook = bookRepository.findByIsbn(book.getIsbn());
        oldBook.setAuthor(book.getAuthor());
        oldBook.setName(book.getName());
        oldBook.setPrice(book.getPrice());
        oldBook.setImage(book.getImage());
        oldBook.setPageSize(book.getPageSize());
        oldBook.setPublishDate(book.getPublishDate());
        oldBook.setDescription(book.getDescription());
        bookRepository.save(oldBook);
        return new Response(true, "Updated successfully");
    }

    @Override
    public Response delete(String isbn) {
        Book book = bookRepository.findByIsbn(isbn);
        bookRepository.delete(book);
        return new Response(true, "Deleted successfully");
    }

    @Override
    public Book find(long id) {
        return null;
    }

    @Override
    public Book find(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    @Override
    public List<Book> findAll() {

        List<Book> books = new ArrayList<>();

        for (Book book : bookRepository.findAll()) {
            books.add(book);
        }

        return books;
    }

    @Override
    public void addToCart(String isbn) {
        Book book = bookRepository.findByIsbn(isbn);
        Cart cart = cartService.find();

        List<Book> books = cart.getBooks();
        books.add(book);

        cartService.save(cart);
    }

    @Override
    public void removeFromCart(String isbn) {

        Cart cart = cartService.find();

        List<Book> books = cart.getBooks();

        books.removeIf(currBook -> currBook.getIsbn().equals(isbn));

        System.out.println(books.size());

        cart.setBooks(books);

        cartService.save(cart);
    }
}
