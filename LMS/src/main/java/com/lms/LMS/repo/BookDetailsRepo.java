package com.lms.LMS.repo;

import com.lms.LMS.model.BookDetails;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class BookDetailsRepo {
    private final Map<String, BookDetails> books = new HashMap<>();

    public BookDetails save(BookDetails book) {
        books.put(book.getId(), book);
        return book;
    }

    public List<BookDetails> findAll() {
        return new ArrayList<>(books.values());
    }

    public Optional<BookDetails> findById(String id) {
        return Optional.ofNullable(books.get(id));
    }

    public boolean deleteById(String id) {
        return books.remove(id) != null;
    }

    public boolean existsById(String id) {
        return books.containsKey(id);
    }

    public long count() {
        return books.size();
    }

    // Find by title (contains)
    public List<BookDetails> findByTitleContaining(String title) {
        return books.values().stream()
                .filter(book -> book.getTitle().toLowerCase().contains(title.toLowerCase()))
                .toList();
    }
}