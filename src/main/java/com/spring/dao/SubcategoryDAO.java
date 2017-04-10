package com.spring.dao;

import com.spring.model.Subcategory;

import java.util.Optional;

public interface SubcategoryDAO extends MyDAO<Subcategory, Long> {

    Optional<Subcategory> findBySubcategory(String category);
}
