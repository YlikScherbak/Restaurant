package com.spring.dto.waitresDTO;

import com.spring.model.Discount;

public class DiscountDTO {

    private String name;

    private Double percentage;

    public DiscountDTO() {
    }

    public DiscountDTO(String name, Double percentage) {
        this.name = name;
        this.percentage = percentage;
    }

    public DiscountDTO(Discount discount){
        this.name = discount.getDiscountName();
        this.percentage = discount.getDiscountPercentage();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }
}
