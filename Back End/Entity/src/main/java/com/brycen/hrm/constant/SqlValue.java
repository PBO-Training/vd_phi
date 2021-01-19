package com.brycen.hrm.constant;

/**
 * [Description]:Constant Value of sql column
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public class SqlValue {
    /**
     * Length default if type of entity is string
     */
    public static int LENGTH_STRING = 255;
    
    /**
     * Length of name string
     */
    public static int LENGTH_NAME = 255;
    
    /**
     * Length of code string
     */
    public static int LENGTH_CODE = 40;
    
    /**
     * Length type of entity is varchar(2000)
     */
    public static int LENGTH_DESCRIPTION = 2000;

    
    /**
     * Length of year field
     */
    public static int LENGTH_OF_YEAR = 4;
    
    /**
     * Max length phone number
     */
    public static int MAX_LENGTH_PHONE = 15;
    
    /**
     * Max length input text
     */
    public static int MAX_LENGTH_INPUT = 250;

    /**
     * Max length Project Leader/Team Manager
     */
    public static int MAX_LENGTH_APPROVER1 = 1;

    /**
     * Max length Team GA
     */
    public static int MAX_LENGTH_APPROVER3 = 255;
    
    /**
     * Max length ID
     */
    public static int MAX_LENGTH_IDENTITY_CARD = 12;
    
    /**
     * Min length ID
     */
    public static int MIN_LENGTH_IDENTITY_CARD = 9;
}
