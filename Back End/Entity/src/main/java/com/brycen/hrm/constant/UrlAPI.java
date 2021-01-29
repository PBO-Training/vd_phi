package com.brycen.hrm.constant;

/**
 * [Description]: Define variable constant Url API<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public class UrlAPI {
    /**
     * Main API service
     */
    public static final String PROJECT_SERVICE = "http://project-service/";
    public static final String EMPLOYEE_SERVICE = "http://employee-service/";
    public static final String MASTER_SERVICE = "http://master-service/";
    // STG
    // public static final String PROJECT_SERVICE = "http://localhost:8080/STG-project-service/";
    // public static final String EMPLOYEE_SERVICE = "http://localhost:8080/STG-employee-service/";
    // public static final String MASTER_SERVICE = "http://localhost:8080/STG-master-service/";

    // DEV
    // public static final String PROJECT_SERVICE = "http://localhost:8080/DEV-project-service/";
    // public static final String EMPLOYEE_SERVICE = "http://localhost:8080/DEV-employee-service/";
    // public static final String MASTER_SERVICE = "http://localhost:8080/DEV-master-service/";

    // Auth
    public static final String MS000000_LOGIN = "ms000000login";

    /**
     * Url Master Service
     */
    // User
    public static final String MS001001_INIT_USER = "ms001001inituser";
    public static final String MS001001_SEARCH_USER = "ms001001searchuser";
    public static final String MS001001_DELETE_USER = "ms001001deleteuser";
    public static final String MS001002_INIT_USER = "ms001002inituser";
    public static final String MS001002_GETDETAIL_USER = "ms001002getdetailuser";
    public static final String MS001002_CREATE_USER = "ms001002createuser";
    public static final String MS001002_UPDATE_USER = "ms001002updateuser";

    // Employee
    public static final String MS001002_CREATE_DEFAULT = "ms001002createdefault";

    // Department
    public static final String MS002001_SEARCH_DEPARTMENT = "ms002001searchdepartment";
    public static final String MS002002_CREATE_DEPARTMENT = "ms002002createdepartment";
    public static final String MS002002_UPDATE_DEPARTMENT = "ms002002updatedepartment";
    public static final String MS002002_GETDETAIL_DEPARTMENT = "ms002002getdetaildepartment";
    public static final String MS002001_DELETE_DEPARTMENT = "ms002001deletedepartment";

    // Skill
    public static final String MS003001_INIT_SKILL = "ms003001initskill";
    public static final String MS003001_SEARCH_SKILL = "ms003001searchskill";
    public static final String MS003002_INIT_SKILL = "ms003002initskill";
    public static final String MS003002_CREATE_SKILL = "ms003002createskill";
    public static final String MS003002_GETDETAIL_SKILL = "ms003002getdetailskill";
    public static final String MS003002_UPDATE_SKILL = "ms003002updateskill";
    public static final String MS003001_DELETE_SKILL = "ms003001deleteskill";

    // Skill Level

    public static final String MS004001_SEARCH_SKILL_LEVEL = "ms004001searchskilllevel";
    public static final String MS004002_CREATE_SKILL_LEVEL = "ms004002createskilllevel";
    public static final String MS004002_GETDETAIL_SKILL_LEVEL = "ms004002getdetailskilllevel";
    public static final String MS004002_UPDATE_SKILL_LEVEL = "ms004002updateskilllevel";
    public static final String MS004001_DELETE_SKILL_LEVEL = "ms004001deleteskilllevel";

    // Language
    public static final String MS005001_SEARCH_LANGUAGE = "ms005001searchlanguage";
    public static final String MS005002_CREATE_LANGUAGE = "ms005002createlanguage";
    public static final String MS005002_UPDATE_LANGUAGE = "ms005002updatelanguage";
    public static final String MS005002_GETDETAIL_LANGUAGE = "ms005002getdetaillanguage";
    public static final String MS005001_DELETE_LANGUAGE = "ms005001deletelanguage";

    // Position Employee
    public static final String MS007001_SEARCH_EMPLOYEE_POSITION = "ms007001searchemployeeposition";
    public static final String MS007002_CREATE_EMPLOYEE_POSITION = "ms007002createemployeeposition";
    public static final String MS007002_GETDETAIL_EMPLOYEE_POSITION = "ms007002getdetailemployeeposition";
    public static final String MS007002_UPDATE_EMPLOYEE_POSITION = "ms007002updateemployeeposition";
    public static final String MS007001_DELETE_EMPLOYEE_POSITION = "ms007001deleteemployeeposition";

    // Employee Status
    public static final String MS008001_SEARCH_EMPLOYEE_STATUS = "ms008001searchemployeestatus";
    public static final String MS008002_CREATE_EMPLOYEE_STATUS = "ms008002createemployeestatus";
    public static final String MS008002_GETDETAIL_EMPLOYEE_STATUS = "ms008002getdetailemployeestatus";
    public static final String MS008002_UPDATE_EMPLOYEE_STATUS = "ms008002updateemployeestatus";
    public static final String MS008001_DELETE_EMPLOYEE_STATUS = "ms008001deleteemployeestatus";

    // Customer
    public static final String MS009001_SEARCH_CUSTOMER = "ms009001searchcustomer";
    public static final String MS009002_CREATE_CUSTOMER = "ms009002createcustomer";
    public static final String MS009002_GETDETAIL_CUSTOMER = "ms009002getdetailcustomer";
    public static final String MS009002_UPDATE_CUSTOMER = "ms009002updatecustomer";
    public static final String MS009001_DELETE_CUSTOMER = "ms009001deletecustomer";

    // Position Project
    public static final String MS010001_SEARCH_POSITION_PROJECT = "ms010001searchpositionproject";
    public static final String MS010002_CREATE_POSITION_PROJECT = "ms010002createpositionproject";
    public static final String MS010002_GETDETAIL_POSITION_PROJECT = "ms010002getdetailpositionproject";
    public static final String MS010002_UPDATE_POSITION_PROJECT = "ms010002updatepositionproject";
    public static final String MS010001_DELETE_POSITION_PROJECT = "ms010001deletepositionproject";

    // ContractType
    public static final String MS015001_SEARCH_CONTRACTTYPE = "ms015001searchcontracttype";

    // Level language
    public static final String MS006001_SEARCH_LEVEL_LANGUAGE = "ms006001searchlevellanguage";
    public static final String MS006002_CREATE_LEVEL_LANGUAGE = "ms006002createlevellanguage";
    public static final String MS006002_UPDATE_LEVEL_LANGUAGE = "ms006002updatelevellanguage";
    public static final String MS006002_GETDETAIL_LEVEL_LANGUAGE = "ms006002getdetaillevellanguage";
    public static final String MS006001_DELETE_LEVEL_LANGUAGE = "ms006001deletelevellanguage";

    // Level Project
    public static final String MS011001_SEARCH_LEVEL_PROJECT = "ms011001searchlevelproject";

    // Vacation Type
    public static final String MS012001_SEARCH_VACATION_TYPE = "ms012001searchvacationtype";
    public static final String MS012002_CREATE_VACATION_TYPE = "ms012002createvacationtype";
    public static final String MS012002_UPDATE_VACATION_TYPE = "ms012002updatevacationtype";
    public static final String MS012002_GETDETAIL_VACATION_TYPE = "ms012002getdetailvacationtype";
    public static final String MS012001_DELETE_VACATION_TYPE = "ms012001deletevacationtype";

    // Holiday
    public static final String MS013001_SEARCH_HOLIDAY = "ms013001searchholiday";
    public static final String MS013002_CREATE_HOLIDAY = "ms013002createholiday";
    public static final String MS013002_UPDATE_HOLIDAY = "ms013002updateholiday";
    public static final String MS013002_GETDETAIL_HOLIDAY = "ms013002getdetailholiday";
    public static final String MS013001_DELETE_HOLIDAY = "ms013001deleteholiday";

    // Status Project
    public static final String MS014001_SEARCH_STATUS_PROJECT = "ms014001searchstatusproject";
    // Evaluate Project
    public static final String MS016001_SEARCH_EVALUATE_PROJECT = "ms016001evaluateproject";

    // Role Screen
    public static final String MS017001_SEARCH_ROLE_SCREEN = "ms017001searchrolescreen";
    public static final String MS017002_INIT_ROLE_SCREEN = "ms017002initrolescreen";
    public static final String MS017002_GETDETAIL_ROLE_SCREEN = "ms017002getdetailrolescreen";
    public static final String MS017002_UPDATE_ROLE_SCREEN = "ms017002updaterolescreen";

    // Group Role
    public static final String MS018001_DELETE_GROUP_ROLE = "ms018001deletegrouprole";
    public static final String MS018001_SEARCH_GROUP_ROLE = "ms018001searchgrouprole";
    public static final String MS018002_CREATE_GROUP_ROLE = "ms018002creategrouprole";
    public static final String MS018002_GETDETAIL_GROUP_ROLE = "ms018002getdetailgrouprole";
    public static final String MS018002_UPDATE_GROUP_ROLE = "ms018002updategrouprole";

    // Scope Work
    public static final String MS019001_DELETE_SCOPEWORK = "ms019001deletescopework";
    public static final String MS019001_SEARCH_SCOPEWORK = "ms019001searchscopework";
    public static final String MS019002_CREATE_SCOPEWORK = "ms019002createscopework";
    public static final String MS019002_GETDETAIL_SCOPEWORK = "ms019002getdetailscopework";
    public static final String MS019002_UPDATE_SCOPEWORK = "ms019002updatescopework";

    // Degree
    public static final String MS020001_DELETE_DEGREE = "ms020001deletedegree";
    public static final String MS020001_SEARCH_DEGREE = "ms020001searchdegree";
    public static final String MS020002_CREATE_DEGREE = "ms020002createdegree";
    public static final String MS020002_GETDETAIL_DEGREE = "ms020002getdetaildegree";
    public static final String MS020002_UPDATE_DEGREE = "ms020002updatedegree";

    // Language
    public static final String MS021001_SEARCH_LANGUAGE_CATEGORY = "ms021001searchlanguagecategory";

    // Skill Project
    public static final String MS022001_SEARCH_SKILL_PROJECT = "ms022001searchskillproject";

    // Dashboard
    public static final String MS999001_INIT_TOTAL_EMPLOYEE = "ms999001inittotalemployee";
    // ShiftWork
    public static final String MS023001_SEARCH_SHIFTWORKOPTION = "ms023001searchshiftworkoption";
    public static final String MS023001_DELETE_SHIFTWORKOPTION = "ms023001deleteshiftworkoption";
    public static final String MS023001_GETDETAIL_SHIFTWORKOPTION = "ms023001getdetailshiftworkoption";
    public static final String MS023001_UPDATE_SHIFTWORKOPTION = "ms023001updateshiftworkoption";
    public static final String MS023001_CREATE_SHIFTWORKOPTION = "ms023001createshiftworkoption";
    /**
     * Url Employee Service
     */
    public static final String EM001001_SEARCH = "em001001search";
    public static final String EM001001_DELETE = "em001001delete";
    public static final String EM001001_EXPORT = "em001001export";
    public static final String EM001002_CREATE = "em001002create";
    public static final String EM001002_INIT = "em001002init";
    public static final String EM001002_UPDATE = "em001002update";

    public static final String EM001001_INIT = "em001001init";
    public static final String EM001002_GET_DETAIL = "em001002getdetail";
    public static final String EM002005_UPDATE_PASSWORD = "em002005updatepassword";

    /**
     * Url Project Service
     */
    // Project Search
    public static final String PM001001_SEARCH = "pm001001search";
    // Project create
    public static final String PM001002_CREATE_PROJECT = "pm001002createproject";
    // Project getDetail
    public static final String PM001002_GETDETAIL_PROJECT = "pm001002getdetailproject";
    // Update project
    public static final String PM001002_UPDATE_PROJECT = "pm001002updateproject";

    // Project Init
    public static final String PM001001_DELETE_PROJECT = "pm001001deleteproject";
    public static final String PM001001_INIT = "pm001001init";
    public static final String PM001002_INIT_PROJECT = "pm001002initproject";

    // Member in a project

    public static final String PM001002_GET_LIST_MEMBER = "pm001002getlistmember";
    public static final String PM001002_INIT_MEMBER = "pm001002initmember";
    public static final String PM001002_REMOVE_MEMBERS = "pm001002removemembers";
    public static final String PM001002_UPDATE_INFO_MEMBER = "pm001002updatemember";
    public static final String PM001002_ADD_MEMBERS = "pm001002addmembers";
    public static final String PM001002_SAVE_CURRENT_MEMBERS = "pm001002savecurrentmembers";

    public static final String PM001002_INIT_ISSUE = "pm001002initissue";
    public static final String PM001002_SEARCH_ISSUE = "pm001002searchissue";
    public static final String PM001002_DELETE_ISSUE = "pm001002deleteissue";
    public static final String PM001002_INIT_DETAIL_ISSUE = "pm001002initdetailissue";
    public static final String PM001002_CREATE_ISSUE = "pm001002createissue";
    public static final String PM001002_UPDATE_ISSUE = "pm001002updateissue";
    public static final String PM001002_GET_DETAIL_ISSUE = "pm001002getdetailissue";
    public static final String PM001002_GET_DETAIL_MEMBER = "pm001002getdetailmember";

    public static final String PM001002_GETDETAIL_TRACKER = "pm001002getdetailtracker";

    // Document
    public static final String PM001002_GET_LIST_DOCUMENT = "pm001002getlistdocument";
    public static final String PM001002_DELETE_DOCUMENT = "pm001002deletedocument";
    public static final String PM001002_CREATE_DOCUMENT = "pm001002createdocument";
    public static final String PM001002_GET_DOCUMENT = "pm001002getdocument";

    /**
     * Url Vacation Service
     */
    // Vacation Send
    public static final String VM001001_SEARCH_VACATION = "vm001001searchvacation";
    public static final String VM001001_INIT_VACATION = "vm001001initvacation";
    public static final String VM001002_CREATE_VACATION = "vm001002createvacation";
    public static final String VM001002_INIT_VACATION = "vm001002initvacation";
    public static final String VM001002_GET_DETAIL_VACATION = "vm001002getdetailvacation";
    public static final String VM001002_UPDATE_VACATION = "vm001002updatevacation";
    public static final String VM001002_DELETE_VACATION = "vm001002deletevacation";

    // Vacation Receive
    public static final String VM002001_SEARCH_VACATION = "vm002001searchvacation";
    public static final String VM002001_INIT_VACATION = "vm002001initvacation";
    public static final String VM002002_APPROVE_VACATION = "vm002002updatevacation";
    public static final String VM002002_GET_DETAIL_VACATION = "vm002002getdetailvacation";
    public static final String VM002002_INIT_VACATION = "vm002002initvacation";

    // Vacation History
    public static final String VM003001_SEARCH_VACATION = "vm003001searchvacation";
    public static final String VM003001_INIT_VACATION = "vm003001initvacation";
    public static final String VM003002_GET_HISTORY_DETAIL_VACATION = "vm003002getdetailvacation";
    public static final String VM003002_INIT_VACATION = "vm003002initvacation";

    // Vacation Summary
    public static final String VM004001_SEARCH_VACATION = "vm004001searchvacation";
    public static final String VM004001_INIT_VACATION = "vm004001initvacation";

    // TimeKeeping
    public static final String VM005001_SEARCH_TIMEKEEPING = "vm005001searchtimekeeping";
    public static final String VM005001_RESET_TIMEKEEPING = "vm005001deletetimekeeping";

    // Import-TimeKeeping
    public static final String VM005002_INIT_TIMEKEEPING = "vm005002inittimekeeping";
    public static final String VM005002_PROCESS_TIMEKEEPING = "vm005002searchtimekeeping";
    public static final String VM005002_SAVE_TIMEKEEPING = "vm005002createtimekeeping";

    // TimeKeeping Detail
    public static final String VM005003_SEARCH_TIMEKEEPING = "vm005003searchtimekeeping";
    public static final String VM005003_BYPASS_VIOLATION = "vm005003updateviolation";

    // TimeKeeping Report
    public static final String VM005004_SEARCH_REPORT = "vm005004searchreport";
    public static final String VM005004_INIT_REPORT = "vm005004initreport";

    // Tracking-TimeKeeping
    public static final String VM005005_SEARCH_TIMEKEEPING = "vm005005searchtimekeeping";

    // Shift Work
    public static final String VM006001_INIT_SHIFT_WORK = "vm006001initshiftwork";
    public static final String VM006001_SEARCH_SHIFT_WORK = "vm006001searchshiftwork";
    public static final String VM006002_CREATE_SHIFT_WORK = "vm006002createshiftwork";
    public static final String VM006002_INIT_SHIFT_WORK = "vm006002initshiftwork";
    public static final String VM006002_DELETE_SHIFT_WORK = "vm006002deleteshiftwork";
    public static final String VM006002_GET_DETAIL_SHIFT_WORK = "vm006002getdetailshiftwork";
    public static final String VM006002_UPDATE_SHIFT_WORK = "vm006002updateshiftwork";

    // Shift Work Receive
    public static final String VM007001_INIT_SHIFT_WORK = "vm007001initshiftwork";
    public static final String VM007001_SEARCH_SHIFT_WORK = "vm007001searchshiftwork";
    public static final String VM007002_GET_DETAIL_SHIFT_WORK = "vm007002getdetailshiftwork";
    public static final String VM007002_UPDATE_SHIFT_WORK = "vm007002updateshiftwork";

    // Shift Work History
    public static final String VM008001_INIT_SHIFT_WORK = "vm008001initshiftwork";
    public static final String VM008001_SEARCH_SHIFT_WORK = "vm008001searchshiftwork";
    public static final String VM008002_GET_DETAIL_SHIFT_WORK = "vm008002getdetailshiftwork";
}
