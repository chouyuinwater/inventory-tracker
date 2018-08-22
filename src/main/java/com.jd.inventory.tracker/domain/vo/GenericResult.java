package com.jd.inventory.tracker.domain.vo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jd.inventory.tracker.domain.enums.ServiceStatusCodeEnum;

/**
 * Created by ThinkPad-PC on 2016/4/19.
 */
@JsonIgnoreProperties({"t"})
public class GenericResult<T> {

    public GenericResult(T data, ServiceStatusCodeEnum marketStatusCode) {
        this.data = data;
        this.code = marketStatusCode.getCode();
        this.message = marketStatusCode.getName();
    }

    public GenericResult(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public GenericResult(ServiceStatusCodeEnum marketStatusCode) {
        this.code = marketStatusCode.getCode();
        this.message = marketStatusCode.getName();
    }


    private int code;

    private String message;

    @JsonProperty("data")
    private T data;


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getT() {
        return this.data;
    }

    public void setT(T data) {
        this.data = data;
    }
}
