package com.lms.LMS.controller;

import com.lms.LMS.model.ReadableItems;
import com.lms.LMS.service.ReadableItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ReadableItemsController {

    private final ReadableItemService readableItemService;

    public ReadableItemsController(ReadableItemService readableItemService) {
        this.readableItemService = readableItemService;
    }

    @GetMapping("/ReadableItems")
    public String listItems(Model model) {
        model.addAttribute("items", readableItemService.getAllReadableItems());
        return "readableitems/index";
    }

    @GetMapping("/ReadableItems/new")
    public String showCreateForm(Model model) {
        model.addAttribute("item", new ReadableItems());
        return "readableitems/form";
    }

    @PostMapping("/ReadableItems")
    public String createItem(@ModelAttribute ReadableItems item) {
        readableItemService.saveReadableItem(item);
        return "redirect:/ReadableItems";
    }

    @PostMapping("/ReadableItems/{id}/delete")
    public String deleteItem(@PathVariable String id) {
        readableItemService.deleteReadableItem(id);
        return "redirect:/ReadableItems";
    }

    @PostMapping("ReadbleItems/{id}/update")
    public String updateItem(@PathVariable String id, @ModelAttribute ReadableItems item) {
        readableItemService.saveReadableItem(item);
        return "redirect:/ReadableItems";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable String id, Model model) {
        readableItemService.getReadableItemById(id).ifPresent(readableItem -> {
            model.addAttribute("item", readableItem);
        });
        return "readableitems/form";
    }
}