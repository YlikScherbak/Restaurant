package com.spring.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@javax.persistence.Table(name = "general_report")
public class GeneralReport implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "total_amount", nullable = false, updatable = false)
    private Double totalAmount;

    @Column(name = "discount_amount", nullable = false, updatable = false)
    private Double discountAmount;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, optional = false)
    @JoinColumn(name = "work_shift_id")
    private WorkShift workShift;

    @OneToMany(mappedBy = "generalReport", cascade = CascadeType.PERSIST)
    private List<WaiterReport> waiterReports = new ArrayList<>();

    public GeneralReport() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(Double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public WorkShift getWorkShift() {
        return workShift;
    }

    public void setWorkShift(WorkShift workShift) {
        this.workShift = workShift;
    }

    public List<WaiterReport> getWaiterReports() {
        return waiterReports;
    }

    public void setWaiterReports(List<WaiterReport> waiterReports) {
        this.waiterReports = waiterReports;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        GeneralReport that = (GeneralReport) o;

        return new EqualsBuilder()
                .append(id, that.id)
                .append(totalAmount, that.totalAmount)
                .append(discountAmount, that.discountAmount)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(totalAmount)
                .append(discountAmount)
                .toHashCode();
    }
}
