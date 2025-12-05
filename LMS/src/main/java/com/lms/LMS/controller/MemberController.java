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

    @GetMapping
    public String listMembers(Model model) {
        model.addAttribute("members", memberService.getAllMembers());
        return "member/index";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("member", new Member());
        return "member/form";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        memberService.getMemberById(id).ifPresent(member -> {
            model.addAttribute("member", member);
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