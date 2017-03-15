package com.spring.dao;

import com.spring.model.Discount;

public interface DiscountDAO extends MyDAO<Discount, Integer> {

    Discount findDiscountByName(String name);
}
