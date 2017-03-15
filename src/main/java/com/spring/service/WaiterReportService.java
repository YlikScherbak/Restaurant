package com.spring.service;

import com.spring.dto.adminDTO.WaiterReportDTO;

import java.util.List;

public interface WaiterReportService {

    List<WaiterReportDTO> getWaiterReports(Long id);
}
