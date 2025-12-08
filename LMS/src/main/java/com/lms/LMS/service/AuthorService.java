package com.lms.LMS.service;

import com.lms.LMS.model.Author;
import com.lms.LMS.model.BookAuthor;
import com.lms.LMS.repo.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author saveAuthor(Author author) {
        return authorRepository.save(author);
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Optional<Author> getAuthorById(Long id) {
        return authorRepository.findById(id);
    }

    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }

    public List<Author> searchAuthorsByName(String name) {
        return authorRepository.findAll().stream()
                .filter(a -> a.getName().toLowerCase().contains(name.toLowerCase()))
                .toList();
    }

    public Author addBookToAuthor(Long authorId, BookAuthor bookAuthor) {
        Optional<Author> authorOpt = authorRepository.findById(authorId);
        if (authorOpt.isPresent()) {
            Author author = authorOpt.get();
            author.addBook(bookAuthor);
            return authorRepository.save(author);
        }
        return null;
    }

    public long getAuthorsCount() {
        return authorRepository.count();
    }
}