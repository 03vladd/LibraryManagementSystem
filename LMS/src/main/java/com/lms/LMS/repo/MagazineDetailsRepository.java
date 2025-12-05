package com.lms.LMS.repo;

import com.lms.LMS.model.MagazineDetails;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository

public interface MagazineDetailsRepository extends JpaRepository<MagazineDetails, Long> {
}
