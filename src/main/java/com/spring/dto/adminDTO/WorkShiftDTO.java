package com.spring.dto.adminDTO;

import com.spring.model.WorkShift;

import java.util.Date;

public class WorkShiftDTO {

    private Long id;

    private Date creationDate;

    private Date closedDate;

    private Long generalReportId;

    public WorkShiftDTO() {
    }

    public WorkShiftDTO(Long id, Date creationDate, Date closedDate, Long generalReportId) {
        this.id = id;
        this.creationDate = creationDate;
        this.closedDate = closedDate;
        this.generalReportId = generalReportId;
    }

    public WorkShiftDTO(WorkShift workShift) {
        this.id = workShift.getId();
        this.creationDate = workShift.getCreationDate();
        this.closedDate = workShift.getClosedDate();
        this.generalReportId = workShift.getGeneralReports().getId();
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getClosedDate() {
        return closedDate;
    }

    public void setClosedDate(Date closedDate) {
        this.closedDate = closedDate;
    }

    public Long getGeneralReportId() {
        return generalReportId;
    }

    public void setGeneralReportId(Long generalReportId) {
        this.generalReportId = generalReportId;
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
