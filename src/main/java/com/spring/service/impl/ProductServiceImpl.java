package com.spring.service.impl;

import com.spring.dao.ProductDAO;
import com.spring.dao.SubcategoryDAO;
import com.spring.dto.adminDTO.ProductDTO;
import com.spring.exception.DAOException;
import com.spring.model.Product;
import com.spring.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {


    private final ProductDAO productDAO;

    private final SubcategoryDAO subcategoryDAO;

    @Autowired
    public ProductServiceImpl(ProductDAO productDAO, SubcategoryDAO subcategoryDAO) {
        this.productDAO = productDAO;
        this.subcategoryDAO = subcategoryDAO;
    }

    @Override
    @Transactional
    public void addProduct(Product product, Long subcategory) {
        if (productDAO.findProductByName(product.getProdName()).isPresent()) {
            throw new DAOException("Duplicate product name");
        }

        productDAO.save(new Product(product.getProdName(), product.getPrice(), subcategoryDAO.findReferenceById(subcategory)));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductDTO> getAllProduct() {
        List<ProductDTO> list = new ArrayList<>();
        subcategoryDAO.findAll().forEach(subcategory ->
                list.add(new ProductDTO(subcategory.getSubcategory(), subcategory.getProducts())));
        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public Product getById(Long id) {
        return productDAO.findById(id).orElseThrow(() -> new DAOException("Such product does not exist"));
    }

    @Override
    @Transactional
    public void edit(Product product, Long id, Long subcategory) {
        Product oldProd = productDAO.findById(id).orElseThrow(() -> new DAOException("Such product does not exist"));

        if (productDAO.findProductByName(product.getProdName()).isPresent()
                && (!oldProd.getProdName().equals(product.getProdName()))) {
            throw new DAOException("Duplicate product name");
        }
        oldProd.setProdName(product.getProdName());
        oldProd.setPrice(product.getPrice());
        oldProd.setSubcategory(subcategoryDAO.findReferenceById(subcategory));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Product product = productDAO.findById(id).orElseThrow(() -> new DAOException("Such product does not exist"));

        productDAO.remove(product);
    }
}
