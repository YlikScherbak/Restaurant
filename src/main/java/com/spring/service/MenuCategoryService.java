package com.spring.service;

import com.spring.model.MenuCategory;

import java.util.List;

public interface MenuCategoryService {

    void addMainCategory(MenuCategory menuCategory);

    List<MenuCategory> getAllMenuCategory();

    MenuCategory getById(Long id);

    void edit(MenuCategory menuCategory, Long id);

    void delete(Long id);
}
