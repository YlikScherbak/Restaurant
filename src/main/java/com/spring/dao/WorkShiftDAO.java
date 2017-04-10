package com.spring.dao;

import com.spring.dto.adminDTO.WorkShiftDTO;
import com.spring.model.WorkShift;

import java.util.List;
import java.util.Optional;

public interface WorkShiftDAO extends MyDAO<WorkShift, Long> {

    Optional<WorkShift> getActiveWorkShift();

    List<WorkShiftDTO> getWorkShifts(int start, int count);
}
