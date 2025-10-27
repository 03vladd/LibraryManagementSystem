package com.lms.LMS.model;

public class MagazineDetails extends Publication {
    private String Publisher;

    public MagazineDetails() {
        super();
    }

    public MagazineDetails(String id, String title, java.util.List<ReadableItems> copies, String publisher) {
        super(id, title, copies);
        this.Publisher = publisher;
    }


    public String getPublisher() {
        return Publisher;
    }

    public void setPublisher(String publisher) {
        Publisher = publisher;
    }
}
