import { Component, EventEmitter, Input, OnInit, Output } from "@angular/core";
import { FormGroup } from "@angular/forms";
import { ActivatedRoute } from "@angular/router";
import { Pm001002StoreSerivce } from '../pm001002-store.service';
import { Pm001002Service } from "../pm001002.service";
import { DetailMembersRequest} from "./pm001002-tab-member-detail-entity";
import { formatDate} from '../../../../common/datepicker-config/datepicker-config';

@Component({
    selector: 'app-member-detail',
    templateUrl: './pm001002-tab-member-detail.component.html'
})
export class Pm001002MemberDetailComponent implements OnInit {
    @Input() employeeID: number;
    @Output() detailIssue = new EventEmitter<any>();
    //  employeeID: number;
    requestData: DetailMembersRequest;
    data: any = [];
    email: string;
    dateJoinProject: string;
    emlpoyeeName: string;
    assignedIssue: number;
    closedIssue: number;
    count: number;
    formDetails: FormGroup;
    employeeName: string;
    historyIssueUpdate: any[];
    listIssue: any[];
    issueDate: any = [];
    constructor(
        private pm001002GlobalStore: Pm001002StoreSerivce,
        private route: ActivatedRoute,
        private pm001002Service: Pm001002Service
    ) { }
    ngOnInit(): void {
        this.initData();
    }

    initData() {

        this.requestData = {
            employeeID: this.employeeID,
            projectID: this.route.snapshot.params.id
        };
        this.pm001002Service.pm001002GetDetailMember(this.requestData).subscribe(
            (res: any) => {
                this.emlpoyeeName = res.content.memberDetail.memberName;
                this.email = res.content.memberDetail.email;
                this.dateJoinProject = formatDate(res.content.memberDetail.datejoinProject.split('T')[0]);
                this.listIssue = res.content?.issueDetail;
                this.assignedIssue = this.listIssue?.length;
                this.count = 0;
                for (let i = 0; i < this.listIssue?.length; i++) {
                    if (this.listIssue[i].statusIssue.statusIssueName === 'Close') {
                        this.count = this.count + 1;
                    }
                    this.historyIssueUpdate = this.listIssue[i].historyIssueUpdate;               
                    for (let j = 0; j < this.historyIssueUpdate?.length; j++) {
                        this.historyIssueUpdate[j].historyUpdateIssueDate = formatDate(this.historyIssueUpdate[j].historyUpdateIssueDate.split('T')[0]);
                        if (this.issueDate.indexOf(this.historyIssueUpdate[j].historyUpdateIssueDate) === -1) {
                            this.issueDate.push(this.historyIssueUpdate[j].historyUpdateIssueDate);
                        }
                    }
                }
                this.closedIssue = this.count;
            }
        )
    }

    onBack(isShowDetailMember: any): void {
        this.pm001002GlobalStore.onShowDetailMember({ isShowDetailMember });
    }

    navigateIssueDetail(issueID: number) : void {
        this.detailIssue.emit(issueID);
      }
    
}