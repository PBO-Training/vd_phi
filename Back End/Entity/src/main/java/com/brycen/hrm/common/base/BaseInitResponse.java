package com.brycen.hrm.common.base;

public class BaseInitResponse {
    private Long keyResponse;
    private String valueResponse;

    public Long getKeyResponse() {
        return keyResponse;
    }

    public void setKeyResponse(Long keyResponse) {
        this.keyResponse = keyResponse;
    }

    public String getValueResponse() {
        return valueResponse;
    }

    public void setValueResponse(String valueResponse) {
        this.valueResponse = valueResponse;
    }

    public BaseInitResponse() {
    }

    public BaseInitResponse(Long keyResponse, String valueResponse) {
        this.keyResponse = keyResponse;
        this.valueResponse = valueResponse;
    }

}
