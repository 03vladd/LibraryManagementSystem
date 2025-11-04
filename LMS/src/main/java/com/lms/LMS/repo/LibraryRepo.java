package com.lms.LMS.repo;

import com.lms.LMS.model.Library;
import org.springframework.stereotype.Repository;

@Repository
public class LibraryRepo extends BaseRepo<Library> {

    @Override
    protected String getId(Library entity) {
        return entity.getId();
    }
}