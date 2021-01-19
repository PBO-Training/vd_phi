/*export class Type {
    public static typeReceive = 'R';
    public static typeSend = 'S';
    public static typeHistory = 'H';
}*/
export class TimeDefault {
    // AM
    public static startHourAM = 0;
    public static startMinAM = 0;
    public static endHourAM = 0;
    public static endMinAM = 0;

    // PM
    public static startHourPM = 0;
    public static startMinPM = 0;
    public static endHourPM = 23;
    public static endMinPM = 59;

    // Step
    public static stepBreakTime = 0;
    // Break Time
    public static breakTime = 0.0;
    // Work Time
    public static workTime = 0.0;
    // Last Weekend
    public static lastWeekend = 0;
}

export class TimeType {
    // full time
    public static fullTime = '0';
    // haft time
    public static haftTime = '1';
    // special time
    public static specialTime = '2';
}

export class StatusType {
    public static PENDING = 'Pending';
    public static CONFIRMED = 'Confirmed';
    public static APPROVED = 'Approved';
    public static DONE = 'Done';
    public static REJECTED = 'Rejected';
}

export class Hour {
    constructor(key: number, value: string) {
        this.key = key;
        this.value = value;
    }

    key: number;
    value: string;
}

export class Minute {
    constructor(key: number, value: string) {
        this.key = key;
        this.value = value;
    }

    key: number;
    value: string;
}

export class Year {
    constructor(key: number, value: string) {
        this.key = key;
        this.value = value;
    }

    key: number;
    value: string;
}

export class TimeKeeping {
    constructor(employeeCode: string, employeeName: string, date: Date, violation: string,
        checkInAM: string, checkOutAM: string, checkInPM: string, checkOutPM: string,
        times: Number, minusHour: Number, timekeepingID: Number,
        startDate: Date, endDate: Date, isSalary: Boolean) {
        this.employeeCode = employeeCode;
        this.employeeName = employeeName;
        this.date = date;
        this.violation = violation;

        this.checkInAM = checkInAM;
        this.checkOutAM = checkOutAM;
        this.checkInPM = checkInPM;
        this.checkOutPM = checkOutPM;

        this.timekeepingID = timekeepingID;
        this.times = times;
        this.minusHour = minusHour;

        this.startDate = startDate;
        this.endDate = endDate;
        this.isSalary = isSalary;
    }

    employeeCode: string;
    employeeName: string;
    date: Date;
    violation: string;

    checkInAM: string;
    checkOutAM: string;
    checkInPM: string;
    checkOutPM: string;
    timekeepingID: Number;
    times: Number;
    minusHour: Number;

    startDate: Date;
    endDate: Date;
    isSalary: Boolean;
}
export class ExportType {
    public static PROFILE = 'PROFILE';
    public static HISTORY = 'HISTORY';
}

export class ViolationType {
    public static checkInAM = "check-in-am";
    public static checkOutAM = "check-out-am";
    public static checkInPM = "check-in-pm";
    public static checkOutPM = "check-out-pm";
    public static checkInAMMissing = "missing-check-in-am";
    public static checkOutAMMissing = "missing-check-out-am";
    public static checkInPMMissing = "missing-check-in-pm";
    public static checkOutPMMissing = "missing-check-out-pm";
}
export class ActionReviews {
    public static REMOVE = 10;
    public static TEMPORARY = 20;
    public static FINISHED = 30;
}

export class ShiftOption {
    public static SHIFT1 = '1';
    public static SHIFT2 = '2';
}

export class ViolationTracking {
    constructor(title: string, start: Date, end: Date) {
        this.title = title;
        this.start = start;
        this.end = end;        
    }

    title: string;
    start: Date;
    end: Date;   
}