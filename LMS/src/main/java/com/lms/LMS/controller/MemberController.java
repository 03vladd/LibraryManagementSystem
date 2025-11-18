package com.lms.LMS.controller;

import com.lms.LMS.model.Member;
import com.lms.LMS.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // GET /members - Show all members
    @GetMapping
    public String listMembers(Model model) {
        model.addAttribute("members", memberService.getAllMembers());
        return "member/index";
    }

    // GET /members/new - Show create form
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("member", new Member());
        return "member/form";
    }

    // GET /members/{id}/edit - Show edit form
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable String id, Model model) {
        memberService.getMemberById(id).ifPresent(member -> {
            model.addAttribute("member", member);
        });
        return "member/form";
    }

    // POST /members - Create new member
    @PostMapping
    public String createMember(@ModelAttribute Member member) {
        memberService.saveMember(member);
        return "redirect:/members";
    }

    // POST /members/{id} - Update member
    @PostMapping("/{id}/update")
    public String updateMember(@PathVariable String id, @ModelAttribute Member member) {
        memberService.updateMember(id, member);
        return "redirect:/members";
    }

    // POST /members/{id}/delete - Delete member
    @PostMapping("/{id}/delete")
    public String deleteMember(@PathVariable String id) {
        memberService.deleteMember(id);
        return "redirect:/members";
    }
}