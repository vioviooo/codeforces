package com.example.codeforces.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String homePage() {
        return "register";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @GetMapping("/main")
    public String mainPage() {
        return "main";
    }

    @GetMapping("/profile")
    public String profilePage() {
        return "profile";
    }

    @GetMapping("/contest")
    public String contestPage() {
        return "contest";
    }

    @GetMapping("/archive")
    public String archivePage() {
        return "archive"; // view name or resource path
    }

    @GetMapping("/archive-problems/problem-description")
    public String problemDetailsPage() {
        return "problem-description"; // view name or resource path
    }
}
