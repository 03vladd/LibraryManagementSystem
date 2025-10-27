package com.lms.LMS.model;

import java.util.List;

public abstract class Publication {
    private String id;
    private String Title;
    private List<ReadableItems> copies;

    public Publication() {
    }

    public Publication(String id, String title, List<ReadableItems> copies) {
        this.id = id;
        Title = title;
        this.copies = copies;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public List<ReadableItems> getCopies() {
        return copies;
    }

    public void setCopies(List<ReadableItems> copies) {
        this.copies = copies;
    }
}
