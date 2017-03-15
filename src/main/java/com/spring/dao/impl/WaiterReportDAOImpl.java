package com.spring.dao.impl;

import com.spring.dao.WaiterReportDAO;
import com.spring.model.WaiterReport;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class WaiterReportDAOImpl extends MyDAOImpl<WaiterReport, Long> implements WaiterReportDAO {

    public WaiterReportDAOImpl() {
        super(WaiterReport.class);
    }

    @Override
    public List<WaiterReport> getReportByGeneralId(Long id) {
        TypedQuery<WaiterReport> query = em.createQuery
                ("SELECT r FROM WaiterReport r WHERE r.generalReport.id = :id", WaiterReport.class).
                setParameter("id", id);
        return query.getResultList();
    }
}
