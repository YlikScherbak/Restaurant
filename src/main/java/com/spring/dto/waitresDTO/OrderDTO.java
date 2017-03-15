package com.spring.dto.waitresDTO;

import com.spring.model.Order;

import java.util.List;

public class OrderDTO {

    private Double totalAmount;

    private Double discountAmount;

    private List<OrderDetalisDTO> list;

    public OrderDTO() {
    }

    public OrderDTO(Order order, List<OrderDetalisDTO> list) {
        this.totalAmount = order.getTotalAmount();
        this.discountAmount = order.getDiscountAmount();
        this.list = list;
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

    public List<OrderDetalisDTO> getList() {
        return list;
    }

    public void setList(List<OrderDetalisDTO> list) {
        this.list = list;
    }
}
