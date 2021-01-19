export class DeleteMember {
    employeeID: number;
    positionProjectID: number;
    evaluateEmployeeProjectID: number;
    dateJoinProject: string;
    dateOutProject: string;
    note: string;
}

export class DeleteRequest {
    projectID: number;
    action: number;
    listEmployee: DeleteMember[];
}

export class Member {
    projectID: number;
    employeeID: number;
    employeeCode: string;
    employeeName: string;
    dateJoinProject: string;
    dateOutProject: string;
    positionProjectID: number;
}
