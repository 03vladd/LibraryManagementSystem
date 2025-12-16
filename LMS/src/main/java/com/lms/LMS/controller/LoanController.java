package com.lms.LMS.controller;

import com.lms.LMS.model.Loan;
import com.lms.LMS.model.LoanStatus;
import com.lms.LMS.model.ReadableItems;
import com.lms.LMS.repo.ReadableItemsRepository;
import com.lms.LMS.service.LoanService;
import com.lms.LMS.service.LoanStatusService;
import com.lms.LMS.service.MemberService;
import com.lms.LMS.service.ReadableItemService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/loans")
public class LoanController {

    private final LoanService loanService;
    private final MemberService memberService;
    private final ReadableItemService readableItemService;
    private final ReadableItemsRepository readableItemRepository;
    private final LoanStatusService loanStatusService;

    public LoanController(LoanService loanService,
                          MemberService memberService,
                          ReadableItemService readableItemService,
                          ReadableItemsRepository readableItemRepository,
                          LoanStatusService loanStatusService) {
        this.loanService = loanService;
        this.memberService = memberService;
        this.readableItemService = readableItemService;
        this.readableItemRepository = readableItemRepository;
        this.loanStatusService = loanStatusService;
    }

    @GetMapping
    public String listLoans(@RequestParam(required = false) String memberName,
                            @RequestParam(required = false) String status,
                            @RequestParam(required = false) String sortBy,
                            @RequestParam(required = false) String sortOrder,
                            Model model) {
        // Update overdue status before listing
        loanStatusService.updateOverdueLoansNow();

        String finalSortBy = (sortBy == null || sortBy.isEmpty()) ? "id" : sortBy;
        String finalSortOrder = (sortOrder == null || sortOrder.isEmpty()) ? "asc" : sortOrder;

        model.addAttribute("loans", loanService.getFilteredAndSortedLoans(memberName, status, finalSortBy, finalSortOrder));
        model.addAttribute("filterMemberName", memberName);
        model.addAttribute("filterStatus", status);
        model.addAttribute("sortBy", finalSortBy);
        model.addAttribute("sortOrder", finalSortOrder);
        model.addAttribute("statuses", LoanStatus.values());
        return "loan/index";
    }

    @GetMapping("/{id}/details")
    public String showLoanDetails(@PathVariable Long id, Model model) {
        loanService.getLoanById(id).ifPresent(loan -> {
            model.addAttribute("loan", loan);
        });
        return "loan/details";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("loan", new Loan());
        model.addAttribute("members", memberService.getAllMembers());
        model.addAttribute("items", readableItemService.getAllReadableItems());
        model.addAttribute("statuses", LoanStatus.values());
        return "loan/form";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        loanService.getLoanById(id).ifPresent(loan -> {
            model.addAttribute("loan", loan);
            model.addAttribute("selectedItemIds", loan.getItems().stream().map(ReadableItems::getId).toList());
        });
        model.addAttribute("members", memberService.getAllMembers());
        model.addAttribute("items", readableItemService.getAllReadableItems());
        model.addAttribute("statuses", LoanStatus.values());
        return "loan/form";
    }

    @PostMapping
    public String createLoan(@Valid @ModelAttribute Loan loan,
                             BindingResult result,
                             @RequestParam(required = false) List<Long> itemIds,
                             Model model) {
        if (result.hasErrors()) {
            model.addAttribute("members", memberService.getAllMembers());
            model.addAttribute("items", readableItemService.getAllReadableItems());
            model.addAttribute("statuses", LoanStatus.values());
            return "loan/form";
        }

        // Set default status if not provided
        if (loan.getStatus() == null) {
            loan.setStatus(LoanStatus.ACTIVE);
        }

        // Add items to loan
        if (itemIds != null && !itemIds.isEmpty()) {
            List<ReadableItems> selectedItems = new ArrayList<>();
            for (Long itemId : itemIds) {
                readableItemRepository.findById(itemId).ifPresent(item -> {
                    item.setLoan(loan);
                    selectedItems.add(item);
                });
            }
            loan.setItems(selectedItems);
        }

        loanService.saveLoan(loan);
        return "redirect:/loans";
    }

    @PostMapping("/{id}")
    public String updateLoan(@PathVariable Long id,
                             @Valid @ModelAttribute Loan loan,
                             BindingResult result,
                             @RequestParam(required = false) List<Long> itemIds,
                             Model model) {
        if (result.hasErrors()) {
            model.addAttribute("members", memberService.getAllMembers());
            model.addAttribute("items", readableItemService.getAllReadableItems());
            model.addAttribute("statuses", LoanStatus.values());
            return "loan/form";
        }

        loan.setId(id);

        // Update items
        if (itemIds != null && !itemIds.isEmpty()) {
            List<ReadableItems> selectedItems = new ArrayList<>();
            for (Long itemId : itemIds) {
                readableItemRepository.findById(itemId).ifPresent(item -> {
                    item.setLoan(loan);
                    selectedItems.add(item);
                });
            }
            loan.setItems(selectedItems);
        } else {
            loan.setItems(new ArrayList<>());
        }

        loanService.saveLoan(loan);
        return "redirect:/loans";
    }

    @PostMapping("/{id}/delete")
    public String deleteLoan(@PathVariable Long id, Model model) {
        try {
            loanService.deleteLoan(id);
            return "redirect:/loans";
        } catch (Exception e) {
            model.addAttribute("loans", loanService.getAllLoans());
            model.addAttribute("error", e.getMessage());
            return "loan/index";
        }
    }

    @PostMapping("/{id}/return")
    public String returnLoan(@PathVariable Long id, Model model) {
        try {
            loanService.getLoanById(id).ifPresent(loan -> {
                loan.setStatus(LoanStatus.RETURNED);
                loanService.saveLoan(loan);
            });
            return "redirect:/loans";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "loan/details";
        }
    }
}