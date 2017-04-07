package com.spring.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@javax.persistence.Table(name = "waiter_report")
public class WaiterReport implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "waiter_name")
    private String waiter;

    @Column(name = "total_amount", nullable = false, updatable = false)
    private BigDecimal totalAmount;

    @Column(name = "discount_amount", nullable = false, updatable = false)
    private BigDecimal discountAmount;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "general_report_id")
    private GeneralReport generalReport;

    public WaiterReport() {
    }

    public WaiterReport(String waiter, BigDecimal totalAmount, BigDecimal discountAmount, GeneralReport generalReport) {
        this.waiter = waiter;
        this.totalAmount = totalAmount;
        this.discountAmount = discountAmount;
        this.generalReport = generalReport;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWaiter() {
        return waiter;
    }

    public void setWaiter(String waiter) {
        this.waiter = waiter;
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

    public GeneralReport getGeneralReport() {
        return generalReport;
    }

    public void setGeneralReport(GeneralReport generalReport) {
        this.generalReport = generalReport;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        WaiterReport that = (WaiterReport) o;

        return new EqualsBuilder()
                .append(id, that.id)
                .append(waiter, that.waiter)
                .append(totalAmount, that.totalAmount)
                .append(discountAmount, that.discountAmount)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(waiter)
                .append(totalAmount)
                .append(discountAmount)
                .toHashCode();
    }
}
