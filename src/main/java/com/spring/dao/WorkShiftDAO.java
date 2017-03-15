package com.spring.dao;

import com.spring.dto.adminDTO.WorkShiftDTO;
import com.spring.model.WorkShift;

import java.util.List;

public interface WorkShiftDAO extends MyDAO<WorkShift, Long> {

    List<WorkShift> getActiveWorkShift();

    List<WorkShiftDTO> getWorkShifts(int start, int count);
}
