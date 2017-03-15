package com.spring.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@javax.persistence.Table(name = "floors")
public class Floor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "floor_name")
    private String floorName;

    @OneToMany(mappedBy = "floor", cascade = CascadeType.ALL)
    private List<Table> tables = new ArrayList<>();

    public Floor() {
    }

    public Floor(String floorName, List<com.spring.model.Table> tables) {
        this.floorName = floorName;
        this.tables = tables;
    }

    public String getFloorName() {
        return floorName;
    }

    public void setFloorName(String floorName) {
        this.floorName = floorName;
    }

    public List<com.spring.model.Table> getTables() {
        return tables;
    }

    public void setTables(List<Table> tables) {
        this.tables = tables;
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

        Floor floor = (Floor) o;

        return new EqualsBuilder()
                .append(id, floor.id)
                .append(floorName, floor.floorName)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(floorName)
                .toHashCode();
    }
}
