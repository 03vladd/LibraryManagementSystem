package com.lms.LMS.controller;

import com.lms.LMS.model.BookDetails;
import com.lms.LMS.service.BookDetailsService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BookDetailsController {

    private final BookDetailsService bookDetailsService;

    public BookDetailsController(BookDetailsService bookDetailsService) {
        this.bookDetailsService = bookDetailsService;
    }

    @GetMapping
    public String listBooks(@RequestParam(required = false) String title,
                            @RequestParam(required = false) String sortBy,
                            @RequestParam(required = false) String sortOrder,
                            Model model) {
        String finalSortBy = (sortBy == null || sortBy.isEmpty()) ? "id" : sortBy;
        String finalSortOrder = (sortOrder == null || sortOrder.isEmpty()) ? "asc" : sortOrder;

        model.addAttribute("books", bookDetailsService.getFilteredAndSortedBooks(title, finalSortBy, finalSortOrder));
        model.addAttribute("filterTitle", title);
        model.addAttribute("sortBy", finalSortBy);
        model.addAttribute("sortOrder", finalSortOrder);
        return "bookdetails/index";
    }

    @GetMapping("/{id}/details")
    public String showBookDetails(@PathVariable Long id, Model model) {
        bookDetailsService.getBookById(id).ifPresent(book -> {
            model.addAttribute("book", book);
        });
        return "bookdetails/details";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("book", new BookDetails());
        return "bookdetails/form";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        bookDetailsService.getBookById(id).ifPresent(book -> {
            model.addAttribute("book", book);
        });
        return "bookdetails/form";
    }

    @PostMapping
    public String createBook(@Valid @ModelAttribute BookDetails book, BindingResult result) {
        if (result.hasErrors()) {
            return "bookdetails/form";
        }
        bookDetailsService.saveBook(book);
        return "redirect:/books";
    }

    @PostMapping("/{id}")
    public String updateBook(@PathVariable Long id, @Valid @ModelAttribute BookDetails book, BindingResult result) {
        if (result.hasErrors()) {
            return "bookdetails/form";
        }
        book.setId(id);
        bookDetailsService.saveBook(book);
        return "redirect:/books";
    }

    @PostMapping("/{id}/delete")
    public String deleteBook(@PathVariable Long id, Model model) {
        try {
            bookDetailsService.deleteBook(id);
            return "redirect:/books";
        } catch (Exception e) {
            model.addAttribute("books", bookDetailsService.getAllBooks());
            model.addAttribute("error", e.getMessage());
            return "bookdetails/index";
        }
    }
}