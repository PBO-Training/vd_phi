package com.brycen.hrm.logger;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.RollingFileAppender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brycen.hrm.constant.LogValue;
import com.google.gson.Gson;

/**
 * [Description]:CustomLogger [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public class ILogger implements LoggerService {
    
    @Autowired
    Gson gson;
    
    public Logger logger = Logger.getLogger(ILogger.class);
    public ILogger instance = null;
    public RollingFileAppender rollingAppenderObj = new RollingFileAppender();

    /**
     * [Description]: Get instance <br/>
     * [ Remarks ]:<br/>
     *
     * @return instance
     */
    public ILogger getInstance() {
        if (instance == null) {
            configureLogger();
            instance = new ILogger();
        } else {
            checkFolderNameAndFileNameLog();
        }
        return instance;
    }

    /* (non-Javadoc)
     * @see com.brycen.hrm.logger.LoggerService#checkFolderNameAndFileNameLog()
     */
    @Override
    public void checkFolderNameAndFileNameLog() {
        String path = System.getenv(LogValue.PATH_NAME_SYSTEM);
        File folder = new File(path + LogValue.LOG_FOLDER_NAME);
        if (!folder.exists()) {
            folder.mkdir();
        }

        Date dateNow = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(LogValue.FORMAT_DATE);
        File logFile = new File(folder + "\\" + simpleDateFormat.format(dateNow));
        if (!logFile.exists()) {
            logFile.mkdir();
            rollingAppenderObj.setFile(logFile + "\\" + simpleDateFormat.format(dateNow) + LogValue.FILE_EXTENSION);
        }

    }

    /* (non-Javadoc)
     * @see com.brycen.hrm.logger.LoggerService#configureLogger()
     */
    @Override
    public void configureLogger() {
        PatternLayout layout = new PatternLayout();
        layout.setConversionPattern(LogValue.PATTERN_FORMAT);

        String path = System.getenv(LogValue.PATH_NAME_SYSTEM);
        File folder = new File(path + LogValue.LOG_FOLDER_NAME);
        if (!folder.exists()) {
            folder.mkdir();
        }

        Date dateNow = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(LogValue.FORMAT_DATE);
        File folderChild = new File(folder + "\\" + simpleDateFormat.format(dateNow));
        if (!folderChild.exists()) {
            folderChild.mkdir();
        }

        // Create Daily Rolling Log File Appender
        rollingAppenderObj.setFile(folderChild + "\\" + simpleDateFormat.format(dateNow) + LogValue.FILE_EXTENSION);
        rollingAppenderObj.setMaxFileSize(LogValue.LOG_MAX_FILE_SIZE);
        rollingAppenderObj.setLayout(layout);
        rollingAppenderObj.activateOptions();

        logger.setLevel(Level.DEBUG);
        logger.addAppender(rollingAppenderObj);
    }

    /* (non-Javadoc)
     * @see com.brycen.hrm.logger.LoggerService#write(java.lang.String, java.lang.String, java.lang.Object, java.lang.Object, java.lang.Object)
     */
    @Override
    public void write(String logLevel, String path, Object request, Object response, Object message) {
        if (logLevel == null || logLevel.length() == 0 || logLevel.contains(LogValue.LOG_LEVEL_VALUE)) {
            return;
        }
        getInstance();

        String logApi = generateAPI(path);
        String logRequest = generateRequest(request);
        String logResponse = generateResponse(response);
        String logMessage = generateMessage(message);

        String log = generateFormatLog(logApi, logRequest, logResponse, logMessage);
        if ("I".equals(logLevel)) {
            logger.info(log);
        } else if ("E".equals(logLevel)) {
            logger.error(log);
        } else if ("W".equals(logLevel)) {
            logger.warn(log);
        } else if ("D".equals(logLevel)) {
            logger.debug(log);
        }

    }


    /* (non-Javadoc)
     * @see com.brycen.hrm.logger.LoggerService#generateAPI(java.lang.String)
     */
    @Override
    public String generateAPI(String path) {
        String logApi = "[API]-" + path;
        return logApi;
    }


    /* (non-Javadoc)
     * @see com.brycen.hrm.logger.LoggerService#generateRequest(java.lang.Object)
     */
    @Override
    public String generateRequest(Object request) {
        String logRequest = "[Request]-";
        if(request != null ) {
          logRequest += gson.toJson(request);
        }
        return logRequest;
    }

    /* (non-Javadoc)
     * @see com.brycen.hrm.logger.LoggerService#generateResponse(java.lang.Object)
     */
    @Override
    public String generateResponse(Object response) {
        String logResponse = "[Response]-";
        if(response != null ) {
            logResponse += gson.toJson(response);
        }
        return logResponse;
    }

    /* (non-Javadoc)
     * @see com.brycen.hrm.logger.LoggerService#generateMessage(java.lang.Object)
     */
    @Override
    public String generateMessage(Object message) {
        String logMessage = "[Message]-";
        if(message instanceof Object) {
            logMessage += gson.toJson(message);
        }else {
            logMessage += message;
        }
        return logMessage;
    }

    /* (non-Javadoc)
     * @see com.brycen.hrm.logger.LoggerService#generateFormatLog(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public String generateFormatLog(String logMethod, String logRequest, String logResponse, String logMessage) {
        String log =  logMessage + LogValue.SPLIT_LOG 
                    + logMethod + LogValue.SPLIT_LOG 
                    + logRequest + LogValue.SPLIT_LOG
                    + logResponse + LogValue.SPLIT_LOG;
        return log;
    }

    
    
}
