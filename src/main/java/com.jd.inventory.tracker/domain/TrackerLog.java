package com.jd.inventory.tracker.domain;

import java.util.Date;

/**
 * @author tdd
 */
public class TrackerLog {
    private Long id;
    private Long sysid;
    private Long templateid;
    private String eventno;
    private String sku;
    private String amount;
    private Integer eventstatus;    //当前状态
    private Date createTime;        //send time
    private Date updateTime;
    public TrackerLog(TrackerLogExt ext){
        this.sysid = ext.getSysid();
        this.templateid = ext.getTemplateid();
        this.eventno = ext.getEventno();
        this.sku = ext.getSku();
        this.amount = ext.getAmount();
        this.eventstatus = ext.getEventstatus();
        this.createTime = ext.getCreateTime();
        this.updateTime = ext.getUpdateTime();
    }

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

    public Integer getEventstatus() {
        return eventstatus;
    }

    public void setEventstatus(Integer eventstatus) {
        this.eventstatus = eventstatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getStepKey(){
        return this.sysid +"_"+ this.templateid ;
    }
}
