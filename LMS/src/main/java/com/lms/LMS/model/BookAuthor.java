package com.lms.LMS.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "book_authors")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookAuthor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private BookDetails book;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;
}