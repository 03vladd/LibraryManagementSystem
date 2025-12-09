package com.lms.LMS.controller;

import com.lms.LMS.model.MagazineDetails;
import com.lms.LMS.service.MagazineDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/magazines")
public class MagazineDetailsController {

    private final MagazineDetailsService magazineDetailsService;

    public MagazineDetailsController(MagazineDetailsService magazineDetailsService) {
        this.magazineDetailsService = magazineDetailsService;
    }

    @GetMapping
    public String listMagazines(Model model) {
        model.addAttribute("magazines", magazineDetailsService.getAllMagazines());
        return "magazinedetails/index";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("magazine", new MagazineDetails());
        return "magazinedetails/form";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        magazineDetailsService.getMagazineById(id).ifPresent(magazine -> {
            model.addAttribute("magazine", magazine);
        });
        return "magazinedetails/form";
    }

    @PostMapping
    public String createMagazine(@ModelAttribute MagazineDetails magazine) {
        magazineDetailsService.saveMagazine(magazine);
        return "redirect:/magazines";
    }

    @PostMapping("/{id}")
    public String updateMagazine(@PathVariable Long id, @ModelAttribute MagazineDetails magazine) {
        magazine.setId(id);
        magazineDetailsService.saveMagazine(magazine);
        return "redirect:/magazines";
    }

    @PostMapping("/{id}/delete")
    public String deleteMagazine(@PathVariable Long id) {
        magazineDetailsService.deleteMagazine(id);
        return "redirect:/magazines";
    }
}