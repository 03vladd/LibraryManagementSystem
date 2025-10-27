package com.lms.LMS.service;

import com.lms.LMS.model.BookAuthor;
import com.lms.LMS.model.BookDetails;
import com.lms.LMS.repo.BookDetailsRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookDetailsService {
    private final BookDetailsRepo bookDetailsRepo;

    public BookDetailsService(BookDetailsRepo bookDetailsRepo) {
        this.bookDetailsRepo = bookDetailsRepo;
    }

    // Create or update book
    public BookDetails saveBook(BookDetails book) {
        return bookDetailsRepo.save(book);
    }

    // Get all books
    public List<BookDetails> getAllBooks() {
        return bookDetailsRepo.findAll();
    }

    // Get book by ID
    public Optional<BookDetails> getBookById(String id) {
        return bookDetailsRepo.findById(id);
    }

    // Delete book
    public boolean deleteBook(String id) {
        return bookDetailsRepo.deleteById(id);
    }

    // Search books by title
    public List<BookDetails> searchBooksByTitle(String title) {
        return bookDetailsRepo.findByTitleContaining(title);
    }

    // Add author to book
    public BookDetails addAuthorToBook(String bookId, BookAuthor bookAuthor) {
        Optional<BookDetails> bookOpt = bookDetailsRepo.findById(bookId);
        if (bookOpt.isPresent()) {
            BookDetails book = bookOpt.get();
            return bookDetailsRepo.save(book);
        }
        return null;
    }

    // Get total books count
    public long getBooksCount() {
        return bookDetailsRepo.count();
    }
}