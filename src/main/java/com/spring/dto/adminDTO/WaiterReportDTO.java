package com.spring.dto.adminDTO;

import java.math.BigDecimal;

public class WaiterReportDTO {

    private String waiter;

    private BigDecimal totalAmount;

    private BigDecimal discountAmount;

    public WaiterReportDTO() {
    }

    public WaiterReportDTO(String waiter, BigDecimal totalAmount, BigDecimal discountAmount) {
        this.waiter = waiter;
        this.totalAmount = totalAmount;
        this.discountAmount = discountAmount;
    }

    public String getWaiter() {
        return waiter;
    }

    public void setWaiter(String waiter) {
        this.waiter = waiter;
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
}
