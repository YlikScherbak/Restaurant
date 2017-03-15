package com.spring.service.impl;

import com.spring.dao.DiscountDAO;
import com.spring.dao.OrderDAO;
import com.spring.dto.waitresDTO.DiscountDTO;
import com.spring.exception.DAOException;
import com.spring.exception.InsufficientPermissionsException;
import com.spring.model.Discount;
import com.spring.model.Order;
import com.spring.model.User;
import com.spring.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class DiscountServiceImpl implements DiscountService {

    final DiscountDAO discountDAO;

    final OrderDAO orderDAO;

    @Autowired
    public DiscountServiceImpl(DiscountDAO discountDAO, OrderDAO orderDAO) {
        this.discountDAO = discountDAO;
        this.orderDAO = orderDAO;
    }

    @Override
    @Transactional(readOnly = true)
    public List<DiscountDTO> getAllDiscount() {
        List<DiscountDTO> list = new ArrayList<>();
        discountDAO.findAll().forEach(discount -> list.add(new DiscountDTO(discount)));
        return list;
    }

    @Override
    @Transactional
    public void setDiscountToOrder(Long id, String discountName) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Order order = orderDAO.findById(id);

        if (order == null) {
            throw new DAOException("Such an order does not exist");
        } else if (!order.getUser().getUsername().equals(user.getUsername())) {
            throw new InsufficientPermissionsException("You can not add discount to foreign order.");
        }

        Discount discount = discountDAO.findDiscountByName(discountName);

        if (discount == null) {
            throw new DAOException("Such an discount does not exist");
        }

        if(order.getDiscount() != null){
            order.setTotalAmount(order.getTotalAmount() + order.getDiscountAmount());
        }

        order.setDiscount(discount);
        order.recount();
    }

    @Override
    @Transactional
    public void disableDiscount(Long id) {
        Order order = orderDAO.findById(id);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (order == null) {
            throw new DAOException("Such an order does not exist");
        } else if (!order.getUser().getUsername().equals(user.getUsername())) {
            throw new InsufficientPermissionsException("You can disable discount in foreign order.");
        }

        order.setDiscount(null);
        order.setTotalAmount(order.getTotalAmount() + order.getDiscountAmount());
        order.setDiscountAmount(0d);

    }
}
