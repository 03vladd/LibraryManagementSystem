package com.lms.LMS.service;

import com.lms.LMS.model.Loan;
import com.lms.LMS.model.ReadableItems;
import com.lms.LMS.model.Reservation;
import com.lms.LMS.repo.LoanRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoanService {
    private final LoanRepo loanRepo;

    public LoanService(LoanRepo loanRepo) {
        this.loanRepo = loanRepo;
    }

    // Create or update loan
    public Loan saveLoan(Loan loan) {
        return loanRepo.save(loan);
    }

    // Get all loans
    public List<Loan> getAllLoans() {
        return loanRepo.findAll();
    }

    // Get loan by ID
    public Optional<Loan> getLoanById(String id) {
        return loanRepo.findById(id);
    }

    // Delete loan
    public boolean deleteLoan(String id) {
        return loanRepo.deleteById(id);
    }

    // Get loans by member ID
    public List<Loan> getLoansByMemberId(String memberId) {
        return loanRepo.findByMemberId(memberId);
    }

    // Add item to loan
    public Loan addItemToLoan(String loanId, ReadableItems item) {
        Optional<Loan> loanOpt = loanRepo.findById(loanId);
        if (loanOpt.isPresent()) {
            Loan loan = loanOpt.get();
            return loanRepo.save(loan);
        }
        return null;
    }

    // Add reservation to loan
    public Loan addReservationToLoan(String loanId, Reservation reservation) {
        Optional<Loan> loanOpt = loanRepo.findById(loanId);
        if (loanOpt.isPresent()) {
            Loan loan = loanOpt.get();
            return loanRepo.save(loan);
        }
        return null;
    }

    // Get total loans count
    public long getLoansCount() {
        return loanRepo.count();
    }
}