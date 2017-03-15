package com.spring.dto.waitresDTO;

import java.util.Date;

public class CheckDTO {

    private Long orderNumber;

    private String floorName;

    private Integer tableNumber;

    private Date creationDate;

    private Double totalAmount;

    private Double discountAmount;

    public CheckDTO() {
    }

    public CheckDTO(Long orderNumber, String floorName, Integer tableNumber, Date creationDate, Double totalAmount, Double discountAmount) {
        this.orderNumber = orderNumber;
        this.floorName = floorName;
        this.tableNumber = tableNumber;
        this.creationDate = creationDate;
        this.totalAmount = totalAmount;
        this.discountAmount = discountAmount;
    }

    public Long getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Long orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getFloorName() {
        return floorName;
    }

    public void setFloorName(String floorName) {
        this.floorName = floorName;
    }

    public Integer getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(Integer tableNumber) {
        this.tableNumber = tableNumber;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
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
