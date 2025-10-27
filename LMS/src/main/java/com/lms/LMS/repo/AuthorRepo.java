package com.lms.LMS.repo;

import com.lms.LMS.model.Author;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class AuthorRepo {
    private final Map<String, Author> authors = new HashMap<>();

    public Author save(Author author) {
        authors.put(author.getId(), author);
        return author;
    }

    public List<Author> findAll() {
        return new ArrayList<>(authors.values());
    }

    public Optional<Author> findById(String id) {
        return Optional.ofNullable(authors.get(id));
    }

    public boolean deleteById(String id) {
        return authors.remove(id) != null;
    }

    public boolean existsById(String id) {
        return authors.containsKey(id);
    }

    public long count() {
        return authors.size();
    }

    // Find by name (contains)
    public List<Author> findByNameContaining(String name) {
        return authors.values().stream()
                .filter(author -> author.getName().toLowerCase().contains(name.toLowerCase()))
                .toList();
    }
}