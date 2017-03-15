package com.spring.service;

import com.spring.dto.adminDTO.OrdersDTO;
import com.spring.dto.waitresDTO.CheckDTO;
import com.spring.dto.waitresDTO.OrderDTO;
import com.spring.dto.waitresDTO.WaiterOrdersDTO;

import java.util.List;

public interface OrderService {

    long createOrder(int table);

    OrderDTO getOrder(long id);

    List<WaiterOrdersDTO> getWaiterActiveOrders();

    void closeOrder(long id);

    Long count();

    List<OrdersDTO> getOrders(Boolean active, int start, int count);

    CheckDTO getCheckInfo(Long id);

    OrdersDTO searchOrder(Long id);

}
