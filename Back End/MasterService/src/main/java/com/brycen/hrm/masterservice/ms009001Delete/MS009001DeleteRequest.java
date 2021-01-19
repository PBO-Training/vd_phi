package com.brycen.hrm.masterservice.ms009001Delete;

import java.util.List;

/**
 * [Description]: Contain list customerID to delete<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public class MS009001DeleteRequest {
    /**
     * List customerID
     */
    private List<Long> listDelete;

    public MS009001DeleteRequest() {
        super();
    }

    public MS009001DeleteRequest(List<Long> listDelete) {
        super();
        this.listDelete = listDelete;
    }

    public List<Long> getListDelete() {
        return listDelete;
    }

    public void setListDelete(List<Long> listDelete) {
        this.listDelete = listDelete;
    }
}
