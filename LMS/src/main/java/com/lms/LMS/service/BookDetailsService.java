package com.lms.LMS.service;

import com.lms.LMS.model.BookDetails;
import com.lms.LMS.repo.BookDetailsRepository;
import org.springframework.data.domain.Sort;
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

    public List<BookDetails> getFilteredAndSortedBooks(String title, String sortBy, String sortOrder) {
        Sort.Direction direction = "desc".equalsIgnoreCase(sortOrder) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort sort = Sort.by(direction, sortBy != null && !sortBy.isEmpty() ? sortBy : "id");

        if (title == null || title.isEmpty()) {
            return bookDetailsRepository.findAll(sort);
        }

        return bookDetailsRepository.findByTitleContainingIgnoreCase(title, sort);
    }

    public Optional<BookDetails> getBookById(Long id) {
        return bookDetailsRepository.findById(id);
    }

    public void deleteBook(Long id) {
        Optional<BookDetails> bookOpt = bookDetailsRepository.findById(id);
        if (bookOpt.isPresent()) {
            BookDetails book = bookOpt.get();
            boolean hasActiveLoans = book.getCopies().stream()
                    .anyMatch(copy -> copy.getLoan() != null);
            if (hasActiveLoans) {
                throw new RuntimeException("Cannot delete book with active loans");
            }
            bookDetailsRepository.deleteById(id);
        }
    }

    public List<BookDetails> searchBooksByTitle(String title) {
        return bookDetailsRepository.findByTitleContainingIgnoreCase(title, Sort.by("title"));
    }

    public long getBooksCount() {
        return bookDetailsRepository.count();
    }
}