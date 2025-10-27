package com.lms.LMS.repo;

import com.lms.LMS.model.MagazineDetails;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class MagazineDetailsRepo {
    private final Map<String, MagazineDetails> magazines = new HashMap<>();

    public MagazineDetails save(MagazineDetails magazine) {
        magazines.put(magazine.getId(), magazine);
        return magazine;
    }

    public List<MagazineDetails> findAll() {
        return new ArrayList<>(magazines.values());
    }

    public Optional<MagazineDetails> findById(String id) {
        return Optional.ofNullable(magazines.get(id));
    }

    public boolean deleteById(String id) {
        return magazines.remove(id) != null;
    }

    public boolean existsById(String id) {
        return magazines.containsKey(id);
    }

    public long count() {
        return magazines.size();
    }

    // Find by publisher
    public List<MagazineDetails> findByPublisher(String publisher) {
        return magazines.values().stream()
                .filter(magazine -> magazine.getPublisher().equals(publisher))
                .toList();
    }
}