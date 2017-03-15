package com.spring.dao.impl;

import com.spring.dao.MyDAO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.List;


public class MyDAOImpl<T, ID extends Serializable> implements MyDAO<T, ID> {

    @PersistenceContext
    protected EntityManager em;
    protected final Class<T> entity;


    public MyDAOImpl(Class<T> entity) {
        this.entity = entity;
    }

    @Override
    public T findById(ID id) {
        return em.find(entity, id);
    }

    @Override
    public T findReferenceById(ID id) {
        return em.getReference(entity, id);
    }

    @Override
    public void save(T entity) {
        em.persist(entity);
    }

    @Override
    public List<T> findAll() {
        CriteriaQuery<T> c = em.getCriteriaBuilder().createQuery(entity);
        c.select(c.from(entity));
        return em.createQuery(c).getResultList();
    }

    @Override
    public void remove(T entity) {
        em.remove(entity);
    }

    @Override
    public void merge(T entity) {
        em.merge(entity);
    }

    @Override
    public void detach(T entity) {
        em.detach(entity);
    }

    @Override
    public Long getCount() {
        CriteriaQuery<Long> c =
                em.getCriteriaBuilder().createQuery(Long.class);
        c.select(em.getCriteriaBuilder().count(c.from(entity)));
        return em.createQuery(c).getSingleResult();
    }
}
