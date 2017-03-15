package com.spring.dao.impl;

import com.spring.dao.OrderDAO;
import com.spring.dto.waitresDTO.CheckDTO;
import com.spring.model.Order;
import com.spring.model.WorkShift;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;


@Repository
public class OrderDAOImpl extends MyDAOImpl<Order, Long> implements OrderDAO {

    public OrderDAOImpl() {
        super(Order.class);
    }

    @Override
    public List<Order> getWaiterActiveOrders(long id) {
        TypedQuery<Order> query = em.createQuery(
                "SELECT o FROM Order o where o.user.id = :id AND o.active = true ", Order.class
        ).setParameter("id", id);
        return query.getResultList();
    }

    @Override
    public List<Order> getOrdersPagination(int start, int count) {
        TypedQuery<Order> query = em.createQuery("SELECT o FROM Order o ORDER BY o.id DESC ", Order.class);
        if (start >= 0) {
            query.setFirstResult(start);
            query.setMaxResults(count);
        }
        return query.getResultList();

    }

    @Override
    public List<Order> getActiveOrdersPagination(int start, int count) {
        TypedQuery<Order> query = em.createQuery("SELECT o FROM Order o where o.active = true ORDER BY o.id DESC ", Order.class);
        if (start >= 0) {
            query.setFirstResult(start);
            query.setMaxResults(count);
        }
        return query.getResultList();
    }

    @Override
    public CheckDTO getInfoForCheck(Long id) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<CheckDTO> criteria = cb.createQuery(CheckDTO.class);
        Root<Order> order = criteria.from(Order.class);
        criteria.select(cb.construct(CheckDTO.class, order.get("id"), order.get("table").get("floor").get("floorName"),
                order.get("table").get("number"), order.get("creationDate"), order.get("totalAmount"),
                order.get("discountAmount"))).
                where(cb.equal(order.get("id"), id));
        TypedQuery<CheckDTO> query = em.createQuery(criteria);
        return query.getSingleResult();
    }

    @Override
    public Boolean checkActiveOrders() {
        TypedQuery<Order> query = em.createQuery("SELECT o FROM Order o where o.active = true", Order.class);
            query.setMaxResults(1);
        return !query.getResultList().isEmpty();
    }

    @Override
    public List<Object[]> getReportInfo(WorkShift workShift) {
        Query query = em.createQuery("SELECT o.user.username, SUM(o.totalAmount), SUM(o.discountAmount) " +
                "FROM Order o WHERE o.workShift = :id GROUP BY o.user.username", Object[].class).setParameter("id", workShift);
        List<Object[]> list;
        list = query.getResultList();
        return list;
    }
}
