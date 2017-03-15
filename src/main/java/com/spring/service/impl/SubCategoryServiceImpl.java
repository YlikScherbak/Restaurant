package com.spring.service.impl;

import com.spring.dao.MenuCategoryDAO;
import com.spring.dao.SubcategoryDAO;
import com.spring.exception.DAOException;
import com.spring.model.Subcategory;
import com.spring.service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SubCategoryServiceImpl implements SubCategoryService {

    private final SubcategoryDAO subcategoryDAO;

    private final MenuCategoryDAO menuCategoryDAO;

    @Autowired
    public SubCategoryServiceImpl(SubcategoryDAO subcategoryDAO, MenuCategoryDAO menuCategoryDAO) {
        this.subcategoryDAO = subcategoryDAO;
        this.menuCategoryDAO = menuCategoryDAO;
    }

    @Override
    @Transactional
    public void addSubcategory(Subcategory subcategory, Long mainCategory) {
        if (!subcategoryDAO.findBySubcategory(subcategory.getSubcategory()).isEmpty()) {
            throw new DAOException("Duplicate subcategory name");
        }
        subcategoryDAO.save(new Subcategory(subcategory.getSubcategory(),
                menuCategoryDAO.findReferenceById(mainCategory)));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Subcategory> getAllSubcategory() {
        return subcategoryDAO.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Subcategory getById(Long id) {
        Subcategory subcategory = subcategoryDAO.findById(id);
        if (subcategory == null) {
            throw new DAOException("Such subcategory does not exist");
        }

        return subcategory;
    }

    @Override
    @Transactional
    public void edit(Subcategory subcategory, Long id, Long mainCategory) {
        Subcategory oldSub = subcategoryDAO.findById(id);
        if (oldSub == null) {
            throw new DAOException("Such subcategory does not exist");
        } else if (!subcategoryDAO.findBySubcategory(subcategory.getSubcategory()).isEmpty()) {
            throw new DAOException("Duplicate subcategory name");
        }
        oldSub.setSubcategory(subcategory.getSubcategory());
        oldSub.setMenuCategory(menuCategoryDAO.findReferenceById(mainCategory));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Subcategory subcategory = subcategoryDAO.findById(id);
        if (subcategory == null) {
            throw new DAOException("Such subcategory does not exist");
        }
        subcategoryDAO.remove(subcategory);
    }
}
