package com.spring.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@javax.persistence.Table(name = "orders_details")
@Audited
public class OrderDetail implements Serializable {

    @EmbeddedId
    private OrderDetailsId id;

    @Column(name = "category", updatable = false)
    private String category;

    @Column(name = "count", nullable = false)
    private int count;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate = new Date();

    public OrderDetail() {
    }

    public OrderDetail(OrderDetailsId id, String category, int count) {
        this.id = id;
        this.category = category;
        this.count = count;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }


    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public OrderDetailsId getId() {
        return id;
    }

    public void setId(OrderDetailsId id) {
        this.id = id;
    }


    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        OrderDetail that = (OrderDetail) o;

        return new EqualsBuilder()
                .append(count, that.count)
                .append(id, that.id)
                .append(category, that.category)
                .append(price, that.price)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(category)
                .append(count)
                .append(price)
                .toHashCode();
    }

    @PreUpdate
    protected void onUpdate() {
        creationDate = new Date();
    }

}
