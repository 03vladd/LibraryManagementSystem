package com.lms.LMS.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDetails extends Publication {
    private List<BookAuthor> bookAuthors;

    public BookDetails(String id, String title, List<ReadableItems> copies) {
        super(id, title, copies);
        this.bookAuthors = new ArrayList<>();
    }
}