package com.spring.service;

import com.spring.dto.adminDTO.ProductDTO;
import com.spring.model.Product;

import java.util.List;

public interface ProductService {

    void addProduct(Product product, Long subcategory);

    List<ProductDTO> getAllProduct();

    Product getById(Long id);

    void edit(Product product, Long id, Long subcategory);

    void delete(Long id);
}
