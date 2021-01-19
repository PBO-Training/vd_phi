package com.brycen.hrm.masterservice.ms001001Search;

import com.brycen.hrm.common.base.BaseRequest;

/**
 * [Description]: Search Request for User Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public class MS001001SearchRequest extends BaseRequest {

    /**
     * Search with name
     */
    String searchName;

    /**
     * Search with role
     */
    Long searchRole;

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    public String getSearchName() {
        return this.searchName;
    }

    public void setSearchRole(Long searchRole) {
        this.searchRole = searchRole;
    }

    public Long getSearchRole() {
        return this.searchRole;
    }

}
