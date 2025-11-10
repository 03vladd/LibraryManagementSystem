package com.lms.LMS.repo;

import com.lms.LMS.model.Library;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class LibraryRepo extends InFileRepo<Library> {

    public LibraryRepo(
            @Value("${app.data.directory:src/main/resources/data}") String dataDirectory
    ) {
        super(Library.class, "libraries.json", dataDirectory);
    }
}