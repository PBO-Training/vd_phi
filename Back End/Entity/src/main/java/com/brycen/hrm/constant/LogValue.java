package com.brycen.hrm.constant;

/**
 * [Description]:Constant Value of ILogger
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public class LogValue {
    /**
     * Log storage directory
     */
    public static String PATH_NAME_SYSTEM = "APPDATA";
    
    
    /**
     * Log directory name 
     */
    public static String LOG_FOLDER_NAME = "\\logs";
    
    
    /**
     * Log format
     */
    public static String PATTERN_FORMAT = "%d{yyyy-MM-dd HH:mm:ss}  %p  %m\n";
    
    /**
     * Date format
     */
    public static String FORMAT_DATE = "dd-MM-yyyy";
    
    /**
     * Log file extension
     */
    public static String FILE_EXTENSION = ".log";
    
    
    /**
     * Maximum size of the log file
     */
    public static String LOG_MAX_FILE_SIZE = "10MB";
    
    
    /**
     * log level character string
     */
    public static String LOG_LEVEL_VALUE = "IEWD";
    
    /**
     * Message begin api
     */
    public static String BEGIN_API ="BEGIN API";
    
    /**
     * Message end api
     */
    public static String END_API ="END API";
    
    /**
     * character split string log
     */
    public static String SPLIT_LOG = "\t";
}
