package com.lms.LMS.repo;

import com.lms.LMS.model.ReadableItems;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class ReadableItemRepo {
    private final Map<String, ReadableItems> readableItems = new HashMap<>();

    public ReadableItems save(ReadableItems item) {
        readableItems.put(item.getId(), item);
        return item;
    }

    public List<ReadableItems> findAll() {
        return new ArrayList<>(readableItems.values());
    }

    public Optional<ReadableItems> findById(String id) {
        return Optional.ofNullable(readableItems.get(id));
    }

    public boolean deleteById(String id) {
        return readableItems.remove(id) != null;
    }

    public boolean existsById(String id) {
        return readableItems.containsKey(id);
    }

    public long count() {
        return readableItems.size();
    }

    // Find by status
    public List<ReadableItems> findByStatus(String status) {
        return readableItems.values().stream()
                .filter(item -> item.getStatus().equals(status))
                .toList();
    }

    // Find by barcode
    public Optional<ReadableItems> findByBarcode(String barcode) {
        return readableItems.values().stream()
                .filter(item -> item.getBarcode().equals(barcode))
                .findFirst();
    }
}