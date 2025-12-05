package com.lms.LMS.service;

import com.lms.LMS.model.ReadableItemStatus;
import com.lms.LMS.model.ReadableItems;
import com.lms.LMS.repo.ReadableItemsRepository;
import com.lms.LMS.repo.ReadableItemsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReadableItemService {
    private final ReadableItemsRepository readableItemRepository;

    public ReadableItemService(ReadableItemsRepository readableItemRepository) {
        this.readableItemRepository = readableItemRepository;
    }

    public ReadableItems saveReadableItem(ReadableItems item) {
        return readableItemRepository.save(item);
    }

    public List<ReadableItems> getAllReadableItems() {
        return readableItemRepository.findAll();
    }

    public Optional<ReadableItems> getReadableItemById(Long id) {
        return readableItemRepository.findById(id);
    }

    public void deleteReadableItem(Long id) {
        readableItemRepository.deleteById(id);
    }

    public List<ReadableItems> getItemsByStatus(ReadableItemStatus status) {
        return readableItemRepository.findAll().stream()
                .filter(item -> item.getStatus().equals(status))
                .toList();
    }

    public Optional<ReadableItems> getItemByBarcode(String barcode) {
        return readableItemRepository.findAll().stream()
                .filter(item -> item.getBarcode().equals(barcode))
                .findFirst();
    }

    public ReadableItems updateItemStatus(Long itemId, ReadableItemStatus newStatus) {
        Optional<ReadableItems> itemOpt = readableItemRepository.findById(itemId);
        if (itemOpt.isPresent()) {
            ReadableItems item = itemOpt.get();
            item.setStatus(newStatus);
            return readableItemRepository.save(item);
        }
        return null;
    }

    public List<ReadableItems> getAvailableItems() {
        return readableItemRepository.findAll().stream()
                .filter(item -> item.getStatus().equals(ReadableItemStatus.Available))
                .toList();
    }

    public long getItemsCount() {
        return readableItemRepository.count();
    }
}