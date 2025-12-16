package com.lms.LMS.controller;

import com.lms.LMS.exception.EntityDeletionException;
import com.lms.LMS.model.Reservation;
import com.lms.LMS.service.ReservationService;
import com.lms.LMS.service.LoanService;
import com.lms.LMS.service.ReadableItemService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    public String createReservation(@Valid @ModelAttribute Reservation reservation, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("loans", loanService.getAllLoans());
            model.addAttribute("items", readableItemService.getAllReadableItems());
            return "reservation/form";
        }
        reservationService.saveReservation(reservation);
        return "redirect:/reservations";
    }

    @PostMapping("/{id}")
    public String updateReservation(@PathVariable Long id, @Valid @ModelAttribute Reservation reservation, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("loans", loanService.getAllLoans());
            model.addAttribute("items", readableItemService.getAllReadableItems());
            return "reservation/form";
        }
        reservation.setId(id);
        reservationService.saveReservation(reservation);
        return "redirect:/reservations";
    }

    @PostMapping("/{id}/delete")
    public String deleteReservation(@PathVariable Long id, Model model) {
        try {
            reservationService.deleteReservation(id);
            return "redirect:/reservations";
        } catch (EntityDeletionException e) {
            model.addAttribute("reservations", reservationService.getAllReservations());
            model.addAttribute("error", e.getMessage());
            return "reservation/index";
        }
    }
}