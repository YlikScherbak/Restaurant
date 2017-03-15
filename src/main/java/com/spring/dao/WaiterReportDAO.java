package com.spring.dao;

import com.spring.model.WaiterReport;

import java.util.List;

public interface WaiterReportDAO extends MyDAO<WaiterReport, Long> {

    List<WaiterReport> getReportByGeneralId(Long id);

}
