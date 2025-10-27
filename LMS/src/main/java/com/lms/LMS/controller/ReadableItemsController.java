package com.lms.LMS.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class ReadableItemsController {
    @GetMapping("/ReadableItems")
    public String testReadableItems() {
        return "Hello Readable Items - Readable Items endpoint is running!";
    }
}

