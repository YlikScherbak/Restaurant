package com.spring.dao;

import com.spring.model.Subcategory;

import java.util.List;

public interface SubcategoryDAO extends MyDAO<Subcategory, Long> {

    List<Subcategory> findBySubcategory(String category);
}
