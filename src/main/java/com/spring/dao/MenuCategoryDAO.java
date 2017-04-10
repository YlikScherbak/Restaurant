package com.spring.dao;


import com.spring.model.MenuCategory;

import java.util.Optional;

public interface MenuCategoryDAO extends MyDAO<MenuCategory, Long> {

    Optional<MenuCategory> findByCategory(String category);
}
