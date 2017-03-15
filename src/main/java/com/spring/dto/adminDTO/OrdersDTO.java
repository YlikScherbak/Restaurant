package com.spring.dto.adminDTO;

import com.spring.model.Order;

public class OrdersDTO {

    private Long id;

    private String username;

    private Boolean active;

    public OrdersDTO() {
    }

    public OrdersDTO(Order order) {
        this.id = order.getId();
        this.username = order.getUser().getUsername();
        this.active = order.isActive();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
