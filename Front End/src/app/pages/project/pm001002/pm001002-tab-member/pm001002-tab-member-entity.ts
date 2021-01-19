export class Member {
    projectID: number;
    employeeID: number;
    employeeCode: string;
    employeeName: string;
    dateJoinProject: string;
    dateOutProject: string;
    positionProjectID: number;
}
export class RemoveMemberRequest {
    projectID: number;
    action: number;
    listEmployee: any[];
}
