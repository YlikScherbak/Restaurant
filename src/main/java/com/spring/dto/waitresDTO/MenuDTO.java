package com.spring.dto.waitresDTO;


import com.spring.model.MenuCategory;
import com.spring.model.Product;
import com.spring.model.Subcategory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MenuDTO {

    private String category;

    private Map<String, List<String>> map = new HashMap<>();

    public MenuDTO() {
    }

    public MenuDTO(MenuCategory menuCategory) {
        setCategory(menuCategory.getCategory());

        for(Subcategory sub : menuCategory.getSubcategories()){
            getMap().put(sub.getSubcategory(),
                    sub.getProducts().stream().map(Product::getProdName).collect(Collectors.toList()));
        }
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Map<String, List<String>> getMap() {
        return map;
    }

    public void setMap(Map<String, List<String>> map) {
        this.map = map;
    }

}
