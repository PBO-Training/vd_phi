import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Subject } from 'rxjs';
import { mergeMap, takeUntil } from 'rxjs/operators';
import * as type from '../../../common/constant/type';
import { DateConfig } from '../../../common/datepicker-config/datepicker-config';
import { Listprops } from '../../../common/page-options';
import { DateValidator } from '../../../common/validator/date-validator';
import { StorageService } from '../../../services/storage/storage.service';
import { TranslationService } from '../../../services/translate/translation.service';
import { compare, SortEvent } from '../../../theme/shared/directives/sort-table-header.directive';
import { Vacation } from './vm003001-entity';
import { VM003001Service } from './vm003001.service';
import {
  ModalNotificationMaintenanceComponent
} from '../../../theme/shared/components/modal-notification-maintenance/modal-notification-maintenance.component';
import { Router, ActivatedRoute } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Title } from '@angular/platform-browser';
import { TranslateService } from '@ngx-translate/core';

@Component({
  selector: 'app-vm003001',
  templateUrl: './vm003001.component.html',
  styleUrls: ['./vm003001.component.scss']
})
export class VM003001Component implements OnInit {

  accessEmployeeID: string;
  submitted: boolean;
  vacationYear: string;
  isCollapsed = false;
  displayMessage: string;
  errorsCode: string;
  formSearch: FormGroup;
  isDataAvailable: boolean = false;
  backFlag: boolean;

  direction: string;
  column: string;
  Type: any = type;

  screenPropsHistory = new Listprops();
  config = new DateConfig();

  formBaseHistory: FormGroup;
  vacationHistoryList: Vacation[];

  selectDepartments: any[] = [];
  selectTypes: any[] = [];
  selectOptions: any[] = [];
  selectStatus: any[] = [];

  valueOldForHistory = {
    employeeName: [null],
    employeeCode: [null],
    accessEmployeeID: [null],
    vacationYear: [null],
    vacationTypeID: [null],
    vacationOptionID: [null],
    vacationStatusID: [null],
    startDate: [null],
    endDate: [null],
    departmentID: [null],
  };

  constructor(
    private formBuilder: FormBuilder,
    private localStorage: StorageService,
    private vm003001Service: VM003001Service,
    public translationService: TranslationService,
    private modalService: NgbModal,
    private router: Router,
    public route: ActivatedRoute,
    private datepipe: DatePipe,
    private titleService: Title,
    private translate: TranslateService,

  ) {
    this.readstorageData();
    this.initForm();
    this.initDataLoad();
    this.formBaseHistory = this.formBuilder.group({
      employeeName: [null],
      employeeCode: [null],
      accessEmployeeID: this.accessEmployeeID,
      vacationYear: this.vacationYear,
      vacationTypeID: [[], Validators.min(-1)],
      vacationOptionID: [[], Validators.min(-1)],
      vacationStatusID: [[], Validators.min(-1)],
      startDate: [null],
      endDate: [null],
      departmentID: [[], Validators.min(-1)],
      pageNum: [1],
      pageSize: [this.localStorage.getPageSize() !== null ? this.localStorage.getPageSize() : 30],
    });

    const data = this.localStorage.getSearchData();
    this.backFlag = this.localStorage.getBackFlag();
    if (this.backFlag === true && data !== null) {
      let parseToJson = JSON.parse(JSON.stringify(data));
      let newVacationTypeIDList: number[] = parseToJson.vacationTypeID;
      let newVacationOptionIDList: number[] = parseToJson.vacationOptionID;
      let newVacationStatusIDList: number[] = parseToJson.vacationStatusID;
      let newDepartmentIDList: number[] = parseToJson.departmentID;

      this.formBaseHistory.patchValue({
        employeeName: data.employeeName,
        employeeCode: data.employeeCode,
        startDate: data.startDate,
        endDate: data.endDate,
        accessEmployeeID: data.accessEmployeeID,
        vacationYear: this.vacationYear,
        vacationTypeID: newVacationTypeIDList,
        vacationStatusID: newVacationStatusIDList,
        vacationOptionID: newVacationOptionIDList,
        departmentID: newDepartmentIDList,
        pageNum: data.pageNum,
        pageSize: data.pageSize,
      });

      var myPastDate = new Date(data.endDate);
      myPastDate.setDate(myPastDate.getDate() - 1);
      this.formSearch.patchValue({
        employeeName: data.employeeName,
        employeeCode: data.employeeCode,
        startDate: this.datepipe.transform(data.startDate, 'dd-MM-yyyy'),
        endDate: this.datepipe.transform(myPastDate, 'dd-MM-yyyy'),
        accessEmployeeID: data.accessEmployeeID,
        vacationYear: this.vacationYear,
        vacationTypeID: newVacationTypeIDList,
        vacationStatusID: newVacationStatusIDList,
        vacationOptionID: newVacationOptionIDList,
        departmentID: newDepartmentIDList,
      });
    }
    else {
      this.formSearch.patchValue({
        startDate: null,
        endDate: null,
        employeeName: null,
        employeeCode: null,
        vacationTypeID: [-1],
        vacationStatusID: [-1],
        vacationOptionID: [-1],
        departmentID: [-1]
      });
      this.localStorage.removeSearchData();
    }

    setTimeout(() => {
      this.initDataVacation(null, this.localStorage.getPageSize(), this.formBaseHistory);
    }, 1000);
    this.localStorage.saveBackFlag(false);
  }

  get f() { return this.formSearch.controls; }
  private unsubscribe$ = new Subject();

  ngOnInit(): void {
    // change title
    this.translate.get('title.vacation.history-request').subscribe((title: string) => {
      this.titleService.setTitle(title);
    });
    // change title when change language
    this.translationService.changeTranslatetion().pipe(takeUntil(this.unsubscribe$),
      mergeMap(lang => this.translate.get('title.vacation.history-request'))
    ).subscribe((title: string) => {
      this.titleService.setTitle(title);
    });


  }

  initForm(): void {
    this.formSearch = this.formBuilder.group({
      employeeName: [null],
      employeeCode: [null],
      accessEmployeeID: this.accessEmployeeID,
      vacationYear: this.vacationYear,
      vacationTypeID: [[], Validators.min(-1)],
      vacationOptionID: [[], Validators.min(-1)],
      vacationStatusID: [[], Validators.min(-1)],
      startDate: [null],
      endDate: [null],
      departmentID: [[], Validators.min(-1)],
      filterType: [null],
      pageNum: [1],
      pageSize: [this.localStorage.getPageSize() !== null ? this.localStorage.getPageSize() : 30],
    },
      {
        validator: Validators.compose([DateValidator.dateLessThan('startDate', 'endDate')])
      });
  }

  readstorageData() {
    const storage: any = this.localStorage.getUser();
    const id = this.route.snapshot.params.id;
    const year = this.route.snapshot.params.year;
    if (id) {
      this.accessEmployeeID = id;
      this.vacationYear = year;
    }
    else {
      this.accessEmployeeID = storage.employeeID;
      this.vacationYear = null;
    }
  }

  initDataLoad() {
    this.vm003001Service.vm003001InitList().subscribe(data => {
      if (data.error === null) {
        this.selectDepartments = data.content.listDepartment;
        this.selectTypes = data.content.listVacationType;
        this.selectOptions = data.content.listVacationOption;
        this.selectStatus = data.content.listVacationStatus;
        this.isDataAvailable = true;
        const dataStorage = this.localStorage.getSearchData();
        this.backFlag = this.localStorage.getBackFlag();
        if (this.backFlag != true && dataStorage === null) {
          this.formSearch.patchValue({
            employeeName: null,
            employeeCode: null,
            startDate: null,
            endDate: null,
            vacationTypeID: [],
            vacationStatusID: [],
            vacationOptionID: [],
            departmentID: []
          });
        }
        this.selectTypes.shift();
        this.selectOptions.shift();
        this.selectStatus.shift();
        this.selectDepartments.shift();
      }
      else {
        this.openPopupIsDataAvailable('notification.500');
      }
    });
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

  searchHistoryList() {
    this.screenPropsHistory.loading = true;
    this.screenPropsHistory.page = 1;

    //this.formBaseHistory.value.filterType = type.Type.typeHistory;
    this.formBaseHistory.value.pageNum = 1;
    this.formBaseHistory.value.pageSize = this.localStorage.getPageSize() !== null ? this.localStorage.getPageSize() : 30;

    this.valueOldForHistory = JSON.parse(JSON.stringify(this.formSearch.value));
    this.formBaseHistory.value.employeeName = this.valueOldForHistory.employeeName;
    this.formBaseHistory.value.employeeCode = this.valueOldForHistory.employeeCode;
    this.formBaseHistory.value.accessEmployeeID = this.valueOldForHistory.accessEmployeeID;
    this.formBaseHistory.value.vacationYear = this.valueOldForHistory.vacationYear;
    this.formBaseHistory.value.vacationTypeID = this.valueOldForHistory.vacationTypeID;
    this.formBaseHistory.value.vacationOptionID = this.valueOldForHistory.vacationOptionID;
    this.formBaseHistory.value.vacationStatusID = this.valueOldForHistory.vacationStatusID;

    let startDate = this.parseDateToMonth(this.valueOldForHistory.startDate);
    if (startDate) {
      startDate.setHours(type.TimeDefault.startHourAM);
      startDate.setMinutes(type.TimeDefault.startMinAM);
    }
    let endDate = this.parseDateToMonth(this.valueOldForHistory.endDate);
    if (endDate) {
      endDate.setHours(type.TimeDefault.endHourPM);
      endDate.setMinutes(type.TimeDefault.endMinPM);
    }
    this.formBaseHistory.value.startDate = this.setDefaultTime(startDate);
    this.formBaseHistory.value.endDate = this.setDefaultTime(endDate);

    this.formBaseHistory.value.departmentID = this.valueOldForHistory.departmentID;

    this.initDataVacation(null, this.localStorage.getPageSize(), this.formBaseHistory);
    this.localStorage.saveSearchData(this.formBaseHistory.value, null);
  }

  initDataVacation(pageN: any, pSize: any, form: FormGroup) {
    this.resetSort();
    this.submitted = true;
    const formData = {
      pageSize: pSize,
      pageNum: pageN,
      ...form.value,
    };
    if (this.formSearch.valid) {
      this.submitted = false;
      this.vm003001Service.vm003001searchVacation(formData).pipe(takeUntil(this.unsubscribe$)).subscribe(datacheck => {
        if (datacheck.error === null) {
          this.vacationHistoryList = datacheck.content.data;

          this.screenPropsHistory.pageNum = datacheck.content.pageNum ? datacheck.content.pageNum - 1 : 0;
          this.screenPropsHistory.pageSize = datacheck.content.pageSize ? datacheck.content.pageSize : 30;
          this.screenPropsHistory.totalRecord = datacheck.content.totalElements;
          this.screenPropsHistory.loading = false;
          this.vacationHistoryList = datacheck.content.data;
          //this.overideLeaveHour();
        }
      }, err => {
        const httpErr = err.error;
        this.vacationHistoryList = [];
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
    this.localStorage.removeSearchData();
    this.initForm();
    this.initDataLoad();
    const id = this.route.snapshot.params.id;
    const year = this.route.snapshot.params.year;
    if (id && year) {
      this.formBaseHistory.patchValue({
        employeeName: null,
        employeeCode: null,
        startDate: null,
        endDate: null,
        vacationTypeID: [-1],
        vacationStatusID: [-1],
        vacationOptionID: [-1],
        departmentID: [-1],
        accessEmployeeID: id,
        vacationYear: year,
      });
    }
    else {
      this.formBaseHistory.patchValue({
        employeeName: null,
        employeeCode: null,
        startDate: null,
        endDate: null,
        vacationTypeID: [-1],
        vacationStatusID: [-1],
        vacationOptionID: [-1],
        departmentID: [-1]
      });
    }
    this.screenPropsHistory.page = 1;
    this.formBaseHistory.value.pageSize = this.localStorage.getPageSize() !== null ? this.localStorage.getPageSize() : 30;
    this.initDataVacation(null, this.localStorage.getPageSize(), this.formBaseHistory);
  }

  changePageSize(pageSize: number): void {
    this.screenPropsHistory.pageSize = pageSize;
    this.localStorage.savePageSizeLocal(this.screenPropsHistory.pageSize);
    this.formBaseHistory.value.pageSize = pageSize;
    this.initDataVacation(null, this.localStorage.getPageSize(), this.formBaseHistory);
  }

  pageChangeOutput(currentPage: number) {
    this.formBaseHistory.value.pageNum = currentPage;
    this.screenPropsHistory.pageNum = currentPage - 1;
    this.formBaseHistory.value.pageSize = this.screenPropsHistory.pageSize;
    this.initDataVacation(null, this.localStorage.getPageSize(), this.formBaseHistory);
  }

  resetSort() {
    // reset sort
    this.column = 'startDate';
    this.direction = 'desc';
  }

  onSort({ column, direction }: SortEvent) {
    this.direction = direction;
    this.column = column;
    this.vacationHistoryList = [...this.vacationHistoryList].sort((a, b) => {
      let res: number;
      switch (column) {
        case 'employeeName':
          res = compare(a.employeeName, b.employeeName);
          return direction === 'asc' ? res : -res;
        case 'vacationDepartment':
          res = compare(a.departmentName, b.departmentName);
          return direction === 'asc' ? res : -res;
        case 'startDate':
          res = compare(a.startDate, b.startDate);
          return direction === 'desc' ? res : -res;
        case 'endDate':
          res = compare(a.endDate, b.endDate);
          return direction === 'asc' ? res : -res;
        case 'vacationType':
          res = compare(a.vacationTypeName, b.vacationTypeName);
          return direction === 'asc' ? res : -res;
        case 'vacationOption':
          res = compare(a.vacationOptionName, b.vacationOptionName);
          return direction === 'asc' ? res : -res;
        case 'vacationStatus':
          res = compare(a.statusName, b.statusName);
          return direction === 'asc' ? res : -res;
        case 'vacationReason':
          res = compare(a.title, b.title);
          return direction === 'asc' ? res : -res;
        case 'employeeCode':
          res = compare(a.employeeCode, b.employeeCode);
          return direction === 'asc' ? res : -res;
        case 'leaveHour':
          res = compare(a.leaveHour, b.leaveHour);
          return direction === 'asc' ? res : -res;
        case 'updateDate':
          res = compare(a.updateDate, b.updateDate);
          return direction === 'asc' ? res : -res;
      }
    });
  }

  onAddNewVacationTypeID(event) {
    const itemValue: number = Number(event);
    this.valueOldForHistory = JSON.parse(JSON.stringify(this.formSearch.value));
    let newVacationTypeIDList: number[] = this.valueOldForHistory.vacationTypeID;
    if (newVacationTypeIDList.length > 1) {
      if (itemValue === -1) {
        newVacationTypeIDList = [-1];
      } else {
        if (newVacationTypeIDList.includes(-1)) {
          newVacationTypeIDList = newVacationTypeIDList.filter(item => item !== -1);
        }
      }
      if (itemValue === 0) {
        newVacationTypeIDList = [0];
      } else {
        if (newVacationTypeIDList.includes(0)) {
          newVacationTypeIDList = newVacationTypeIDList.filter(item => item !== 0);
        }
      }
    }
    this.formSearch.patchValue({
      vacationTypeID: newVacationTypeIDList,
    });
  }

  onAddNewVacationOptionID(event) {
    const itemValue: number = Number(event);
    this.valueOldForHistory = JSON.parse(JSON.stringify(this.formSearch.value));
    let newVacationOptionIDList: number[] = this.valueOldForHistory.vacationOptionID;
    if (newVacationOptionIDList.length > 1) {
      if (itemValue === -1) {
        newVacationOptionIDList = [-1];
      } else {
        if (newVacationOptionIDList.includes(-1)) {
          newVacationOptionIDList = newVacationOptionIDList.filter(item => item !== -1);
        }
      }
      if (itemValue === 0) {
        newVacationOptionIDList = [0];
      } else {
        if (newVacationOptionIDList.includes(0)) {
          newVacationOptionIDList = newVacationOptionIDList.filter(item => item !== 0);
        }
      }
    }
    this.formSearch.patchValue({
      vacationOptionID: newVacationOptionIDList,
    });
  }

  onAddNewVacationStatusID(event) {
    const itemValue: number = Number(event);
    this.valueOldForHistory = JSON.parse(JSON.stringify(this.formSearch.value));
    let newVacationStatusIDList: number[] = this.valueOldForHistory.vacationStatusID;
    if (newVacationStatusIDList.length > 1) {
      if (itemValue === -1) {
        newVacationStatusIDList = [-1];
      } else {
        if (newVacationStatusIDList.includes(-1)) {
          newVacationStatusIDList = newVacationStatusIDList.filter(item => item !== -1);
        }
      }
      if (itemValue === 0) {
        newVacationStatusIDList = [0];
      } else {
        if (newVacationStatusIDList.includes(0)) {
          newVacationStatusIDList = newVacationStatusIDList.filter(item => item !== 0);
        }
      }
    }
    this.formSearch.patchValue({
      vacationStatusID: newVacationStatusIDList,
    });
  }

  onAddNewDepartmentID(event) {
    const itemValue: number = Number(event);
    this.valueOldForHistory = JSON.parse(JSON.stringify(this.formSearch.value));
    let newDepartmentIDList: number[] = this.valueOldForHistory.departmentID;
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

  back(event: any) {
    this.localStorage.saveBackFlagExpand(true);
    this.router.navigate(['/vacation-summary-list']);
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
