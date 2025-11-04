package com.lms.LMS.repo;

import com.lms.LMS.model.Member;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberRepo extends BaseRepo<Member> {

    @Override
    protected String getId(Member entity) {
        return entity.getId();
    }

    // Custom method - Find by library ID
    public List<Member> findByLibraryId(String libraryId) {
        return data.values().stream()
                .filter(member -> member.getLibraryId().equals(libraryId))
                .toList();
    }
}