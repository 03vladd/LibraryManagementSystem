package com.lms.LMS.model;

import java.util.List;
import com.lms.LMS.model.Reservation;
import com.lms.LMS.model.Loan;

public class Member {
    private String id;
    private String name;
    private String LibraryId;
    private List<Reservation> reservations;
    private List<Loan> loans;

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
        return LibraryId;
    }

    public void setLibraryId(String libraryId) {
        LibraryId = libraryId;
    }
}
