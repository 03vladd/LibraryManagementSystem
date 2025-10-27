package com.lms.LMS.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MagazineDetailsController {
    @GetMapping("/MagazineDetails")
    public String testMagazineDetails() {
        return "Hello Magazine Details - Magazine Details endpoint is running!";
    }
}
