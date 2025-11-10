package com.lms.LMS.repo;

import com.lms.LMS.model.BookDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class BookDetailsRepo extends InFileRepo<BookDetails> {

    public BookDetailsRepo(
            @Value("${app.data.directory:src/main/resources/data}") String dataDirectory
    ) {
        super(BookDetails.class, "books.json", dataDirectory);
    }
}