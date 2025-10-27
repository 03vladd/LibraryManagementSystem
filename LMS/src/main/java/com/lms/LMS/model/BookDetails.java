package com.lms.LMS.model;

import java.util.*;

public class BookDetails extends Publication {
    private List<BookAuthor> bookAuthors;

    public BookDetails() {
        super();
        this.bookAuthors = new ArrayList<>();
    }

    public BookDetails(String id, String title, List<ReadableItems> copies) {
        super(id, title,copies);
        this.bookAuthors = new ArrayList<>();
    }

    public List<BookAuthor> getBookAuthors() {
        return bookAuthors;
    }

    public void setBookAuthors(List<BookAuthor> bookAuthors) {
        this.bookAuthors = bookAuthors;
    }
}
