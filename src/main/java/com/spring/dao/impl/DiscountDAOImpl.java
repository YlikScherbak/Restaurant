package com.spring.dao.impl;

import com.spring.dao.DiscountDAO;
import com.spring.model.Discount;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
public class DiscountDAOImpl extends MyDAOImpl<Discount, Integer> implements DiscountDAO {

    public DiscountDAOImpl() {
        super(Discount.class);
    }

    @Override
    public Discount findDiscountByName(String name) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Discount> criteria = cb.createQuery(Discount.class);
        Root<Discount> user = criteria.from(Discount.class);
        criteria.select(user).where(cb.equal(user.get("discountName"), name));
        TypedQuery<Discount> query = em.createQuery(criteria);
        return query.getSingleResult();
    }
}
