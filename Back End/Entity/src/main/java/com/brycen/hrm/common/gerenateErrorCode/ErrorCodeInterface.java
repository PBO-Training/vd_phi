package com.brycen.hrm.common.gerenateErrorCode;

import java.util.List;

public interface ErrorCodeInterface {   
    
    /**
     * [Description]:Compare get value error code <br/>
     * [ Remarks ]:<br/>
     *
     * @param listValue
     * @param value
     * @param length
     * @return
     */
    String compareError(List<String> listValue, String value, int length);
    
    /**
     * [Description]:Generate Error Code<br/>
     * [ Remarks ]:<br/>
     *
     * @param errorCode
     * @return errorCode (string)
     */
    String generateErrorCode(ErrorCodeObj errorCode);
}
