package com.lms.LMS.repo;

import com.lms.LMS.model.Loan;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class LoanRepo {
    private final Map<String, Loan> loans = new HashMap<>();

    public Loan save(Loan loan) {
        loans.put(loan.getId(), loan);
        return loan;
    }

    public List<Loan> findAll() {
        return new ArrayList<>(loans.values());
    }

    public Optional<Loan> findById(String id) {
        return Optional.ofNullable(loans.get(id));
    }

    public boolean deleteById(String id) {
        return loans.remove(id) != null;
    }

    public boolean existsById(String id) {
        return loans.containsKey(id);
    }

    public long count() {
        return loans.size();
    }

    // Find by member ID
    public List<Loan> findByMemberId(String memberId) {
        return loans.values().stream()
                .filter(loan -> loan.getMemberId().equals(memberId))
                .toList();
    }
}