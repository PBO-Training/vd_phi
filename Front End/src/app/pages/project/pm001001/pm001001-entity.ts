export interface ProjectsSearch {
    pageNum?: number;
    pageSize?: number;
    projectName?: string;
    projectCode?: number;
    starDate?: Date;
    endDate?: Date;
    level?: {};
    status?: {};
}
export interface Project {
    projectID: number;
    projectCode: string;
    projectName: string;
    projectDescription: string;
    projectStartDate: Date;
    projectEndDate: Date;
    issueAmountDifferenceAverage: number;
    issueQualityDifferenceAverage: number;
    issuePercentDoneAverage: number;
    levelProject: Level;
    statusProject: Status;
    evaluateProject: EvaluateProject;
    customer: Customer;
    department: Department;
}
export interface Status {
    statusProjectID: number;
    statusProjectName: string;
}
export interface Customer {
    customerID: number;
    customerName: string;
}
export interface Department {
    departmentID: number;
    departmentName: string;
}
export interface EvaluateProject {
    evaluateProjectID: number;
    evaluateProjectName: string;
}
export interface Level {
    levelProjectID: number;
    levelProjectName: string;
}
