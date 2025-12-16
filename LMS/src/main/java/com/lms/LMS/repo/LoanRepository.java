package com.lms.LMS.repo;

import com.lms.LMS.model.Loan;
import com.lms.LMS.model.LoanStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
    List<Loan> findByStatus(LoanStatus loanStatus);
}
