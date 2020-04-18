package com.developia.bookstore.controller;

import com.developia.bookstore.model.Response;
import com.developia.bookstore.model.User;
import com.developia.bookstore.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public String register(Model model, @ModelAttribute User user) {

        Response response = userService.register(user);

        if (response.isSuccess()) {
            model.addAttribute("newUser", user);
            return "registerSuccessful";
        } else {
            model.addAttribute("error", response.getMessage());
            return "registerFail";
        }
    }

    @PostMapping("/login")
    public String login(Model model, @RequestParam String username, @RequestParam String password, final RedirectAttributes redirectAttributes) {
        User user = userService.login(username, password);
        redirectAttributes.addAttribute("user", user);
        return "redirect:/home";
    }

}
