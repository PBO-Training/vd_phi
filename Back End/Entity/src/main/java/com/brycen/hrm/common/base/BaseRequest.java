package com.brycen.hrm.common.base;

import com.brycen.hrm.constant.TypeInitValue;

public class BaseRequest {
    private int pageNum = 0;
    private int pageSize = 30;
    private String type = TypeInitValue.DEFAULT_TYPE;

    public BaseRequest() {
        super();
    }

    public BaseRequest(int pageNum, int pageSize, String type) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.type = type;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
