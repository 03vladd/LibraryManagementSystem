package com.lms.LMS.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MemberController {
    @GetMapping("/Member")
    @ResponseBody
    public String testMember() {
        return "Hello Member - Member endpoint is running!";
    }
}
