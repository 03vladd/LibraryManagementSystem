package com.lms.LMS.controller;

import com.lms.LMS.exception.EntityDeletionException;
import com.lms.LMS.model.Member;
import com.lms.LMS.service.MemberService;
import com.lms.LMS.service.LibraryService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;
    private final LibraryService libraryService;

    public MemberController(MemberService memberService,
                            LibraryService libraryService) {
        this.memberService = memberService;
        this.libraryService = libraryService;
    }

    @GetMapping
    public String listMembers(@RequestParam(required = false) String name,
                              @RequestParam(required = false) String email,
                              @RequestParam(defaultValue = "id") String sortBy,
                              @RequestParam(defaultValue = "asc") String sortOrder,
                              Model model) {
        model.addAttribute("members", memberService.getFilteredAndSortedMembers(name, email, sortBy, sortOrder));
        model.addAttribute("filterName", name);
        model.addAttribute("filterEmail", email);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("sortOrder", sortOrder);
        return "member/index";
    }

    @GetMapping("/{id}/details")
    public String showMemberDetails(@PathVariable Long id, Model model) {
        memberService.getMemberById(id).ifPresent(member -> {
            model.addAttribute("member", member);
        });
        return "member/details";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("member", new Member());
        model.addAttribute("libraries", libraryService.getAllLibraries());
        return "member/form";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        memberService.getMemberById(id).ifPresent(member -> {
            model.addAttribute("member", member);
            model.addAttribute("libraries", libraryService.getAllLibraries());
        });
        return "member/form";
    }

    @PostMapping
    public String createMember(@Valid @ModelAttribute Member member, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("libraries", libraryService.getAllLibraries());
            return "member/form";
        }
        memberService.saveMember(member);
        return "redirect:/members";
    }

    @PostMapping("/{id}")
    public String updateMember(@PathVariable Long id, @Valid @ModelAttribute Member member, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("libraries", libraryService.getAllLibraries());
            return "member/form";
        }
        member.setId(id);
        memberService.saveMember(member);
        return "redirect:/members";
    }

    @PostMapping("/{id}/delete")
    public String deleteMember(@PathVariable Long id, Model model) {
        try {
            memberService.deleteMember(id);
            return "redirect:/members";
        } catch (Exception e) {
            model.addAttribute("members", memberService.getAllMembers());
            model.addAttribute("error", e.getMessage());
            return "member/index";
        }
    }
}