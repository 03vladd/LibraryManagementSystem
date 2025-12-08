package com.lms.LMS.repo;

import com.lms.LMS.model.ReadableItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ReadableItemsRepository extends JpaRepository<ReadableItems, Long> {
}
