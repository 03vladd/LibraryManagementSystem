package com.lms.LMS.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller

public class LoanController {
    @GetMapping("/Loan")
    @ResponseBody
    public String testController() {
        return "Hello Loan - Loan endpoint is running!";
    }

}
