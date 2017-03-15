package com.spring.dto.waitresDTO;

import com.spring.model.Order;

import java.util.Date;

public class WaiterOrdersDTO {

    private Long id;

    private int tableId;

    private Date date;

    public WaiterOrdersDTO() {
    }

    public WaiterOrdersDTO(Order order) {
        this.id = order.getId();
        this.date = order.getCreationDate();
        this.tableId = order.getTable().getNumber();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
