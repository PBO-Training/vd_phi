package com.brycen.hrm.common.gerenateErrorCode;

import java.util.List;

import org.springframework.stereotype.Service;

import com.brycen.hrm.common.checkValue.CheckValueService;
import com.brycen.hrm.constant.ErrorValue;

@Service
public class ErrorCodeImplements implements ErrorCodeInterface {
    
    /* (non-Javadoc)
     * @see com.brycen.hrm.common.gerenateErrorCode.GerenateErrorCodeService#generateErrorCode(com.brycen.hrm.common.gerenateErrorCode.ErrorCodeObj)
     */
    @Override
    public String generateErrorCode(ErrorCodeObj errorCode) {

        String errortype = compareError(ErrorValue.LIST_TYPE, errorCode.getErrorType(), ErrorValue.PATTERN_TYPE);
        String errorApiService = compareError(ErrorValue.LIST_SERVICE, errorCode.getErrorApiService(), ErrorValue.PATTERN_SERVICE);
        String errorPackage = compareError(ErrorValue.LIST_PACKAGE, errorCode.getErrorPackage(), ErrorValue.PATTERN_PACKAGE);
        String errorApiPackage = compareError(ErrorValue.LIST_API_PACKAGE, errorCode.getErrorApiPackage(), ErrorValue.PATTERN_API_PACKAGE);
        String errorMethod = compareError(ErrorValue.LIST_METHOD, errorCode.getErrorMethod(), ErrorValue.PATTERN_METHOD);
        String errorReason = compareError(ErrorValue.LIST_REASON, errorCode.getErrorReason(), ErrorValue.PATTERN_REASON);

        String error = errortype + errorApiService + errorPackage + errorApiPackage + errorMethod + errorReason;
        return error;
    }


    /* (non-Javadoc)
     * @see com.brycen.hrm.common.gerenateErrorCode.GerenateErrorCodeService#compareError(java.util.List, java.lang.String, int)
     */
    @Override
    public String compareError(List<String> listValue, String value, int length) {
        String result = "-";
        
        switch (length) {
        case 2:
            result = "--";
            break;
        case 3:
            result = "---";
            break;
        }
        
        if (CheckValueService.checkNull(value)) {
            return result;
        }
        
        for (String item : listValue) {
            if(value.equals(item)) {
                result = item;
                break;
            }
        }
        return result;
    }
    
}
