package com.lms.LMS.repo;

import com.lms.LMS.model.Member;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findByNameContainingIgnoreCase(String name, Sort sort);
    List<Member> findByEmailContainingIgnoreCase(String email, Sort sort);
    List<Member> findByNameContainingIgnoreCaseAndEmailContainingIgnoreCase(String name, String email, Sort sort);
}