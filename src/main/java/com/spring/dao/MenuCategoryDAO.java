package com.spring.dao;


import com.spring.model.MenuCategory;

import java.util.List;

public interface MenuCategoryDAO extends MyDAO<MenuCategory, Long> {

    List<MenuCategory> findByCategory(String category);
}
