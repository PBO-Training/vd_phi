package com.brycen.hrm.constant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * [Description]:Constant Value Of Error <br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public class ErrorValue {
    /*=================1 CHARACTER====================*/
    /*=================TYPE===========================*/
    
    /**
     * List Type
     */
    public static final List<String> LIST_TYPE = new ArrayList<String>(Arrays.asList("A", "B"));
    
    public static final int PATTERN_TYPE = 1;
    
    /**
     * Internal error of server
     */
    public static final String TYPE_INTERNAL_ERROR_OF_SERVER = "A";
    
    /**
     * Input value error
     */
    public static final String TYPE_INPUT_VALUE_ERROR = "B";  
    
    /*=================2 CHARACTER====================*/
    /*=================SERVICE========================*/
    
    public static final List<String> LIST_SERVICE = new ArrayList<String>(Arrays.asList("01", "02", "03", "99"));
    
    public static final int PATTERN_SERVICE = 2;
    
    /**
     * Service Employee
     */
    public static final String SERVICE_API_EMPLOYEE = "01";
    
    /**
     * Service Project
     */
    public static final String SERVICE_API_PROJECT = "02";
    
    /**
     * Service Vacation 
     */
    public static final String SERVICE_API_VACATION = "03";
    
    /**
     * Service Master 
     */
    public static final String SERVICE_API_MASTER = "99";
    
    
    /*=================3 CHARACTER====================*/
    /*=================PACKAGE========================*/
    
    public static final List<String> LIST_PACKAGE = 
            new ArrayList<String>(Arrays.asList("001", "002", "003", "004", "005", "006", "007", "008", "009", "010", "011", "012", "013", "017", "018", "019", "020"));
    
    public static final int PATTERN_PACKAGE = 3;
    
    /**
     * Package Login in Login Service
     */
    public static final String PACKAGE_LOGIN = "000";
    
    /**
     * Package Employee in Employee Service
     */
    public static final String PACKAGE_EMPLOYEE = "001";
    
    /**
     * Package Project in Project Service
     */
    public static final String PACKAGE_PROJECT = "001";
    
    /**
     * Package Issue in Project Service
     */
    public static final String PACKAGE_ISSUE = "002";
    
    /**
     * Package Vacation in Vacation Service
     */
    public static final String PACKAGE_VACTION = "001";
    
    /**
     * Package User in Master Service
     */
    public static final String PACKAGE_USER = "001";
    
    /**
     * Package Department in Master Service
     */
    public static final String PACKAGE_DEPARTMENT = "002";

    /**
     * Package Skill in Master Service
     */
    public static final String PACKAGE_SKILL = "003";
    
    /**
     * Package Skill Level in Master Service
     */
    public static final String PACKAGE_SKILL_LEVEL = "004";
    
    /**
     * Package Language in Master Service
     */
    public static final String PACKAGE_LANGUAGE = "005";

    /**
     * Package Level Language in Master Service
     */
    public static final String PACKAGE_LEVEL_LANGUAGE = "006";
    
    /**
     * Package Position in Master Service
     */
    public static final String PACKAGE_POSITION = "007";
    
    /**
     * Package Status Employee in Master Service
     */
    public static final String PACKAGE_STATUS_EMPLOYEE = "008";

    /**
     * Package Customer in Master Service
     */
    public static final String PACKAGE_CUSTOMER = "009";
    
    /**
     * Package Position Project in Master Service
     */
    public static final String PACKAGE_POSITION_PROJECT = "010";
    
    /**
     * Package Level Project in Master Service
     */
    public static final String PACKAGE_LEVEL_PROJECT = "011";
    
    /**
     * Package Type Vacation in Master Service
     */
    public static final String PACKAGE_TYPE_VACATION = "012";
    
    /**
     * Package Holiday in Master Service
     */
    public static final String PACKAGE_HOLIDAY = "013";
    
    
    /**
     * Package Permission in Master Service
     */
    public static final String PACKAGE_ROLE_SCREEN = "017";
    
    /**
     * Package Role in Master Service
     */
    public static final String PACKAGE_ROLE = "018";
    
    /**
     * Package Scope Work in Master Service
     */
    public static final String PACKAGE_SCOPE_WORK = "019";
    
    /**
     * Package Degree in Master Service
     */
    public static final String PACKAGE_DEGREE = "020";
    
    /*=================3 CHARACTER========================*/
    /*=================API IN PACKAGE=====================*/

    public static final List<String> LIST_API_PACKAGE = 
            new ArrayList<String>(Arrays.asList("000", "001", "002", "003", "004", "005", "006", "007", "008", "009", "010", "011", "012", "013", "014", "015", "016", "017", "018", "019", "020", "021", "022"));
    
    public static final int PATTERN_API_PACKAGE = 3;
    
    /**
     * API Init list Employee of Package Employee in EmployeeService
     */
    public static final String API_LOGIN = "000";
    
    /**
     * API Init list Employee of Package Employee in EmployeeService
     */
    public static final String API_INIT_LIST_EMPLOYEE = "001";
    
    /**
     * API Search list Employee of Package Employee in EmployeeService
     */
    public static final String API_SEARCH_LIST_EMPLOYEE = "002";
    
    /**
     * API Delete list Employee of Package Employee in EmployeeService
     */
    public static final String API_DELETE_LIST_EMPLOYEE = "003";
    
    /**
     * API Init Detail Employee of Package Employee in EmployeeService
     */
    public static final String API_INIT_DETAIL_EMPLOYEE = "004";
    
    /**
     * API Create Detail Employee of Package Employee in EmployeeService
     */
    public static final String API_CREATE_DETAIL_EMPLOYEE = "005";

    /**
     * API Update Detail Employee of Package Employee in EmployeeService
     */
    public static final String API_UPDATE_DETAIL_EMPLOYEE = "006";

    /**
     * API Init List Project of Package Project in ProjectService
     */
    public static final String API_INIT_LIST_PROJECT = "001";
    
    /**
     * API Search List Project of Package Project in ProjectService
     */
    public static final String API_SEARCH_LIST_PROJECT = "002";
    
    /**
     * API Delete List Project of Package Project in ProjectService
     */
    public static final String API_DELETE_LIST_PROJECT = "003";
    
    /**
     * API Init Detail Project of Package Project in ProjectService
     */
    public static final String API_INIT_DETAIL_PROJECT = "004";
    
    /**
     * API Create Detail Project of Package Project in ProjectService
     */
    public static final String API_CREATE_DETAIL_PROJECT = "005";
    
    /**
     * API Update Detail Project of Package Project in ProjectService
     */
    public static final String API_UPDATE_DETAIL_PROJECT = "006";
    
    /**
     * API Create Detail Project of Package Project in ProjectService
     */
    public static final String API_GET_DETAIL_PROJECT = "007";
    
    /**
     * API Init List Issue of Package Issue in ProjectService
     */
    public static final String API_INIT_LIST_ISSUE = "001";
    
    /**
     * API Search List Issue of Package Issue in ProjectService
     */
    public static final String API_SEARCH_LIST_ISSUE = "002";
    
    /**
     * API Delete List Issue of Package Issue in ProjectService
     */
    public static final String API_DELETE_LIST_ISSUE = "003";
    
    /**
     * API Init Detail Issue of Package Issue in ProjectService
     */
    public static final String API_INIT_DETAIL_ISSUE = "004";
    
    /**
     * API Create Detail Issue of Package Issue in ProjectService
     */
    public static final String API_CREATE_DETAIL_ISSUE = "005";
    
    /**
     * API Update Detail Issue of Package Issue in ProjectService
     */
    public static final String API_UPDATE_DETAIL_ISSUE = "006";
    
    /**
     * API Get Detail Issue of Package Issue in ProjectService
     */
    public static final String API_GET_DETAIL_ISSUE = "008";
    
    /**
     * API Create Detail Issue of Package Issue in ProjectService
     */
    public static final String API_CREATE_DOCUMENT = "005";
    
    /**
     * API Get List Member of Package Member in ProjectService
     */
    public static final String API_GET_LIST_MEMBER = "017";
    
    /**
     * API Remove Members of Package Member in ProjectService
     */
    public static final String API_REMOVE_MEMBERS = "018";
    
    /**
     * API Update Info Member of Package Member in ProjectService
     */
    public static final String API_UPDATE_INFO_MEMBER = "019";
    
    /**
     * API Add Members of Package Member in ProjectService
     */
    public static final String API_ADD_MEMBERS = "020";
    /**
     * API Save History Member of Package Member in ProjectService
     */
    public static final String API_SAVE_HISTORY_MEMBER = "021";
    
    /**
     * API Get History Member of Package Member in ProjectService
     */
    public static final String API_GET_HISTORY_MEMBER = "022";
    
    /**
     * API Init List Vacation of Package Vacation in VacationService
     */
    public static final String API_INIT_LIST_VACATION = "001";
    
    /**
     * API Search List Vacation of Package Vacation in VacationService
     */
    public static final String API_SEARCH_LIST_VACATION = "002";
    
    /**
     * API Delete List Vacation of Package Vacation in VacationService
     */
    public static final String API_DELETE_LIST_VACATION = "003";
    
    /**
     * API Init Detail Vacation of Package Vacation in VacationService
     */
    public static final String API_INIT_DETAIL_VACATION = "004";
    
    /**
     * API Create Detail Vacation of Package Vacation in VacationService
     */
    public static final String API_CREATE_DETAIL_VACATION = "005";
    
    /**
     * API Update Detail Vacation of Package Vacation in VacationService
     */
    public static final String API_UPDATE_DETAIL_VACATION = "006";
    
    /**
     * API Init List Shift Work of Package Vacation in VacationService
     */
    public static final String API_INIT_LIST_SHIFT_WORK = "001";
    
    /**
     * API Search List Shift Work of Package Vacation in VacationService
     */
    public static final String API_SEARCH_LIST_SHIFT_WORK = "002";
    
    /**
     * API Create Detail Shift Work of Package Vacation in VacationService
     */
    public static final String API_CREATE_DETAIL_SHIFT_WORK = "005";
    
    /**
     * API Update Detail Shift Work of Package Vacation in VacationService
     */
    public static final String API_UPDATE_DETAIL_SHIFT_WORK = "006";
    
    /**
     * API Init List User of Package User in MasterService
     */
    public static final String API_INIT_LIST_USER = "001";
    
    /**
     * API Search List User of Package User in MasterService
     */
    public static final String API_SEARCH_LIST_USER = "002";
    
    /**
     * API Delete List User of Package User in MasterService
     */
    public static final String API_DELETE_LIST_USER = "003";
    
    /**
     * API Init Detail User of Package User in MasterService
     */
    public static final String API_INIT_DETAIL_USER = "004";
    
    /**
     * API Create Detail User of Package User in MasterService
     */
    public static final String API_CREATE_DETAIL_USER = "005";
    
    /**
     * API Update Detail User of Package User in MasterService
     */
    public static final String API_UPDATE_DETAIL_USER = "006";
    
    /**
     * API Init List Department of Package Department in MasterService
     */
    public static final String API_INIT_LIST_DEPARTMENT = "001";
    
    /**
     * API Search List Department of Package Department in MasterService
     */
    public static final String API_SEARCH_LIST_DEPARTMENT = "002";
    
    /**
     * API Delete List Department of Package Department in MasterService
     */
    public static final String API_DELETE_LIST_DEPARTMENT = "003";
    
    /**
     * API Init Detail Department of Package Department in MasterService
     */
    public static final String API_INIT_DETAIL_DEPARTMENT = "004";
    
    /**
     * API Create Detail Department of Package Department in MasterService
     */
    public static final String API_CREATE_DETAIL_DEPARTMENT = "005";
    
    /**
     * API Update Detail Department of Package Department in MasterService
     */
    public static final String API_UPDATE_DETAIL_DEPARTMENT = "006";
    
    /**
     * API Init List Skill of Package Skill in MasterService
     */
    public static final String API_INIT_LIST_SKILL = "001";
    
    /**
     * API Search List Skill of Package Skill in MasterService
     */
    public static final String API_SEARCH_LIST_SKILL = "002";
    
    /**
     * API Delete List Skill of Package Skill in MasterService
     */
    public static final String API_DELETE_LIST_SKILL = "003";
    
    /**
     * API Init Detail Skill of Package Skill in MasterService
     */
    public static final String API_INIT_DETAIL_SKILL = "004";
    
    /**
     * API Create Detail Skill of Package Skill in MasterService
     */
    public static final String API_CREATE_DETAIL_SKILL = "005";
    
    /**
     * API Update Detail Skill of Package Skill in MasterService
     */
    public static final String API_UPDATE_DETAIL_SKILL = "006";
    
    /**
     * API Init List Level Skill of Package Level Skill in MasterService
     */
    public static final String API_INIT_LIST_LEVEL_SKILL = "001";
    
    /**
     * API Search List Level Skill of Package Level Skill in MasterService
     */
    public static final String API_SEARCH_LIST_LEVEL_SKILL = "002";
    
    /**
     * API Delete List Level Skill of Package Level Skill in MasterService
     */
    public static final String API_DELETE_LIST_LEVEL_SKILL = "003";
    
    /**
     * API Init Detail Level Skill of Package Level Skill in MasterService
     */
    public static final String API_INIT_DETAIL_LEVEL_SKILL = "004";
    
    /**
     * API Create Detail Level Skill of Package Level Skill in MasterService
     */
    public static final String API_CREATE_DETAIL_LEVEL_SKILL = "005";
    
    /**
     * API Update Detail Level Skill of Package Level Skill in MasterService
     */
    public static final String API_UPDATE_DETAIL_LEVEL_SKILL = "006";
    
    
    /**
     * API Init List Language of Package Language in MasterService
     */
    public static final String API_INIT_LIST_LANGUAGE = "001";
    
    /**
     * API Search List Language of Package Language in MasterService
     */
    public static final String API_SEARCH_LIST_LANGUAGE = "002";
    
    /**
     * API Delete List Language of Package Language in MasterService
     */
    public static final String API_DELETE_LIST_LANGUAGE = "003";
    
    /**
     * API Init Detail Language of Package Language in MasterService
     */
    public static final String API_INIT_DETAIL_LANGUAGE = "004";
    
    /**
     * API Create Detail Language of Package Language in MasterService
     */
    public static final String API_CREATE_DETAIL_LANGUAGE = "005";
    
    /**
     * API Update Detail Language of Package Language in MasterService
     */
    public static final String API_UPDATE_DETAIL_LANGUAGE = "006";
    
    /**
     * API Init List Level Language of Package Level Language in MasterService
     */
    public static final String API_INIT_LIST_LEVEL_LANGUAGE = "001";
    
    /**
     * API Search List Level Language of Package Level Language in MasterService
     */
    public static final String API_SEARCH_LIST_LEVEL_LANGUAGE = "002";
    
    /**
     * API Delete List Level Language of Package Level Language in MasterService
     */
    public static final String API_DELETE_LIST_LEVEL_LANGUAGE = "003";
    
    /**
     * API Init Detail Level Language of Package Level Language in MasterService
     */
    public static final String API_INIT_DETAIL_LEVEL_LANGUAGE = "004";
    
    /**
     * API Create Detail Level Language of Package Level Language in MasterService
     */
    public static final String API_CREATE_DETAIL_LEVEL_LANGUAGE = "005";
    
    /**
     * API Update Detail Level Language of Package Level Language in MasterService
     */
    public static final String API_UPDATE_DETAIL_LEVEL_LANGUAGE = "006";
    
    /**
     * API Init List Position of Package Position in MasterService
     */
    public static final String API_INIT_LIST_POSITION = "001";
    
    /**
     * API Search List Position of Package Position in MasterService
     */
    public static final String API_SEARCH_LIST_POSITION = "002";
    
    /**
     * API Delete List Position of Package Position in MasterService
     */
    public static final String API_DELETE_LIST_POSITION = "003";
    
    /**
     * API Init Detail Position of Package Position in MasterService
     */
    public static final String API_INIT_DETAIL_POSITION = "004";
    
    /**
     * API Create Detail Position of Package Position in MasterService
     */
    public static final String API_CREATE_DETAIL_POSITION = "005";
    
    /**
     * API Update Detail Position of Package Position in MasterService
     */
    public static final String API_UPDATE_DETAIL_POSITION = "006";

    /**
     * API Init List Status Employee of Package Status Employee in MasterService
     */
    public static final String API_INIT_LIST_STATUS_EMPLOYEE = "001";
    
    /**
     * API Search List Status Employee of Package Status Employee in MasterService
     */
    public static final String API_SEARCH_LIST_STATUS_EMPLOYEE = "002";
    
    /**
     * API Delete List Status Employee of Package Status Employee in MasterService
     */
    public static final String API_DELETE_LIST_STATUS_EMPLOYEE = "003";
    
    /**
     * API Init Detail Status Employee of Package Status Employee in MasterService
     */
    public static final String API_INIT_DETAIL_STATUS_EMPLOYEE = "004";
    
    /**
     * API Create Detail Status Employee of Package Status Employee in MasterService
     */
    public static final String API_CREATE_DETAIL_STATUS_EMPLOYEE = "005";
    
    /**
     * API Update Detail Status Employee of Package Status Employee in MasterService
     */
    public static final String API_UPDATE_DETAIL_STATUS_EMPLOYEE = "006";
    
    /**
     * API Init List Customer of Package Customer in MasterService
     */
    public static final String API_INIT_LIST_CUSTOMER = "001";
    
    /**
     * API Search List Customer of Package Customer in MasterService
     */
    public static final String API_SEARCH_LIST_CUSTOMER = "002";
    
    /**
     * API Delete List Customer of Package Customer in MasterService
     */
    public static final String API_DELETE_LIST_CUSTOMER = "003";
    
    /**
     * API Init Detail Customer of Package Customer in MasterService
     */
    public static final String API_INIT_DETAIL_CUSTOMER = "004";
    
    /**
     * API Create Detail Customer of Package Customer in MasterService
     */
    public static final String API_CREATE_DETAIL_CUSTOMER = "005";
    
    /**
     * API Update Detail Customer of Package Customer in MasterService
     */
    public static final String API_UPDATE_DETAIL_CUSTOMER = "006";
    
    /**
     * API Init List Position Project of Package Position Project in MasterService
     */
    public static final String API_INIT_LIST_POSITION_PROJECT = "001";
    
    /**
     * API Search List Position Project of Package Position Project in MasterService
     */
    public static final String API_SEARCH_LIST_POSITION_PROJECT = "002";
    
    /**
     * API Delete List Position Project of Package Position Project in MasterService
     */
    public static final String API_DELETE_LIST_POSITION_PROJECT = "003";
    
    /**
     * API Init Detail Position Project of Package Position Project in MasterService
     */
    public static final String API_INIT_DETAIL_POSITION_PROJECT = "004";
    
    /**
     * API Create Detail Position Project of Package Position Project in MasterService
     */
    public static final String API_CREATE_DETAIL_POSITION_PROJECT = "005";
    
    /**
     * API Update Detail Position Project of Package Position Project in MasterService
     */
    public static final String API_UPDATE_DETAIL_POSITION_PROJECT = "006";
    
    /**
     * API Init List Level Project of Package Level Project in MasterService
     */
    public static final String API_INIT_LIST_LEVEL_PROJECT = "001";
    
    /**
     * API Search List Level Project of Package Level Project in MasterService
     */
    public static final String API_SEARCH_LIST_LEVEL_PROJECT = "002";
    
    /**
     * API Delete List Level Project of Package Level Project in MasterService
     */
    public static final String API_DELETE_LIST_LEVEL_PROJECT = "003";
    
    /**
     * API Init Detail Level Project of Package Level Project in MasterService
     */
    public static final String API_INIT_DETAIL_LEVEL_PROJECT = "004";
    
    /**
     * API Create Detail Level Project of Package Level Project in MasterService
     */
    public static final String API_CREATE_DETAIL_LEVEL_PROJECT = "005";
    
    /**
     * API Update Detail Level Project of Package Level Project in MasterService
     */
    public static final String API_UPDATE_DETAIL_LEVEL_PROJECT = "006";
    
    /**
     * API Init List Type Vacation of Package Type Vacation in MasterService
     */
    public static final String API_INIT_LIST_TYPE_PROJECT = "001";
    
    /**
     * API Search List Type Vacation of Package Type Vacation in MasterService
     */
    public static final String API_SEARCH_LIST_TYPE_PROJECT = "002";
    
    /**
     * API Delete List Type Vacation of Package Type Vacation in MasterService
     */
    public static final String API_DELETE_LIST_TYPE_PROJECT = "003";
    
    /**
     * API Init Detail Type Vacation of Package Type Vacation in MasterService
     */
    public static final String API_INIT_DETAIL_TYPE_PROJECT = "004";
    
    /**
     * API Create Detail Type Vacation of Package Type Vacation in MasterService
     */
    public static final String API_CREATE_DETAIL_TYPE_PROJECT = "005";
    
    /**
     * API Update Detail Type Vacation of Package Type Vacation in MasterService
     */
    public static final String API_UPDATE_DETAIL_TYPE_PROJECT = "006";
    
    /**
     * API Init List Holiday of Package Holiday in MasterService
     */
    public static final String API_INIT_LIST_HOLIDAY = "001";
    
    /**
     * API Search List Holiday of Package Holiday in MasterService
     */
    public static final String API_SEARCH_LIST_HOLIDAY = "002";
    
    /**
     * API Delete List Holiday of Package Holiday in MasterService
     */
    public static final String API_DELETE_LIST_HOLIDAY = "003";
    
    /**
     * API Init Detail Holiday of Package Holiday in MasterService
     */
    public static final String API_INIT_DETAIL_HOLIDAY = "004";
    
    /**
     * API Create Detail Holiday of Package Holiday in MasterService
     */
    public static final String API_CREATE_DETAIL_HOLIDAY = "005";
    
    /**
     * API Update Detail Holiday of Package Holiday in MasterService
     */
    public static final String API_UPDATE_DETAIL_HOLIDAY = "006";
    
    /**
     * API Init Detail Role-Screen of Package Holiday in MasterService
     */
    public static final String API_INIT_DETAIL_ROLE_SCREEN = "004";
    
    /**
     * API Search List Role-Screen of Package Holiday in MasterService
     */
    public static final String API_UPDATE_DETAIL_ROLE_SCREEN = "006";
    
    /**
     * API Delete List Role of Package Role in MasterService
     */
    public static final String API_DELETE_LIST_ROLE = "003";
    
    /**
     * API Search List Role of Package Role in MasterService
     */
    public static final String API_SEARCH_LIST_ROLE = "002";
    
    /**
     * API Create A Role of Package Role in MasterService
     */
    public static final String API_CREATE_DETAIL_ROLE = "005";
    
    /**
     * API Update A Role of Package Role in MasterService
     */
    public static final String API_UPDATE_DETAIL_ROLE = "006";
    
    /**
     * API Delete List Role of Package Scope Work in MasterService
     */
    public static final String API_DELETE_LIST_SCOPE_WORK = "003";
    
    /**
     * API Search List Role of Package Scope Work in MasterService
     */
    public static final String API_SEARCH_LIST_SCOPE_WORK = "002";
    
    /**
     * API Create A Role of Package Scope Work in MasterService
     */
    public static final String API_CREATE_DETAIL_SCOPE_WORK = "005";
    
    /**
     * API Update A Role of Package Scope Work in MasterService
     */
    public static final String API_UPDATE_DETAIL_SCOPE_WORK = "006";
    
    /**
     * API Delete List Role of Package Degree in MasterService
     */
    public static final String API_DELETE_LIST_DEGREE = "003";
    
    /**
     * API Search List Role of Package Degree in MasterService
     */
    public static final String API_SEARCH_LIST_DEGREE = "002";
    
    /**
     * API Create A Role of Package Degree in MasterService
     */
    public static final String API_CREATE_DETAIL_DEGREE = "005";
    
    /**
     * API Update A Role of Package Degree in MasterService
     */
    public static final String API_UPDATE_DETAIL_DEGREE = "006";
    
    
    /*=================1 CHARACTER====================*/
    /*=================METHOD=========================*/
    
    public static final List<String> LIST_METHOD = new ArrayList<String>(Arrays.asList("G", "A", "U", "D"));
    
    public static final int PATTERN_METHOD = 1;
    
    /**
     * Method GET
     */
    public static final String METHOD_GET = "G";
    
    /**
     * Method POST
     */
    public static final String METHOD_POST = "A";
    
    /**
     * Method PUT
     */
    public static final String METHOD_PUT = "U";
    
    /**
     * Method DELETE
     */
    public static final String METHOD_DELETE = "D";

    /**
     * BATCH PROCESSING
     */
    public static final String METHOD_BATCH_PROCESSING = "B";
    
    /*=================3 CHARACTER====================*/
    /*=================REASON=========================*/
    
    public static final List<String> LIST_REASON = 
            new ArrayList<String>(Arrays.asList("001", "002", "003", "004", "005", "006", "007", "008", "009", "010", "801", "802", "851", "852", "853", "902","903", "904", "905", "906", "999"));
    
    public static final int PATTERN_REASON = 3;
    
    /**
     * When setting a blank value the item cannot be ignored.
     */
    public static final String REASON_VALUE_UNSETTING = "001";
    
    /**
     * When specifying the value does not exist.
     */
    public static final String REASON_UNKNOWN_VALUE = "002";
    
    /**
     * When errors are found in forms like date format, time.
     */
    public static final String REASON_DATE_TIME_FORMAT_ILLEGAL = "003";
    
    /**
     * When an error is found in the date, time, data is time start bigger than time end
     */
    public static final String REASON_DATE_TIME_START_GREATER_THAN_END = "004";
    
    /**
     * When entering characters in the required fields enter the number
     */
    public static final String REASON_NUMBER_ILLEGAL = "005";
    
    /**
     * When specifying a numerical value outside the boundary
     */
    public static final String REASON_NUMBER_SIZE_ILLEGAL = "006";
    
    /**
     * When characters that cannot be used are included.
     */
    public static final String REASON_CHARACTER_ILLEGAL_OR_CANNOT_USED = "007";
    
    /**
     * When the characters can be exceeded
     */
    public static final String REASON_CHARACTER_LENGHT_ILLEGAL = "008";
    
    /**
     * Already exists or cannot be used
     */
    public static final String REASON_VALUE_ILLEGAL = "009";
    
    /**
     * When the number of cases exceeds the number of data that can be processed.
     */
    public static final String REASON_NUMBER_CASE_OF_EXCESS = "010";

    /**
     * When an error is found in the date, time, data is time end less than time start
     */
    public static final String REASON_DATE_TIME_END_LESS_THAN_START = "012";

    /**
     * When password is incorrect
     */
    public static final String REASON_PASSWORD_INCORRECT = "801";
    
    /**
     * When password is illegal
     */
    public static final String REASON_PASSWORD_ILLEGAL = "802";
    
    /**
     * When the request is duplicated with list holiday or at the weekend
     */
    public static final String REASON_REQUEST_DUPLICATES_HOLIDAY = "851";
    
    /**
     * When the request is duplicated with an another request
     */
    public static final String REASON_REQUEST_DUPLICATES_ANOTHER_REQUEST = "852";
    
    /**
     * When the request is over than 1 month
     */
    public static final String REASON_REQUEST_IS_OVER_TIME = "853";
    
    /**
     * When JSON is invalid
     */
    public static final String REASON_JSON_FORM_ILLEGAL = "901";
    
    /**
     * When the method is invalid
     */
    public static final String REASON_METHOD_ILLEGAL = "902";
    
    /**
     * When the specification of the content type is invalid
     */
    public static final String REASON_CONTENT_TYPE_ILLEGAL = "903";
    
    /**
     * When the data size is exceeded
     */
    public static final String REASON_DATA_SIZE_EXCESS = "904";
    
    /**
     * When an API authentication error occurs between VPCs.
     */
    public static final String REASON_ATTESTATION_ERROR = "905";
    
    /**
     * When there is no access in the Internet Protocol address in the requested origin.
     */
    public static final String REASON_ACCESS_RIGHT_ERROR = "906";
    
    /**
     * When an error occurs by processing in the system server this is an error in the DB connection, etc.
     */
    public static final String REASON_INTERNAL_ERROR = "999";
 

    
}
