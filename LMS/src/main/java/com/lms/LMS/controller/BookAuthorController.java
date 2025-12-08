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
        model.addAttribute("bookAuthor", new BookAuthor());
        return "bookauthor/form";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        bookAuthorService.getBookAuthorById(id).ifPresent(bookAuthor -> {
            model.addAttribute("bookAuthor", bookAuthor);
        });
        return "bookauthor/form";
    }

    @PostMapping
    public String createBookAuthor(@ModelAttribute BookAuthor bookAuthor) {
        bookAuthorService.saveBookAuthor(bookAuthor);
        return "redirect:/bookauthors";
    }

    @PostMapping("/{id}")
    public String updateBookAuthor(@PathVariable Long id, @ModelAttribute BookAuthor bookAuthor) {
        bookAuthor.setId(id);
        bookAuthorService.saveBookAuthor(bookAuthor);
        return "redirect:/bookauthors";
    }

    @PostMapping("/{id}/delete")
    public String deleteBookAuthor(@PathVariable Long id) {
        bookAuthorService.deleteBookAuthor(id);
        return "redirect:/bookauthors";
    }
}