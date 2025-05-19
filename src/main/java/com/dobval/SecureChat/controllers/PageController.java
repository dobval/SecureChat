package com.dobval.SecureChat.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/login")
    public String login() {
        return "login"; // templates/login.html
    }
    
    @GetMapping("/register")
    public String register() {
        return "register"; //TODO: add register page
    }

    @GetMapping("/chat")
    public String chat() {
        return "chat";
    }
}
