package com.lms.LMS.service;

import com.lms.LMS.model.Author;
import com.lms.LMS.repo.AuthorRepository;
import org.springframework.data.domain.Sort;
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

    public List<Author> getFilteredAndSortedAuthors(String name, String nationality, String sortBy, String sortOrder) {
        Sort.Direction direction = "desc".equalsIgnoreCase(sortOrder) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort sort = Sort.by(direction, sortBy != null && !sortBy.isEmpty() ? sortBy : "id");

        List<Author> authors = authorRepository.findAll(sort);

        if ((name == null || name.isEmpty()) && (nationality == null || nationality.isEmpty())) {
            return authors;
        }

        return authors.stream()
                .filter(author -> name == null || name.isEmpty() ||
                        author.getName().toLowerCase().contains(name.toLowerCase()))
                .filter(author -> nationality == null || nationality.isEmpty() ||
                        (author.getNationality() != null && author.getNationality().equalsIgnoreCase(nationality)))
                .toList();
    }

    public Optional<Author> getAuthorById(Long id) {
        return authorRepository.findById(id);
    }

    public void deleteAuthor(Long id) {
        Optional<Author> authorOpt = authorRepository.findById(id);
        if (authorOpt.isPresent()) {
            Author author = authorOpt.get();
            if (!author.getBooks().isEmpty()) {
                throw new RuntimeException("Cannot delete author with books");
            }
            authorRepository.deleteById(id);
        }
    }

    public List<Author> searchAuthorsByName(String name) {
        return authorRepository.findByNameContainingIgnoreCase(name, Sort.by("name"));
    }

    public long getAuthorsCount() {
        return authorRepository.count();
    }
}