package com.spring.service.impl;

import com.spring.dao.MenuCategoryDAO;
import com.spring.dao.ProductDAO;
import com.spring.dao.SubcategoryDAO;
import com.spring.exception.DAOException;
import com.spring.model.Product;
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

    private final ProductDAO productDAO;

    @Autowired
    public SubCategoryServiceImpl(SubcategoryDAO subcategoryDAO, MenuCategoryDAO menuCategoryDAO, ProductDAO productDAO) {
        this.subcategoryDAO = subcategoryDAO;
        this.menuCategoryDAO = menuCategoryDAO;
        this.productDAO = productDAO;
    }

    @Override
    @Transactional
    public void addSubcategory(Subcategory subcategory, Long mainCategory) {
        if (subcategoryDAO.findBySubcategory(subcategory.getSubcategory()).isPresent()) {
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
        return subcategoryDAO.findById(id).orElseThrow(() -> new DAOException("Such subcategory does not exist"));
    }

    @Override
    @Transactional
    public void edit(Subcategory subcategory, Long id, Long mainCategory) {
        Subcategory oldSub = subcategoryDAO.findById(id).
                orElseThrow(() -> new DAOException("Such subcategory does not exist"));

        if (subcategoryDAO.findBySubcategory(subcategory.getSubcategory()).isPresent()) {
            throw new DAOException("Duplicate subcategory name");
        }
        oldSub.setSubcategory(subcategory.getSubcategory());
        oldSub.setMenuCategory(menuCategoryDAO.findReferenceById(mainCategory));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Subcategory subcategory = subcategoryDAO.findById(id).
                orElseThrow(() -> new DAOException("Such subcategory does not exist"));

        List<Product> products = subcategory.getProducts();
        products.forEach(productDAO::remove);
        subcategoryDAO.remove(subcategory);
    }
}
