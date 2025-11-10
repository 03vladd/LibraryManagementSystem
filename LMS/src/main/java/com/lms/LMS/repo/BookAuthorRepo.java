package com.lms.LMS.repo;

import com.lms.LMS.model.BookAuthor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookAuthorRepo extends BaseRepo<BookAuthor> {

    @Override
    protected String getId(BookAuthor entity) {
        return entity.getId();
    }

    // Custom method - Find by book ID
    public List<BookAuthor> findByBookId(String bookId) {
        return data.values().stream()
                .filter(ba -> ba.getBookId().equals(bookId))
                .toList();
    }

    // Custom method - Find by author ID
    public List<BookAuthor> findByAuthorId(String authorId) {
        return data.values().stream()
                .filter(ba -> ba.getAuthorId().equals(authorId))
                .toList();
    }
}