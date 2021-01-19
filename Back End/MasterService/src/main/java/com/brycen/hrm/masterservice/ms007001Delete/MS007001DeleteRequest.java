package com.brycen.hrm.masterservice.ms007001Delete;

import java.util.List;

/**
 * [Description]: Contain list employeePositionID to delete<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public class MS007001DeleteRequest {
    /**
     * List employeePositionID
     */
    private List<Long> listDelete;

    public List<Long> getListDelete() {
        return listDelete;
    }

    public void setListDelete(List<Long> listDelete) {
        this.listDelete = listDelete;
    }
}
