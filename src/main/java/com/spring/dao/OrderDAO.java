package com.spring.dao;

import com.spring.dto.waitresDTO.CheckDTO;
import com.spring.model.Order;
import com.spring.model.WorkShift;

import java.util.List;

public interface OrderDAO extends MyDAO<Order, Long> {

    List<Order> getWaiterActiveOrders(long id);

    List<Order> getOrdersPagination(int start, int count);

    List<Order> getActiveOrdersPagination(int start, int count);

    CheckDTO getInfoForCheck(Long id);

    Boolean checkActiveOrders();

    List<Object[]> getReportInfo(WorkShift workShift);

}
