package com.example.springdemo.utils;

import lombok.Getter;
import lombok.Setter;

public class BaseResult {

    private static final String SUCCESS_CODE = "000000";
    private static final String ERROR_CODE = "000500";

    private static final String SUCCESS_DESC = "SUCCESS";
    private static final String ERROR_DESC = "ERROR";

    private String bizCode;
    private Object bizData;
    private String bizDesc;

    public String getBizCode() {
        return bizCode;
    }
    public void setBizCode(String bizCode) {
        this.bizCode = bizCode;
    }
    public Object getBizData() {
        return bizData;
    }
    public void setBizData(Object bizData) {
        this.bizData = bizData;
    }
    public String getBizDesc() {
        return bizDesc;
    }
    public void setBizDesc(String bizDesc) {
        this.bizDesc = bizDesc;
    }
    public void  setSuccess(Object bizData){
        setBizData(bizData);
        setBizCode(SUCCESS_CODE);
        setBizDesc(SUCCESS_DESC);
    }
    public void  setError(Object bizData){
        setBizData(bizData);
        setBizCode(ERROR_CODE);
        setBizDesc(ERROR_DESC);
    }
}
