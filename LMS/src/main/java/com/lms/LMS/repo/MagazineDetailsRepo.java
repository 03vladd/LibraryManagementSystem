package com.lms.LMS.repo;

import com.lms.LMS.model.MagazineDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class MagazineDetailsRepo extends InFileRepo<MagazineDetails> {

    public MagazineDetailsRepo(
            @Value("${app.data.directory:src/main/resources/data}") String dataDirectory
    ) {
        super(MagazineDetails.class, "magazines.json", dataDirectory);
    }
}