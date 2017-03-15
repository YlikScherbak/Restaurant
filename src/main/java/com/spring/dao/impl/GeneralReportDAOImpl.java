package com.spring.dao.impl;

import com.spring.dao.GeneralReportDAO;
import com.spring.model.GeneralReport;
import org.springframework.stereotype.Repository;

@Repository
public class GeneralReportDAOImpl extends MyDAOImpl<GeneralReport, Long> implements GeneralReportDAO {

    public GeneralReportDAOImpl() {
        super(GeneralReport.class);
    }
}
