package com.lms.LMS.repo;

import com.lms.LMS.model.BookAuthor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class BookAuthorRepo {
    private final Map<String, BookAuthor> bookAuthors = new HashMap<>();

    public BookAuthor save(BookAuthor bookAuthor) {
        bookAuthors.put(bookAuthor.getId(), bookAuthor);
        return bookAuthor;
    }

    public List<BookAuthor> findAll() {
        return new ArrayList<>(bookAuthors.values());
    }

    public Optional<BookAuthor> findById(String id) {
        return Optional.ofNullable(bookAuthors.get(id));
    }

    public boolean deleteById(String id) {
        return bookAuthors.remove(id) != null;
    }

    public boolean existsById(String id) {
        return bookAuthors.containsKey(id);
    }

    public long count() {
        return bookAuthors.size();
    }

    // Find by book ID
    public List<BookAuthor> findByBookId(String bookId) {
        return bookAuthors.values().stream()
                .filter(ba -> ba.getBookId().equals(bookId))
                .toList();
    }

    // Find by author ID
    public List<BookAuthor> findByAuthorId(String authorId) {
        return bookAuthors.values().stream()
                .filter(ba -> ba.getAuthorId().equals(authorId))
                .toList();
    }
}