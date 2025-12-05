package com.lms.LMS.service;

import com.lms.LMS.model.Library;
import com.lms.LMS.model.ReadableItems;
import com.lms.LMS.repo.LibraryRepository;
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

    public Optional<Library> getLibraryById(Long id) {
        return libraryRepository.findById(id);
    }

    public boolean deleteLibrary(Long id) {
        libraryRepository.deleteById(id);
        return true;
    }

    public Library addReadableItemToLibrary(Long libraryId, ReadableItems item) {
        Optional<Library> libraryOpt = libraryRepository.findById(libraryId);
        if (libraryOpt.isPresent()) {
            Library library = libraryOpt.get();
            library.addReadableItem(item);
            return libraryRepository.save(library);
        }
        return null;
    }

    public long getLibrariesCount() {
        return libraryRepository.count();
    }
}