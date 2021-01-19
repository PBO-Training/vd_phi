export interface Login {
    userID: number;
    username: string;
    tokenType: string;
    accessToken: string;
    employeeID: number;
}

export interface Screen {
    access: boolean;
    screenID: number;
    screenName: string;
    screenUrl: string;
    screenLevel: number;
    groupScreenID: number;
    groupScreenName: string;
    groupIcon: string;
    groupIndex: number;
}

export interface User {
    username: string;
    employeeID: number;
    employeeName: string;
    avataURL: string;
    companyCode: string;
    companyName: string;
}
