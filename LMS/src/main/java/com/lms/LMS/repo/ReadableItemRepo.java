package com.lms.LMS.repo;

import com.lms.LMS.model.ReadableItems;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ReadableItemRepo extends BaseRepo<ReadableItems> {

    @Override
    protected String getId(ReadableItems entity) {
        return entity.getId();
    }

    // Custom method - Find by status
    public List<ReadableItems> findByStatus(String status) {
        return data.values().stream()
                .filter(item -> item.getStatus().equals(status))
                .toList();
    }

    // Custom method - Find by barcode
    public Optional<ReadableItems> findByBarcode(String barcode) {
        return data.values().stream()
                .filter(item -> item.getBarcode().equals(barcode))
                .findFirst();
    }
}