package com.spring.dao.impl;

import com.spring.dao.OrderDetailsDAO;
import com.spring.dto.waitresDTO.OrderDetalisDTO;
import com.spring.model.Order;
import com.spring.model.OrderDetail;
import com.spring.model.OrderDetailsId;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;


@Repository
public class OrderDetailsDAOImpl extends MyDAOImpl<OrderDetail, OrderDetailsId> implements OrderDetailsDAO {

    public OrderDetailsDAOImpl() {
        super(OrderDetail.class);
    }

    @Override
    public List<OrderDetalisDTO> getOrderDetails(Long id) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<OrderDetalisDTO> criteria = cb.createQuery(OrderDetalisDTO.class);
        Root<OrderDetail> od = criteria.from(OrderDetail.class);
        criteria.select(cb.construct(OrderDetalisDTO.class,
                od.get("id").get("prodName"), od.get("category"), od.get("count"), od.get("price"))).
                where(cb.equal(od.get("id").get("order").get("id"), id));
        criteria.orderBy(cb.asc(od.get("category")));
        TypedQuery<OrderDetalisDTO> query = em.createQuery(criteria);
        return query.getResultList();
    }


    @Override
    public List<OrderDetail> getRevision(Order order) {
        AuditReader auditReader = AuditReaderFactory.get(em);
        AuditQuery query = auditReader.createQuery().forRevisionsOfEntity(OrderDetail.class, false, false).
                add(AuditEntity.property("id.order").eq(order));
        List<Object[]> list = query.getResultList();
        List<OrderDetail> orderDetails = new ArrayList<>(list.size());

        list.forEach(objects -> orderDetails.add((OrderDetail) objects[0]));

        return orderDetails;
    }
}


