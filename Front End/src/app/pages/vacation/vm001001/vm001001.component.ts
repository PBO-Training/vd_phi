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
import { Vacation } from './vm001001-entity';
import { VM001001Service } from './vm001001.service';
import {
  ModalNotificationMaintenanceComponent
} from '../../../theme/shared/components/modal-notification-maintenance/modal-notification-maintenance.component';
import { Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ScreenAction } from './../../../common/screen-action/screen-action';
import { CommonFunctionService } from './../../../services/common-function/common-function.service';
import { Title } from '@angular/platform-browser';
import { TranslateService } from '@ngx-translate/core';

@Component({
  selector: 'app-vm001001',
  templateUrl: './vm001001.component.html',
  styleUrls: ['./vm001001.component.scss']
})
export class VM001001Component implements OnInit {

  displayMessage: string;
  errorsCode: string;
  accessEmployeeID: string;
  direction: string;
  column: string;
  submitted: boolean;
  backFlag: boolean;

  isCollapsed = false;
  isDataAvailable: boolean = false;

  formSearch: FormGroup;
  formBaseSend: FormGroup;
  selectTypes: any[] = [];
  selectOptions: any[] = [];
  selectStatus: any[] = [];
  vacationSendList: Vacation[];

  screenPropsSend = new Listprops();
  config = new DateConfig();

  Type: any = type;

  valueOldForSend = {
    accessEmployeeID: [null],
    vacationTypeID: [null],
    vacationOptionID: [null],
    vacationStatusID: [null],
    startDate: [null],
    endDate: [null],
  };
  authButton = new ScreenAction();

  constructor(
    private formBuilder: FormBuilder,
    private vm001001Service: VM001001Service,
    private localStorage: StorageService,
    public translationService: TranslationService,
    private modalService: NgbModal,
    private router: Router,
    private datepipe: DatePipe,
    private commonFunctionService: CommonFunctionService,
    private titleService: Title,
    private translate: TranslateService,
  ) {
    this.authButton = this.commonFunctionService.initAuthAction(this.authButton);
    this.readstorageData();
    this.initForm();
    this.initDataLoad();
    this.formBaseSend = this.formBuilder.group({
      employeeName: [null],
      employeeCode: [null],
      accessEmployeeID: this.accessEmployeeID,
      vacationTypeID: [[], Validators.min(-1)],
      vacationOptionID: [[], Validators.min(-1)],
      vacationStatusID: [[], Validators.min(-1)],
      startDate: [null],
      endDate: [null],
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

      this.formBaseSend.patchValue({
        startDate: data.startDate,
        endDate: data.endDate,
        accessEmployeeID: data.accessEmployeeID,
        vacationTypeID: newVacationTypeIDList,
        vacationStatusID: newVacationStatusIDList,
        vacationOptionID: newVacationOptionIDList,
        pageNum: data.pageNum,
        pageSize: data.pageSize,
      });

      var myPastDate = new Date(data.endDate);
      myPastDate.setDate(myPastDate.getDate() - 1);
      this.formSearch.patchValue({
        startDate: this.datepipe.transform(data.startDate, 'dd-MM-yyyy'),
        endDate: this.datepipe.transform(myPastDate, 'dd-MM-yyyy'),
        accessEmployeeID: data.accessEmployeeID,
        vacationTypeID: newVacationTypeIDList,
        vacationStatusID: newVacationStatusIDList,
        vacationOptionID: newVacationOptionIDList,
      });
    }
    else {
      this.formSearch.patchValue({
        startDate: null,
        endDate: null,
        vacationTypeID: [],
        vacationStatusID: [],
        vacationOptionID: []
      });
      this.localStorage.removeSearchData();
    }

    setTimeout(() => {
      this.initDataVacation(null, this.localStorage.getPageSize(), this.formBaseSend);
    }, 1000);
    this.localStorage.saveBackFlag(false);
  }

  get f() { return this.formSearch.controls; }
  private unsubscribe$ = new Subject();

  ngOnInit(): void {
    // change title
    this.translate.get('title.vacation.send-request').subscribe((title: string) => {
      this.titleService.setTitle(title);
    });
    // change title when change language
    this.translationService.changeTranslatetion().pipe(takeUntil(this.unsubscribe$),
      mergeMap(lang => this.translate.get('title.vacation.send-request'))
    ).subscribe((title: string) => {
      this.titleService.setTitle(title);
    });

  }

  readstorageData() {
    const storage: any = this.localStorage.getUser();
    this.accessEmployeeID = storage.employeeID;
  }

  initForm(): void {
    this.formSearch = this.formBuilder.group({
      startDate: [null],
      endDate: [null],
      accessEmployeeID: this.accessEmployeeID,
      vacationTypeID: [[], Validators.min(-1)],
      vacationOptionID: [[], Validators.min(-1)],
      vacationStatusID: [[], Validators.min(-1)],
      pageNum: [1],
      pageSize: [this.localStorage.getPageSize() !== null ? this.localStorage.getPageSize() : 30],
    },
      {
        validator: Validators.compose([DateValidator.dateLessThan('startDate', 'endDate')])
      });
  }

  resetForm() {
    this.localStorage.removeSearchData();
    this.initForm();
    this.initDataLoad();
    this.formBaseSend.patchValue({
      startDate: null,
      endDate: null,
      vacationTypeID: [-1],
      vacationStatusID: [-1],
      vacationOptionID: [-1]
    });
    this.screenPropsSend.page = 1;
    this.formBaseSend.value.pageSize = this.localStorage.getPageSize() !== null ? this.localStorage.getPageSize() : 30;
    this.initDataVacation(null, this.localStorage.getPageSize(), this.formBaseSend);
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

  searchSendList() {
    this.screenPropsSend.loading = true;
    this.screenPropsSend.page = 1;

    //this.formBaseSend.value.filterType = type.Type.typeSend;
    this.formBaseSend.value.pageNum = 1;
    this.formBaseSend.value.pageSize = this.localStorage.getPageSize() !== null ? this.localStorage.getPageSize() : 30;

    this.valueOldForSend = JSON.parse(JSON.stringify(this.formSearch.value));
    this.formBaseSend.value.accessEmployeeID = this.valueOldForSend.accessEmployeeID;
    this.formBaseSend.value.vacationTypeID = this.valueOldForSend.vacationTypeID;
    this.formBaseSend.value.vacationOptionID = this.valueOldForSend.vacationOptionID;
    this.formBaseSend.value.vacationStatusID = this.valueOldForSend.vacationStatusID;

    let startDate = this.parseDateToMonth(this.valueOldForSend.startDate);
    if (startDate) {
      startDate.setHours(type.TimeDefault.startHourAM);
      startDate.setMinutes(type.TimeDefault.startMinAM);
    }
    let endDate = this.parseDateToMonth(this.valueOldForSend.endDate);
    if (endDate) {
      endDate.setHours(type.TimeDefault.endHourPM);
      endDate.setMinutes(type.TimeDefault.endMinPM);
    }
    this.formBaseSend.value.startDate = this.setDefaultTime(startDate);
    this.formBaseSend.value.endDate = this.setDefaultTime(endDate);

    this.initDataVacation(null, this.localStorage.getPageSize(), this.formBaseSend);
    this.localStorage.saveSearchData(this.formBaseSend.value, null);
  }

  initDataLoad() {
    this.vm001001Service.vm001001InitList().subscribe(data => {
      if (data.error === null) {
        this.selectTypes = data.content.listVacationType;
        this.selectOptions = data.content.listVacationOption;
        this.selectStatus = data.content.listVacationStatus;
        this.isDataAvailable = true;
        const dataStorage = this.localStorage.getSearchData();
        this.backFlag = this.localStorage.getBackFlag();
        if (this.backFlag != true && dataStorage === null) {
          this.formSearch.patchValue({
            vacationTypeID: [],
            vacationStatusID: [],
            vacationOptionID: []
          });
        }
        this.selectTypes.shift();
        this.selectOptions.shift();
        this.selectStatus.shift();
      }
      else {
        this.openPopupIsDataAvailable('notification.500');
      }
    });
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
      this.vm001001Service.vm001001searchVacation(formData).pipe(takeUntil(this.unsubscribe$)).subscribe(datacheck => {
        if (datacheck.error === null) {
          this.vacationSendList = datacheck.content.data;

          this.screenPropsSend.pageNum = datacheck.content.pageNum ? datacheck.content.pageNum - 1 : 0;
          this.screenPropsSend.pageSize = datacheck.content.pageSize ? datacheck.content.pageSize : 30;
          this.screenPropsSend.totalRecord = datacheck.content.totalElements;
          this.screenPropsSend.loading = false;
          this.vacationSendList = datacheck.content.data;
          //this.overideLeaveHour();
        }
      }, err => {
        const httpErr = err.error;
        this.vacationSendList = [];
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

  changePageSize(pageSize: number): void {
    this.screenPropsSend.pageSize = pageSize;
    this.localStorage.savePageSizeLocal(this.screenPropsSend.pageSize);
    this.formBaseSend.value.pageSize = pageSize;
    this.initDataVacation(null, this.localStorage.getPageSize(), this.formBaseSend);
  }

  onSort({ column, direction }: SortEvent) {
    this.direction = direction;
    this.column = column;
    this.vacationSendList = [...this.vacationSendList].sort((a, b) => {
      let res: number;
      switch (column) {
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
          res = compare(a.vacationReason, b.vacationReason);
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

  pageChangeOutput(currentPage: number) {
    this.formBaseSend.value.pageNum = currentPage;
    this.screenPropsSend.pageNum = currentPage - 1;
    this.formBaseSend.value.pageSize = this.screenPropsSend.pageSize;
    this.initDataVacation(null, this.localStorage.getPageSize(), this.formBaseSend);
  }

  onAddNewVacationTypeID(event) {
    const itemValue: number = Number(event);
    this.valueOldForSend = JSON.parse(JSON.stringify(this.formSearch.value));
    let newVacationTypeIDList: number[] = this.valueOldForSend.vacationTypeID;
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
    this.valueOldForSend = JSON.parse(JSON.stringify(this.formSearch.value));
    let newVacationOptionIDList: number[] = this.valueOldForSend.vacationOptionID;
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
    this.valueOldForSend = JSON.parse(JSON.stringify(this.formSearch.value));
    let newVacationStatusIDList: number[] = this.valueOldForSend.vacationStatusID;
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

  resetSort() {
    // reset sort
    this.column = 'startDate';
    this.direction = 'desc';
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
