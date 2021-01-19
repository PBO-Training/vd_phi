package com.brycen.hrm.common.base;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;

/**
 * [Description]: Create header to request<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public class BaseHeader {
    /**
     * [Description]:Parse jwt from request<br/>
     * [ Remarks ]:<br/>
     *
     * @param request
     * @return
     */
    private static String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");

        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7, headerAuth.length());
        }

        return null;
    }

    /**
     * [Description]:Return headers contain infomation to add to request<br/>
     * [ Remarks ]:<br/>
     *
     * @param servletRequest
     * @return
     */
    public static HttpHeaders createHeader(HttpServletRequest servletRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + parseJwt(servletRequest));
        headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        return headers;
    }
    
    public static HttpHeaders modifyHeader(HttpServletRequest servletRequest, String jwtToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + jwtToken);
        headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        return headers;
    }

}
