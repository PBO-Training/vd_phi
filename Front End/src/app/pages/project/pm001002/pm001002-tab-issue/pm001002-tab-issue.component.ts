import { Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { TranslateService } from '@ngx-translate/core';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { Listprops } from '../../../../common/page-options';
import { ScreenAction } from '../../../../common/screen-action/screen-action';
import { CommonFunctionService } from '../../../../services/common-function/common-function.service';
import { ExportExcelService } from '../../../../services/export-excel/export-excel.service';
import { StorageService } from '../../../../services/storage/storage.service';
import { ModalConfirmComponent } from '../../../../theme/shared/components/modal-confirm/modal-confirm.component';
import { ToastService } from '../../../../theme/shared/components/toast-container/toast-service';
import { DropDownDataIssue } from '../pm001002-entity';
import { Pm001002Service } from '../pm001002.service';
import { Issue } from './pm001002-tab-issue-entity';

@Component({
  selector: 'app-list-issue',
  templateUrl: './pm001002-tab-issue.component.html',
  styleUrls: ['./pm001002-tab-issue.component.scss']
})
export class Pm001002SearchIssueComponent implements OnInit, OnChanges {
  @Input() dropdownData: DropDownDataIssue;
  @Output() navigateToDetail = new EventEmitter<any>();
  @Output() reGetProjectDetail = new EventEmitter<boolean>();
  screenProps = new Listprops();
  formSearch = new FormGroup({});
  submitted = false;
  authButton = new ScreenAction();
  issueList: Issue[];
  checked: boolean;
  indeterminate = false;
  private unsubscribe$ = new Subject();
  isShowProject = this.route.snapshot.params.id !== undefined ? false : true;
  projectID = this.isShowProject !== false ? '-1' : this.route.snapshot.params.id;
  valueOld = {
    pageNum: '0',
    pageSize: [this.storegeService.getPageSize() !== null ? this.storegeService.getPageSize() : 30],
    trackerID: '-1',
    employeeID: '-1',
    statusIssueID: '-1',
    issueID: '-1',
    projectID: this.projectID,
  };
  baseForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private modalService: NgbModal,
    public toastService: ToastService,
    private storegeService: StorageService,
    private projectService: Pm001002Service,
    private translateService: TranslateService,
    private exportExcelService: ExportExcelService,
    private commonFunctionService: CommonFunctionService,
  ) {
    this.authButton = this.commonFunctionService.initAuthAction(this.authButton);
    this.baseForm = this.fb.group({
      pageNum: ['0'],
      pageSize: [this.storegeService.getPageSize() !== null ? this.storegeService.getPageSize() : 30],
      trackerID: ['-1'],
      employeeID: ['-1'],
      statusIssueID: ['-1'],
      issueID: ['-1'],
      projectID: [this.projectID],
    });
  }

  ngOnChanges(changes: SimpleChanges): void {
  }

  ngOnInit(): void {
    this.initForm();
    this.initData(this.formSearch);
  }

  /*
  * Form
  */
  get formControl() {
    return this.formSearch.controls;
  }

  initForm(): void {
    this.formSearch = this.fb.group({
      pageNum: ['0'],
      pageSize: [this.storegeService.getPageSize() !== null ? this.storegeService.getPageSize() : 30],
      trackerID: ['-1', Validators.min(-1)],
      employeeID: ['-1', Validators.min(-1)],
      statusIssueID: ['-1', Validators.min(-1)],
      issueID: ['-1', Validators.min(-1)],
      projectID: [this.projectID, Validators.min(-1)],
    });
  }

  initData(formSearch: FormGroup): void {
    this.screenProps.loading = true;
    this.projectService.pm001002SearchIssue(formSearch.value).subscribe(
      (listIssue: any) => {
        this.issueList = listIssue.content.data;
        this.resetCheckedSet();
        this.screenProps.pageNum = listIssue.content.pageNum ? listIssue.content.pageNum - 1 : 0;
        this.screenProps.pageSize = listIssue.content.pageSize ? listIssue.content.pageSize : 30;
        this.screenProps.totalRecord = listIssue.content.totalElements;
        this.screenProps.loading = false;
      }
    );
  }

  /*
  * Actions
  */

  resetForm() {
    this.baseForm = this.fb.group({
      pageNum: ['0'],
      pageSize: [this.storegeService.getPageSize() !== null ? this.storegeService.getPageSize() : 30],
      trackerID: ['-1'],
      employeeID: ['-1'],
      statusIssueID: ['-1'],
      issueID: ['-1'],
      projectID: [this.projectID],
    });
    this.initForm();
    this.initData(this.baseForm);
    this.screenProps.page = 1;
  }

  // Event click button create
  navigateDetailIssue = (value: boolean, issueID: number) => {
    this.navigateToDetail.emit({ value, issueID });
  }

  // Event click button search
  searchIssue() {
    this.screenProps.loading = true;
    this.screenProps.page = 1;
    this.submitted = true;
    this.formSearch.value.pageNum = 1;
    this.formSearch.value.pageSize = this.storegeService.getPageSize() !== null ? this.storegeService.getPageSize() : 30;
    this.valueOld = JSON.parse(JSON.stringify(this.formSearch.value));
    this.baseForm.value.pageNum = this.valueOld.pageNum;
    this.baseForm.value.pageSize = this.valueOld.pageSize;
    this.baseForm.value.trackerID = this.valueOld.trackerID;
    this.baseForm.value.employeeID = this.valueOld.employeeID;
    this.baseForm.value.statusIssueID = this.valueOld.statusIssueID;
    this.baseForm.value.issueID = this.valueOld.issueID;
    this.baseForm.value.projectID = this.valueOld.projectID;
    this.initData(this.baseForm);
  }

  // Event click button delete
  deleteIssue() {
    if (this.screenProps.setOfCheckedId.size > 0) {
      this.screenProps.setOfCheckedId.forEach(x => {
        this.screenProps.ids.push(x);
      });
      this.openPopup(this.screenProps.ids);
    } else {
      this.translateService.get('notification-message.unchecked').subscribe((text: string) => {
        this.toastService.show(text, { classname: 'bg-warning text-light', delay: 3000 });
      });
    }
  }

  // Process to show popup
  openPopup(ids: number[]) {
    // NgbModal bootstrap using abtract ViewContainerRef of angular create dynamic components, https://angular.io/api/core/ViewContainerRef;
    const modalRef = this.modalService.open(ModalConfirmComponent);
    // COMUNICATION WITH DYNAMIC COMPONENTS USING @Input() or @Ouput() with componentInstance
    this.translateService.get('confirm-message.delete').subscribe(
      (text: string) => {
        modalRef.componentInstance.title = text;
        modalRef.result.then(result => {
          if (result === 'delete') {
            this.projectService.pm001002DeleteIssue(ids).pipe(takeUntil(this.unsubscribe$)).subscribe(
              (res: any) => {
                if (res.error === null) {
                  // tslint:disable-next-line: max-line-length
                  const pageEnd = Math.floor(this.screenProps.totalRecord / this.screenProps.pageSize) + ((this.screenProps.totalRecord % this.screenProps.pageSize) === 0 ? 0 : 1);
                  if (this.screenProps.page === pageEnd) {
                    if (this.screenProps.ids.length === this.screenProps.pageSize ||
                      this.screenProps.ids.length === (this.screenProps.totalRecord % this.screenProps.pageSize)) {
                      this.screenProps.pageNum = this.screenProps.pageNum - 1;
                      this.formSearch.value.pageNum = this.formSearch.value.pageNum - 1;
                    }
                  }
                  this.screenProps.ids = [];
                  this.screenProps.setOfCheckedId.clear();
                  this.initData(this.formSearch);
                  this.toastService.show('notification-message.delete-success', { classname: 'bg-success text-light', delay: 3000 });
                  this.reGetProjectDetail.emit(true);
                } else {
                  // show toast if delete fail
                  this.translateService.get('notification-message.delete-fail').subscribe((message: string) => {
                    this.toastService.show(message, { classname: 'bg-danger text-light', delay: 3000 });
                  });
                }
              }
            );
          }
        }, reason => {
        });
      }
    );
  }

  refreshCheckedStatus(): void {
    this.checked = this.issueList.every(item => this.screenProps.setOfCheckedId.has(item.issueID));
    this.indeterminate = this.issueList.some(item => this.screenProps.setOfCheckedId.has(item.issueID)) && !this.checked;
  }

  resetCheckedSet(): void {
    this.checked = false;
    this.screenProps.setOfCheckedId.clear();
    this.refreshCheckedStatus();
  }

  // Process to check or uncheck
  updateCheckedSet(id: number, checked: boolean): void {
    if (checked) {
      this.screenProps.setOfCheckedId.add(id);
    } else {
      this.screenProps.setOfCheckedId.delete(id);
    }
  }

  // Event click on checkbox
  onCheckedItem(id: number, event): void {
    this.updateCheckedSet(id, event.target.checked);
  }

  // Event click on checkbox check all
  onCheckedAll(event) {
    this.issueList.forEach(item => this.updateCheckedSet(item.issueID, event.target.checked));
    this.refreshCheckedStatus();
  }

  // Event click on change page size
  changePageSize(pageSize: number) {
    this.resetCheckedSet();
    this.screenProps.pageSize = pageSize;
    this.formSearch.value.pageSize = pageSize;
    this.baseForm.value.pageSize = pageSize;
    this.storegeService.savePageSizeLocal(this.screenProps.pageSize);
    this.screenProps.ids = [];
    this.initData(this.baseForm);
  }

  // Event click on change page number
  pageChangeOutput(currentPage: number) {
    this.resetCheckedSet();
    this.formSearch.value.pageNum = currentPage;
    this.baseForm.value.pageNum = currentPage;
    this.screenProps.pageNum = currentPage - 1;
    this.formSearch.value.pageSize = this.screenProps.pageSize;
    this.baseForm.value.pageSize = this.screenProps.pageSize;
    this.screenProps.ids = [];
    this.initData(this.baseForm);
  }

  // Generate excel from database
  generateExcel() {
    const header = ['No.', 'Issue Name', 'Issue Subject', 'Issue Description', 'Start Date Plan', 'End Date Plan', 'Amount Plan'
      , 'Quality Plan', 'Start Date Actual', 'End Date Actual', 'Amount Actual', 'Quality Actual', 'Project', 'Tracker', 'Status'];
    const footer = ['Human Resource - Brycen Vietnam company'];
    const listToExport: any[] = [];
    this.issueList.forEach(e => {
      const row: any[] = [];
      row.push(e.issueName);
      row.push(e.issueSubject);
      row.push(e.issueDescription);
      row.push(e.issueStartDatePlan);
      row.push(e.issueEndDatePlan);
      row.push(e.issueAmountPlan);
      row.push(e.issueQualityPlan);
      row.push(e.issueStartDateActual);
      row.push(e.issueEndDateActual);
      row.push(e.issueAmountActual);
      row.push(e.issueQualityActual);
      row.push(e.project?.projectName);
      row.push(e.tracker?.trackerName);
      row.push(e.statusIssue?.statusIssueName);
      listToExport.push(row);
    });
    this.exportExcelService.generateExcel('Issue', listToExport, header, footer);
  }
}
