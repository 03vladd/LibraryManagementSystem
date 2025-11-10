package com.lms.LMS.model;

import java.util.Date;

import java.awt.*;

import java.util.Date;
import java.util.List;

public class Loan {
    private String id;
    private String memberId;
    private Date date;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
