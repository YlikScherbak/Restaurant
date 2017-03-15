package com.spring.dao.impl;

import com.spring.dao.WorkShiftDAO;
import com.spring.dto.adminDTO.WorkShiftDTO;
import com.spring.model.WorkShift;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class WorkShiftDAOImpl extends MyDAOImpl<WorkShift, Long> implements WorkShiftDAO {

    public WorkShiftDAOImpl() {
        super(WorkShift.class);
    }

    @Override
    public List<WorkShift> getActiveWorkShift() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<WorkShift> criteria = cb.createQuery(WorkShift.class);
        Root<WorkShift> shift = criteria.from(WorkShift.class);
        criteria.select(shift).where(cb.equal(shift.get("active"), true));
        TypedQuery<WorkShift> query = em.createQuery(criteria);
        return query.getResultList();
    }

    @Override
    public List<WorkShiftDTO> getWorkShifts(int start, int count) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<WorkShiftDTO> criteria = cb.createQuery(WorkShiftDTO.class);
        Root<WorkShift> shift = criteria.from(WorkShift.class);
        criteria.select(cb.construct(WorkShiftDTO.class, shift.get("id"), shift.get("creationDate"),
                shift.get("closedDate"), shift.get("generalReports").get("id")));
        criteria.orderBy(cb.desc(shift.get("id")));
        TypedQuery<WorkShiftDTO> query = em.createQuery(criteria);
        if (start >= 0) {
            query.setFirstResult(start);
            query.setMaxResults(count);
        }
        return query.getResultList();
    }
}
