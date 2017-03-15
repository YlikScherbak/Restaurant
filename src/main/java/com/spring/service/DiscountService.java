package com.spring.service;

import com.spring.dto.waitresDTO.DiscountDTO;

import java.util.List;

public interface DiscountService {

    List<DiscountDTO> getAllDiscount();

    void setDiscountToOrder(Long id, String discountName);

    void disableDiscount(Long id);
}
