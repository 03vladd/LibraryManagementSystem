package com.lms.LMS.service;

import com.lms.LMS.model.Author;
import com.lms.LMS.model.BookAuthor;
import com.lms.LMS.repo.AuthorRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    private final AuthorRepo authorRepo;

    public AuthorService(AuthorRepo authorRepo) {
        this.authorRepo = authorRepo;
    }

    // Create or update author
    public Author saveAuthor(Author author) {
        return authorRepo.save(author);
    }

    // Get all authors
    public List<Author> getAllAuthors() {
        return authorRepo.findAll();
    }

    // Get author by ID
    public Optional<Author> getAuthorById(String id) {
        return authorRepo.findById(id);
    }

    // Delete author
    public boolean deleteAuthor(String id) {
        return authorRepo.deleteById(id);
    }

    // Search authors by name
    public List<Author> searchAuthorsByName(String name) {
        return authorRepo.findByNameContaining(name);
    }

    // Add book to author
    public Author addBookToAuthor(String authorId, BookAuthor bookAuthor) {
        Optional<Author> authorOpt = authorRepo.findById(authorId);
        if (authorOpt.isPresent()) {
            Author author = authorOpt.get();
            return authorRepo.save(author);
        }
        return null;
    }

    // Get total authors count
    public long getAuthorsCount() {
        return authorRepo.count();
    }
}