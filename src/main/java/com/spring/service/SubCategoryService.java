package com.spring.service;

import com.spring.model.Subcategory;

import java.util.List;

public interface SubCategoryService {

    void addSubcategory(Subcategory subcategory, Long mainCategory);

    List<Subcategory> getAllSubcategory();

    Subcategory getById(Long id);

    void edit(Subcategory subcategory, Long id, Long mainCategory);

    void delete(Long id);
}
