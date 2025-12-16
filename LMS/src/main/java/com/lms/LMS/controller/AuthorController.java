package com.lms.LMS.controller;

import com.lms.LMS.model.Author;
import com.lms.LMS.service.AuthorService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public String listAuthors(@RequestParam(required = false) String name,
                              @RequestParam(required = false) String nationality,
                              @RequestParam(required = false) String sortBy,
                              @RequestParam(required = false) String sortOrder,
                              Model model) {
        String finalSortBy = (sortBy == null || sortBy.isEmpty()) ? "id" : sortBy;
        String finalSortOrder = (sortOrder == null || sortOrder.isEmpty()) ? "asc" : sortOrder;

        model.addAttribute("authors", authorService.getFilteredAndSortedAuthors(name, nationality, finalSortBy, finalSortOrder));
        model.addAttribute("filterName", name);
        model.addAttribute("filterNationality", nationality);
        model.addAttribute("sortBy", finalSortBy);
        model.addAttribute("sortOrder", finalSortOrder);
        return "author/index";
    }

    @GetMapping("/{id}/details")
    public String showAuthorDetails(@PathVariable Long id, Model model) {
        authorService.getAuthorById(id).ifPresent(author -> {
            model.addAttribute("author", author);
        });
        return "author/details";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("author", new Author());
        return "author/form";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        authorService.getAuthorById(id).ifPresent(author -> {
            model.addAttribute("author", author);
        });
        return "author/form";
    }

    @PostMapping
    public String createAuthor(@Valid @ModelAttribute Author author, BindingResult result) {
        if (result.hasErrors()) {
            return "author/form";
        }
        authorService.saveAuthor(author);
        return "redirect:/authors";
    }

    @PostMapping("/{id}")
    public String updateAuthor(@PathVariable Long id, @Valid @ModelAttribute Author author, BindingResult result) {
        if (result.hasErrors()) {
            return "author/form";
        }
        author.setId(id);
        authorService.saveAuthor(author);
        return "redirect:/authors";
    }

    @PostMapping("/{id}/delete")
    public String deleteAuthor(@PathVariable Long id, Model model) {
        try {
            authorService.deleteAuthor(id);
            return "redirect:/authors";
        } catch (Exception e) {
            model.addAttribute("authors", authorService.getAllAuthors());
            model.addAttribute("error", e.getMessage());
            return "author/index";
        }
    }
}