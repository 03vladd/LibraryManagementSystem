package com.lms.LMS.repo;

import com.lms.LMS.model.Member;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepo extends InFileRepo<Member> {

    public MemberRepo(
            @Value("${app.data.directory:src/main/resources/data}") String dataDirectory
    ) {
        super(Member.class, "members.json", dataDirectory);
    }
}