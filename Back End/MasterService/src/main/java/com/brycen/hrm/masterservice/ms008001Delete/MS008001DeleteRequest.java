package com.brycen.hrm.masterservice.ms008001Delete;

import java.util.List;

/**
 * [Description]: Delete Request for Employee Status Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public class MS008001DeleteRequest {

    private List<Long> listDelete;

    public MS008001DeleteRequest() {
        super();
    }

    public MS008001DeleteRequest(List<Long> listDelete) {
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
