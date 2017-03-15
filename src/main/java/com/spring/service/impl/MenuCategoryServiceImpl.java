package com.spring.service.impl;

import com.spring.dao.MenuCategoryDAO;
import com.spring.exception.DAOException;
import com.spring.model.MenuCategory;
import com.spring.service.MenuCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MenuCategoryServiceImpl implements MenuCategoryService {

    private final MenuCategoryDAO menuCategoryDAO;

    @Autowired
    public MenuCategoryServiceImpl(MenuCategoryDAO menuCategoryDAO) {
        this.menuCategoryDAO = menuCategoryDAO;
    }

    @Override
    @Transactional
    public void addMainCategory(MenuCategory menuCategory) {
        if (!menuCategoryDAO.findByCategory(menuCategory.getCategory()).isEmpty()) {
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
        MenuCategory menuCategory = menuCategoryDAO.findById(id);
        if (menuCategory == null) {
            throw new DAOException("Such category does not exist");
        }
        return menuCategory;
    }

    @Override
    @Transactional
    public void edit(MenuCategory menuCategory, Long id) {
        MenuCategory oldCategory = menuCategoryDAO.findById(id);
        if (oldCategory == null) {
            throw new DAOException("Such main category does not exist");
        }else if (!menuCategoryDAO.findByCategory(menuCategory.getCategory()).isEmpty()){
            throw new DAOException("Duplicate main category name");
        }
        oldCategory.setCategory(menuCategory.getCategory());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        MenuCategory menuCategory = menuCategoryDAO.findById(id);
        if(menuCategory == null){
            throw new DAOException("Such category does not exist");
        }
        menuCategoryDAO.remove(menuCategory);
    }
}
