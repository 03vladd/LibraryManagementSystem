package com.lms.LMS.service;

import com.lms.LMS.model.BookAuthor;
import com.lms.LMS.repo.BookAuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookAuthorService {
    private final BookAuthorRepository bookAuthorRepository;

    public BookAuthorService(BookAuthorRepository bookAuthorRepository) {
        this.bookAuthorRepository = bookAuthorRepository;
    }

    public BookAuthor saveBookAuthor(BookAuthor bookAuthor) {
        return bookAuthorRepository.save(bookAuthor);
    }

    public List<BookAuthor> getAllBookAuthors() {
        return bookAuthorRepository.findAll();
    }

    public Optional<BookAuthor> getBookAuthorById(Long id) {
        return bookAuthorRepository.findById(id);
    }

    public void deleteBookAuthor(Long id) {
        bookAuthorRepository.deleteById(id);
    }

    public List<BookAuthor> getAuthorsByBookId(Long bookId) {
        return bookAuthorRepository.findAll().stream()
                .filter(ba -> ba.getBook().getId().equals(bookId))
                .toList();
    }

    public List<BookAuthor> getBooksByAuthorId(Long authorId) {
        return bookAuthorRepository.findAll().stream()
                .filter(ba -> ba.getAuthor().getId().equals(authorId))
                .toList();
    }

    public long getBookAuthorsCount() {
        return bookAuthorRepository.count();
    }
}