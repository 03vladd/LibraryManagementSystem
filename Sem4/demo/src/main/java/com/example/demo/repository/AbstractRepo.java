package com.example.demo.repository;
import java.util.*;

public interface AbstractRepo<T> {
    void save(T device);
    List<T> getAll();
    T findById(Integer id);
}
