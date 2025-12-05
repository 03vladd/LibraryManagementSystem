package com.lms.LMS.service;

import com.lms.LMS.model.Loan;
import com.lms.LMS.model.Member;
import com.lms.LMS.repo.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member saveMember(Member member) {
        return memberRepository.save(member);
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> getMemberById(Long id) {
        return memberRepository.findById(id);
    }

    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }

    public List<Member> getMembersByLibraryId(Long libraryId) {
        return memberRepository.findAll().stream()
                .filter(m -> m.getLibrary().getId().equals(libraryId))
                .toList();
    }

    public Member addLoanToMember(Long memberId, Loan loan) {
        Optional<Member> memberOpt = memberRepository.findById(memberId);
        if (memberOpt.isPresent()) {
            Member member = memberOpt.get();
            member.addLoan(loan);
            return memberRepository.save(member);
        }
        return null;
    }

    public long getMembersCount() {
        return memberRepository.count();
    }
}