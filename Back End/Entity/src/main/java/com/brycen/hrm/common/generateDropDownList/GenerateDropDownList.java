package com.brycen.hrm.common.generateDropDownList;

import java.util.List;

import com.brycen.hrm.common.base.BaseInitResponse;
import com.brycen.hrm.constant.TypeInitValue;

/**
 * [Description]:Class is used to generate 2 options All, None<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public class GenerateDropDownList {
    /**
     * [Description]:Add new 2 option2 to list: All, Other<br/>
     * [ Remarks ]:<br/>
     *
     * @param list
     * @return
     */
    public static List<BaseInitResponse> generateDefaultValue(List<BaseInitResponse> list) {
        list.add(new BaseInitResponse(0L, TypeInitValue.OTHER));
        list.add(0, new BaseInitResponse(-1L, TypeInitValue.ALL));
        return list;
    }
}
