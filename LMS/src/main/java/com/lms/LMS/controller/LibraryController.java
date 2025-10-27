package com.lms.LMS.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LibraryController {
    @GetMapping("/library")
    @ResponseBody
    public String testLibrary() {
        return "Hello Library - Library Management System is running!";
    }
}
