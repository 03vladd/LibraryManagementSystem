package com.lms.LMS.service;

import com.lms.LMS.model.Library;
import com.lms.LMS.model.ReadableItems;
import com.lms.LMS.repo.LibraryRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibraryService {
    private final LibraryRepo libraryRepo;

    public LibraryService(LibraryRepo libraryRepo) {
        this.libraryRepo = libraryRepo;
    }

    // Create or update library
    public Library saveLibrary(Library library) {
        return libraryRepo.save(library);
    }

    // Get all libraries
    public List<Library> getAllLibraries() {
        return libraryRepo.findAll();
    }

    // Get library by ID
    public Optional<Library> getLibraryById(String id) {
        return libraryRepo.findById(id);
    }

    // Delete library
    public boolean deleteLibrary(String id) {
        return libraryRepo.deleteById(id);
    }

    // Add readable item to library
    public Library addReadableItemToLibrary(String libraryId, ReadableItems item) {
        Optional<Library> libraryOpt = libraryRepo.findById(libraryId);
        if (libraryOpt.isPresent()) {
            Library library = libraryOpt.get();
            return libraryRepo.save(library);
        }
        return null;
    }

    // Get total libraries count
    public long getLibrariesCount() {
        return libraryRepo.count();
    }
}