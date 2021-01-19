export interface Vacation extends StatusVacation, OptionVacation, TypeVacation {
    vacationID :number | string;
    employeeId : number | string;
    employeeName: string;
    departmentName : string;
    departmentId: number | string;
    title: string;
    startDate: string;
    endDate: string;
    approve: string;

}
export interface StatusVacation {
    status: string
}

export interface OptionVacation {
    option: string
}

export interface TypeVacation {
    type: string
}

export class VacationToExport {
    vacationID :number | string;
    employeeId : number | string;
    employeeName: string;
    departmentName : string;
    departmentId: number | string;
    title: string;
    startDate: string;
    endDate: string;
    approve: string;
    status: string
}

export interface ServerErrors {
    itemName: string;
    code: string;
}