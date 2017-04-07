package com.spring.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@javax.persistence.Table(name = "orders")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Basic
    @Column(name = "active")
    private boolean active;

    @Basic
    @Column(name = "total_amount", precision = 10, scale = 2)
    private BigDecimal totalAmount;

    @Column(name = "discount_amount", precision = 10, scale = 2)
    private BigDecimal discountAmount;

    @OneToMany(mappedBy = "id.order")
    private List<OrderDetail> orderDetails = new ArrayList<>();

    @OneToOne(mappedBy = "order", fetch = FetchType.LAZY, optional = false)
    private Table table;

    @Column(name = "creation_date", updatable = false)
    @CreationTimestamp
    private Date creationDate;

    @Column(name = "closed_date")
    private Date closedDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "discount_id")
    private Discount discount;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "work_shift_id")
    private WorkShift workShift;


    public Order() {
    }

    public Order(boolean active, Table table, User user, BigDecimal totalAmount, BigDecimal discountAmount) {
        this.active = active;
        this.table = table;
        this.user = user;
        this.totalAmount = totalAmount;
        this.discountAmount = discountAmount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public com.spring.model.Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public Date getClosedDate() {
        return closedDate;
    }

    public void setClosedDate(Date closedDate) {
        this.closedDate = closedDate;
    }

    public WorkShift getWorkShift() {
        return workShift;
    }

    public void setWorkShift(WorkShift workShift) {
        this.workShift = workShift;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        return new EqualsBuilder()
                .append(active, order.active)
                .append(id, order.id)
                .append(table, order.table)
                .append(creationDate, order.creationDate)
                .append(user, order.user)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(active)
                .append(table)
                .append(creationDate)
                .append(user)
                .toHashCode();
    }

    public void recount() {
        BigDecimal discount = getTotalAmount().multiply(BigDecimal.valueOf(getDiscount().getDiscountPercentage()).
                divide(new BigDecimal(100), 6, BigDecimal.ROUND_HALF_UP));
        setDiscountAmount(discount.setScale(2, BigDecimal.ROUND_HALF_UP));
        setTotalAmount(getTotalAmount().subtract(discount).setScale(2, BigDecimal.ROUND_HALF_UP));
    }

    public void setAmountWithDiscount(BigDecimal price) {
        setDiscountAmount((getDiscountAmount().
                add(price.multiply(BigDecimal.valueOf(getDiscount().getDiscountPercentage()).
                        divide(new BigDecimal(100), 6, BigDecimal.ROUND_HALF_UP)))).setScale(2, BigDecimal.ROUND_HALF_UP));
        setTotalAmount((getTotalAmount().add(price.subtract(price.multiply
                (BigDecimal.valueOf(getDiscount().getDiscountPercentage()).
                        divide(new BigDecimal(100), 6, BigDecimal.ROUND_HALF_UP))))).setScale(2, BigDecimal.ROUND_HALF_UP));
    }

    public void setAmountOnDelete(BigDecimal price) {
        setDiscountAmount((getDiscountAmount().subtract(price.multiply(BigDecimal.valueOf(getDiscount().getDiscountPercentage()).
                divide(new BigDecimal(100), 6, BigDecimal.ROUND_HALF_UP)))).setScale(2, BigDecimal.ROUND_HALF_UP));
        setTotalAmount((getTotalAmount().subtract(price.subtract(price.multiply(BigDecimal.valueOf(getDiscount().getDiscountPercentage()).
                divide(new BigDecimal(100), 6, BigDecimal.ROUND_HALF_UP))))).setScale(2, BigDecimal.ROUND_HALF_UP));
    }

}
