package com.lms.LMS.model;

import java.awt.*;

import java.util.List;

public class Loan {
    private String id;
    private String memberId;
    private String date;
    private List<Reservation> reservations;
    private List<ReadableItems> Items;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
