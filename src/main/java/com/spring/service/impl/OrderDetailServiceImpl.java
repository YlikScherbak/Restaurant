package com.spring.service.impl;

import com.spring.dao.OrderDAO;
import com.spring.dao.OrderDetailsDAO;
import com.spring.dao.ProductDAO;
import com.spring.dto.adminDTO.OrderDetailsAudition;
import com.spring.dto.waitresDTO.OrderDetalisDTO;
import com.spring.exception.DAOException;
import com.spring.exception.InsufficientPermissionsException;
import com.spring.model.*;
import com.spring.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    private final OrderDetailsDAO orderDetailsDAO;

    private final OrderDAO orderDAO;

    private final ProductDAO productDAO;

    @Autowired
    public OrderDetailServiceImpl(OrderDetailsDAO orderDetailsDAO, OrderDAO orderDAO, ProductDAO productDAO) {
        this.orderDetailsDAO = orderDetailsDAO;
        this.orderDAO = orderDAO;
        this.productDAO = productDAO;
    }


    @Override
    @Transactional
    public String addOrderDetail(long id, String productName, Boolean compliment) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Order order = orderDAO.findById(id);
        Product product = productDAO.findProductByName(productName).
                orElseThrow(() -> new DAOException("Such product does not exist"));

        if (!order.getUser().equals(user)) {
            throw new InsufficientPermissionsException("You can not control others order");
        }

        OrderDetailsId orderDetailsId = new OrderDetailsId
                ((compliment) ? product.getProdName() + " (compliment)" : product.getProdName(), order);

        OrderDetail od = orderDetailsDAO.findById(orderDetailsId);

        if (od == null) {
            od = new OrderDetail(orderDetailsId, product.getSubcategory().getSubcategory(), 1);
            od.setPrice((compliment) ? BigDecimal.ZERO : BigDecimal.valueOf(product.getPrice()));
            orderDetailsDAO.save(od);
            order.getOrderDetails().add(od);
        } else {
            od.setCount(od.getCount() + 1);
        }

        if (order.getDiscount() == null) {
            order.setTotalAmount(order.getTotalAmount().add(od.getPrice()));
        } else {
            order.setAmountWithDiscount(od.getPrice());
        }

        return od.getId().getProdName();
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderDetalisDTO> getOrderDetails(Long id) {
        return orderDetailsDAO.getOrderDetails(id);
    }

    @Override
    @Transactional
    public void deleteOrderDetail(Long id, String prodName) {
        Order order = orderDAO.findReferenceById(id);
        OrderDetail orderDetail = orderDetailsDAO.findById(new OrderDetailsId(prodName, order));
        if (orderDetail == null) {
            throw new DAOException("Such product in order does not exist");
        } else if (!order.isActive()) {
            throw new InsufficientPermissionsException("You can't delete product from closed order");
        }

        if (order.getDiscount() == null) {
            order.setTotalAmount(order.getTotalAmount().subtract(orderDetail.getPrice()));
        } else {
            order.setAmountOnDelete(orderDetail.getPrice());
        }
        orderDetailsDAO.remove(orderDetail);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderDetailsAudition> getAudition(Long id) {
        Order order = orderDAO.findById(id);

        if (order == null) {
            throw new DAOException("Such order does not exist");
        }
        List<OrderDetailsAudition> dto = new ArrayList<>();
        orderDetailsDAO.getRevision(order).forEach(orderDetail -> dto.add(new OrderDetailsAudition(orderDetail)));

        return dto;
    }
}
