package com.brycen.hrm.masterservice.ms004001Delete;

import java.util.List;

/**
 * [Description]: Delete Request for Skill Level Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public class MS004001DeleteRequest {

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
