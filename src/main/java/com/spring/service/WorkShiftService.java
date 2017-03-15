package com.spring.service;

import com.spring.dto.adminDTO.WorkShiftDTO;
import com.spring.model.WorkShift;

import java.util.List;

public interface WorkShiftService {

    WorkShift getActiveWorkShift();

    void openNewWorkShift();

    Long closeWorkShift();

    List<WorkShiftDTO> getShifts(int start, int count);

    Long countWorkShift();

    WorkShiftDTO getWorkShift(Long id);
}
