package com.spring.dao.impl;

import com.spring.dao.MenuCategoryDAO;
import com.spring.model.MenuCategory;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Optional;

@Repository
public class MenuCategoryDAOImpl extends MyDAOImpl<MenuCategory, Long> implements MenuCategoryDAO {

    public MenuCategoryDAOImpl() {
        super(MenuCategory.class);
    }

    @Override
    public Optional<MenuCategory> findByCategory(String category) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<MenuCategory> criteria = cb.createQuery(MenuCategory.class);
        Root<MenuCategory> menuCategory = criteria.from(MenuCategory.class);
        criteria.select(menuCategory).where(cb.equal(menuCategory.get("category"), category));
        TypedQuery<MenuCategory> query = em.createQuery(criteria);
        return Optional.of(query.getSingleResult());
    }
}
