package com.spring.service.impl;

import com.spring.dao.GeneralReportDAO;
import com.spring.dao.OrderDAO;
import com.spring.dao.WorkShiftDAO;
import com.spring.dto.adminDTO.GeneralReportDTO;
import com.spring.exception.DAOException;
import com.spring.model.GeneralReport;
import com.spring.model.WaiterReport;
import com.spring.service.GeneralReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class GeneralReportServiceImpl implements GeneralReportService {

    private final GeneralReportDAO generalReportDAO;

    private final OrderDAO orderDAO;

    private final WorkShiftDAO workShiftDAO;

    @Autowired
    public GeneralReportServiceImpl(GeneralReportDAO generalReportDAO,
                                    OrderDAO orderDAO, WorkShiftDAO workShiftDAO) {
        this.generalReportDAO = generalReportDAO;
        this.orderDAO = orderDAO;
        this.workShiftDAO = workShiftDAO;
    }

    @Override
    @Transactional
    public Long createGeneralReport(Long id) {
        GeneralReport generalReport = new GeneralReport();
        List<Object[]> list = orderDAO.getReportInfo(workShiftDAO.findReferenceById(id));
        generalReport.setTotalAmount(list.stream().map(objects -> (Double) objects[1]).
                reduce((o1, o2) -> o1 + o2).orElse(0D));
        generalReport.setDiscountAmount(list.stream().map(objects -> (Double) objects[2]).
                reduce((o1, o2) -> o1 + o2).orElse(0D));
        generalReport.setWorkShift(workShiftDAO.findReferenceById(id));
        List<WaiterReport> waiterReports = new ArrayList<>();
        list.forEach(objects -> waiterReports.add(new WaiterReport((String) objects[0],  (BigDecimal) objects[1],
                (BigDecimal) objects[2], generalReport)));
        generalReport.setWaiterReports(waiterReports);
        generalReportDAO.save(generalReport);
        return generalReport.getId();
    }

    @Override
    @Transactional(readOnly = true)
    public GeneralReportDTO getGenReport(Long id) {
        GeneralReport generalReport = generalReportDAO.findById(id);
        if (generalReport == null) {
            throw new DAOException("Such report does not exist");
        }
        return new GeneralReportDTO(generalReport.getId(), generalReport.getTotalAmount()
                , generalReport.getDiscountAmount());
    }
}
