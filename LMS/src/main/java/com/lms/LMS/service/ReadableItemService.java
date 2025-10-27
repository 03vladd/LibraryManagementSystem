package com.lms.LMS.service;

import com.lms.LMS.model.ReadableItemStatus;
import com.lms.LMS.model.ReadableItems;
import com.lms.LMS.repo.ReadableItemRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReadableItemService {
    private final ReadableItemRepo readableItemRepo;

    public ReadableItemService(ReadableItemRepo readableItemRepo) {
        this.readableItemRepo = readableItemRepo;
    }

    // Create or update readable item
    public ReadableItems saveReadableItem(ReadableItems item) {
        return readableItemRepo.save(item);
    }

    // Get all readable items
    public List<ReadableItems> getAllReadableItems() {
        return readableItemRepo.findAll();
    }

    // Get readable item by ID
    public Optional<ReadableItems> getReadableItemById(String id) {
        return readableItemRepo.findById(id);
    }

    // Delete readable item
    public boolean deleteReadableItem(String id) {
        return readableItemRepo.deleteById(id);
    }

    // Get items by status
    public List<ReadableItems> getItemsByStatus(String status) {
        return readableItemRepo.findByStatus(status);
    }

    // Get item by barcode
    public Optional<ReadableItems> getItemByBarcode(String barcode) {
        return readableItemRepo.findByBarcode(barcode);
    }

    // Update item status
    public ReadableItems updateItemStatus(String itemId, ReadableItemStatus newStatus) {
        Optional<ReadableItems> itemOpt = readableItemRepo.findById(itemId);
        if (itemOpt.isPresent()) {
            ReadableItems item = itemOpt.get();
            item.setStatus(newStatus);
            return readableItemRepo.save(item);
        }
        return null;
    }

    // Get available items
    public List<ReadableItems> getAvailableItems() {
        return readableItemRepo.findByStatus("Available");
    }

    // Get total items count
    public long getItemsCount() {
        return readableItemRepo.count();
    }
}