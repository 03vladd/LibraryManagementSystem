package com.lms.LMS.repo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public abstract class BaseRepo<T> {

    protected final Map<String, T> data = new HashMap<>();

    // Save or update
    public T save(T entity) {
        data.put(getId(entity), entity);
        return entity;
    }

    // Find all
    public List<T> findAll() {
        return new ArrayList<>(data.values());
    }

    // Find by ID
    public Optional<T> findById(String id) {
        return Optional.ofNullable(data.get(id));
    }

    // Delete by ID
    public boolean deleteById(String id) {
        return data.remove(id) != null;
    }

    // Check if exists
    public boolean existsById(String id) {
        return data.containsKey(id);
    }

    // Count
    public long count() {
        return data.size();
    }

    // Abstract method - each repo must implement how to get ID from entity
    protected abstract String getId(T entity);
}