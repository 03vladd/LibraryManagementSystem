package com.lms.LMS.repo;

import com.lms.LMS.model.Loan;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LoanRepo extends BaseRepo<Loan> {

    @Override
    protected String getId(Loan entity) {
        return entity.getId();
    }

    // Custom method - Find by member ID
    public List<Loan> findByMemberId(String memberId) {
        return data.values().stream()
                .filter(loan -> loan.getMemberId().equals(memberId))
                .toList();
    }
}