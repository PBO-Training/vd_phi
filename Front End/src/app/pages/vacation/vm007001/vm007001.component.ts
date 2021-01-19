import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Title } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { TranslateService } from '@ngx-translate/core';
import { Subject } from 'rxjs';
import { mergeMap, takeUntil } from 'rxjs/operators';
import * as type from '../../../common/constant/type';
import { DateConfig } from '../../../common/datepicker-config/datepicker-config';
import { Listprops } from '../../../common/page-options';
import { DateValidator } from '../../../common/validator/date-validator';
import { StorageService } from '../../../services/storage/storage.service';
import { TranslationService } from '../../../services/translate/translation.service';
import {
  ModalNotificationMaintenanceComponent
} from '../../../theme/shared/components/modal-notification-maintenance/modal-notification-maintenance.component';
import { compare, SortEvent } from '../../../theme/shared/directives/sort-table-header.directive';
import { ShiftWork } from './vm007001-entity';
import { VM007001Service } from './vm007001.service';

@Component({
  selector: 'app-vm007001',
  templateUrl: './vm007001.component.html',
  styleUrls: ['./vm007001.component.scss'],
})
export class VM007001Component implements OnInit {

  accessEmployeeID: string;
  errorsCode: string;
  displayMessage: string;
  column: string;
  direction: string;

  submitted: boolean;
  isCollapsed: boolean = false;
  isDataAvailable: boolean = false;

  listOption: any[] = [];
  listStatus: any[] = [];
  listDepartment: any[] = [];
  swReceiveList: ShiftWork[];

  formSearch: FormGroup;
  formBaseReceive: FormGroup;

  Type: any = type;

  config = new DateConfig();
  screenPropsReceive = new Listprops();

  valueOldForReceive = {
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
    private vm007001Service: VM007001Service,
    public translationService: TranslationService,
    private modalService: NgbModal,
    private router: Router,
    private titleService: Title,
    private translate: TranslateService,

  ) {
    this.readstorageData();
    this.initDataLoad();
    this.initForm();
    this.formBaseReceive = this.formBuilder.group({
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
      this.initDataShiftWork(null, this.localStorage.getPageSize(), this.formBaseReceive);
    }, 1000);
    this.localStorage.saveBackFlag(false);
  }

  ngOnInit(): void {
    // change title
    this.translate.get('title.vacation.shift-work-receive').subscribe((title: string) => {
      this.titleService.setTitle(title);
    });
    // change title when change language
    this.translationService.changeTranslatetion().pipe(takeUntil(this.unsubscribe$),
      mergeMap(lang => this.translate.get('title.vacation.shift-work-receive'))
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
      this.vm007001Service.vm007001SearchShiftWork(formData).pipe(takeUntil(this.unsubscribe$)).subscribe(datacheck => {
        if (datacheck.error === null) {
          this.swReceiveList = datacheck.content.data;
          this.screenPropsReceive.pageNum = datacheck.content.pageNum ? datacheck.content.pageNum - 1 : 0;
          this.screenPropsReceive.pageSize = datacheck.content.pageSize ? datacheck.content.pageSize : 30;
          this.screenPropsReceive.totalRecord = datacheck.content.totalElements;
          this.screenPropsReceive.loading = false;
        }
      }, err => {
        const httpErr = err.error;
        this.swReceiveList = [];
        if (!httpErr) {
          return;
        }
        else {
          this.displayErrorMessage(httpErr);
        }
      });
    }
  }

  /** Initialize drop down list for search form **/
  initDataLoad() {
    this.vm007001Service.vm007001InitList().subscribe(data => {
      if (data.error === null) {
        this.listDepartment = data.content.listDepartment;
        this.listOption = data.content.listShiftWorkOption;
        this.listStatus = data.content.listShiftWorkStatus;
        this.isDataAvailable = true;
        const dataStorage = this.localStorage.getSearchData();
      }
      else {
        this.openPopupIsDataAvailable('notification.500');
      }
    });
  }

  /** Find user login **/
  readstorageData() {
    const storage: any = this.localStorage.getUser();
    this.accessEmployeeID = storage.employeeID;
  }

  /** Search process when click on search button **/
  searchReceiveList() {
    this.screenPropsReceive.loading = true;
    this.screenPropsReceive.page = 1;

    this.formBaseReceive.value.pageNum = 1;
    this.formBaseReceive.value.pageSize = this.localStorage.getPageSize() !== null ? this.localStorage.getPageSize() : 30;

    this.valueOldForReceive = JSON.parse(JSON.stringify(this.formSearch.value));
    this.formBaseReceive.value.employeeName = this.valueOldForReceive.employeeName;
    this.formBaseReceive.value.employeeCode = this.valueOldForReceive.employeeCode;
    this.formBaseReceive.value.accessEmployeeID = this.valueOldForReceive.accessEmployeeID;
    this.formBaseReceive.value.swOptionID = this.valueOldForReceive.swOptionID;
    this.formBaseReceive.value.swStatusID = this.valueOldForReceive.swStatusID;
    this.formBaseReceive.value.departmentID = this.valueOldForReceive.departmentID;

    let startDate = this.parseDateToMonth(this.valueOldForReceive.swStartDateAM);
    if (startDate) {
      startDate.setHours(type.TimeDefault.startHourAM);
      startDate.setMinutes(type.TimeDefault.startMinAM);
    }
    let endDate = this.parseDateToMonth(this.valueOldForReceive.swEndDatePM);
    if (endDate) {
      endDate.setHours(type.TimeDefault.endHourPM);
      endDate.setMinutes(type.TimeDefault.endMinPM);
    }
    this.formBaseReceive.value.swStartDateAM = this.setDefaultTime(startDate);
    this.formBaseReceive.value.swEndDatePM = this.setDefaultTime(endDate);

    this.initDataShiftWork(null, this.localStorage.getPageSize(), this.formBaseReceive);
    this.localStorage.saveSearchData(this.formBaseReceive.value, null);
  }

  /** Reset search form with default value **/
  resetForm() {
    this.localStorage.removeSearchData();
    this.initForm();
    this.initDataLoad();
    this.formBaseReceive.patchValue({
      employeeName: null,
      employeeCode: null,
      swStartDateAM: null,
      swEndDatePM: null,
      vacationStatusID: -1,
      vacationOptionID: -1,
      departmentID: -1
    });
    this.screenPropsReceive.page = 1;
    this.formBaseReceive.value.pageSize = this.localStorage.getPageSize() !== null ? this.localStorage.getPageSize() : 30;
    this.initDataShiftWork(null, this.localStorage.getPageSize(), this.formBaseReceive);
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
    this.swReceiveList = [...this.swReceiveList].sort((a, b) => {
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
    this.screenPropsReceive.pageSize = pageSize;
    this.localStorage.savePageSizeLocal(this.screenPropsReceive.pageSize);
    this.formBaseReceive.value.pageSize = pageSize;
    this.initDataShiftWork(null, this.localStorage.getPageSize(), this.formBaseReceive);
  }

  pageChangeOutput(currentPage: number) {
    this.formBaseReceive.value.pageNum = currentPage;
    this.screenPropsReceive.pageNum = currentPage - 1;
    this.formBaseReceive.value.pageSize = this.screenPropsReceive.pageSize;
    this.initDataShiftWork(null, this.localStorage.getPageSize(), this.formBaseReceive);
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
