package com.lms.LMS.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Author {
    private String id;
    private String name;
    private List<BookAuthor> books = new ArrayList<>();

    //new properties
    private LocalDate birthDate;
    private String nationality;

    // Custom constructor for backward compatibility
    public Author(String id, String name) {
        this.id = id;
        this.name = name;
        this.books = new ArrayList<>();
    }

    // Keep the custom method
    public void addBook(BookAuthor bookAuthor) {
        this.books.add(bookAuthor);
    }
}