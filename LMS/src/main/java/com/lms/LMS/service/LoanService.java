package com.lms.LMS.service;

import com.lms.LMS.model.Loan;
import com.lms.LMS.model.ReadableItems;
import com.lms.LMS.model.Reservation;
import com.lms.LMS.repo.LoanRepository;
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

    public Optional<Loan> getLoanById(Long id) {
        return loanRepository.findById(id);
    }

    public void deleteLoan(Long id) {
        loanRepository.deleteById(id);
    }

    public List<Loan> getLoansByMemberId(Long memberId) {
        return loanRepository.findAll().stream()
                .filter(l -> l.getMember().getId().equals(memberId))
                .toList();
    }

    public Loan addItemToLoan(Long loanId, ReadableItems item) {
        Optional<Loan> loanOpt = loanRepository.findById(loanId);
        if (loanOpt.isPresent()) {
            Loan loan = loanOpt.get();
            loan.getItems().add(item);
            return loanRepository.save(loan);
        }
        return null;
    }

    public Loan addReservationToLoan(Long loanId, Reservation reservation) {
        Optional<Loan> loanOpt = loanRepository.findById(loanId);
        if (loanOpt.isPresent()) {
            Loan loan = loanOpt.get();
            loan.getReservations().add(reservation);
            return loanRepository.save(loan);
        }
        return null;
    }

    public long getLoansCount() {
        return loanRepository.count();
    }
}