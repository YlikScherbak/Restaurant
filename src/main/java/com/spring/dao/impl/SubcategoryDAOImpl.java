package com.spring.dao.impl;

import com.spring.dao.SubcategoryDAO;
import com.spring.model.Subcategory;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class SubcategoryDAOImpl extends MyDAOImpl<Subcategory, Long> implements SubcategoryDAO {

    public SubcategoryDAOImpl() {
        super(Subcategory.class);
    }

    @Override
    public List<Subcategory> findBySubcategory(String subcategory) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Subcategory> criteria = cb.createQuery(Subcategory.class);
        Root<Subcategory> subCategory = criteria.from(Subcategory.class);
        criteria.select(subCategory).where(cb.equal(subCategory.get("subcategory"), subcategory));
        TypedQuery<Subcategory> query = em.createQuery(criteria);
        return query.getResultList();
    }
}
