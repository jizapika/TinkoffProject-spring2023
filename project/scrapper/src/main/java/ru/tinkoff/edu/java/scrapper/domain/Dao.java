package ru.tinkoff.edu.java.scrapper.domain;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public interface Dao<K, E> {

    List<E> findAll();

    Optional<E> findById(K key);

    void update(E updatedElement);

    E save(E element);

    public boolean delete(K key);
}
