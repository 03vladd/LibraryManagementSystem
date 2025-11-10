package com.lms.LMS.controller;

import com.lms.LMS.model.BookAuthor;
import com.lms.LMS.service.BookAuthorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/bookauthors")
public class BookAuthorController {

    private final BookAuthorService bookAuthorService;

    public BookAuthorController(BookAuthorService bookAuthorService) {
        this.bookAuthorService = bookAuthorService;
    }

    @GetMapping
    public String listBookAuthors(Model model) {
        model.addAttribute("bookAuthors", bookAuthorService.getAllBookAuthors());
        return "bookauthor/index";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("bookAuthor", new BookAuthor(null, null, null));
        return "bookauthor/form";
    }

    @PostMapping
    public String createBookAuthor(@ModelAttribute BookAuthor bookAuthor) {
        bookAuthorService.saveBookAuthor(bookAuthor);
        return "redirect:/bookauthors";
    }

    @PostMapping("/{id}/delete")
    public String deleteBookAuthor(@PathVariable String id) {
        bookAuthorService.deleteBookAuthor(id);
        return "redirect:/bookauthors";
    }
}