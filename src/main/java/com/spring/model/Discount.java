package com.spring.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@javax.persistence.Table(name = "discount")
public class Discount implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String discountName;

    @Column(name = "percentage", nullable = false)
    private Double discountPercentage;

    public Discount() {
    }

    public Discount(String discountName, Double discountPercentage) {
        this.discountName = discountName;
        this.discountPercentage = discountPercentage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDiscountName() {
        return discountName;
    }

    public void setDiscountName(String discountName) {
        this.discountName = discountName;
    }

    public Double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(Double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Discount discount = (Discount) o;

        return new EqualsBuilder()
                .append(id, discount.id)
                .append(discountName, discount.discountName)
                .append(discountPercentage, discount.discountPercentage)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(discountName)
                .append(discountPercentage)
                .toHashCode();
    }
}
