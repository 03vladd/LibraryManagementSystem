package com.lms.LMS.controller;

import com.lms.LMS.model.Loan;
import com.lms.LMS.service.LoanService;
import com.lms.LMS.service.MemberService;
import com.lms.LMS.service.ReadableItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/loans")
public class LoanController {

    private final LoanService loanService;
    private final MemberService memberService;
    private final ReadableItemService readableItemService;

    public LoanController(LoanService loanService,
                          MemberService memberService,
                          ReadableItemService readableItemService) {
        this.loanService = loanService;
        this.memberService = memberService;
        this.readableItemService = readableItemService;
    }

    @GetMapping
    public String listLoans(Model model) {
        model.addAttribute("loans", loanService.getAllLoans());
        return "loan/index";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("loan", new Loan());
        model.addAttribute("members", memberService.getAllMembers());
        model.addAttribute("items", readableItemService.getAllReadableItems());
        return "loan/form";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        loanService.getLoanById(id).ifPresent(loan -> {
            model.addAttribute("loan", loan);
        });
        model.addAttribute("members", memberService.getAllMembers());
        model.addAttribute("items", readableItemService.getAllReadableItems());
        return "loan/form";
    }

    @PostMapping
    public String createLoan(@ModelAttribute Loan loan) {
        loanService.saveLoan(loan);
        return "redirect:/loans";
    }

    @PostMapping("/{id}")
    public String updateLoan(@PathVariable Long id, @ModelAttribute Loan loan) {
        loan.setId(id);
        loanService.saveLoan(loan);
        return "redirect:/loans";
    }

    @PostMapping("/{id}/delete")
    public String deleteLoan(@PathVariable Long id) {
        loanService.deleteLoan(id);
        return "redirect:/loans";
    }
}