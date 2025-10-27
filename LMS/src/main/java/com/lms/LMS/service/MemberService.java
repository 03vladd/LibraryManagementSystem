package com.lms.LMS.service;

import com.lms.LMS.model.Loan;
import com.lms.LMS.model.Member;
import com.lms.LMS.repo.MemberRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    private final MemberRepo memberRepo;

    public MemberService(MemberRepo memberRepo) {
        this.memberRepo = memberRepo;
    }

    // Create or update member
    public Member saveMember(Member member) {
        return memberRepo.save(member);
    }

    // Get all members
    public List<Member> getAllMembers() {
        return memberRepo.findAll();
    }

    // Get member by ID
    public Optional<Member> getMemberById(String id) {
        return memberRepo.findById(id);
    }

    // Delete member
    public boolean deleteMember(String id) {
        return memberRepo.deleteById(id);
    }

    // Get members by library ID
    public List<Member> getMembersByLibraryId(String libraryId) {
        return memberRepo.findByLibraryId(libraryId);
    }

    // Add loan to member
    public Member addLoanToMember(String memberId, Loan loan) {
        Optional<Member> memberOpt = memberRepo.findById(memberId);
        if (memberOpt.isPresent()) {
            Member member = memberOpt.get();
            return memberRepo.save(member);
        }
        return null;
    }

    // Get total members count
    public long getMembersCount() {
        return memberRepo.count();
    }
}