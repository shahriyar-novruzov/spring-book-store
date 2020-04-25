package com.developia.bookstore.controller;

import com.developia.bookstore.model.Book;
import com.developia.bookstore.model.Card;
import com.developia.bookstore.model.Cart;
import com.developia.bookstore.model.Order;
import com.developia.bookstore.model.User;
import com.developia.bookstore.service.BookService;
import com.developia.bookstore.service.CartService;
import com.developia.bookstore.service.OrderService;
import com.developia.bookstore.service.SessionService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class PageController {

    private SessionService sessionService;
    private BookService bookService;
    private CartService cartService;
    private OrderService orderService;

    PageController(SessionService sessionService, BookService bookService,
                   CartService cartService,
                   OrderService orderService) {
        this.sessionService = sessionService;
        this.bookService = bookService;
        this.cartService = cartService;
        this.orderService = orderService;
    }

    @GetMapping(value = "/ready")
    @ResponseBody
    public ResponseEntity<String> ready() {
        return ResponseEntity.ok().body("salam");
    }

    @GetMapping(value = {"/", "/home"})
    public String home(Model model) {
        List<Book> books = bookService.findAll();
        model.addAttribute("books", books);
        return "home";
    }

    @GetMapping(value = "/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }

    @GetMapping(value = "/bookPage")
    public String bookPage() {
        return "bookPage";
    }

    @GetMapping(value = "/logout")
    public String logout(Model model) {
        sessionService.delete();
        return "redirect:/home";
    }

    @GetMapping(value = "/bookCreate")
    public String bookCreate(Model model) {
        model.addAttribute("book", new Book());
        return "bookCreate";
    }

    @GetMapping(value = "/bookUpdate")
    public String bookUpdate(Model model) {
        model.addAttribute("book", new Book());
        return "bookUpdate";
    }

    @GetMapping(value = "/bookDelete")
    public String bookDelete(Model model) {
        model.addAttribute("book", new Book());
        return "bookDelete";
    }

    @GetMapping(value = "/bookAll")
    public String bookAll(Model model) {

        List<Book> books = bookService.findAll();
        model.addAttribute("books", books);

        return "bookAll";
    }

    @GetMapping(value = "/cartPage")
    public String cartPage(Model model) {

        Cart cart = cartService.find();
        model.addAttribute("cart", cart);

        return "cartPage";
    }

    @GetMapping(value = "/checkoutPage")
    public String checkoutPage(Model model) {
        model.addAttribute("card", new Card());
        model.addAttribute("total", cartService.total());
        return "checkoutPage";
    }

    @GetMapping(value = "/ordersPage")
    public String ordersPage(Model model) {

        List<Order> orders = orderService.findAll();
        model.addAttribute("orders", orders);

        return "ordersPage";
    }
}
