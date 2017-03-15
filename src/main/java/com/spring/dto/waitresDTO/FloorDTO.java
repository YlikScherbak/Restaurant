package com.spring.dto.waitresDTO;

import java.util.ArrayList;
import java.util.List;

public class FloorDTO {

    private Long id;

    private String floorName;

    private List<TablesDTO> tables = new ArrayList<>();

    public FloorDTO() {
    }

    public FloorDTO(String floorName, List<TablesDTO> tables, Long id) {
        this.floorName = floorName;
        this.tables = tables;
        this.id = id;
    }

    public String getFloorName() {
        return floorName;
    }

    public void setFloorName(String floorName) {
        this.floorName = floorName;
    }

    public List<TablesDTO> getTables() {
        return tables;
    }

    public void setTables(List<TablesDTO> tables) {
        this.tables = tables;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
