package com.spring.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface MyDAO<T, ID extends Serializable> extends Serializable {

    Optional<T> findById(ID id);

    T findReferenceById(ID id);

    List<T> findAll();

    void save(T entity);

    void remove(T entity);

    void merge(T entity);

    void detach(T entity);

    Optional<Long> getCount();

}
