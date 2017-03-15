package com.spring.service;

import com.spring.dto.adminDTO.GeneralReportDTO;

public interface GeneralReportService {

    Long createGeneralReport(Long id);

    GeneralReportDTO getGenReport(Long id);

}
