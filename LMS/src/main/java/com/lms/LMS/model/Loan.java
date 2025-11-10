package com.lms.LMS.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Loan {
    private String id;
    private String memberId;
    private Date date;
    private List<Reservation> reservations;
    private List<ReadableItems> items;
}