package com.brycen.hrm.eureka.zuul.GatewayService.config.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException.BadRequest;


@ControllerAdvice
public class CustomGlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globleExcpetionHandler(HttpServletRequest req ,Exception ex) {
        if( ex instanceof UnsupportedOperationException)
        {
            
        }
        if( ex instanceof BadRequest)
        {
            
        }
        
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
