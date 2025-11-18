package com.lms.LMS.controller;

import com.lms.LMS.model.Reservation;
import com.lms.LMS.model.ReservationStatus;
import com.lms.LMS.service.ReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public String listReservations(Model model) {
        model.addAttribute("reservations", reservationService.getAllReservations());
        return "reservation/index";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("reservation", new Reservation(null, null, null, null, ReservationStatus.Active));
        return "reservation/form";
    }

    @PostMapping
    public String createReservation(@ModelAttribute Reservation reservation) {
        reservationService.saveReservation(reservation);
        return "redirect:/reservations";
    }

    @PostMapping("/{id}/delete")
    public String deleteReservation(@PathVariable String id) {
        reservationService.deleteReservation(id);
        return "redirect:/reservations";
    }

    @PostMapping("/{id}/update")
    public String updateReservation(@PathVariable String id, @ModelAttribute Reservation reservation) {
        reservationService.saveReservation(reservation);
        return "redirect:/reservations";
    }
}