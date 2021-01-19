export interface TimeKeepingDetail {
    employeeCode: string;
    employeeName: string;
    departmentName: string;
    first_name: string;
    last_name: string;
    date: string;
    violation: string;
    startTime: number | string;
    endTime: number | string;   
    timekeepingID: Number;
    timeKeepingDetailID: Number;
    check_in: number | string;
    check_out: number | string;
    missing_check_in: number | string;
    missing_check_out: number | string;
    salary_deduction: number | string;
    un_salary_deduction: number | string;
}