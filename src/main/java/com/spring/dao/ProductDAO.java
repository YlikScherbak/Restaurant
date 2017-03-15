package com.spring.dao;

import com.spring.model.Product;

import java.util.List;

public interface ProductDAO extends MyDAO<Product, Long> {

    List<Product> findProductByName(String name);

}
