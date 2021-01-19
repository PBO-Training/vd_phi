// Master BEGIN
export class EvaluateProject {
    evaluateProjectID: string;
    evaluateProjectCode: string;
    evaluateProjectName: string;
    evaluateProjectDescription: string;
}

export class LevelProject {
    levelProjectID: string;
    levelProjectCode: string;
    levelProjectName: string;
    levelProjectDescription: string;
}

export class Customer {
    customerID: string;
    customerCode: string;
    customerName: string;
    customerDescription: string;
}

export class Department {
    departmentID: string;
    departmentCode: string;
    departmentName: string;
    departmentDescription: string;
}

export class Employee {
    employeeID: number;
    employeeCode: string;
    employeeName: string;
    dateJoinProject: string;
    dateOutProject: string;
    positionProjectID: number;
}

export class Issue {
    issueID: string;
    issueName: string;
}

// tslint:disable-next-line: class-name
export class listMemberFollowMonth {
    timeline: string;
    totalMember: number;
}

export class StatusProject {
    statusProjectID: string;
    statusProjectCode: string;
    statusProjectName: string;
    statusProjectDescription: string;
}

export class PositionProject {
    positionProjectID: number;
    positionProjectCode: string;
    positionProjectName: string;
    positionProjectDescription: string;
}

export class Project {
    keyResponse: number;
    valueResponse: string;
}

export class StatusIssue {
    keyResponse: number;
    valueResponse: string;
}

export class Tracker {
    keyResponse: number;
    valueResponse: string;
}

export class PriorityIssue {
    keyResponse: number;
    valueResponse: string;
}

export class EvaluateEmployeeProject {
    evaluateEmployeeProjectID: number;
    evaluateEmployeeProjectName: string;
}

// Master END
export interface ServerErrors {
    itemName: string;
    code: string;
}

export class Pm001002Entity {
    projectID: number;
    isDelete: boolean;
    projectCode: string;
    projectName: string;
    projectDescription: string;
    projectEffort: number;
    projectStartDate: Date;
    projectEndDate: Date;
    customerID: number;
    departmentID: number;
    statusProjectID: number;
}

export class Pm001002Response {
    projectID: number;
    isDelete: boolean;
    projectCode: string;
    projectName: string;
    projectDescription: string;
    projectEffort: number;
    projectStartDate: string;
    projectEndDate: string;
    issuePercentDoneAverage: number;
    objectCustomer: Customer;
    objectDepartment: Department;
    objectLevelProject: LevelProject;
    objectStatusProject: StatusProject;
    objectEvaluateProject: EvaluateProject;
    listEmployee: Array<Employee>;
    listIssue: Issue;
    listFollowMonth: listMemberFollowMonth;
}

export class Pm001002DocumentResponse {
    documentID: number;
    documentName: string;
    documentUrl: string;
    documentDescription: string;
}

export class DropDownDataProjectInfo {
    listCustomer: Array<Customer>;
    listDepartment: Array<Department>;
    listEvaluateProject: Array<EvaluateProject>;
    listLevelProject: Array<LevelProject>;
    listPositionProject: Array<PositionProject>;
    listStatusProject: Array<StatusProject>;
}

export class DropDownDataIssue {
    listProject: Array<Project>;
    listStatusIssue: Array<StatusIssue>;
    listTracker: Array<Tracker>;
}

export class DropDownIssueDetail {
    listProject: Array<Project>;
    listStatusIssue: Array<StatusIssue>;
    listTracker: Array<Tracker>;
    listPriorityIssue: Array<PriorityIssue>;
}

export class DropDownDataMember {
    listDepartment: Array<Department>;
    listEvaluateEmployeeProject: Array<EvaluateEmployeeProject>;
    listPositionProject: Array<PositionProject>;
}

export class DocumentResponse {
    documentID: number;
    documentName: string;
    documentUrl: string;
    documentDescription: string;
}
