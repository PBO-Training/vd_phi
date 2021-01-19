// FormSearch
export class ParamEmployee {
  // column 1
  employeeCode: string = null;
  employeeName: string = null;
  listSkillID: any = null;
  startDate: string = null;
  endDate: string = null;
  employeeExperience: string = null;
  // column 2
  departmentID: number = null;
  listProjectID: any = null;
  languageID: any = null;
  genderID: number = null;
  contractID: number = null;
  // Page
  pageNum: number = null;
  pageSize: number = null;
}
// Employee List
export interface Employee {
  employeeID: number;
  employeeName: string;
  employeeCode: number | string;
  employeeContract: any;
  employeePhone: string;
  employeeSkype: string;
  employeeDepartment: any; //  Name Department
  listLanguage: any; // Exp
  listSkill: any;
  listProject: any;
}

export class EmployeeToExportHistory {
  employeeFileName: string;
  employeeName: string;
  employeeBirthdate: Date;
  employeeDegree: string;
  employeeGender: boolean;
  employeeDateJoinCompany: Date;
  employeeUniversity: string;
  listHistory: EmpHistory[];
}

export class EmpHistory {
  description: string;
  startDate: Date;
  endDate: Date;
  listScopeWork: ScopeWork[];
  employeePositionProjectCode: string;
  osName: string;
  listSkillName: string;
  databaseName: string;
  tools: string;
}

export class ScopeWork {
  scopeWorkName: string;
}

// Employee Skill List
export interface EmployeeSkill {
  skillID: number;
  skillName: string;
  levelSkillID: number;
  levelSkillName: string;
}
// Employee Project List
export interface EmployeeProject {
  skillID: number;
  skillName: string;
  levelSkillID: number;
  levelSkillName: string;
}
export interface Skills {
  skillID: number;
  levelSkillID: number;
  experienceSkillID: number;
}
export interface RequestLanguage {
  languageID: number;
  levellanguageID: number;
  languagePoints: number;
  expirationDate: string;
}
export class EmployeeToExportProfile {
  employeeCode: string;
  fullName: string;
  biography: Date;
  birthday: Date;
  gender: string;
  email: string;
  phone: string;
  address: string;
  identityCard: string;
  graduateYear: number;
  university: string;
  skype: string;
  dateJoinCompany: Date;
  numOfChild: number;
  emergency: string;
  dateOfIssueOfIDCard: Date;
  description: string;
  married: string;
  departmentName: string;
  statusEmployeeName: string;
  positionEmployeeName: string;
  nationalityName: string;
  degreeName: string;
  listEmpSkillName: string;
  listEmpLanguageName: string;
  listEmpProjectName: string;
  contractTypeName: string;
}

export class ExportProfile {
  listHeaders: string[];
  profile: EmployeeToExportProfile[];
}

export class SkillExperience {
  skillID: number;
  skillExperience: number;
}
