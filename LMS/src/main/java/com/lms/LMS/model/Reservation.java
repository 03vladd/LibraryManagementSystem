package com.lms.LMS.model;

public class Reservation {
    private String id;
    private String memberId;
    private String readableItemId;
    private String reservationDate;
    private ReservationStatus status;

    public Reservation(String id, String memberId, String readableItemId, String reservationDate, ReservationStatus status) {
        this.id = id;
        this.memberId = memberId;
        this.readableItemId = readableItemId;
        this.reservationDate = reservationDate;
        this.status = status;
    }

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

    public String getReadableItemId() {
        return readableItemId;
    }

    public void setReadableItemId(String readableItemId) {
        this.readableItemId = readableItemId;
    }

    public String getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(String reservationDate) {
        this.reservationDate = reservationDate;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }
}
