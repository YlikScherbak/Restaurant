package com.spring.dto.waitresDTO;

import java.math.BigDecimal;

public class OrderDetalisDTO {

    private String prod_name;

    private String prod_category;

    private int count;

    private BigDecimal sum;

    public OrderDetalisDTO() {
    }

    public OrderDetalisDTO(String prod_name, String prod_category, int count, BigDecimal sum) {
        this.prod_name = prod_name;
        this.prod_category = prod_category;
        this.count = count;
        this.sum = sum;
    }

    public String getProd_name() {
        return prod_name;
    }

    public void setProd_name(String prod_name) {
        this.prod_name = prod_name;
    }

    public String getProd_category() {
        return prod_category;
    }

    public void setProd_category(String prod_category) {
        this.prod_category = prod_category;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

}
