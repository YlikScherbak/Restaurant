package com.spring.dto.adminDTO;

import com.spring.model.OrderDetail;

import java.util.Date;

public class OrderDetailsAudition {

    private String prodName;

    private String category;

    private Integer count;

    private Date date;

    public OrderDetailsAudition() {
    }

    public OrderDetailsAudition(OrderDetail orderDetail){
        this.prodName = orderDetail.getId().getProdName();
        this.category = orderDetail.getCategory();
        this.count = orderDetail.getCount();
        this.date = orderDetail.getCreationDate();
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
