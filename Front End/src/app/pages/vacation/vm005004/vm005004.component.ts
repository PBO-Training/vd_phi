import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Subject } from 'rxjs';
import { mergeMap, takeUntil } from 'rxjs/operators';
import * as type from '../../../common/constant/type';
import { DateConfig } from '../../../common/datepicker-config/datepicker-config';
import { Listprops } from '../../../common/page-options';
import { StorageService } from '../../../services/storage/storage.service';
import { TranslationService } from '../../../services/translate/translation.service';
import { compare, SortEvent } from '../../../theme/shared/directives/sort-table-header.directive';
import { TimeKeepingDetail } from './vm005004-entity';
import { VM005004Service } from './vm005004.service';
import {
  ModalNotificationMaintenanceComponent
} from '../../../theme/shared/components/modal-notification-maintenance/modal-notification-maintenance.component';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Title } from '@angular/platform-browser';
import { TranslateService } from '@ngx-translate/core';

@Component({
  selector: 'app-vm005004',
  templateUrl: './vm005004.component.html',
  styleUrls: ['./vm005004.component.scss']
})
export class VM005004Component implements OnInit {

  submitted: boolean;
  isCollapsed = false;
  displayMessage: string;
  errorsCode: string;
  formSearch: FormGroup;
  isDataAvailable: boolean = false;
  //backFlag: boolean;

  direction: string;
  column: string;
  Type: any = type;

  screenPropsSummary = new Listprops();
  config = new DateConfig();

  formBaseSummary: FormGroup;
  reportSummaryList: TimeKeepingDetail[];

  selectDepartments: any[] = [];

  valueOldForSummary = {
    employeeName: [null],
    employeeCode: [null],
    departmentID: [null],
    timekeepingID: this.route.snapshot.params.id
  };

  constructor(
    private formBuilder: FormBuilder,
    private localStorage: StorageService,
    private vm005004Service: VM005004Service,
    public translationService: TranslationService,
    private modalService: NgbModal,
    private router: Router,
    private route: ActivatedRoute,
    private titleService: Title,
    private translate: TranslateService,

  ) {
    this.readstorageData();
    this.initForm();
    this.initDataLoad();
    this.formBaseSummary = this.formBuilder.group({
      employeeName: [null],
      employeeCode: [null],
      departmentID: [[], Validators.min(-1)],
      timekeepingID: this.route.snapshot.params.id,
      pageNum: [1],
      pageSize: [this.localStorage.getPageSize() !== null ? this.localStorage.getPageSize() : 30],
    });
    this.formSearch.patchValue({
      employeeName: null,
      employeeCode: null,
      departmentID: [-1],
      timekeepingID: this.route.snapshot.params.id,
    });

    setTimeout(() => {
      this.initDataVacation(null, this.localStorage.getPageSize(), this.formBaseSummary);
    }, 1000);
  }

  get f() { return this.formSearch.controls; }
  private unsubscribe$ = new Subject();

  ngOnInit(): void {
    // change title
    this.translate.get('title.vacation.report-timekeeping').subscribe((title: string) => {
      this.titleService.setTitle(title);
    });
    // change title when change language
    this.translationService.changeTranslatetion().pipe(takeUntil(this.unsubscribe$),
      mergeMap(lang => this.translate.get('title.vacation.report-timekeeping'))
    ).subscribe((title: string) => {
      this.titleService.setTitle(title);
    });

  }

  initForm(): void {
    this.formSearch = this.formBuilder.group({
      employeeName: [null],
      employeeCode: [null],
      departmentID: [[], Validators.min(-1)],
      timekeepingID: this.route.snapshot.params.id,
      pageNum: [1],
      pageSize: [this.localStorage.getPageSize() !== null ? this.localStorage.getPageSize() : 30],
    });
  }

  readstorageData() {
    const storage: any = this.localStorage.getUser();
  }

  initDataLoad() {
    this.vm005004Service.vm005004InitList().subscribe(data => {
      if (data.error === null) {
        this.selectDepartments = data.content.listDepartment;
        this.isDataAvailable = true;
        this.formSearch.patchValue({
          departmentID: [],
          vacationYear: new Date().getFullYear()
        });
        this.selectDepartments.shift();
      }
      else {
        this.openPopupIsDataAvailable('notification.500');
      }
    });
  }

  searchSummaryList() {
    this.screenPropsSummary.loading = true;
    this.screenPropsSummary.page = 1;
    //this.formBaseSummary.value.filterType = type.Type.typeHistory;
    this.formBaseSummary.value.pageNum = 1;
    this.formBaseSummary.value.pageSize = this.localStorage.getPageSize() !== null ? this.localStorage.getPageSize() : 30;
    this.valueOldForSummary = JSON.parse(JSON.stringify(this.formSearch.value));
    this.formBaseSummary.value.employeeName = this.valueOldForSummary.employeeName;
    this.formBaseSummary.value.employeeCode = this.valueOldForSummary.employeeCode;
    this.formBaseSummary.value.departmentID = this.valueOldForSummary.departmentID;
    this.formBaseSummary.value.timekeepingID = this.valueOldForSummary.timekeepingID;

    this.initDataVacation(null, this.localStorage.getPageSize(), this.formBaseSummary);
    this.localStorage.saveSearchDataWithKey(this.formBaseSummary.value, 'VACATION_SUMMARY');
  }

  initDataVacation(pageN: any, pSize: any, form: FormGroup) {
    this.resetSort();
    this.submitted = true;
    const formData = {
      ...form.value,
    };
    if (this.formSearch.valid) {
      this.submitted = false;
      this.vm005004Service.vm005004searchReport(formData).pipe(takeUntil(this.unsubscribe$)).subscribe(datacheck => {
        if (datacheck.error === null) {
          this.reportSummaryList = datacheck.content.data;

          this.screenPropsSummary.pageNum = datacheck.content.pageNum ? datacheck.content.pageNum - 1 : 0;
          this.screenPropsSummary.pageSize = datacheck.content.pageSize ? datacheck.content.pageSize : 30;
          this.screenPropsSummary.totalRecord = datacheck.content.totalElements;
          this.screenPropsSummary.loading = false;
          this.reportSummaryList = datacheck.content.data;
          //this.overideLeaveHour();
        }
      }, err => {
        const httpErr = err.error;
        this.reportSummaryList = [];
        if (!httpErr) {
          return;
        }
        else {
          this.displayErrorMessage(httpErr);
        }
      });
    }
  }

  displayErrorMessage(errors) {
    const formControl = this.formSearch.get(errors.error.itemName);
    if (formControl) {
      this.translationService.getTranslation(errors.error.code).pipe(takeUntil(this.unsubscribe$)).subscribe(errorMessage => {
        // set errors formcontrol
        this.errorsCode = errors.error.code;
        this.displayMessage = errorMessage;
        formControl.setErrors({
          serverError: errorMessage
        });
      });
    }
  }

  resetForm() {
    this.initForm();
    this.initDataLoad();
    this.formBaseSummary.patchValue({
      employeeCode: null,
      employeeName: null,
      timekeepingID: this.route.snapshot.params.id,
      departmentID: [-1]
    });
    this.screenPropsSummary.page = 1;
    this.formBaseSummary.value.pageSize = this.localStorage.getPageSize() !== null ? this.localStorage.getPageSize() : 30;
    this.initDataVacation(null, this.localStorage.getPageSize(), this.formBaseSummary);
  }

  changePageSize(pageSize: number): void {
    this.screenPropsSummary.pageSize = pageSize;
    this.localStorage.savePageSizeLocal(this.screenPropsSummary.pageSize);
    this.formBaseSummary.value.pageSize = pageSize;
    this.initDataVacation(null, this.localStorage.getPageSize(), this.formBaseSummary);
  }

  pageChangeOutput(currentPage: number) {
    this.formBaseSummary.value.pageNum = currentPage;
    this.screenPropsSummary.pageNum = currentPage - 1;
    this.formBaseSummary.value.pageSize = this.screenPropsSummary.pageSize;
    this.initDataVacation(null, this.localStorage.getPageSize(), this.formBaseSummary);
  }

  resetSort() {
    this.column = 'employeeCode';
    this.direction = 'asc';
  }

  onSort({ column, direction }: SortEvent) {
    this.direction = direction;
    this.column = column;
    this.reportSummaryList = [...this.reportSummaryList].sort((a, b) => {
      let nullableStringA = '';
      let nullableStringB = '';
      let res: number;
      switch (column) {
        case 'employeeCode':
          res = a.employeeCode.localeCompare(b.employeeCode);
          return direction === 'asc' ? res : -res;
        case 'employeeName':
          nullableStringA = a.first_name + a.last_name;
          nullableStringB = b.first_name + b.last_name;
          res = nullableStringA.localeCompare(nullableStringB);
          return direction === 'asc' ? res : -res;
        case 'departmentName':
          res = compare(a.departmentName, b.departmentName);
          return direction === 'asc' ? res : -res;
        case 'startTime':
          res = compare(a.startTime, b.startTime);
          return direction === 'asc' ? res : -res;
        case 'endTime':
          res = compare(a.endTime, b.endTime);
          return direction === 'asc' ? res : -res;
      }
    });
  }

  onAddNewDepartmentID(event) {
    const itemValue: number = Number(event);
    this.valueOldForSummary = JSON.parse(JSON.stringify(this.formSearch.value));
    let newDepartmentIDList: number[] = this.valueOldForSummary.departmentID;
    if (newDepartmentIDList.length > 1) {
      if (itemValue === -1) {
        newDepartmentIDList = [-1];
      } else {
        if (newDepartmentIDList.includes(-1)) {
          newDepartmentIDList = newDepartmentIDList.filter(item => item !== -1);
        }
      }
      if (itemValue === 0) {
        newDepartmentIDList = [0];
      } else {
        if (newDepartmentIDList.includes(0)) {
          newDepartmentIDList = newDepartmentIDList.filter(item => item !== 0);
        }
      }
    }
    this.formSearch.patchValue({
      departmentID: newDepartmentIDList,
    });
  }

  openPopupIsDataAvailable(key: string) {
    // NgbModal bootstrap using abtract ViewContainerRef of angular create dynamic components
    // https://angular.io/api/core/ViewContainerRef;
    const modalRef = this.modalService.open(ModalNotificationMaintenanceComponent, { backdrop: 'static', keyboard: false });
    modalRef.componentInstance.key = key;
    modalRef.result.then(
      (result) => {
        if (result === 'reload') {
          location.reload();
        } else {
          this.router.navigate(['/dashboard']);
        }
      },
      (reason) => {
      });
  }

  back(event: any) {
    this.router.navigate(['/timekeeping']);
  }

}
