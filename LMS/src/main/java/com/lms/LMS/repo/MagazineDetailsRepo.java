package com.lms.LMS.repo;

import com.lms.LMS.model.MagazineDetails;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MagazineDetailsRepo extends BaseRepo<MagazineDetails> {

    @Override
    protected String getId(MagazineDetails entity) {
        return entity.getId();
    }

    // Custom method - Find by publisher
    public List<MagazineDetails> findByPublisher(String publisher) {
        return data.values().stream()
                .filter(magazine -> magazine.getPublisher().equals(publisher))
                .toList();
    }
}