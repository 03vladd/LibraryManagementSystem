package com.lms.LMS.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Author {
    private String id;
    private String name;
    private List<BookAuthor> books;

    // NEW PROPERTIES ADDED
    private LocalDate birthDate;
    private String nationality;

    // Constructor
    public Author() {
        this.books = new ArrayList<>();
    }

    public Author(String id, String name) {
        this.id = id;
        this.name = name;
        this.books = new ArrayList<>();
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BookAuthor> getBooks() {
        return books;
    }

    public void setBooks(List<BookAuthor> books) {
        this.books = books;
    }

    public void addBook(BookAuthor bookAuthor) {
        this.books.add(bookAuthor);
    }

    // NEW GETTERS AND SETTERS
    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
}
}