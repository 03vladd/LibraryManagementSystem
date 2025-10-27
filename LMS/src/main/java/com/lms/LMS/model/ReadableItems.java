package com.lms.LMS.model;

public class ReadableItems {
    private String id;
    private String title;
    private String barcode;

    private ReadableItemStatus status;

    private ReadableItems(String id, String title, String barcode, ReadableItemStatus status) {
        this.id = id;
        this.title = title;
        this.barcode = barcode;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public ReadableItemStatus getStatus() {
        return status;
    }

    public void setStatus(ReadableItemStatus status) {
        this.status = status;
    }
}
