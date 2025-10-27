package com.lms.LMS.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthorController {
    @GetMapping("/Author")
    public String testAuthor() {
        return "Hello Author - Author endpoint is running!";
    }
}
