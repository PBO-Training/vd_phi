package com.brycen.hrm.config.exception;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.PropertyAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException.BadRequest;

import com.brycen.hrm.common.base.BaseResponse;
import com.brycen.hrm.common.base.ErrorResponse;
import com.brycen.hrm.common.gerenateErrorCode.ErrorCodeImplements;
import com.brycen.hrm.common.gerenateErrorCode.ErrorCodeObj;
import com.brycen.hrm.constant.ErrorValue;
import com.brycen.hrm.logger.LogLevel;
import com.brycen.hrm.logger.LoggerService;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

@ControllerAdvice
public class CustomGlobalExceptionHandler {

    @Autowired
    private LoggerService logger;

    /**
     * [Description]:Handle Excpetion before Controller<br/>
     * [ Remarks ]:<br/>
     *
     * @param req
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globleExcpetionHandler(HttpServletRequest req, Exception ex) throws Exception {
        logger.write(LogLevel.ERROR, getEndPointAPI(req.getRequestURL().toString()), null, null, ex.getMessage());
        // @formatter:off
        ErrorCodeObj errorCode = new ErrorCodeObj(
                ErrorValue.TYPE_INPUT_VALUE_ERROR, 
                ErrorValue.SERVICE_API_MASTER, 
                ErrorValue.PACKAGE_DEPARTMENT,
                ErrorValue.API_CREATE_DETAIL_DEPARTMENT, 
                ErrorValue.METHOD_POST, 
                ErrorValue.REASON_CHARACTER_LENGHT_ILLEGAL);
        // @formatter:on
        ErrorCodeImplements error = new ErrorCodeImplements();
        String code = error.generateErrorCode(errorCode);
        String itemName = null;
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        if (ex instanceof UnsupportedOperationException) {
            System.out.print("UnsupportedOperationException");
        }
        else if (ex instanceof BadRequest) {
            System.out.println("BadRequest");
            System.out.println(ex);
            httpStatus = HttpStatus.BAD_REQUEST;
        }        
        else if (ex.getCause() instanceof InvalidFormatException) {
            InvalidFormatException exception = (InvalidFormatException) ex.getCause();
            itemName = exception.getPath().get(0).getFieldName();
        }        
        else if (ex instanceof BadCredentialsException) {
            itemName = "password";
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        else if (ex instanceof JpaSystemException) {
            PropertyAccessException exception = (PropertyAccessException) ex.getCause();
            itemName = exception.getPropertyName();
        }
        BaseResponse baseReponse = new BaseResponse();
        baseReponse.setError(new ErrorResponse(code, itemName));
        return new ResponseEntity<>(baseReponse, httpStatus);
    }
    
    private String getEndPointAPI(String requestUrl)
    {
        String endUrl = "";
        if(requestUrl != null && requestUrl != "")
        {
            endUrl = requestUrl.substring(requestUrl.lastIndexOf("/") + 1);
        }
        return endUrl;
    }
}
