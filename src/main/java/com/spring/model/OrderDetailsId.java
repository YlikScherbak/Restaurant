package com.spring.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class OrderDetailsId implements Serializable {


    @Column(name = "product_name")
    private String prodName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    public OrderDetailsId() {
    }

    public OrderDetailsId(String prodName, Order order) {
        this.prodName = prodName;
        this.order = order;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        OrderDetailsId orderDetailsId = (OrderDetailsId) o;

        return new EqualsBuilder()
                .append(prodName, orderDetailsId.prodName)
                .append(order, orderDetailsId.order)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(prodName)
                .append(order)
                .toHashCode();
    }
}
