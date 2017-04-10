package com.spring.service.impl;

import com.spring.dao.OrderDAO;
import com.spring.dao.OrderDetailsDAO;
import com.spring.dao.TableDAO;
import com.spring.dao.WorkShiftDAO;
import com.spring.dto.adminDTO.OrdersDTO;
import com.spring.dto.waitresDTO.CheckDTO;
import com.spring.dto.waitresDTO.OrderDTO;
import com.spring.dto.waitresDTO.OrderDetalisDTO;
import com.spring.dto.waitresDTO.WaiterOrdersDTO;
import com.spring.exception.DAOException;
import com.spring.exception.InsufficientPermissionsException;
import com.spring.model.Order;
import com.spring.model.Table;
import com.spring.model.User;
import com.spring.model.WorkShift;
import com.spring.model.enums.Role;
import com.spring.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderDAO orderDAO;

    private final TableDAO tableDAO;

    private final OrderDetailsDAO orderDetailsDAO;

    private final WorkShiftDAO workShiftDAO;

    @Autowired
    public OrderServiceImpl(OrderDAO orderDAO, TableDAO tableDAO, OrderDetailsDAO orderDetailsDAO,
                            WorkShiftDAO workShiftDAO) {
        this.orderDAO = orderDAO;
        this.tableDAO = tableDAO;
        this.workShiftDAO = workShiftDAO;
        this.orderDetailsDAO = orderDetailsDAO;
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public long createOrder(int number) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Table table = tableDAO.findById(number).
                orElseThrow(() -> new DAOException("Table not found"));

        if (table.getUser() != null) {
            throw new InsufficientPermissionsException("You can not create a new order, as the table is already in use");
        }

        WorkShift workShift = workShiftDAO.getActiveWorkShift().orElseThrow(() -> new DAOException("Work shift is not open"));
        Order order = new Order(true, table, user, BigDecimal.ZERO, BigDecimal.ZERO);
        order.setWorkShift(workShift);
        table.setOccupied(true);
        table.setUser(user);
        table.setOrder(order);
        orderDAO.save(order);
        return order.getId();
    }

    @Override
    @Transactional(readOnly = true)
    public OrderDTO getOrder(long id) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<OrderDetalisDTO> list = new ArrayList<>();
        Order order = orderDAO.findById(id).orElseThrow(() -> new DAOException("Order not found"));

        if (!order.getUser().equals(user) ^ user.getUserAuthorities() == Role.ROLE_ADMIN) {
            throw new InsufficientPermissionsException("This order is served by another waiter");
        }

        List<OrderDetalisDTO> sorted = orderDetailsDAO.getOrderDetails(id);
        return new OrderDTO(order, sorted);

    }

    @Override
    @Transactional(readOnly = true)
    public List<WaiterOrdersDTO> getWaiterActiveOrders() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<WaiterOrdersDTO> list = new ArrayList<>();
        orderDAO.getWaiterActiveOrders(user.getId()).forEach(order ->
                list.add(new WaiterOrdersDTO(order)));
        return list;
    }

    @Override
    @Transactional
    public void closeOrder(long id) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Order order = orderDAO.findById(id).orElseThrow(() -> new DAOException("Such an order does not exist"));

        if (!order.getUser().getUsername().equals(user.getUsername())) {
            throw new InsufficientPermissionsException("You can not close the foreign order");
        }
        order.setActive(false);
        order.getTable().setOccupied(false);
        order.getTable().setUser(null);
        order.getTable().setOrder(null);
        order.setClosedDate(new Date());
    }

    @Override
    @Transactional(readOnly = true)
    public Long count() {
        return orderDAO.getCount().orElse(0L);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrdersDTO> getOrders(Boolean active, int start, int count) {
        List<OrdersDTO> list = new ArrayList<>();
        if (active) {
            orderDAO.getActiveOrdersPagination(start, count).forEach(order -> list.add(new OrdersDTO(order)));
        } else {
            orderDAO.getOrdersPagination(start, count).forEach(order -> list.add(new OrdersDTO(order)));
        }
        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public CheckDTO getCheckInfo(Long id) {
        return orderDAO.getInfoForCheck(id);
    }

    @Override
    @Transactional
    public OrdersDTO searchOrder(Long id) {
        Order order = orderDAO.findById(id).orElseThrow(() -> new DAOException("Such an order does not exist"));

        return new OrdersDTO(order);
    }
}
