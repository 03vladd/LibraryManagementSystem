package com.lms.LMS.repo;

import com.lms.LMS.model.Library;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class LibraryRepo {
    private final Map<String, Library> libraries = new HashMap<>();

    // Save or update
    public Library save(Library library) {
        libraries.put(library.getId(), library);
        return library;
    }

    // Find all
    public List<Library> findAll() {
        return new ArrayList<>(libraries.values());
    }

    // Find by ID
    public Optional<Library> findById(String id) {
        return Optional.ofNullable(libraries.get(id));
    }

    // Delete by ID
    public boolean deleteById(String id) {
        return libraries.remove(id) != null;
    }

    // Check if exists
    public boolean existsById(String id) {
        return libraries.containsKey(id);
    }

    // Count
    public long count() {
        return libraries.size();
    }
}