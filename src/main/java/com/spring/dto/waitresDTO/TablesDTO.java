package com.spring.dto.waitresDTO;


import com.spring.model.Table;

public class TablesDTO {

    private Integer number;

    private boolean occupied;

    private String name;

    private Long orderId;

    public TablesDTO() {
    }

    public TablesDTO(Table table) {
        if (table.isOccupied()) {
            this.number = table.getNumber();
            this.name = table.getUser().getUsername();
            this.occupied = table.isOccupied();
            this.orderId = table.getOrder().getId();
        } else {
            this.occupied = table.isOccupied();
            this.number = table.getNumber();
        }
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long id) {
        this.orderId = id;
    }

}
