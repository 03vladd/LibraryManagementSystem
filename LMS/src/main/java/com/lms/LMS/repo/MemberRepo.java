package com.lms.LMS.repo;

import com.lms.LMS.model.Member;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class MemberRepo {
    private final Map<String, Member> members = new HashMap<>();

    public Member save(Member member) {
        members.put(member.getId(), member);
        return member;
    }

    public List<Member> findAll() {
        return new ArrayList<>(members.values());
    }

    public Optional<Member> findById(String id) {
        return Optional.ofNullable(members.get(id));
    }

    public boolean deleteById(String id) {
        return members.remove(id) != null;
    }

    public boolean existsById(String id) {
        return members.containsKey(id);
    }

    public long count() {
        return members.size();
    }

    // Find by library ID
    public List<Member> findByLibraryId(String libraryId) {
        return members.values().stream()
                .filter(member -> member.getLibraryId().equals(libraryId))
                .toList();
    }
}