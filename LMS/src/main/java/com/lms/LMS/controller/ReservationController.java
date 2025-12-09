package com.lms.LMS.controller;

import com.lms.LMS.model.Reservation;
import com.lms.LMS.service.ReservationService;
import com.lms.LMS.service.LoanService;
import com.lms.LMS.service.ReadableItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationService reservationService;
    private final LoanService loanService;
    private final ReadableItemService readableItemService;

    public ReservationController(ReservationService reservationService,
                                 LoanService loanService,
                                 ReadableItemService readableItemService) {
        this.reservationService = reservationService;
        this.loanService = loanService;
        this.readableItemService = readableItemService;
    }

    @GetMapping
    public String listReservations(Model model) {
        model.addAttribute("reservations", reservationService.getAllReservations());
        return "reservation/index";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("reservation", new Reservation());
        model.addAttribute("loans", loanService.getAllLoans());
        model.addAttribute("items", readableItemService.getAllReadableItems());
        return "reservation/form";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        reservationService.getReservationById(id).ifPresent(reservation -> {
            model.addAttribute("reservation", reservation);
        });
        model.addAttribute("loans", loanService.getAllLoans());
        model.addAttribute("items", readableItemService.getAllReadableItems());
        return "reservation/form";
    }

    @PostMapping
    public String createReservation(@ModelAttribute Reservation reservation) {
        reservationService.saveReservation(reservation);
        return "redirect:/reservations";
    }

    @PostMapping("/{id}")
    public String updateReservation(@PathVariable Long id, @ModelAttribute Reservation reservation) {
        reservation.setId(id);
        reservationService.saveReservation(reservation);
        return "redirect:/reservations";
    }

    @PostMapping("/{id}/delete")
    public String deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservation(id);
        return "redirect:/reservations";
    }
}