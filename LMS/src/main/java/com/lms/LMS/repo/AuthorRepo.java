package com.lms.LMS.repo;

import com.lms.LMS.model.Author;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class AuthorRepo extends InFileRepo<Author> {

    public AuthorRepo(
            @Value("${app.data.directory:src/main/resources/data}") String dataDirectory
    ) {
        super(Author.class, "authors.json", dataDirectory);
    }
}