package com.lms.LMS.service;

import com.lms.LMS.model.BookAuthor;
import com.lms.LMS.repo.BookAuthorRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookAuthorService {
    private final BookAuthorRepo bookAuthorRepo;

    public BookAuthorService(BookAuthorRepo bookAuthorRepo) {
        this.bookAuthorRepo = bookAuthorRepo;
    }

    // Create or update book-author relationship
    public BookAuthor saveBookAuthor(BookAuthor bookAuthor) {
        return bookAuthorRepo.save(bookAuthor);
    }

    // Get all book-author relationships
    public List<BookAuthor> getAllBookAuthors() {
        return bookAuthorRepo.findAll();
    }

    // Get book-author by ID
    public Optional<BookAuthor> getBookAuthorById(String id) {
        return bookAuthorRepo.findById(id);
    }

    // Delete book-author relationship
    public boolean deleteBookAuthor(String id) {
        return bookAuthorRepo.deleteById(id);
    }

    // Get authors for a book
    public List<BookAuthor> getAuthorsByBookId(String bookId) {
        return bookAuthorRepo.findAll().stream()
                .filter(ba -> ba.getBookId().equals(bookId))
                .toList();
    }

    // Get books by an author
    public List<BookAuthor> getBooksByAuthorId(String authorId) {
        return bookAuthorRepo.findAll().stream()
                .filter(ba -> ba.getAuthorId().equals(authorId))
                .toList();
    }

    // Get total book-author relationships count
    public long getBookAuthorsCount() {
        return bookAuthorRepo.count();
    }

    public BookAuthor updateBookAuthor(String authorId, BookAuthor bookAuthor) {
        return bookAuthorRepo.update(authorId, bookAuthor);
    }
}