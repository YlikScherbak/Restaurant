package com.spring.dto.adminDTO;

public class GeneralReportDTO {

    private Long id;

    private Double totalAmount;

    private Double discountAmount;

    public GeneralReportDTO(Long id, Double totalAmount, Double discountAmount) {
        this.id = id;
        this.totalAmount = totalAmount;
        this.discountAmount = discountAmount;
    }

    public GeneralReportDTO() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
