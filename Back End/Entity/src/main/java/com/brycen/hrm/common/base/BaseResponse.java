package com.brycen.hrm.common.base;

/**
 * [Description]:Base Response [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public class BaseResponse {
    /**
     * Content for Response
     */
    private Object content = null;

    /**
     * Error for Response
     */
    private ErrorResponse error = null;

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public ErrorResponse getError() {
        return error;
    }

    public void setError(ErrorResponse error) {
        this.error = error;
    }

}
