package com.spring.dto.adminDTO;

public class WaiterReportDTO {

    private String waiter;

    private Double totalAmount;

    private Double discountAmount;

    public WaiterReportDTO() {
    }

    public WaiterReportDTO(String waiter, Double totalAmount, Double discountAmount) {
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
}
