package com.lms.LMS.repo;

import com.lms.LMS.model.BookDetails;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookDetailsRepository extends JpaRepository<BookDetails, Long> {
    List<BookDetails> findByTitleContainingIgnoreCase(String title, Sort title1);
}