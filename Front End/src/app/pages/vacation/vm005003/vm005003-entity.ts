export interface TimeKeeping {
    employeeCode: string;
    employeeName: string;
    date: string;
    violation: string;

    checkInAM: string;
    checkOutAM: string;
    checkInPM: string;
    checkOutPM: string;
    comment: string;
    timekeepingID: Number;
    timeKeepingDetailID: Number;
}