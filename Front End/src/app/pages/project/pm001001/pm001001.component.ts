import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Title } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { TranslateService } from '@ngx-translate/core';
import { Subject } from 'rxjs';
import { mergeMap, takeUntil } from 'rxjs/operators';
import { CommonFunctionService } from '../../../services/common-function/common-function.service';
import { TranslationService } from '../../../services/translate/translation.service';
import { DateConfig, parseDate } from '../../../common/datepicker-config/datepicker-config';
import { Listprops } from '../../../common/page-options';
import { ScreenAction } from '../../../common/screen-action/screen-action';
import { DateValidator } from '../../../common/validator/date-validator';
import { ExportExcelService } from '../../../services/export-excel/export-excel.service';
import { StorageService } from '../../../services/storage/storage.service';
import { ModalConfirmComponent } from '../../../theme/shared/components/modal-confirm/modal-confirm.component';
import { ToastService } from '../../../theme/shared/components/toast-container/toast-service';
import { compare, SortEvent } from '../../../theme/shared/directives/sort-table-header.directive';
import { Project } from './pm001001-entity';
import { Pm001001SearchService } from './pm001001.service';


@Component({
  selector: 'app-list',
  templateUrl: './pm001001.component.html',
  styleUrls: ['./pm001001.component.scss']
})
export class Pm001001SearchComponent implements OnInit {
  screenProps = new Listprops();
  config = new DateConfig();
  projectList: Project[];
  private unsubscribe$ = new Subject();
  formSearch = new FormGroup({});
  direction: string; // using sort
  column: string; // using sort
  checked: boolean;
  indeterminate = false;
  status: any[] = [];
  levels: any[] = [];
  evaluates: any[] = [];
  departments: any[] = [];
  customers: any[] = [];
  dateDiffInvalid: boolean;
  startDateInvalid: boolean;
  endDateInvalid: boolean;
  authButton = new ScreenAction();
  valueOld = {
    projectName: '',
    projectCode: '',
    projectStartDateFrom: '',
    projectStartDateTo: '',
    statusProjectID: -1,
    levelProjectID: -1,
    evaluateProjectID: -1,
    departmentID: -1,
    customerID: -1,
    pageNum: 0,
    pageSize: 30,
  };
  baseForm: FormGroup;

  constructor(
    private projectsService: Pm001001SearchService,
    private modalService: NgbModal,
    private fb: FormBuilder,
    // import toast in common components toast service
    public toastService: ToastService,
    private titleService: Title,
    private translateService: TranslateService,
    private exportExcelService: ExportExcelService,
    private router: Router,
    private commonFunctionService: CommonFunctionService,
    private storegeService: StorageService,
    public translationService: TranslationService,
  ) {
    this.authButton = this.commonFunctionService.initAuthAction(this.authButton);
    this.baseForm = this.fb.group({
      projectName: [null],
      projectCode: [null],
      projectStartDateFrom: [null],
      projectStartDateTo: [null],
      statusProjectID: ['-1'],
      levelProjectID: ['-1'],
      evaluateProjectID: ['-1'],
      departmentID: ['-1'],
      customerID: ['-1'],
      pageNum: ['0'],
      pageSize: [this.storegeService.getPageSize() !== null ? this.storegeService.getPageSize() : 30],
    });
  }
  get f() { return this.formSearch.controls; }

  ngOnInit(): void {
    // change title
    this.translateService.get('title.project.project').subscribe((title: string) => {
      this.titleService.setTitle(title);
    });
    // change title when change language
    this.translationService.changeTranslatetion().pipe(takeUntil(this.unsubscribe$),
      mergeMap(lang => this.translateService.get('title.project.project'))
    ).subscribe((title: string) => {
      this.titleService.setTitle(title);
    });

    this.titleService.setTitle('Project manager - Project');
    this.screenProps.loading = true;
    this.initForm();
    this.projectsService.pm0010001Init().subscribe(
      data => {
        if (data.content) {
          this.levels = data.content.listLevelProject;
          this.departments = data.content.listDepartment;
          this.status = data.content.listStatusProject;
          this.evaluates = data.content.listEvaluateProject;
          this.customers = data.content.listCustomer;
        }
      }
    );
    this.initData(this.baseForm);
  }

  initForm(): void {
    this.formSearch = this.fb.group({
      projectName: [null],
      projectCode: [null],
      projectStartDateFrom: [null],
      projectStartDateTo: [null],
      statusProjectID: ['-1', Validators.min(-1)],
      levelProjectID: ['-1', Validators.min(-1)],
      evaluateProjectID: ['-1', Validators.min(-1)],
      departmentID: ['-1', Validators.min(-1)],
      customerID: ['-1', Validators.min(-1)],
      pageNum: ['0'],
      pageSize: [this.storegeService.getPageSize() !== null ? this.storegeService.getPageSize() : 30],
    }, { validator: Validators.compose([DateValidator.dateLessThan('projectStartDateFrom', 'projectStartDateTo')]) });
  }

  // tslint:disable-next-line: use-lifecycle-interface
  ngOnDestroy(): void {
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
  }
  defaultSort(list: Project[]) {
    list = list.sort((a, b) => {
      return a.projectCode > b.projectCode ? 1 : -1;
    });
  }
  resetSort() {
    // reset sort
    this.column = 'projectCode';
    this.direction = 'asc';
  }
  onSort({ column, direction }: SortEvent) {
    // resetting other headers
    this.direction = direction;
    this.column = column;
    this.projectList = [...this.projectList].sort((a, b) => {
      let res: number;
      let x: string;
      let y: string;
      switch (column) {
        case 'projectCode':
          res = compare(a.projectCode, b.projectCode);
          return direction === 'asc' ? res : -res;
        case 'projectName':
          res = a.projectName.localeCompare(b.projectName);
          return direction === 'asc' ? res : -res;
        case 'issueAmountDifferenceAverage':
          res = this.compareNumber(a.issueAmountDifferenceAverage, b.issueAmountDifferenceAverage);
          return direction === 'asc' ? res : -res;
        case 'issueQualityDifferenceAverage':
          res = this.compareNumber(a.issueQualityDifferenceAverage, b.issueQualityDifferenceAverage);
          return direction === 'asc' ? res : -res;
        case 'issuePercentDoneAverage':
          res = this.compareNumber(a.issuePercentDoneAverage, b.issuePercentDoneAverage);
          return direction === 'asc' ? res : -res;
        case 'projectLevel':
          x = a.levelProject == null ? '' : a.levelProject.levelProjectName;
          y = b.levelProject == null ? '' : b.levelProject.levelProjectName;
          res = x.localeCompare(y);
          return direction === 'asc' ? res : -res;
        case 'projectStatus':
          x = a.statusProject == null ? '' : a.statusProject.statusProjectName;
          y = b.statusProject == null ? '' : b.statusProject.statusProjectName;
          res = x.localeCompare(y);
          return direction === 'asc' ? res : -res;
        case 'projectDepartment':
          x = a.department == null ? '' : a.department.departmentName;
          y = b.department == null ? '' : b.department.departmentName;
          res = x.localeCompare(y);
          return direction === 'asc' ? res : -res;
        case 'projectCustomer':
          x = a.customer == null ? '' : a.customer.customerName;
          y = b.customer == null ? '' : b.customer.customerName;
          res = x.localeCompare(y);
          return direction === 'asc' ? res : -res;
        case 'projectStartDate':
          res = this.compareDate(a.projectStartDate, b.projectStartDate);
          return direction === 'asc' ? res : -res;
        case 'projectEndDate':
          res = this.compareDate(a.projectEndDate, b.projectEndDate);
          return direction === 'asc' ? res : -res;
      }
    });
  }

  public compareDate(date1: Date, date2: Date): number {
    const d1 = new Date(date1);
    const d2 = new Date(date2);
    // Check if the dates are equal
    const same = d1.getTime() === d2.getTime();
    if (same) { return 0; }
    // Check if the first is greater than second
    if (d1 > d2) { return 1; }
    // Check if the first is less than second
    if (d1 < d2) { return -1; }
  }

  public compareNumber(num1: number, num2: number): number {
    const same = num1 === num2;
    if (same) { return 0; }
    // Check if the first is greater than second
    if (num1 > num2) { return 1; }
    // Check if the first is less than second
    if (num1 < num2) { return -1; }
  }

  resetForm() {
    this.dateDiffInvalid = false;
    this.startDateInvalid = false;
    this.endDateInvalid = false;
    this.screenProps.page = 1;
    this.initForm();
    this.baseForm = this.fb.group({
      projectName: [null],
      projectCode: [null],
      projectStartDateFrom: [null],
      projectStartDateTo: [null],
      statusProjectID: ['-1'],
      levelProjectID: ['-1'],
      evaluateProjectID: ['-1'],
      departmentID: ['-1'],
      customerID: ['-1'],
      pageNum: ['0'],
      pageSize: [this.storegeService.getPageSize() !== null ? this.storegeService.getPageSize() : 30],
    });
    this.initData(this.baseForm);
  }

  private initData(formSearch: FormGroup): void {
    this.screenProps.loading = true;
    // this.formSearch.value.pageSize = this.storegeService.getPageSize() === null ? 30 : this.storegeService.getPageSize();
    this.resetSort();
    const formData = {
      ...formSearch.value,
      projectStartDateFrom: parseDate(formSearch.value.projectStartDateFrom),
      projectStartDateTo: parseDate(formSearch.value.projectStartDateTo),
    };
    this.projectsService.pm0010001GetList(formData).pipe(takeUntil(this.unsubscribe$)).subscribe(resp => {
      if (resp.content.data !== undefined && resp.content.data !== null && resp.content.data.length >= 0) {
        // tells Angular that you are now ready for it to run change detection.
        // this.changeDetector.detectChanges();
        this.projectList = resp.content.data;
        this.defaultSort(this.projectList);
        this.resetCheckedSet();
        this.screenProps.pageNum = resp.content.pageNum ? resp.content.pageNum : 0;
        this.screenProps.pageSize = resp.content.pageSize ? resp.content.pageSize : 30;
        this.screenProps.totalRecord = resp.content.totalElements;
        this.screenProps.loading = false;
      }
    },
      err => {
        const httpErr = err.error;
        if (!httpErr) {
          return;
        }

        if (httpErr.error.code === 'B01001002A004') {
          if (httpErr.error.itemName === 'startDate, endDate') {
            this.dateDiffInvalid = true;
            return;
          }

          this.dateDiffInvalid = false;
          if (httpErr.error.itemName === 'startDate') {
            this.startDateInvalid = true;
            return;
          }
          this.startDateInvalid = false;
          if (httpErr.error.itemName === 'endDate') {
            this.endDateInvalid = true;
            return;
          }
          this.endDateInvalid = false;
        }
      });

  }

  searchProject() {
    if (this.formSearch.valid) {
      this.screenProps.loading = true;
      this.formSearch.value.pageNum = 0;
      this.screenProps.page = 1;
      this.formSearch.value.projectStartDateFrom = this.formSearch.value.projectStartDateFrom;
      this.formSearch.value.projectStartDateTo = this.formSearch.value.projectStartDateTo;
      this.valueOld = JSON.parse(JSON.stringify(this.formSearch.value));
      this.baseForm.value.projectName = this.valueOld.projectName;
      this.baseForm.value.projectCode = this.valueOld.projectCode;
      this.baseForm.value.projectStartDateFrom = this.valueOld.projectStartDateFrom;
      this.baseForm.value.projectStartDateTo = this.valueOld.projectStartDateTo;
      this.baseForm.value.statusProjectID = this.valueOld.statusProjectID;
      this.baseForm.value.levelProjectID = this.valueOld.levelProjectID;
      this.baseForm.value.evaluateProjectID = this.valueOld.evaluateProjectID;
      this.baseForm.value.departmentID = this.valueOld.departmentID;
      this.baseForm.value.customerID = this.valueOld.customerID;
      this.baseForm.value.pageNum = this.valueOld.pageNum;
      this.baseForm.value.pageSize = this.valueOld.pageSize;
      this.initData(this.baseForm);
    }
  }

  changePageSize(pageSize: number) {
    this.resetCheckedSet();
    this.screenProps.pageSize = pageSize;
    this.formSearch.value.pageSize = pageSize;
    this.baseForm.value.pageSize = pageSize;
    this.storegeService.savePageSizeLocal(this.screenProps.pageSize);
    this.screenProps.ids = [];
    this.initData(this.baseForm);
  }

  pageChangeOutput(currentPage: number) {
    this.resetCheckedSet();
    this.formSearch.value.pageNum = currentPage - 1;
    this.formSearch.value.pageSize = this.screenProps.pageSize;
    this.baseForm.value.pageNum = currentPage - 1;
    this.baseForm.value.pageSize = this.screenProps.pageSize;
    this.screenProps.ids = [];
    this.initData(this.baseForm);
  }

  resetCheckedSet(): void {
    this.checked = false;
    this.screenProps.setOfCheckedId.clear();
    this.refreshCheckedStatus();
  }

  onAllChecked(event) {
    this.projectList.forEach(item => this.updateCheckedSet(item.projectID, event.target.checked));
    this.refreshCheckedStatus();
  }

  onItemChecked(id: number, event): void {
    this.updateCheckedSet(id, event.target.checked);
    this.refreshCheckedStatus();
  }

  refreshCheckedStatus(): void {
    this.checked = this.projectList.every(item => this.screenProps.setOfCheckedId.has(item.projectID));
    this.indeterminate = this.projectList.some(item => this.screenProps.setOfCheckedId.has(item.projectID)) && !this.checked;
  }

  updateCheckedSet(id: number, checked: boolean): void {
    if (checked) {
      this.screenProps.setOfCheckedId.add(id);
    } else {
      this.screenProps.setOfCheckedId.delete(id);
    }
  }

  deleteProject() {
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
  // open popup confirm
  openPopup(ids: number[]) {
    // NgbModal bootstrap using abtract ViewContainerRef of angular create dynamic components, https://angular.io/api/core/ViewContainerRef;
    const modalRef = this.modalService.open(ModalConfirmComponent);
    // COMUNICATION WITH DYNAMIC COMPONENTS USING @Input() or @Ouput() with componentInstance
    this.translateService.get('confirm-message.delete').subscribe(
      (text: string) => {
        modalRef.componentInstance.title = text;
        modalRef.result.then(result => {
          if (result === 'delete') {
            this.projectsService.pm0010001Delete(ids).pipe(takeUntil(this.unsubscribe$)).subscribe(resp => {
              if (resp.error === null) {
                // show toast if delete success
                this.translateService.get('notification-message.delete-success').subscribe((message: string) => {
                  this.toastService.show(message, { classname: 'bg-success text-light', delay: 3000 });
                });
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
                this.initData(this.baseForm);
              } else {
                // show toast if delete fail
                this.translateService.get('notification-message.delete-fail').subscribe((message: string) => {
                  this.toastService.show(message, { classname: 'bg-danger text-light', delay: 3000 });
                });
              }
            });
          }
        }, reason => {
        });
      }
    );
  }

  checkAll(event) {
    this.projectList.forEach(item => {
      this.updateCheckedSet(+item.projectID, event.target.checked);
    });
  }

  generateExcel() {
    const header = ['No.', 'Project Code', 'Project Name', 'Level', 'Status', 'Department', 'Customer'
      , 'Start Date', 'End Date', 'Description'];
    const footer = ['Human Resource - Brycen Vietnam company'];
    const listToExport: any[] = [];
    this.projectList.forEach(e => {
      const row: any[] = [];
      row.push(e.projectCode);
      row.push(e.projectName);
      row.push(e.levelProject?.levelProjectName);
      row.push(e.statusProject?.statusProjectName);
      row.push(e.department?.departmentName);
      row.push(e.customer?.customerName);
      row.push(e.projectStartDate);
      row.push(e.projectEndDate);
      row.push(e.projectDescription);
      listToExport.push(row);
    });
    this.exportExcelService.generateExcel('Project', listToExport, header, footer);
  }
}
