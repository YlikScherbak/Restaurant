package com.spring.dao;

import com.spring.model.Product;

import java.util.Optional;

public interface ProductDAO extends MyDAO<Product, Long> {

    Optional<Product> findProductByName(String name);

}
