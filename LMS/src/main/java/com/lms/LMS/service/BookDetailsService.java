package com.lms.LMS.service;

import com.lms.LMS.model.BookAuthor;
import com.lms.LMS.model.BookDetails;
import com.lms.LMS.repo.BookDetailsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookDetailsService {
    private final BookDetailsRepository bookDetailsRepository;

    public BookDetailsService(BookDetailsRepository bookDetailsRepository) {
        this.bookDetailsRepository = bookDetailsRepository;
    }

    public BookDetails saveBook(BookDetails book) {
        return bookDetailsRepository.save(book);
    }

    public List<BookDetails> getAllBooks() {
        return bookDetailsRepository.findAll();
    }

    public Optional<BookDetails> getBookById(Long id) {
        return bookDetailsRepository.findById(id);
    }

    public void deleteBook(Long id) {
        bookDetailsRepository.deleteById(id);
    }

    public List<BookDetails> searchBooksByTitle(String title) {
        return bookDetailsRepository.findAll().stream()
                .filter(b -> b.getTitle().toLowerCase().contains(title.toLowerCase()))
                .toList();
    }

    public BookDetails addAuthorToBook(Long bookId, BookAuthor bookAuthor) {
        Optional<BookDetails> bookOpt = bookDetailsRepository.findById(bookId);
        if (bookOpt.isPresent()) {
            BookDetails book = bookOpt.get();
            book.getBookAuthors().add(bookAuthor);
            return bookDetailsRepository.save(book);
        }
        return null;
    }

    public long getBooksCount() {
        return bookDetailsRepository.count();
    }
}