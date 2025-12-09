package com.lms.LMS.controller;

import com.lms.LMS.model.Member;
import com.lms.LMS.service.MemberService;
import com.lms.LMS.service.LibraryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String listMembers(Model model) {
        model.addAttribute("members", memberService.getAllMembers());
        return "member/index";
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
    public String createMember(@ModelAttribute Member member) {
        memberService.saveMember(member);
        return "redirect:/members";
    }

    @PostMapping("/{id}")
    public String updateMember(@PathVariable Long id, @ModelAttribute Member member) {
        member.setId(id);
        memberService.saveMember(member);
        return "redirect:/members";
    }

    @PostMapping("/{id}/delete")
    public String deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
        return "redirect:/members";
    }
}