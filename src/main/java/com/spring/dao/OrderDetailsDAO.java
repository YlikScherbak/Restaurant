package com.spring.dao;

import com.spring.dto.waitresDTO.OrderDetalisDTO;
import com.spring.model.Order;
import com.spring.model.OrderDetail;
import com.spring.model.OrderDetailsId;

import java.util.List;

public interface OrderDetailsDAO extends MyDAO<OrderDetail, OrderDetailsId> {

    List<OrderDetalisDTO> getOrderDetails(Long id);

    List<OrderDetail> getRevision(Order order);

}
