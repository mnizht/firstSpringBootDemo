package com.example.demo.utils;

import java.io.Serializable;

/**
 * @author zhuhaitao
 * @date 2018/12/21 18:16
 */

public class ServiceResult implements Serializable {
    private static final long serialVersionUID = 1L;

    private String rtnInfo;
    private String errorMessage;
    private String rtnCode;
    private String rtnPage;
    private boolean isSuccess;

    public String getRtnInfo() {
        return rtnInfo;
    }

    public void setRtnInfo(String rtnInfo) {
        this.rtnInfo = rtnInfo;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getRtnCode() {
        return rtnCode;
    }

    public void setRtnCode(String rtnCode) {
        this.rtnCode = rtnCode;
    }

    public String getRtnPage() {
        return rtnPage;
    }

    public void setRtnPage(String rtnPage) {
        this.rtnPage = rtnPage;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }




}
