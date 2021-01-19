export class Pm002001SearchRequest {
    pageNum: number;
    pageSize: number;
    trackerID: number;
    statusIssueID: number;
    projectID: number;
}

export class Issue {
    issueID: number;
    issueName: string;
    issueSubject: string;
    issueDescription: string;
    issueStartDatePlan: Date;
    issueEndDatePlan: Date;
    issueAmountPlan: number;
    issueQualityPlan: number;
    issueStartDateActual: Date;
    issueEndDateActual: Date;
    issueAmountActual: number;
    issueQualityActual: number;
    issueAmountDifference: number;
    issueQualityDifference: number;
    issueStartDateDifference: number;
    issueEndDateDifference: number;
    project: any;
    tracker: any;
    statusIssue: any;
}
