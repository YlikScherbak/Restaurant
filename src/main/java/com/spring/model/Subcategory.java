package com.spring.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@javax.persistence.Table(name = "subcategories")
public class Subcategory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "Subcategory must be entered")
    @Column(name = "subcategory")
    private String subcategory;

    @OneToMany(mappedBy = "subcategory")
    private List<Product> products = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private MenuCategory menuCategory;

    public Subcategory() {
    }

    public Subcategory(String subcategory, MenuCategory menuCategory) {
        this.subcategory = subcategory;
        this.menuCategory = menuCategory;
    }

    public Subcategory(String subcategory) {
        this.subcategory = subcategory;
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

    public MenuCategory getMenuCategory() {
        return menuCategory;
    }

    public void setMenuCategory(MenuCategory menuCategory) {
        this.menuCategory = menuCategory;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Subcategory that = (Subcategory) o;

        return new EqualsBuilder()
                .append(id, that.id)
                .append(subcategory, that.subcategory)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(subcategory)
                .toHashCode();
    }
}
