package com.lms.LMS.repo;

import com.lms.LMS.model.Library;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibraryRepository extends JpaRepository<Library, Long> {
    List<Library> findByNameContainingIgnoreCase(String name, Sort sort);
}