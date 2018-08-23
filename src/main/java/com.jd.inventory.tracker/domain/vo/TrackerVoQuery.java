package com.jd.inventory.tracker.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class TrackerVoQuery {
    private Long sourceSysid;
    private Long targetSysid;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime;
    private String eventno;
    private String sku;
    private Boolean diff;
    public Long getSourceSysid() {
        return sourceSysid;
    }

    public void setSourceSysid(Long sourceSysid) {
        this.sourceSysid = sourceSysid;
    }

    public Long getTargetSysid() {
        return targetSysid;
    }

    public void setTargetSysid(Long targetSysid) {
        this.targetSysid = targetSysid;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getEventno() {
        return eventno;
    }

    public void setEventno(String eventno) {
        this.eventno = eventno;
    }

    public Boolean getDiff() {
        return diff;
    }

    public void setDiff(Boolean diff) {
        this.diff = diff;
    }
}
