package com.brycen.hrm.common.gerenateErrorCode;

public class ErrorCodeObj {
    private String errorType;
    private String errorApiService;
    private String errorPackage;
    private String errorApiPackage;
    private String errorMethod;
    private String errorReason;

    public ErrorCodeObj() {

    }

    public ErrorCodeObj(String errorType, String errorApiService, String errorPackage, String errorApiPackage, String errorMethod, String errorReason) {
        this.errorType = errorType;
        this.errorApiService = errorApiService;
        this.errorPackage = errorPackage;
        this.errorApiPackage = errorApiPackage;
        this.errorMethod = errorMethod;
        this.errorReason = errorReason;
    }

    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    public String getErrorApiService() {
        return errorApiService;
    }

    public void setErrorApiService(String errorApiService) {
        this.errorApiService = errorApiService;
    }

    public String getErrorPackage() {
        return errorPackage;
    }

    public void setErrorPackage(String errorPackage) {
        this.errorPackage = errorPackage;
    }

    public String getErrorApiPackage() {
        return errorApiPackage;
    }

    public void setErrorApiPackage(String errorApiPackage) {
        this.errorApiPackage = errorApiPackage;
    }

    public String getErrorMethod() {
        return errorMethod;
    }

    public void setErrorMethod(String errorMethod) {
        this.errorMethod = errorMethod;
    }

    public String getErrorReason() {
        return errorReason;
    }

    public void setErrorReason(String errorReason) {
        this.errorReason = errorReason;
    }

}
