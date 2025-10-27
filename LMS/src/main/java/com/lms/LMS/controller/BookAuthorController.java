package com.lms.LMS.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookAuthorController {
    @GetMapping("/BookAuthor")
    public String testBookAuthor() {
        return "Hello Book Author - Book Author endpoint is running!";
    }
}
