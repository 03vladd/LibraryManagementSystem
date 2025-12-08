package com.lms.LMS.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "authors")
@Data
@NoArgsConstructor
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Author name cannot be blank")
    private String name;

    @Past(message = "Birth date must be in the past")
    private LocalDate birthDate;

    private String nationality;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookAuthor> books = new ArrayList<>();

    public Author(String name) {
        this.name = name;
        this.books = new ArrayList<>();
    }

    public void addBook(BookAuthor bookAuthor) {
        this.books.add(bookAuthor);
    }
}