package com.spring.dto.waitresDTO;

import com.spring.model.Order;

import java.math.BigDecimal;
import java.util.List;

public class OrderDTO {

    private BigDecimal totalAmount;

    private BigDecimal discountAmount;

    private List<OrderDetalisDTO> list;

    public OrderDTO() {
    }

    public OrderDTO(Order order, List<OrderDetalisDTO> list) {
        this.totalAmount = order.getTotalAmount();
        this.discountAmount = order.getDiscountAmount();
        this.list = list;
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

    public List<OrderDetalisDTO> getList() {
        return list;
    }

    public void setList(List<OrderDetalisDTO> list) {
        this.list = list;
    }
}
