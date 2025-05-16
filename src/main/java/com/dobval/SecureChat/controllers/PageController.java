package com.dobval.SecureChat.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/login-page")
    public String loginPage() {
        return "login"; // templates/login.html
    }

    @GetMapping("/chat")
    public String chatPage() {
        return "chat";
    }
}
