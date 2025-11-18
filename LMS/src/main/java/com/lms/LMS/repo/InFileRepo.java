package com.lms.LMS.repo;

import com.fasterxml.jackson.databind.DeserializationFeature;
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
        this.objectMapper.findAndRegisterModules();
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
            Field idField = null;
            Class<?> currentClass = entityClass;

            // Search for 'id' field in current class and superclasses
            while (currentClass != null && idField == null) {
                try {
                    idField = currentClass.getDeclaredField("id");
                } catch (NoSuchFieldException e) {
                    currentClass = currentClass.getSuperclass();
                }
            }

            if (idField == null) {
                throw new RuntimeException("Entity must have an 'id' field");
            }

            idField.setAccessible(true);
            Object id = idField.get(entity);
            return id != null ? id.toString() : null;
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Error accessing 'id' field", e);
        }
    }

    private void setEntityId(T entity, String id) {
        try {
            Field idField = null;
            Class<?> currentClass = entityClass;

            // Search for 'id' field in current class and superclasses
            while (currentClass != null && idField == null) {
                try {
                    idField = currentClass.getDeclaredField("id");
                } catch (NoSuchFieldException e) {
                    currentClass = currentClass.getSuperclass();
                }
            }

            if (idField == null) {
                throw new RuntimeException("Entity must have an 'id' field");
            }

            idField.setAccessible(true);
            idField.set(entity, id);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Error setting 'id' field", e);
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
    public boolean deleteById(String id) {
        List<T> entities = loadAllFromFile();
        boolean removed = entities.removeIf(e -> getEntityId(e).equals(id));
        if (removed) {
            saveAllToFile(entities);
        }
        return removed;
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

    @Override
    public T update(String id, T entity) {
        List<T> entities = loadAllFromFile();
        final String finalId = id;

        boolean found = false;
        for (int i = 0; i < entities.size(); i++) {
            if (getEntityId(entities.get(i)).equals(finalId)) {
                setEntityId(entity, id);
                entities.set(i, entity);
                found = true;
                break;
            }
        }

        if (found) {
            saveAllToFile(entities);
            return entity;
        }

        return null;
    }
}