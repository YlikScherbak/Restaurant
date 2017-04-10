package com.spring.dao;

import com.spring.model.Discount;

import java.util.Optional;

public interface DiscountDAO extends MyDAO<Discount, Integer> {

    Optional<Discount> findDiscountByName(String name);
}
