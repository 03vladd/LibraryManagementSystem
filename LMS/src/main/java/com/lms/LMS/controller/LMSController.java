package com.lms.LMS.controller;

import com.lms.LMS.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LMSController {

    private final LibraryService libraryService;
    private final MemberService memberService;
    private final LoanService loanService;
    private final AuthorService authorService;
    private final BookDetailsService bookDetailsService;
    private final MagazineDetailsService magazineDetailsService;
    private final ReservationService reservationService;
    private final ReadableItemService readableItemService;

    public LMSController(LibraryService libraryService,
                         MemberService memberService,
                         LoanService loanService,
                         AuthorService authorService,
                         BookDetailsService bookDetailsService,
                         MagazineDetailsService magazineDetailsService,
                         ReservationService reservationService,
                         ReadableItemService readableItemService) {
        this.libraryService = libraryService;
        this.memberService = memberService;
        this.loanService = loanService;
        this.authorService = authorService;
        this.bookDetailsService = bookDetailsService;
        this.magazineDetailsService = magazineDetailsService;
        this.reservationService = reservationService;
        this.readableItemService = readableItemService;
    }

    @GetMapping("/")
    public String home(Model model) {
        // Get statistics
        model.addAttribute("librariesCount", libraryService.getLibrariesCount());
        model.addAttribute("membersCount", memberService.getMembersCount());
        model.addAttribute("loansCount", loanService.getLoansCount());
        model.addAttribute("authorsCount", authorService.getAuthorsCount());
        model.addAttribute("booksCount", bookDetailsService.getBooksCount());
        model.addAttribute("magazinesCount", magazineDetailsService.getMagazinesCount());
        model.addAttribute("reservationsCount", reservationService.getReservationsCount());
        model.addAttribute("itemsCount", readableItemService.getItemsCount());

        // Get recent data
        model.addAttribute("recentLoans", loanService.getAllLoans().stream().limit(5).toList());
        model.addAttribute("allMembers", memberService.getAllMembers());

        return "home";
    }
}