package com.spring.dto.adminDTO;

import com.spring.model.Product;

import java.util.List;

public class ProductDTO {

    private String subcategory;

    private List<Product> products;

    public ProductDTO(String subcategory, List<Product> products) {
        this.subcategory = subcategory;
        this.products = products;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
