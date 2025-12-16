package com.lms.LMS.service;

import com.lms.LMS.model.Member;
import com.lms.LMS.repo.MemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public List<Member> getFilteredAndSortedMembers(String name, String email, String sortBy, String sortOrder) {
        Sort.Direction direction = "desc".equalsIgnoreCase(sortOrder) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort sort = Sort.by(direction, sortBy != null && !sortBy.isEmpty() ? sortBy : "id");

        if ((name == null || name.isEmpty()) && (email == null || email.isEmpty())) {
            return memberRepository.findAll(sort);
        }

        if (name != null && !name.isEmpty() && (email == null || email.isEmpty())) {
            return memberRepository.findByNameContainingIgnoreCase(name, sort);
        }

        if ((name == null || name.isEmpty()) && email != null && !email.isEmpty()) {
            return memberRepository.findByEmailContainingIgnoreCase(email, sort);
        }

        return memberRepository.findByNameContainingIgnoreCaseAndEmailContainingIgnoreCase(name, email, sort);
    }

    public Optional<Member> getMemberById(Long id) {
        return memberRepository.findById(id);
    }

    public void deleteMember(Long id) {
        Optional<Member> memberOpt = memberRepository.findById(id);
        if (memberOpt.isPresent()) {
            Member member = memberOpt.get();

            boolean hasActiveLoans = member.getLoans().stream()
                    .anyMatch(loan -> loan.getStatus().toString().equals("ACTIVE"));

            if (hasActiveLoans) {
                throw new RuntimeException("Cannot delete member with active loans");
            }

            memberRepository.deleteById(id);
        }
    }

    public List<Member> getMembersByLibraryId(Long libraryId) {
        return memberRepository.findAll().stream()
                .filter(m -> m.getLibrary().getId().equals(libraryId))
                .toList();
    }

    public long getMembersCount() {
        return memberRepository.count();
    }
}