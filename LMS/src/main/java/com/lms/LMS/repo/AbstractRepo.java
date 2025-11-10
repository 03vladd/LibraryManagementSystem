package com.lms.LMS.repo;

import java.util.List;
import java.util.Optional;

public interface AbstractRepo<T> {

    T save(T entity);

    List<T> findAll();

    Optional<T> findById(String id);

    boolean existsById(String id);

    void deleteById(String id);

    long count();
}
