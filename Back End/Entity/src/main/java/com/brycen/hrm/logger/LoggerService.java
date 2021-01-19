package com.brycen.hrm.logger;

/**
 * [Description]:Interface LoggerService <br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public interface LoggerService {
    /**
     * [Description]:Change the directory name and file name as the new day passes <br/>
     * [ Remarks ]:<br/>
     *
     */
    void checkFolderNameAndFileNameLog();

    /**
     * [Description]:Configuration for Logger <br/>
     * [ Remarks ]:<br/>
     *
     */
    void configureLogger();

    /**
     * [Description]:Log mode <br/>
     * [ Remarks ]:<br/>
     *
     * @param logLevel
     * @param path
     * @param request
     * @param response
     * @param message
     */
    void write(String logLevel, String path, Object request, Object response, Object message);

    /**
     * [Description]: Generate path to string <br/>
     * [ Remarks ]:<br/>
     *
     * @param path
     * @return string format log api
     */
    String generateAPI(String path);

    /**
     * [Description]:Generate request to string <br/>
     * [ Remarks ]:<br/>
     *
     * @param request
     * @return string format log request
     */
    String generateRequest(Object request);

    /**
     * [Description]: Generate response to string<br/>
     * [ Remarks ]:<br/>
     *
     * @param response
     * @return string format log response
     */
    String generateResponse(Object response);

    /**
     * [Description]:Generate message to string<br/>
     * [ Remarks ]:<br/>
     *
     * @param message
     * @return string format log message
     */
    String generateMessage(Object message);

    /**
     * [Description]: Generate log to format log <br/>
     * [ Remarks ]:<br/>
     *
     * @param logApi
     * @param logRequest
     * @param logResponse
     * @param logMessage
     * @return string format log
     */
    String generateFormatLog(String logApi, String logRequest, String logResponse, String logMessage);
}
