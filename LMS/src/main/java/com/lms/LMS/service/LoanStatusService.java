package com.lms.LMS.service;

import com.lms.LMS.model.Loan;
import com.lms.LMS.model.LoanStatus;
import com.lms.LMS.repo.LoanRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class LoanStatusService {
    private final LoanRepository loanRepository;

    // Configurable loan duration (in days)
    private static final long LOAN_DURATION_DAYS = 14;

    public LoanStatusService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    /**
     * Automatically update loan statuses:
     * - ACTIVE loans past due date → OVERDUE
     * Runs every hour at minute 0
     */
    @Scheduled(cron = "0 0 * * * *")
    public void updateOverdueLoans() {
        List<Loan> activeLoans = loanRepository.findByStatus(LoanStatus.ACTIVE);
        LocalDate today = LocalDate.now();

        for (Loan loan : activeLoans) {
            if (loan.getDate() != null) {
                long daysElapsed = ChronoUnit.DAYS.between(loan.getDate(), today);

                if (daysElapsed > LOAN_DURATION_DAYS) {
                    loan.setStatus(LoanStatus.OVERDUE);
                    loanRepository.save(loan);
                }
            }
        }
    }

    /**
     * Mark a loan as returned and update status
     */
    public void returnLoan(Long loanId) {
        loanRepository.findById(loanId).ifPresent(loan -> {
            loan.setStatus(LoanStatus.RETURNED);
            loanRepository.save(loan);
        });
    }

    /**
     * Check if a loan is overdue
     */
    public boolean isOverdue(Loan loan) {
        if (loan.getStatus() != LoanStatus.ACTIVE || loan.getDate() == null) {
            return false;
        }

        LocalDate today = LocalDate.now();
        long daysElapsed = ChronoUnit.DAYS.between(loan.getDate(), today);
        return daysElapsed > LOAN_DURATION_DAYS;
    }

    /**
     * Get days remaining for a loan (negative if overdue)
     */
    public long getDaysRemaining(Loan loan) {
        if (loan.getDate() == null) {
            return 0;
        }

        LocalDate dueDate = loan.getDate().plusDays(LOAN_DURATION_DAYS);
        return ChronoUnit.DAYS.between(LocalDate.now(), dueDate);
    }

    /**
     * Get loan duration
     */
    public static long getLoanDurationDays() {
        return LOAN_DURATION_DAYS;
    }

    /**
     * Manual trigger for testing - updates overdue status immediately
     */
    public void updateOverdueLoansNow() {
        updateOverdueLoans();
    }
}