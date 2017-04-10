package com.spring.service.impl;

import com.spring.dao.MenuCategoryDAO;
import com.spring.exception.DAOException;
import com.spring.model.MenuCategory;
import com.spring.model.Subcategory;
import com.spring.service.MenuCategoryService;
import com.spring.service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MenuCategoryServiceImpl implements MenuCategoryService {

    private final MenuCategoryDAO menuCategoryDAO;

    private final SubCategoryService subCategoryService;

    @Autowired
    public MenuCategoryServiceImpl(MenuCategoryDAO menuCategoryDAO, SubCategoryService subCategoryService) {
        this.menuCategoryDAO = menuCategoryDAO;
        this.subCategoryService = subCategoryService;
    }

    @Override
    @Transactional
    public void addMainCategory(MenuCategory menuCategory) {
        if (menuCategoryDAO.findByCategory(menuCategory.getCategory()).isPresent()) {
            throw new DAOException("Duplicate main category name");
        }
        menuCategoryDAO.save(menuCategory);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MenuCategory> getAllMenuCategory() {
        return menuCategoryDAO.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public MenuCategory getById(Long id) {
        return menuCategoryDAO.findById(id).
                orElseThrow(() -> new DAOException("Such category does not exist"));
    }

    @Override
    @Transactional
    public void edit(MenuCategory menuCategory, Long id) {
        MenuCategory oldCategory = menuCategoryDAO.findById(id).
                orElseThrow(() -> new DAOException("Such main category does not exist"));

        if (menuCategoryDAO.findByCategory(menuCategory.getCategory()).isPresent()) {
            throw new DAOException("Duplicate main category name");
        }
        oldCategory.setCategory(menuCategory.getCategory());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        MenuCategory menuCategory = menuCategoryDAO.findById(id).
                orElseThrow(() -> new DAOException("Such category does not exist"));

        List<Subcategory> subcategories = menuCategory.getSubcategories();
        subcategories.forEach(subcategory -> subCategoryService.delete(subcategory.getId()));
        menuCategoryDAO.remove(menuCategory);
    }
}
