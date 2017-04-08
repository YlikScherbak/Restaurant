package com.spring.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@javax.persistence.Table(name = "menu_categories")
public class MenuCategory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "Name can not be empty")
    @Column(name = "category")
    private String category;

    @OneToMany(mappedBy = "menuCategory", cascade = CascadeType.ALL)
    private List<Subcategory> subcategories = new ArrayList<>();

    public MenuCategory(String category) {
        this.category = category;
    }

    public MenuCategory() {
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<Subcategory> getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(List<Subcategory> subcategories) {
        this.subcategories = subcategories;
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

        MenuCategory that = (MenuCategory) o;

        return new EqualsBuilder()
                .append(id, that.id)
                .append(category, that.category)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(category)
                .toHashCode();
    }
}
