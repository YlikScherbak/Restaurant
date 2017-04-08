package com.spring.service;

import com.spring.dto.adminDTO.OrderDetailsAudition;
import com.spring.dto.waitresDTO.OrderDetalisDTO;

import java.util.List;

public interface OrderDetailService {

    String addOrderDetail(long id, String product, Boolean compliment);

    void deleteOrderDetail(Long id, String prodName);

    List<OrderDetailsAudition> getAudition(Long id);

    List<OrderDetalisDTO> getOrderDetails(Long id);
}
