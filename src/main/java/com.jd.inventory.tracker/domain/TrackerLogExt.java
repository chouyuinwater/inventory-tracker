package com.jd.inventory.tracker.domain;

import java.util.Date;

/**
 * @author tdd
 */
public class TrackerLogExt {
    private Long id;
    private Long sysid;
    private Long templateid;
    private String eventno;
    private String sku;
    private String amount;
    private Integer eventstatus;    //当前状态
    private Date createTime;        //send time
    private Date updateTime;
    private int yn; //0:unhandled, 1:hundling, 2:hundled
    public TrackerLogExt(TrackerLog log){
        this.sysid = log.getSysid();
        this.templateid = log.getTemplateid();
        this.eventno = log.getEventno();
        this.sku = log.getSku();
        this.amount = log.getAmount();
        this.eventstatus = log.getEventstatus();
        this.createTime = log.getCreateTime();
        this.updateTime = log.getUpdateTime();
    }
    public TrackerLogExt(){

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

    public int getYn() {
        return yn;
    }

    public void setYn(int status) {
        this.yn = status;
    }

    public String getStepKey(){
        return this.sysid +"_"+ this.templateid ;
    }
}
