package com.lms.LMS.controller;

import com.lms.LMS.model.ReadableItems;
import com.lms.LMS.service.ReadableItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/items")
public class ReadableItemsController {

    private final ReadableItemService readableItemService;

    public ReadableItemsController(ReadableItemService readableItemService) {
        this.readableItemService = readableItemService;
    }

    @GetMapping
    public String listItems(Model model) {
        model.addAttribute("items", readableItemService.getAllReadableItems());
        return "readableitems/index";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("item", new ReadableItems());
        return "readableitems/form";
    }

    @PostMapping
    public String createItem(@ModelAttribute ReadableItems item) {
        readableItemService.saveReadableItem(item);
        return "redirect:/items";
    }

    @PostMapping("/{id}/delete")
    public String deleteItem(@PathVariable String id) {
        readableItemService.deleteReadableItem(id);
        return "redirect:/items";
    }
}