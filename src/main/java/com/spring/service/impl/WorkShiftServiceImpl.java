package com.spring.service.impl;

import com.spring.dao.OrderDAO;
import com.spring.dao.WorkShiftDAO;
import com.spring.dto.adminDTO.WorkShiftDTO;
import com.spring.exception.DAOException;
import com.spring.exception.InsufficientPermissionsException;
import com.spring.model.WorkShift;
import com.spring.service.WorkShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class WorkShiftServiceImpl implements WorkShiftService {

    private final WorkShiftDAO workShiftDAO;

    private final OrderDAO orderDAO;

    @Autowired
    public WorkShiftServiceImpl(WorkShiftDAO workShiftDAO, OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
        this.workShiftDAO = workShiftDAO;
    }

    @Override
    @Transactional(readOnly = true)
    public WorkShift getActiveWorkShift() {
        if (workShiftDAO.getActiveWorkShift().isEmpty()) {
            return null;
        } else return workShiftDAO.getActiveWorkShift().get(0);
    }

    @Override
    @Transactional
    public void openNewWorkShift() {
        if (!workShiftDAO.getActiveWorkShift().isEmpty()) {
            throw new InsufficientPermissionsException("Work shift is already open");
        }

        WorkShift workShift = new WorkShift();
        workShift.setActive(true);
        workShiftDAO.save(workShift);
    }

    @Override
    @Transactional
    public Long closeWorkShift() {
        if (orderDAO.checkActiveOrders()) {
            throw new InsufficientPermissionsException("You can not close the work shift, because there are not yet closed orders");
        }
        WorkShift workShift = workShiftDAO.getActiveWorkShift().get(0);
        workShift.setActive(false);
        workShift.setClosedDate(new Date());
        return workShift.getId();
    }

    @Override
    @Transactional
    public List<WorkShiftDTO> getShifts(int start, int count) {
        return workShiftDAO.getWorkShifts(start, count);
    }

    @Override
    @Transactional
    public Long countWorkShift() {
        return workShiftDAO.getCount();
    }

    @Override
    @Transactional
    public WorkShiftDTO getWorkShift(Long id) {
        WorkShift workShift = workShiftDAO.findById(id);
        if(workShift==null){
            throw new DAOException("Such work shift does not exist");
        }
        return new WorkShiftDTO(workShift);
    }
}
