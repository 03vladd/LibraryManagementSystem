package com.lms.LMS.service;

import com.lms.LMS.model.Loan;
import com.lms.LMS.model.LoanStatus;
import com.lms.LMS.repo.LoanRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoanService {
    private final LoanRepository loanRepository;

    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public Loan saveLoan(Loan loan) {
        return loanRepository.save(loan);
    }

    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    public List<Loan> getFilteredAndSortedLoans(String memberName, String status, String sortBy, String sortOrder) {
        Sort.Direction direction = "desc".equalsIgnoreCase(sortOrder) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort sort = Sort.by(direction, sortBy != null && !sortBy.isEmpty() ? sortBy : "id");

        List<Loan> loans = loanRepository.findAll(sort);

        if ((memberName == null || memberName.isEmpty()) && (status == null || status.isEmpty())) {
            return loans;
        }

        return loans.stream()
                .filter(loan -> memberName == null || memberName.isEmpty() ||
                        loan.getMember().getName().toLowerCase().contains(memberName.toLowerCase()))
                .filter(loan -> status == null || status.isEmpty() ||
                        loan.getStatus().toString().equalsIgnoreCase(status))
                .toList();
    }

    public Optional<Loan> getLoanById(Long id) {
        return loanRepository.findById(id);
    }

    public void deleteLoan(Long id) {
        loanRepository.deleteById(id);
    }

    public List<Loan> getLoansByMemberId(Long memberId) {
        return loanRepository.findAll().stream()
                .filter(loan -> loan.getMember().getId().equals(memberId))
                .toList();
    }

    public long getLoansCount() {
        return loanRepository.count();
    }
}