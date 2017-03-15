package com.spring.dto.adminDTO;

import com.spring.dto.waitresDTO.OrderDetalisDTO;
import com.spring.model.Order;

import java.util.List;

public class EditOrderDTO {

    private String username;

    private Long id;

    private List<OrderDetalisDTO> list;

    public EditOrderDTO(Order order, List<OrderDetalisDTO> list) {
        this.list = list;
        this.username = order.getUser().getUsername();
        this.id = order.getId();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<OrderDetalisDTO> getList() {
        return list;
    }

    public void setList(List<OrderDetalisDTO> list) {
        this.list = list;
    }
}
