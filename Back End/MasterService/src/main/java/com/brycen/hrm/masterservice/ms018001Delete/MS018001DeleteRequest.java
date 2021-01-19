package com.brycen.hrm.masterservice.ms018001Delete;

import java.util.List;

/**
 * [Description]: Delete request for Role Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public class MS018001DeleteRequest {

    /**
     * List delete
     */
    private List<Long> listDelete;

    public List<Long> getListDelete() {
        return listDelete;
    }

    public void setListDelete(List<Long> listDelete) {
        this.listDelete = listDelete;
    }

}
