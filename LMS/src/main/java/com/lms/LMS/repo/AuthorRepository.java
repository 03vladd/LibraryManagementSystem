package com.lms.LMS.repo;

import com.lms.LMS.model.Author;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Long> {

    void deleteByName(String name);

    List<Author> findByNameContainingIgnoreCase(String name, Sort name1);
}
