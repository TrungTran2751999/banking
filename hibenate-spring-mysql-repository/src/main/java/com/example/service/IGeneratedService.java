package com.example.service;

import java.util.Optional;

public interface IGeneratedService<T> {
    Iterable<T> findAll();
    Optional<T> findById(long id);
    void save(T t);
    void remove(long id);
}
