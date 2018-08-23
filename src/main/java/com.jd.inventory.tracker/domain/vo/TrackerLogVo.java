package com.jd.inventory.tracker.domain.vo;

import java.util.Date;

/**
 * 原始流水
 * @author tdd
 */
public class TrackerLogVo {
    private Long sysid;
    private Long templateid;
    private String eventno;
    private String sku;
    private String amount;
    private Integer eventstatus;    //当前状态
    private String createTime;        //send time

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

    public Integer getEventstatus() {
        return eventstatus;
    }

    public void setEventstatus(Integer eventstatus) {
        this.eventstatus = eventstatus;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
