import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { StorageService } from '../../../services/storage/storage.service';
import { DateValidator } from '../../../common/validator/date-validator';
import { DateConfig } from '../../../common/datepicker-config/datepicker-config';
import { Subject } from 'rxjs';
import { VM008001Service } from './vm008001.service';
import { ShiftWork } from './vm008001-entity';
import { Listprops } from '../../../common/page-options';
import { mergeMap, takeUntil } from 'rxjs/operators';
import { compare, SortEvent } from '../../../theme/shared/directives/sort-table-header.directive';
import { Router, ActivatedRoute } from '@angular/router';
import * as type from '../../../common/constant/type';
import { TranslationService } from '../../../services/translate/translation.service';
import {
  ModalNotificationMaintenanceComponent
} from '../../../theme/shared/components/modal-notification-maintenance/modal-notification-maintenance.component';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Title } from '@angular/platform-browser';
import { TranslateService } from '@ngx-translate/core';

@Component({
  selector: 'app-vm008001',
  templateUrl: './vm008001.component.html',
  styleUrls: ['./vm008001.component.scss'],
})
export class VM008001Component implements OnInit {

  accessEmployeeID: string;
  column: string;
  direction: string;
  errorsCode: string;
  displayMessage: string;

  submitted: boolean;
  isDataAvailable: boolean = false;
  isCollapsed = false;

  Type: any = type;
  listOption: any[] = [];
  listStatus: any[] = [];
  listDepartment: any[] = [];
  swHistoryList: ShiftWork[];

  formSearch: FormGroup;
  formBaseHistory: FormGroup;

  screenPropsHistory = new Listprops();
  config = new DateConfig();

  valueOldForHistory = {
    accessEmployeeID: [null],
    employeeName: [null],
    employeeCode: [null],
    swStartDateAM: [null],
    swEndDatePM: [null],
    swOptionID: [null],
    swStatusID: [null],
    departmentID: [null],
  };

  constructor(
    private formBuilder: FormBuilder,
    private localStorage: StorageService,
    private vm008001Service: VM008001Service,
    private modalService: NgbModal,
    private router: Router,
    public route: ActivatedRoute,
    public translationService: TranslationService,
    private titleService: Title,
    private translate: TranslateService,

  ) {
    this.readstorageData();
    this.initForm();
    this.initDataLoad();
    this.formBaseHistory = this.formBuilder.group({
      accessEmployeeID: this.accessEmployeeID,
      employeeName: [null],
      employeeCode: [null],
      swStartDateAM: [null],
      swEndDatePM: [null],
      swOptionID: [-1, Validators.min(-1)],
      swStatusID: [-1, Validators.min(-1)],
      departmentID: [-1, Validators.min(-1)],
      pageNum: [1],
      pageSize: [this.localStorage.getPageSize() !== null ? this.localStorage.getPageSize() : 30],
    });
    this.formSearch.patchValue({
      employeeName: null,
      employeeCode: null,
      swStartDateAM: null,
      swEndDatePM: null,
      swOptionID: -1,
      swStatusID: -1,
      departmentID: -1
    });
    this.localStorage.removeSearchData();

    setTimeout(() => {
      this.initDataShiftWork(null, this.localStorage.getPageSize(), this.formBaseHistory);
    }, 1000);
    this.localStorage.saveBackFlag(false);
  }

  ngOnInit(): void {
    // change title
    this.translate.get('title.vacation.shift-work-history').subscribe((title: string) => {
      this.titleService.setTitle(title);
    });
    // change title when change language
    this.translationService.changeTranslatetion().pipe(takeUntil(this.unsubscribe$),
      mergeMap(lang => this.translate.get('title.vacation.shift-work-history'))
    ).subscribe((title: string) => {
      this.titleService.setTitle(title);
    });
  }

  get f() { return this.formSearch.controls; }

  private unsubscribe$ = new Subject();

  /** Initialize a search form **/
  initForm(): void {
    this.formSearch = this.formBuilder.group({
      accessEmployeeID: this.accessEmployeeID,
      employeeName: [null],
      employeeCode: [null],
      swStartDateAM: [null],
      swEndDatePM: [null],
      swOptionID: [-1, Validators.min(-1)],
      swStatusID: [-1, Validators.min(-1)],
      departmentID: [-1, Validators.min(-1)],
      pageNum: [1],
      pageSize: [this.localStorage.getPageSize() !== null ? this.localStorage.getPageSize() : 30],
    },
      {
        validator: Validators.compose([DateValidator.dateLessThan('swStartDateAM', 'swEndDatePM')])
      });
  }

  /** Initialize drop down list for search form **/
  initDataLoad() {
    this.vm008001Service.vm008001InitList().subscribe(data => {
      if (data.error === null) {
        this.listOption = data.content.listShiftWorkOption;
        this.listStatus = data.content.listShiftWorkStatus;
        this.listDepartment = data.content.listDepartment;
        this.isDataAvailable = true;
      }
      else {
        this.openPopupIsDataAvailable('notification.500');
      }
    });
  }

  /** Initialize a list of record by information form search form **/
  initDataShiftWork(pageN: any, pSize: any, form: FormGroup) {
    this.resetSort();
    this.submitted = true;
    const formData = {
      pageSize: pSize,
      pageNum: pageN,
      ...form.value,
    };
    if (this.formSearch.valid) {
      this.submitted = false;
      this.vm008001Service.vm008001Search(formData).pipe(takeUntil(this.unsubscribe$)).subscribe(datacheck => {
        if (datacheck.error === null) {
          this.swHistoryList = datacheck.content.data;

          this.screenPropsHistory.pageNum = datacheck.content.pageNum ? datacheck.content.pageNum - 1 : 0;
          this.screenPropsHistory.pageSize = datacheck.content.pageSize ? datacheck.content.pageSize : 30;
          this.screenPropsHistory.totalRecord = datacheck.content.totalElements;
          this.screenPropsHistory.loading = false;
          this.swHistoryList = datacheck.content.data;
        }
      }, err => {
        const httpErr = err.error;
        this.swHistoryList = [];
        if (!httpErr) {
          return;
        }
        else {
          this.displayErrorMessage(httpErr);
        }
      });
    }
  }

  /** Find user login **/
  readstorageData() {
    const storage: any = this.localStorage.getUser();
    this.accessEmployeeID = storage.employeeID;
  }

  /** Search process when click on search button **/
  searchHistoryList() {
    this.screenPropsHistory.loading = true;
    this.screenPropsHistory.page = 1;

    this.formBaseHistory.value.pageNum = 1;
    this.formBaseHistory.value.pageSize = this.localStorage.getPageSize() !== null ? this.localStorage.getPageSize() : 30;

    this.valueOldForHistory = JSON.parse(JSON.stringify(this.formSearch.value));
    this.formBaseHistory.value.employeeName = this.valueOldForHistory.employeeName;
    this.formBaseHistory.value.employeeCode = this.valueOldForHistory.employeeCode;
    this.formBaseHistory.value.accessEmployeeID = this.valueOldForHistory.accessEmployeeID;
    this.formBaseHistory.value.swOptionID = this.valueOldForHistory.swOptionID;
    this.formBaseHistory.value.swStatusID = this.valueOldForHistory.swStatusID;

    let startDate = this.parseDateToMonth(this.valueOldForHistory.swStartDateAM);
    if (startDate) {
      startDate.setHours(type.TimeDefault.startHourAM);
      startDate.setMinutes(type.TimeDefault.startMinAM);
    }
    let endDate = this.parseDateToMonth(this.valueOldForHistory.swEndDatePM);
    if (endDate) {
      endDate.setHours(type.TimeDefault.endHourPM);
      endDate.setMinutes(type.TimeDefault.endMinPM);
    }
    this.formBaseHistory.value.startDate = this.setDefaultTime(startDate);
    this.formBaseHistory.value.endDate = this.setDefaultTime(endDate);

    this.formBaseHistory.value.departmentID = this.valueOldForHistory.departmentID;

    this.initDataShiftWork(null, this.localStorage.getPageSize(), this.formBaseHistory);
    this.localStorage.saveSearchData(this.formBaseHistory.value, null);
  }

  /** Reset search form with default value **/
  resetForm() {
    this.localStorage.removeSearchData();
    this.initForm();
    this.initDataLoad();
    const id = this.route.snapshot.params.id;
    this.formBaseHistory.patchValue({
      employeeName: null,
      employeeCode: null,
      swStartDateAM: null,
      swEndDatePM: null,
      swStatusID: -1,
      swnOptionID: -1,
      departmentID: -1
    });
    this.screenPropsHistory.page = 1;
    this.formBaseHistory.value.pageSize = this.localStorage.getPageSize() !== null ? this.localStorage.getPageSize() : 30;
    this.initDataShiftWork(null, this.localStorage.getPageSize(), this.formBaseHistory);
  }

  /** Reset sort value to default **/
  resetSort() {
    this.column = 'swStartDateAM';
    this.direction = 'desc';
  }

  /** Sort process **/
  onSort({ column, direction }: SortEvent) {
    this.direction = direction;
    this.column = column;
    this.swHistoryList = [...this.swHistoryList].sort((a, b) => {
      let res: number;
      switch (column) {
        case 'employeeCode':
          res = compare(a.employeeCode, b.employeeCode);
          return direction === 'asc' ? res : -res;
        case 'employeeName':
          res = compare(a.employeeName, b.employeeName);
          return direction === 'asc' ? res : -res;
        case 'departmentName':
          res = compare(a.departmentName, b.departmentName);
          return direction === 'asc' ? res : -res;
        case 'swStartDateAM':
          res = compare(a.swStartDateAM, b.swStartDateAM);
          return direction === 'asc' ? res : -res;
        case 'swEndDatePM':
          res = compare(a.swEndDatePM, b.swEndDatePM);
          return direction === 'asc' ? res : -res;
        case 'swOptionName':
          res = compare(a.swOptionName, b.swOptionName);
          return direction === 'asc' ? res : -res;
        case 'swStatusName':
          res = compare(a.swStatusName, b.swStatusName);
          return direction === 'asc' ? res : -res;
      }
    });
  }

  changePageSize(pageSize: number): void {
    this.screenPropsHistory.pageSize = pageSize;
    this.localStorage.savePageSizeLocal(this.screenPropsHistory.pageSize);
    this.formBaseHistory.value.pageSize = pageSize;
    this.initDataShiftWork(null, this.localStorage.getPageSize(), this.formBaseHistory);
  }

  pageChangeOutput(currentPage: number) {
    this.formBaseHistory.value.pageNum = currentPage;
    this.screenPropsHistory.pageNum = currentPage - 1;
    this.formBaseHistory.value.pageSize = this.screenPropsHistory.pageSize;
    this.initDataShiftWork(null, this.localStorage.getPageSize(), this.formBaseHistory);
  }
  
  parseDateToMonth(dateStr) {
    let checkDate = typeof dateStr;
    if (checkDate.toString() == 'string') {
      var parts = dateStr.split("-")
      return new Date(parts[2], parts[1] - 1, parts[0])
    }
    return dateStr;
  }

  setDefaultTime(date): String {
    let checkDate = new Date(date);
    if (checkDate.toString() != 'Invalid Date' && date) {
      let isoDate = new Date(date.getTime() - date.getTimezoneOffset() * 60000).toISOString();
      return isoDate;
    }
    return date;
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

}
