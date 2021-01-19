package com.brycen.hrm.masterservice.ms001001Delete;

import java.util.List;

import com.brycen.hrm.common.base.BaseRequest;

/**
 * [Description]:Delete Request for User Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public class MS001001DeleteRequest extends BaseRequest {

    /**
     * List delete user
     */
    private List<Long> listDelete;

    public List<Long> getListDelete() {
        return listDelete;
    }

    public void setListDelete(List<Long> listDelete) {
        this.listDelete = listDelete;
    }

}
