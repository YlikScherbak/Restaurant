package com.spring.dao;

import java.io.Serializable;
import java.util.List;

public interface MyDAO<T, ID extends Serializable> extends Serializable {

    T findById(ID id);

    T findReferenceById(ID id);

    List<T> findAll();

    void save(T entity);

    void remove(T entity);

    void merge(T entity);

    void detach(T entity);

    Long getCount();

}
