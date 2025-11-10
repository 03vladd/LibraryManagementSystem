package com.lms.LMS.repo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class InFileRepo<T> implements AbstractRepo<T> {

    private final String dataFilePath;
    private final Class<T> entityClass;
    private final ObjectMapper objectMapper;

    public InFileRepo(Class<T> entityClass, String fileName, String dataDirectory) {
        this.entityClass = entityClass;
        this.objectMapper = new ObjectMapper();
        this.dataFilePath = dataDirectory + File.separator + fileName;

        try {
            Files.createDirectories(Paths.get(dataDirectory));
        } catch (IOException e) {
            throw new RuntimeException("Failed to create data directory: " + dataDirectory, e);
        }
    }

    private List<T> loadAllFromFile() {
        try {
            File file = new File(dataFilePath);
            if (!file.exists()) {
                return new ArrayList<>();
            }

            CollectionType listType = objectMapper.getTypeFactory()
                    .constructCollectionType(List.class, entityClass);
            return objectMapper.readValue(file, listType);

        } catch (IOException e) {
            throw new RuntimeException("Error reading from file: " + dataFilePath, e);
        }
    }

    private void saveAllToFile(List<T> entities) {
        try {
            objectMapper.writerWithDefaultPrettyPrinter()
                    .writeValue(new File(dataFilePath), entities);
        } catch (IOException e) {
            throw new RuntimeException("Error writing to file: " + dataFilePath, e);
        }
    }

    private String getEntityId(T entity) {
        try {
            Field idField = entityClass.getDeclaredField("id");
            idField.setAccessible(true);
            Object id = idField.get(entity);
            return id != null ? id.toString() : null;
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException("Entity must have an 'id' field", e);
        }
    }

    private void setEntityId(T entity, String id) {
        try {
            Field idField = entityClass.getDeclaredField("id");
            idField.setAccessible(true);
            idField.set(entity, id);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException("Entity must have an 'id' field", e);
        }
    }

    @Override
    public T save(T entity) {
        List<T> entities = loadAllFromFile();
        String id = getEntityId(entity);

        if (id == null || id.isEmpty()) {
            id = UUID.randomUUID().toString();
            setEntityId(entity, id);
        }

        final String finalId = id;
        entities.removeIf(e -> getEntityId(e).equals(finalId));
        entities.add(entity);
        saveAllToFile(entities);

        return entity;
    }

    @Override
    public List<T> findAll() {
        return loadAllFromFile();
    }

    @Override
    public Optional<T> findById(String id) {
        return loadAllFromFile().stream()
                .filter(e -> getEntityId(e).equals(id))
                .findFirst();
    }

    @Override
    public void deleteById(String id) {
        List<T> entities = loadAllFromFile();
        entities.removeIf(e -> getEntityId(e).equals(id));
        saveAllToFile(entities);
    }

    @Override
    public boolean existsById(String id) {
        return loadAllFromFile().stream()
                .anyMatch(e -> getEntityId(e).equals(id));
    }

    @Override
    public long count() {
        return loadAllFromFile().size();
    }
}