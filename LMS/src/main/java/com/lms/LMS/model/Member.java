package com.lms.LMS.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Member {
    private String id;
    private String name;
    private String libraryId;
    private String address;
    private List<Loan> loans = new ArrayList<>();

    private String phoneNumber;
    private String email;

    // Custom constructor for backward compatibility
    public Member(String id, String name, String libraryId, String address) {
        this.id = id;
        this.name = name;
        this.libraryId = libraryId;
        this.address = address;
        this.loans = new ArrayList<>();
    }

    // Keep the custom method
    public void addLoan(Loan loan) {
        this.loans.add(loan);
    }
}