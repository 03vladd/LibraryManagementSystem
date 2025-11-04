package com.lms.LMS.repo;

import com.lms.LMS.model.BookDetails;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookDetailsRepo extends BaseRepo<BookDetails> {

    @Override
    protected String getId(BookDetails entity) {
        return entity.getId();
    }

    // Custom method - Find by title (contains)
    public List<BookDetails> findByTitleContaining(String title) {
        return data.values().stream()
                .filter(book -> book.getTitle().toLowerCase().contains(title.toLowerCase()))
                .toList();
    }
}