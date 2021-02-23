export enum AuthApiUrl {
    login = '/ms/auth/ms000000login'
}

export enum DepartmentApiUrl {
    DepartmentSearch = '/ms/ms002001searchdepartment',
    DepartmentCreate = '/ms/ms002002createdepartment',
    DepartmentGetDetail = '/ms/ms002002getdetaildepartment',
    DepartmentUpdate = '/ms/ms002002updatedepartment',
    DepartmentDelete = '/ms/ms002001deletedepartment'
}

export enum SkillApiUrl {
    SkillInit = '/ms/ms003001initskill',
    SkillSearch = '/ms/ms003001searchskill',
    SkillTypeInit = '/ms/ms003002initskill',
    SkillCreate = '/ms/ms003002createskill',
    SkillGetDetail = '/ms/ms003002getdetailskill',
    SkillUpdate = '/ms/ms003002updateskill',
    SkillDelete = '/ms/ms003001deleteskill'
}

export enum LevelSkillApiUrl {
    LevelSkillSearch = '/ms/ms004001searchskilllevel',
    LevelSkillCreate = '/ms/ms004002createskilllevel',
    LevelSkillGetDetail = '/ms/ms004002getdetailskilllevel',
    LevelSkillUpdate = '/ms/ms004002updateskilllevel',
    LevelSkillDelete = '/ms/ms004001deleteskilllevel'
}

export enum UserApiUrl {
    UserSearch = '/ms/ms001001searchuser',
    UserCreate = '/ms/ms001002createuser',
    UserGetDetail = '/ms/ms001002getdetailuser',
    UserUpdate = '/ms/ms001002updateuser',
    UserDelete = '/ms/ms001001deleteuser',
    UserInit = '/ms/ms001001inituser',
    UserInitDetails = '/ms/ms001002inituser',
    EmployeeCreateDefault = '/ms/ms001002createdefault',

}

export enum LanguageApiUrl {
    LanguageSearch = '/ms/ms005001searchlanguage',
    LanguageCreate = '/ms/ms005002createlanguage',
    LanguageGetDetail = '/ms/ms005002getdetaillanguage',
    LanguageUpdate = '/ms/ms005002updatelanguage',
    LanguageDelete = '/ms/ms005001deletelanguage'
}

export enum LevelLanguageApiUrl {
    LevelLanguageSearch = '/ms/ms006001searchlevellanguage',
    LevelLanguageCreate = '/ms/ms006002createlevellanguage',
    LevelLanguageGetDetail = '/ms/ms006002getdetaillevellanguage',
    LevelLanguageUpdate = '/ms/ms006002updatelevellanguage',
    LevelLanguageDelete = '/ms/ms006001deletelevellanguage'
}

export enum EmployeePositionApiUrl {
    EmployeePositionSearch = '/ms/ms007001searchemployeeposition',
    EmployeePositionCreate = '/ms/ms007002createemployeeposition',
    EmployeePositionGetDetail = '/ms/ms007002getdetailemployeeposition',
    EmployeePositionUpdate = '/ms/ms007002updateemployeeposition',
    EmployeePositionDelete = '/ms/ms007001deleteemployeeposition'
}

export enum ProjectPositionApiUrl {
    ProjectPositionSearch = '/ms/ms010001searchpositionproject',
    ProjectPositionCreate = '/ms/ms010002createpositionproject',
    ProjectPositionGetDetail = '/ms/ms010002getdetailpositionproject',
    ProjectPositionUpdate = '/ms/ms010002updatepositionproject',
    ProjectPositionDelete = '/ms/ms010001deletepositionproject'
}

export enum VacationTypeApiUrl {
    VacationTypeSearch = '/ms/ms012001searchvacationtype',
    VacationTypeCreate = '/ms/ms012002createvacationtype',
    VacationTypeGetDetail = '/ms/ms012002getdetailvacationtype',
    VacationTypeUpdate = '/ms/ms012002updatevacationtype',
    VacationTypeDelete = '/ms/ms012001deletevacationtype'
}

export enum VacationApiUrl {
    /*VacationSearch = '/vs/vm001001searchvacation',
    VacationSearchInit = '/vs/vm001001initvacation',
    VacationCreate = '/vs/vm001002createvacation',
    VacationInit = '/vs/vm001002initvacation',
    VacationGetDetail = '/vs/vm001002getdetailvacation',
    VacationUpdate = '/vs/vm001002updatevacation',
    VacationDelete = '/vs/vm001002deletevacation',
    VacationApproveInit = '/vs/vm001003initvacation',
    VacationApproveGetDetail = '/vs/vm001003getdetailvacation',
    VacationGetHistoryDetail = '/vs/vm001003gethistorydetailvacation',
    VacationApprove = '/vs/vm001003approvevacation',*/
    // Send
    VacationSendSearch = '/vs/vm001001searchvacation',
    VacationSendSearchInit = '/vs/vm001001initvacation',
    VacationSendDetailInit = '/vs/vm001002initvacation',
    VacationSendGetDetail = '/vs/vm001002getdetailvacation',
    VacationSendDelete = '/vs/vm001002deletevacation',
    VacationSendCreate = '/vs/vm001002createvacation',
    VacationSendUpdate = '/vs/vm001002updatevacation',
    // Receive
    VacationReceiveSearch = '/vs/vm002001searchvacation',
    VacationReceiveSearchInit = '/vs/vm002001initvacation',
    VacationReceiveGetDetail = '/vs/vm002002getdetailvacation',
    VacationReceiveDetailInit = '/vs/vm002002initvacation',
    VacationReceiveApprove = '/vs/vm002002updatevacation',
    // History
    VacationHistorySearch = '/vs/vm003001searchvacation',
    VacationHistorySearchInit = '/vs/vm003001initvacation',
    VacationHistoryGetDetail = '/vs/vm003002getdetailvacation',
    VacationHistoryDetailInit = '/vs/vm003002initvacation',
    // Summary
    VacationSummarySearch = '/vs/vm004001searchvacation',
    VacationSummarySearchInit = '/vs/vm004001initvacation',
    //TimeKeeping
    TimeKeepingSearch = '/vs/vm005001searchtimekeeping',
    TimeKeepingReset = '/vs/vm005001deletetimekeeping',
    //Import-TimeKeeping
    TimeKeepingInitImport = '/vs/vm005002inittimekeeping',
    TimeKeepingProcessImport = '/vs/vm005002searchtimekeeping',
    TimeKeepingSaveImport = '/vs/vm005002createtimekeeping',
    //Detail TimeKeeping
    TimeKeepingSearchDetail = '/vs/vm005003searchtimekeeping',
    TimeKeepingSearchDelete = '/vs/vm005003updateviolation',
    //Report TimeKeeping
    ReportSummarySearch = '/vs/vm005004searchreport',
    InitReportSummarySearch = '/vs/vm005004initreport',
    //Tracking TimeKeeping
    TrackingTimeKeepingSearch = '/vs/vm005005searchtimekeeping',
    // Shift Work
    ShiftWorkSendSearchInit = '/vs/vm006001initshiftwork',
    ShiftWorkSendSearch = '/vs/vm006001searchshiftwork',
    ShiftWorkSendCreate = '/vs/vm006002createshiftwork',
    ShiftWorkSendDetailInit = '/vs/vm006002initshiftwork',
    ShiftWorkSendGetDetail = '/vs/vm006002getdetailshiftwork',
    ShiftWorkSendUpdate = '/vs/vm006002updateshiftwork',
    ShiftWorkSendDelete = '/vs/vm006002deleteshiftwork',
    // Shift Work Receivce
    ShiftWorkReceiveSearch = '/vs/vm007001searchshiftwork',
    ShiftWorkReceiveSearchInit = '/vs/vm007001initshiftwork',
    ShiftWorkReceiveGetDetail = '/vs/vm007002getdetailshiftwork',
    ShiftWorkReceiveApprove = '/vs/vm007002updateshiftwork',
    // Shift Work History
    ShiftWorkHistorySearch = '/vs/vm008001searchshiftwork',
    ShiftWorkHistorySearchInit = '/vs/vm008001initshiftwork',
    ShiftWorkHistoryGetDetail = '/vs/vm008002getdetailshiftwork',
}

export enum EmployeeApiUrl {
    EmployeeSearch = '/es/em001001search',
    EmployeeCreate = '/es/em001002create',
    EmployeeSearchInit = '/es/em001001init',
    EmployeeUpdate = '/es/em001002update',
    EmployeeInit = '/es/em001002init',
    EmployeeDetails = '/es/em001002getdetail',
    EmployeeDelete = '/es/em001001delete',
    EmployeeUpdatePassword = '/es/em002005updatepassword',
    EmployeeExport = '/es/em001001export'
}

export enum ProjectApiUrl {
    ProjectSearch = '/ps/pm001001search',
    ProjectDelete = '/ps/pm001001deleteproject',
    ProjectSearchInit = '/ps/pm001001init',
    ProjectCreateInit = '/ps/pm001002initproject',
    ProjectCreate = '/ps/pm001002createproject',
    ProjectUpdate = '/ps/pm001002updateproject',
    ProjectGetDetail = '/ps/pm001002getdetailproject',
    GetDocument = '/ps/pm001002getlistdocument',
    deleteDocument = '/ps/pm001002deletedocument',
    GetDocumentFile = '/ps/pm001002getdocument',
    CreateDocument = '/ps/pm001002createdocument',
    IssueSearch = '/ps/pm001002searchissue',
    IssueSearchInit = '/ps/pm001002initissue',
    IssueDelete = '/ps/pm001002deleteissue',
    MemberGetList = '/ps/pm001002getlistmember',
    MemberInit = '/ps/pm001002initmember',
    MemberUpdateInfo = '/ps/pm001002updatemember',
    MemberAddToProject = '/ps/pm001002addmembers',
    MemberRemoveFromProject = '/ps/pm001002removemembers',
    MemberDetailInit = '/ps/pm001002initdetailmember',
    MemberGetDetail = '/ps/pm001002getdetailmember',
    GetDetailTracker = '/ps/pm001002getdetailtracker',
    IssueDetailInit = '/ps/pm001002initdetailissue',
    IssueDetailCreate = '/ps/pm001002createissue',
    IssueDetailUpdate = '/ps/pm001002updateissue',
    IssueGetDetail = '/ps/pm001002getdetailissue'
}

export enum StatusEmployeeApiUrl {
    StatusEmployeeSearch = '/ms/ms008001searchemployeestatus',
    StatusEmployeeCreate = '/ms/ms008002createemployeestatus',
    StatusEmployeeGetDetail = '/ms/ms008002getdetailemployeestatus',
    StatusEmployeeUpdate = '/ms/ms008002updateemployeestatus',
    StatusEmployeeDelete = '/ms/ms008001deleteemployeestatus'
}

export enum CustomerApiUrl {
    CustomerSearch = '/ms/ms009001searchcustomer',
    CustomerDelete = '/ms/ms009001deletecustomer',
    CustomerGetDetail = '/ms/ms009002getdetailcustomer',
    CustomerCreate = '/ms/ms009002createcustomer',
    CustomerUpdate = '/ms/ms009002updatecustomer'
}

export enum HolidayApiUrl {
    HolidaySearch = '/ms/ms013001searchholiday',
    HolidayDelete = '/ms/ms013001deleteholiday',
    HolidayGetDetail = '/ms/ms013002getdetailholiday',
    HolidayCreate = '/ms/ms013002createholiday',
    HolidayUpdate = '/ms/ms013002updateholiday'
}

export enum RoleScreenApiUrl {
    RoleScreenSearch = '/ms/ms017001searchrolescreen',
    RoleScreenDetailInit = '/ms/ms017002initrolescreen',
    RoleScreenGetDetail = '/ms/ms017002getdetailrolescreen',
    RoleScreenUpdate = '/ms/ms017002updaterolescreen'
}

export enum RoleApiUrl {
    RoleDelete = '/ms/ms018001deletegrouprole',
    RoleSearch = '/ms/ms018001searchgrouprole',
    RoleCreate = '/ms/ms018002creategrouprole',
    RoleUpdate = '/ms/ms018002updategrouprole',
    RoleGetDetail = '/ms/ms018002getdetailgrouprole'
}

export enum ScopeWorkApiUrl {
    ScopeWorkDelete = '/ms/ms019001deletescopework',
    ScopeWorkSearch = '/ms/ms019001searchscopework',
    ScopeWorkCreate = '/ms/ms019002createscopework',
    ScopeWorkUpdate = '/ms/ms019002updatescopework',
    ScopeWorkGetDetail = '/ms/ms019002getdetailscopework'
}
export enum DegreeApiUrl {
    DegreeDelete = '/ms/ms020001deletedegree',
    DegreeSearch = '/ms/ms020001searchdegree',
    DegreeCreate = '/ms/ms020002createdegree',
    DegreeUpdate = '/ms/ms020002updatedegree',
    DegreeGetDetail = '/ms/ms020002getdetaildegree'
}

export enum DashboardApiUrl {
    StatisticEmployee = '/ms/ms999001inittotalemployee'
}

export enum ShiftworkApiUrl{
    ShiftworkGetAll = '/ms/ms023001searchshiftworkoption',
    ShiftworkGetDetail = '/ms/ms023001getdetailshiftworkoption',
    ShiftworkUpdate ='/ms/ms023001updateshiftworkoption',
    ShiftworkDelete = '/ms/ms023001deleteshiftworkoption',
    ShiftworkCreate = '/ms/ms023001createshiftworkoption',
    ShiftworkInitBreakTime = '/ms/ms023001init',
    ShiftworkGetTimeWorkSystem = '/ms/ms023001gettimeworksystem'
}
