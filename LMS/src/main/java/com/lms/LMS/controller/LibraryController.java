package com.lms.LMS.controller;

import com.lms.LMS.model.Library;
import com.lms.LMS.service.LibraryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/libraries")
public class LibraryController {

    private final LibraryService libraryService;

    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    // GET /libraries - Show all libraries
    @GetMapping
    public String listLibraries(Model model) {
        model.addAttribute("libraries", libraryService.getAllLibraries());
        return "library/index";
    }

    // GET /libraries/new - Show create form
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("library", new Library());
        return "library/form";
    }

    // POST /libraries - Create new library
    @PostMapping
    public String createLibrary(@ModelAttribute Library library) {
        libraryService.saveLibrary(library);
        return "redirect:/libraries";
    }

    // POST /libraries/{id}/delete - Delete library
    @PostMapping("/{id}/delete")
    public String deleteLibrary(@PathVariable String id) {
        libraryService.deleteLibrary(id);
        return "redirect:/libraries";
    }

    @PostMapping("/{id}")
    public String updateLibrary(@ModelAttribute Library library, @PathVariable String id) {
        libraryService.updateLibrary(id, library);
        return "redirect:/libraries";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable String id,  Model model) {
        libraryService.getLibraryById(id).ifPresent(library -> {
            model.addAttribute("library", library);
        });
        return "redirect:/libraries";
    }
}