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

    @GetMapping
    public String listLibraries(Model model) {
        model.addAttribute("libraries", libraryService.getAllLibraries());
        return "library/index";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("library", new Library());
        return "library/form";
    }

    @PostMapping
    public String createLibrary(@ModelAttribute Library library) {
        libraryService.saveLibrary(library);
        return "redirect:/libraries";
    }

    @PostMapping("/{id}/delete")
    public String deleteLibrary(@PathVariable Long id) {
        libraryService.deleteLibrary(id);
        return "redirect:/libraries";
    }

    @PostMapping("/{id}")
    public String updateLibrary(@ModelAttribute Library library, @PathVariable Long id) {
        library.setId(id);
        libraryService.saveLibrary(library);
        return "redirect:/libraries";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        libraryService.getLibraryById(id).ifPresent(library -> {
            model.addAttribute("library", library);
        });
        return "library/form";
    }
}