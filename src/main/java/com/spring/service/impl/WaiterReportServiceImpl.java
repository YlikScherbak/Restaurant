package com.spring.service.impl;

import com.spring.dao.WaiterReportDAO;
import com.spring.dto.adminDTO.WaiterReportDTO;
import com.spring.service.WaiterReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
public class WaiterReportServiceImpl implements WaiterReportService {

    private final WaiterReportDAO waiterReportDAO;

    @Autowired
    public WaiterReportServiceImpl(WaiterReportDAO waiterReportDAO) {
        this.waiterReportDAO = waiterReportDAO;
    }

    @Override
    @Transactional(readOnly = true)
    public List<WaiterReportDTO> getWaiterReports(Long id) {
        List<WaiterReportDTO> list = new ArrayList<>();
        waiterReportDAO.getReportByGeneralId(id).forEach(waiterReport ->
                list.add(new WaiterReportDTO(waiterReport.getWaiter(),
                        waiterReport.getTotalAmount(), waiterReport.getDiscountAmount())));
        return list;
    }
}
