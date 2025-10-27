package com.lms.LMS.model;

import java.util.ArrayList;
import java.util.List;

public class Member {
    private String id;
    private String name;
    private String libraryId;
    private String address;
    private List<Loan> loans;

    // NEW PROPERTIES ADDED
    private String phoneNumber;
    private String email;

    // Constructor
    public Member() {
        this.loans = new ArrayList<>();
    }

    public Member(String id, String name, String libraryId, String address) {
        this.id = id;
        this.name = name;
        this.libraryId = libraryId;
        this.address = address;
        this.loans = new ArrayList<>();
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLibraryId() {
        return libraryId;
    }

    public void setLibraryId(String libraryId) {
        this.libraryId = libraryId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public void setLoans(List<Loan> loans) {
        this.loans = loans;
    }

    public void addLoan(Loan loan) {
        this.loans.add(loan);
    }

    // NEW GETTERS AND SETTERS
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
}
}