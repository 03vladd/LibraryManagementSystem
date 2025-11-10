package com.lms.LMS.repo;

import com.lms.LMS.model.ReadableItems;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class ReadableItemRepo extends InFileRepo<ReadableItems> {

    public ReadableItemRepo(
            @Value("${app.data.directory:src/main/resources/data}") String dataDirectory
    ) {
        super(ReadableItems.class, "readable_items.json", dataDirectory);
    }
}