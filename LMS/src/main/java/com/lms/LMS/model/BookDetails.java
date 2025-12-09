package com.lms.LMS.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import java.util.*;

@Entity
@Data
@ToString(exclude = "bookAuthors")
public class BookDetails extends Publication {

    @OneToMany(mappedBy = "bookDetails", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookAuthor> bookAuthors;

    public BookDetails() {
        super();
        this.bookAuthors = new ArrayList<>();
    }

    public BookDetails(String id, String title, List<ReadableItems> copies) {
        super(id, title, copies);
        this.bookAuthors = new ArrayList<>();
    }

    public List<BookAuthor> getBookAuthors() {
        return bookAuthors;
    }

    public void setBookAuthors(List<BookAuthor> bookAuthors) {
        this.bookAuthors = bookAuthors;
    }
}