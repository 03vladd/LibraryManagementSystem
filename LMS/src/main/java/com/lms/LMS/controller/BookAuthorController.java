package com.lms.LMS.controller;

import com.lms.LMS.exception.EntityDeletionException;
import com.lms.LMS.model.BookAuthor;
import com.lms.LMS.service.BookAuthorService;
import com.lms.LMS.service.BookDetailsService;
import com.lms.LMS.service.AuthorService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/bookauthors")
public class BookAuthorController {

    private final BookAuthorService bookAuthorService;
    private final BookDetailsService bookDetailsService;
    private final AuthorService authorService;

    public BookAuthorController(BookAuthorService bookAuthorService,
                                BookDetailsService bookDetailsService,
                                AuthorService authorService) {
        this.bookAuthorService = bookAuthorService;
        this.bookDetailsService = bookDetailsService;
        this.authorService = authorService;
    }

    @GetMapping
    public String listBookAuthors(Model model) {
        model.addAttribute("bookAuthors", bookAuthorService.getAllBookAuthors());
        return "bookauthor/index";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("bookAuthor", new BookAuthor());
        model.addAttribute("books", bookDetailsService.getAllBooks());
        model.addAttribute("authors", authorService.getAllAuthors());
        return "bookauthor/form";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        bookAuthorService.getBookAuthorById(id).ifPresent(bookAuthor -> {
            model.addAttribute("bookAuthor", bookAuthor);
        });
        model.addAttribute("books", bookDetailsService.getAllBooks());
        model.addAttribute("authors", authorService.getAllAuthors());
        return "bookauthor/form";
    }

    @PostMapping
    public String createBookAuthor(@Valid @ModelAttribute BookAuthor bookAuthor, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("books", bookDetailsService.getAllBooks());
            model.addAttribute("authors", authorService.getAllAuthors());
            return "bookauthor/form";
        }
        bookAuthorService.saveBookAuthor(bookAuthor);
        return "redirect:/bookauthors";
    }

    @PostMapping("/{id}")
    public String updateBookAuthor(@PathVariable Long id, @Valid @ModelAttribute BookAuthor bookAuthor, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("books", bookDetailsService.getAllBooks());
            model.addAttribute("authors", authorService.getAllAuthors());
            return "bookauthor/form";
        }
        bookAuthor.setId(id);
        bookAuthorService.saveBookAuthor(bookAuthor);
        return "redirect:/bookauthors";
    }

    @PostMapping("/{id}/delete")
    public String deleteBookAuthor(@PathVariable Long id, Model model) {
        try {
            bookAuthorService.deleteBookAuthor(id);
            return "redirect:/bookauthors";
        } catch (EntityDeletionException e) {
            model.addAttribute("bookAuthors", bookAuthorService.getAllBookAuthors());
            model.addAttribute("error", e.getMessage());
            return "bookauthor/index";
        }
    }
}