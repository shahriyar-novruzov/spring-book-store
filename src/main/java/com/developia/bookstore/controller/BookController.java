package com.developia.bookstore.controller;

import com.developia.bookstore.model.Book;
import com.developia.bookstore.model.Card;
import com.developia.bookstore.model.Review;
import com.developia.bookstore.service.BookService;
import com.developia.bookstore.service.CartService;
import com.developia.bookstore.service.ReviewService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/book")
public class BookController {

    private BookService bookService;
    private ReviewService reviewService;
    private CartService cartService;

    BookController(BookService bookService,
                   ReviewService reviewService,
                   CartService cartService) {
        this.bookService = bookService;
        this.reviewService = reviewService;
        this.cartService = cartService;
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Book book) {
        bookService.create(book);
        return "bookPage";
    }

    @GetMapping("/find")
    public String find(Model model, @RequestParam String isbn) {
        Book book = bookService.find(isbn);
        model.addAttribute("book", book);
        return "bookUpdate";
    }

    @GetMapping("/findDelete")
    public String findDelete(Model model, @RequestParam String isbn) {
        Book book = bookService.find(isbn);
        model.addAttribute("book", book);
        return "bookDelete";
    }

    @PostMapping("/update")
    public String update(Model model, @ModelAttribute Book book) {
        bookService.update(book);
        return "bookUpdate";
    }

    @PostMapping("/delete")
    public String delete(@ModelAttribute Book book) {
        bookService.delete(book.getIsbn());
        return "bookDelete";
    }

    @GetMapping("/view")
    public String view(Model model, @RequestParam String isbn) {
        Book book = bookService.find(isbn);
        model.addAttribute("book", book);
        Review review = new Review();
        review.setBook(book);
        model.addAttribute("review", review);
        return "bookView";
    }

    @PostMapping("/review")
    public String review(@ModelAttribute Review review, @RequestParam String isbn) {

        reviewService.create(isbn, review);
        return "redirect:/home";
    }

    @PostMapping("/addToCart")
    public String addToCart(@RequestParam String isbn) {
        bookService.addToCart(isbn);
        return "redirect:/home";
    }

    @PostMapping("/removeFromCart")
    public String removeFromCart(@RequestParam String isbn) {
        bookService.removeFromCart(isbn);
        return "redirect:/home";
    }

    @PostMapping("/checkout")
    public String checkout(@ModelAttribute Card card) {
        cartService.checkout();
        return "checkoutSuccessful";
    }
}
