package com.spring.model;


import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@javax.persistence.Table(name = "work_shift")
public class WorkShift implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "creation_date", updatable = false)
    @CreationTimestamp
    private Date creationDate;

    @Column(name = "closed_date")
    private Date closedDate;

    @Column(name = "active", nullable = false)
    private Boolean active;

    @OneToMany(mappedBy = "workShift")
    private List<Order> orders = new ArrayList<>();

    @OneToOne(mappedBy = "workShift", cascade = CascadeType.PERSIST)
    private GeneralReport generalReports;

    public WorkShift() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getClosedDate() {
        return closedDate;
    }

    public void setClosedDate(Date closedDate) {
        this.closedDate = closedDate;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public GeneralReport getGeneralReports() {
        return generalReports;
    }

    public void setGeneralReports(GeneralReport generalReports) {
        this.generalReports = generalReports;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        WorkShift workShift = (WorkShift) o;

        return new EqualsBuilder()
                .append(id, workShift.id)
                .append(creationDate, workShift.creationDate)
                .append(closedDate, workShift.closedDate)
                .append(active, workShift.active)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(creationDate)
                .append(closedDate)
                .append(active)
                .toHashCode();
    }
}
