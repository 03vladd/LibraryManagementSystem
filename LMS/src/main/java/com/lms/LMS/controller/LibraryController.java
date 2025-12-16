package com.lms.LMS.controller;

import com.lms.LMS.model.Library;
import com.lms.LMS.service.LibraryService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/libraries")
public class LibraryController {

    private final LibraryService libraryService;

    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping
    public String listLibraries(@RequestParam(required = false) String name,
                                @RequestParam(required = false) String sortBy,
                                @RequestParam(required = false) String sortOrder,
                                Model model) {
        String finalSortBy = (sortBy == null || sortBy.isEmpty()) ? "id" : sortBy;
        String finalSortOrder = (sortOrder == null || sortOrder.isEmpty()) ? "asc" : sortOrder;

        model.addAttribute("libraries", libraryService.getFilteredAndSortedLibraries(name, finalSortBy, finalSortOrder));
        model.addAttribute("filterName", name);
        model.addAttribute("sortBy", finalSortBy);
        model.addAttribute("sortOrder", finalSortOrder);
        return "library/index";
    }

    @GetMapping("/{id}/details")
    public String showLibraryDetails(@PathVariable Long id, Model model) {
        libraryService.getLibraryById(id).ifPresent(library -> {
            model.addAttribute("library", library);
        });
        return "library/details";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("library", new Library());
        return "library/form";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        libraryService.getLibraryById(id).ifPresent(library -> {
            model.addAttribute("library", library);
        });
        return "library/form";
    }

    @PostMapping
    public String createLibrary(@Valid @ModelAttribute Library library, BindingResult result) {
        if (result.hasErrors()) {
            return "library/form";
        }
        libraryService.saveLibrary(library);
        return "redirect:/libraries";
    }

    @PostMapping("/{id}")
    public String updateLibrary(@PathVariable Long id, @Valid @ModelAttribute Library library, BindingResult result) {
        if (result.hasErrors()) {
            return "library/form";
        }
        library.setId(id);
        libraryService.saveLibrary(library);
        return "redirect:/libraries";
    }

    @PostMapping("/{id}/delete")
    public String deleteLibrary(@PathVariable Long id, Model model) {
        try {
            libraryService.deleteLibrary(id);
            return "redirect:/libraries";
        } catch (Exception e) {
            model.addAttribute("libraries", libraryService.getAllLibraries());
            model.addAttribute("error", e.getMessage());
            return "library/index";
        }
    }
}