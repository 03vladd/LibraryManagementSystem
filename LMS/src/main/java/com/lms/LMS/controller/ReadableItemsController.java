package com.lms.LMS.controller;

import com.lms.LMS.model.ReadableItems;
import com.lms.LMS.service.ReadableItemService;
import com.lms.LMS.service.LibraryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/readableitems")
public class ReadableItemsController {

    private final ReadableItemService readableItemService;
    private final LibraryService libraryService;

    public ReadableItemsController(ReadableItemService readableItemService,
                                   LibraryService libraryService) {
        this.readableItemService = readableItemService;
        this.libraryService = libraryService;
    }

    @GetMapping
    public String listReadableItems(Model model) {
        model.addAttribute("items", readableItemService.getAllReadableItems());
        return "readableitems/index";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("item", new ReadableItems());
        model.addAttribute("libraries", libraryService.getAllLibraries());
        return "readableitems/form";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        readableItemService.getReadableItemById(id).ifPresent(item -> {
            model.addAttribute("item", item);
        });
        model.addAttribute("libraries", libraryService.getAllLibraries());
        return "readableitems/form";
    }

    @PostMapping
    public String createReadableItem(@ModelAttribute ReadableItems item) {
        readableItemService.saveReadableItem(item);
        return "redirect:/readableitems";
    }

    @PostMapping("/{id}")
    public String updateReadableItem(@PathVariable Long id, @ModelAttribute ReadableItems item) {
        item.setId(id);
        readableItemService.saveReadableItem(item);
        return "redirect:/readableitems";
    }

    @PostMapping("/{id}/delete")
    public String deleteReadableItem(@PathVariable Long id) {
        readableItemService.deleteReadableItem(id);
        return "redirect:/readableitems";
    }
}