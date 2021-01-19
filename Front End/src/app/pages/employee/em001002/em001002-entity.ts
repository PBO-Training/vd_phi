export interface Employee {
    address: string;
    avataUrl: string;
    birthday: string;
    dateJoinCompany: string;
    dateOfIssueOfIDCard: string;
    degreeID: number | null;
    departmentID: number | null;
    email: string;
    emergency: string;
    employeeCode: string | null;
    employeeID: number | null;
    firstName: string;
    gender: boolean;
    graduateYear: number | string;
    identityCard: string;
    lastName: string;
    contract: ContractDetail[];
    listHistory: HistoryWork[];
    listLanguage: LanguageDetail[];
    listProject: any[];
    listSkill: SkillDetail[];
    married: boolean;
    nationalityID: number | null;
    description: string;
    numOfChild: number | null;
    phone: string;
    positionEmployeeID: number | null;
    skype: string;
    statusEmployeeID: number | null;
    university: string;
    biography: string | null;
    inCompanyExperience: number | null;
    totalExperience: number | null;
}
export interface EmployeeDetails {
    content: Employee;
}
export interface RequestUpdate {
    employeeID: number;
    tabIndex: number;
    contractRequest: any;
    personalRequest: any;
    skillRequest: [];
    languageRequest: [];
    historyRequest: any[];
}
export interface ServerErrors {
    itemName: string;
    code: string;
}
export interface Department {
    departmentCode: string;
    departmentDescription: string;
    departmentID: number | string;
    departmentName: string;
}
export interface PositionEmployee {
    positionEmployeeCode: string;
    positionEmployeeDescription: string;
    positionEmployeeID: number | string;
    positionEmployeeName: string;
}
export interface Nationality {
    nationalityID: number;
    nationalityName: string;
}
export interface StatusEmployee {
    statusEmployeeCode: string;
    statusEmployeeID: number;
    statusEmployeeName: string;
}
export interface Contract {
    contractTypeID: number;
    contractTypeName: string;
}
export interface Database {
    databaseHistoryID: number;
    databaseHistoryName: string;
}
export interface Language {
    languageCode: string;
    languageDescription: string;
    languageID: number;
    languageName: string;
    languageCategoryID: number;
}
export interface LevelLanguage {
    levelLanguageCode: string;
    levelLanguageDescription: string;
    levelLanguageID: number;
    levelLanguageName: string;
}
export interface LanguageCategory {
    languageCategoryCode: string;
    languageCategoryDescription: string;
    languageCategoryID: number;
    languageCategoryName: string;
}
export interface LevelSkill {
    levelSkillCode: string;
    levelSkillDescription: string;
    levelSkillID: number;
    levelSkillName: number | string;
}
export interface OperationSystem {
    operationSystemID: number;
    operationSystemName: string;
}
export interface PositionProject {
    positionProjectCode: string;
    positionProjectDescription: string;
    positionProjectID: number;
    positionProjectName: string;
}
export interface InitScopeWork {
    scopeWorkID: number;
    scopeWorkName: string;
}
export interface Skill {
    skillCode: string;
    skillDescription: string;
    skillID: number;
    skillName: string;
    skillTypeID: number;
}
export interface StatusEmployee {
    statusEmployeeCode: string;
    statusEmployeeID: number;
    statusEmployeeName: string;
}
export interface SkillDetail {
    levelSkillID: number;
    levelSkillCode: string;
    levelSkillName: string;
    skillName: string;
    skillID: number;
    skillTypeID: number;
    levelSkillValue: number;
    skillTypeName: string;
    skillExperience: number;
}
export interface LanguageDetail {
    expirationDate: string;
    languageID: number;
    languagePoints: number;
    languageName: string;
    languageCertificate: string;
    languageCategoryID: number;
    languageCategoryName: string;
}
export interface ScopeWork {
    scopeWorkID: number;
    scopeWorkName: string;
}
export interface EvaluateEmployeeProject {
    evaluateEmployeeProjectID: number;
    evaluateEmployeeProjectName: string;
}
export interface HistoryWork {
    databaseID: number;
    evaluateEmployeeProjectID: number;
    endDate: string;
    historyCompanyName: string;
    historyDescription: string;
    historyProjectName: string;
    historyWorkID: number;
    listSkill: Skill[];
    listScopeWork: ScopeWork[];
    insideCompany: boolean;
    operationSystemID: number;
    positionProjectID: number;
    startDate: string;
    tool: string;
    projectID: number;
    listScopeWorkName: string;
    evaluateEmployeeProjectName: string;
    listSkillName: string;
    operationSystemName: string;
    positionProjectName: string;
    databaseName: string;
}
export interface ContractDetail {
    contractID: number;
    contractStartDate: string;
    contractEndDate: string;
    contractTypeID: number;
    positionEmployeeID: number;
    contractAttachments: string;
}
export interface SkillType {
    skillTypeID: number;
    skillTypeName: string;
}
export interface InitEmployee {
    content: {
        listContractType: Contract[];
        listDatabase: Database[];
        listDepartment: Department[];
        listLanguage: Language[];
        listLevelLanguage: LevelLanguage[];
        listLevelSkill: LevelSkill[];
        listNationality: Nationality[];
        listOperationSystem: OperationSystem[];
        listPositionEmployee: PositionEmployee[];
        listPositionProject: PositionProject[];
        listScopeWork: InitScopeWork[];
        listSkill: Skill[];
        listStatusEmployee: StatusEmployee[];
        listEvaluateEmployeeProject: EvaluateEmployeeProject[];
        listSkillType: SkillType[];
        listLanguageCategory: LanguageCategory[];
        listDegree: Degree[];
    };
}

export interface Degree {
    degreeCode: string;
    degreeDescription: string;
    degreeID: number | string;
    degreeName: string;
}
