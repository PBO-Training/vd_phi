export interface Vacation {
    /*vacationID: number | string;
    departmentId: number | string;
    title: string;
    startDate: string;
    endDate: string;
    approve: string;
    leaveHour: number;
    status: string;
    type: string;
    option: string;
    vacationTypeName: string;
    vacationOptionName: string;
    statusName: string;
    vacationReason: string;*/
    employeeName: string;
    employeeCode: string;
    departmentName: string;
    totalVacation: number;
    totalLeave: number;
    bonusVacation: number;
    remainVacation: number;
    firstName: string;
    lastName: string;
    employeeID: string;
    Year: number | string;
    Bonus: number | string;
    salaryDeduction: number | string;
    unsalaryDeduction: number | string;
}