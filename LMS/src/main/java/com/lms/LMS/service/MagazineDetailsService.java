package com.lms.LMS.service;

import com.lms.LMS.model.MagazineDetails;
import com.lms.LMS.repo.MagazineDetailsRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MagazineDetailsService {
    private final MagazineDetailsRepo magazineDetailsRepo;

    public MagazineDetailsService(MagazineDetailsRepo magazineDetailsRepo) {
        this.magazineDetailsRepo = magazineDetailsRepo;
    }

    // Create or update magazine
    public MagazineDetails saveMagazine(MagazineDetails magazine) {
        return magazineDetailsRepo.save(magazine);
    }

    // Get all magazines
    public List<MagazineDetails> getAllMagazines() {
        return magazineDetailsRepo.findAll();
    }

    // Get magazine by ID
    public Optional<MagazineDetails> getMagazineById(String id) {
        return magazineDetailsRepo.findById(id);
    }

    // Delete magazine
    public boolean deleteMagazine(String id) {
        return magazineDetailsRepo.deleteById(id);
    }

    // Get magazines by publisher
    public List<MagazineDetails> getMagazinesByPublisher(String publisher) {
        return magazineDetailsRepo.findByPublisher(publisher);
    }

    // Get total magazines count
    public long getMagazinesCount() {
        return magazineDetailsRepo.count();
    }
}