package com.jd.inventory.tracker.domain;

import java.util.Date;
import java.util.List;

public class Tracker {
    private Long id;
    private Long sysid;
    private Long templateid;
    private String eventno;
    private String sku;
    private String amount;
    private Date currentDate;
    private Integer currentStep;
    private Integer endStatus;

    //查询使用
    private List<String> eventnoList;
    private Date startTime;
    private Date endTime;
    private Boolean diff;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSysid() {
        return sysid;
    }

    public void setSysid(Long sysid) {
        this.sysid = sysid;
    }

    public Long getTemplateid() {
        return templateid;
    }

    public void setTemplateid(Long templateid) {
        this.templateid = templateid;
    }

    public String getEventno() {
        return eventno;
    }

    public void setEventno(String eventno) {
        this.eventno = eventno;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Date getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    public Integer getCurrentStep() {
        return currentStep;
    }

    public void setCurrentStep(Integer currentStep) {
        this.currentStep = currentStep;
    }

    public Integer getEndStatus() {
        return endStatus;
    }

    public void setEndStatus(Integer endStatus) {
        this.endStatus = endStatus;
    }

    public List<String> getEventnoList() {
        return eventnoList;
    }

    public void setEventnoList(List<String> eventnoList) {
        this.eventnoList = eventnoList;
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

    public Boolean getDiff() {
        return diff;
    }

    public void setDiff(Boolean diff) {
        this.diff = diff;
    }
}
