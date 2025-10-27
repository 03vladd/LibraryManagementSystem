package com.lms.LMS.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookDetailsController {
    @GetMapping("/BookDetails")
    public String testBookDetails() {
        return "Hello Book Details - Book Details endpoint is running!";
    }

}
