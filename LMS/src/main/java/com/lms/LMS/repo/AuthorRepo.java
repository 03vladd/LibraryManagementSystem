package com.lms.LMS.repo;

import com.lms.LMS.model.Author;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AuthorRepo extends BaseRepo<Author> {

    @Override
    protected String getId(Author entity) {
        return entity.getId();
    }

    // Custom method - Find by name (contains)
    public List<Author> findByNameContaining(String name) {
        return data.values().stream()
                .filter(author -> author.getName().toLowerCase().contains(name.toLowerCase()))
                .toList();
    }
}