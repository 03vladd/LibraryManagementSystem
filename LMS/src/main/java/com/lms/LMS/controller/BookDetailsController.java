package com.lms.LMS.controller;

import com.lms.LMS.model.BookDetails;
import com.lms.LMS.service.BookDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BookDetailsController {

    private final BookDetailsService bookDetailsService;

    public BookDetailsController(BookDetailsService bookDetailsService) {
        this.bookDetailsService = bookDetailsService;
    }

    @GetMapping
    public String listBooks(Model model) {
        model.addAttribute("books", bookDetailsService.getAllBooks());
        return "bookdetails/index";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("book", new BookDetails());
        return "bookdetails/form";
    }

    @PostMapping
    public String createBook(@ModelAttribute BookDetails book) {
        bookDetailsService.saveBook(book);
        return "redirect:/books";
    }

    @PostMapping("/{id}/delete")
    public String deleteBook(@PathVariable String id) {
        bookDetailsService.deleteBook(id);
        return "redirect:/books";
    }
}