package com.lms.LMS.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("BOOK")
@Data
@NoArgsConstructor
public class BookDetails extends Publication {
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookAuthor> bookAuthors = new ArrayList<>();

    public BookDetails(String title, List<ReadableItems> copies) {
        super(null, title, copies);
        this.bookAuthors = new ArrayList<>();
    }
}