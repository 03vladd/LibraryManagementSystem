package com.lms.LMS.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ReservationController {
    @GetMapping("/Reservation")
    @ResponseBody
    public String testController2() {
        return "Hello Reservation - Reservation endpoint is running!";
    }

}
