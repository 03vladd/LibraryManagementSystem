package com.lms.LMS.controller;

import com.lms.LMS.model.Author;
import com.lms.LMS.service.AuthorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    // GET /authors - Show all authors
    @GetMapping
    public String listAuthors(Model model) {
        model.addAttribute("authors", authorService.getAllAuthors());
        return "author/index";
    }

    // GET /authors/new - Show create form
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("author", new Author());
        return "author/form";
    }

    // GET /authors/{id}/edit - Show edit form
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable String id, Model model) {
        authorService.getAuthorById(id).ifPresent(author -> {
            model.addAttribute("author", author);
        });
        return "author/form";
    }

    // POST /authors - Create new author
    @PostMapping
    public String createAuthor(@ModelAttribute Author author) {
        authorService.saveAuthor(author);
        return "redirect:/authors";
    }

    // POST /authors/{id} - Update author
    @PostMapping("/{id}")
    public String updateAuthor(@PathVariable String id, @ModelAttribute Author author) {
        authorService.updateBookAuthor(id, author);
        return "redirect:/authors";
    }

    // POST /authors/{id}/delete - Delete author
    @PostMapping("/{id}/delete")
    public String deleteAuthor(@PathVariable String id) {
        authorService.deleteAuthor(id);
        return "redirect:/authors";
    }
}