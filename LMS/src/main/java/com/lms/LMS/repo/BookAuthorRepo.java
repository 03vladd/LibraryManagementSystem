package com.lms.LMS.repo;

import com.lms.LMS.model.BookAuthor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class BookAuthorRepo extends InFileRepo<BookAuthor> {

    public BookAuthorRepo(
            @Value("${app.data.directory:src/main/resources/data}") String dataDirectory
    ) {
        super(BookAuthor.class, "book_authors.json", dataDirectory);
    }
}