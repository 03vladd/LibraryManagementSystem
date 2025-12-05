package com.lms.LMS.service;

import com.lms.LMS.model.MagazineDetails;
import com.lms.LMS.repo.MagazineDetailsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MagazineDetailsService {
    private final MagazineDetailsRepository magazineDetailsRepository;

    public MagazineDetailsService(MagazineDetailsRepository magazineDetailsRepository) {
        this.magazineDetailsRepository = magazineDetailsRepository;
    }

    public MagazineDetails saveMagazine(MagazineDetails magazine) {
        return magazineDetailsRepository.save(magazine);
    }

    public List<MagazineDetails> getAllMagazines() {
        return magazineDetailsRepository.findAll();
    }

    public Optional<MagazineDetails> getMagazineById(Long id) {
        return magazineDetailsRepository.findById(id);
    }

    public void deleteMagazine(Long id) {
        magazineDetailsRepository.deleteById(id);
    }

    public List<MagazineDetails> getMagazinesByPublisher(String publisher) {
        return magazineDetailsRepository.findAll().stream()
                .filter(m -> m.getPublisher().equals(publisher))
                .toList();
    }

    public long getMagazinesCount() {
        return magazineDetailsRepository.count();
    }
}