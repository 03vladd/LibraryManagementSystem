package com.lms.LMS.service;

import com.lms.LMS.model.Library;
import com.lms.LMS.repo.LibraryRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibraryService {
    private final LibraryRepository libraryRepository;

    public LibraryService(LibraryRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
    }

    public Library saveLibrary(Library library) {
        return libraryRepository.save(library);
    }

    public List<Library> getAllLibraries() {
        return libraryRepository.findAll();
    }

    public List<Library> getFilteredAndSortedLibraries(String name, String sortBy, String sortOrder) {
        Sort.Direction direction = "desc".equalsIgnoreCase(sortOrder) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort sort = Sort.by(direction, sortBy != null && !sortBy.isEmpty() ? sortBy : "id");

        if (name == null || name.isEmpty()) {
            return libraryRepository.findAll(sort);
        }

        return libraryRepository.findByNameContainingIgnoreCase(name, sort);
    }

    public Optional<Library> getLibraryById(Long id) {
        return libraryRepository.findById(id);
    }

    public void deleteLibrary(Long id) {
        Optional<Library> libraryOpt = libraryRepository.findById(id);
        if (libraryOpt.isPresent()) {
            Library library = libraryOpt.get();
            if (!library.getReadableItems().isEmpty()) {
                throw new RuntimeException("Cannot delete library with readable items");
            }
            libraryRepository.deleteById(id);
        }
    }

    public long getLibrariesCount() {
        return libraryRepository.count();
    }
}