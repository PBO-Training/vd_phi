import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Subject } from 'rxjs';
import { mergeMap, takeUntil } from 'rxjs/operators';
import { ScreenAction } from '../../../common/screen-action/screen-action';
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
import { ShiftWork } from './vm006001-entity';
import { VM006001Service } from './vm006001.service';
import { CommonFunctionService } from '../../../services/common-function/common-function.service';
import { Title } from '@angular/platform-browser';
import { TranslateService } from '@ngx-translate/core';

@Component({
  selector: 'app-vm006001',
  templateUrl: './vm006001.component.html',
  styleUrls: ['./vm006001.component.scss']
})
export class VM006001Component implements OnInit {

  accessEmployeeID: string;
  column: string;
  direction: string;
  errorsCode: string;
  displayMessage: string;

  isDataAvailable: boolean = false;
  isCollapsed: boolean = false;
  submitted: boolean;

  formSearch: FormGroup;
  formBaseSend: FormGroup;

  config = new DateConfig();
  screenPropsSend = new Listprops();
  authButton = new ScreenAction();

  swSendList: ShiftWork[];

  listOption: any[] = [];
  listStatus: any[] = [];

  Type: any = type;

  valueOldForSend = {
    accessEmployeeID: [null],
    swStartDateAM: [null],
    swEndDatePM: [null],
    swOptionID: [null],
    swStatusID: [null],
  };

  constructor(
    private formBuilder: FormBuilder,
    private localStorage: StorageService,
    private vm006001Service: VM006001Service,
    private modalService: NgbModal,
    private router: Router,
    private commonFunctionService: CommonFunctionService,
    public translationService: TranslationService,
    private titleService: Title,
    private translate: TranslateService,

  ) {
    this.readstorageData();
    this.authButton = this.commonFunctionService.initAuthAction(this.authButton);
    this.initForm();
    this.initDataLoad();
    this.formBaseSend = this.formBuilder.group({
      accessEmployeeID: this.accessEmployeeID,
      swStartDateAM: [null],
      swEndDatePM: [null],
      swOptionID: [-1, Validators.min(-1)],
      swStatusID: [-1, Validators.min(-1)],
      pageNum: [1],
      pageSize: [this.localStorage.getPageSize() !== null ? this.localStorage.getPageSize() : 30],
    });
    setTimeout(() => {
      this.initDataShiftWork(null, this.localStorage.getPageSize(), this.formBaseSend);
    }, 1000);
  }

  ngOnInit(): void {
    // change title
    this.translate.get('title.vacation.shift-work-request').subscribe((title: string) => {
      this.titleService.setTitle(title);
    });
    // change title when change language
    this.translationService.changeTranslatetion().pipe(takeUntil(this.unsubscribe$),
      mergeMap(lang => this.translate.get('title.vacation.shift-work-request'))
    ).subscribe((title: string) => {
      this.titleService.setTitle(title);
    });
  }

  /** Find user login **/
  readstorageData() {
    const storage: any = this.localStorage.getUser();
    this.accessEmployeeID = storage.employeeID;
  }

  /** Initialize a search form **/
  initForm(): void {
    this.formSearch = this.formBuilder.group({
      accessEmployeeID: this.accessEmployeeID,
      swStartDateAM: [null],
      swEndDatePM: [null],
      swOptionID: [-1, Validators.min(-1)],
      swStatusID: [-1, Validators.min(-1)],
      pageNum: [1],
      pageSize: [this.localStorage.getPageSize() !== null ? this.localStorage.getPageSize() : 30],
    },
      {
        validator: Validators.compose([DateValidator.dateLessThan('swStartDateAM', 'swEndDatePM')])
      });
  }

  /** Initialize drop down list for search form **/
  initDataLoad() {
    this.vm006001Service.vm006001InitList().subscribe(data => {
      if (data.error === null) {
        this.listOption = data.content.listShiftWorkOption;
        this.listStatus = data.content.listShiftWorkStatus;
        this.isDataAvailable = true;
      }
      else {
        this.openPopupIsDataAvailable('notification.500');
      }
    });
  }

  get f() { return this.formSearch.controls; }

  private unsubscribe$ = new Subject();

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
      this.vm006001Service.vm006001searchShiftWork(formData).pipe(takeUntil(this.unsubscribe$)).subscribe(datacheck => {
        if (datacheck.error === null) {
          this.swSendList = datacheck.content.data;
          this.screenPropsSend.pageNum = datacheck.content.pageNum ? datacheck.content.pageNum - 1 : 0;
          this.screenPropsSend.pageSize = datacheck.content.pageSize ? datacheck.content.pageSize : 30;
          this.screenPropsSend.totalRecord = datacheck.content.totalElements;
          this.screenPropsSend.loading = false;
        }
      }, err => {
        const httpErr = err.error;
        this.swSendList = [];
        if (!httpErr) {
          return;
        }
        else {
          this.displayErrorMessage(httpErr);
        }
      });
    }
  }

  /** Search process when click on search button **/
  searchSendList() {
    this.screenPropsSend.loading = true;
    this.screenPropsSend.page = 1;

    this.formBaseSend.value.pageNum = 1;
    this.formBaseSend.value.pageSize = this.localStorage.getPageSize() !== null ? this.localStorage.getPageSize() : 30;

    this.valueOldForSend = JSON.parse(JSON.stringify(this.formSearch.value));
    this.formBaseSend.value.accessEmployeeID = this.valueOldForSend.accessEmployeeID;
    this.formBaseSend.value.swOptionID = this.valueOldForSend.swOptionID;
    this.formBaseSend.value.swStatusID = this.valueOldForSend.swStatusID;

    let startDate = this.parseDateToMonth(this.valueOldForSend.swStartDateAM);
    if (startDate) {
      startDate.setHours(type.TimeDefault.startHourAM);
      startDate.setMinutes(type.TimeDefault.startMinAM);
    }
    let endDate = this.parseDateToMonth(this.valueOldForSend.swEndDatePM);
    if (endDate) {
      endDate.setHours(type.TimeDefault.endHourPM);
      endDate.setMinutes(type.TimeDefault.endMinPM);
    }
    this.formBaseSend.value.swStartDateAM = this.setDefaultTime(startDate);
    this.formBaseSend.value.swEndDatePM = this.setDefaultTime(endDate);

    this.initDataShiftWork(null, this.localStorage.getPageSize(), this.formBaseSend);
    this.localStorage.saveSearchData(this.formBaseSend.value, null);
  }

  /** Reset search form with default value **/
  resetForm() {
    this.localStorage.removeSearchData();
    this.initForm();
    this.initDataLoad();
    this.formBaseSend.patchValue({
      swStartDateAM: null,
      swEndDatePM: null,
      swOptionID: -1,
      swStatusID: -1
    });
    this.screenPropsSend.page = 1;
    this.formBaseSend.value.pageSize = this.localStorage.getPageSize() !== null ? this.localStorage.getPageSize() : 30;
    this.initDataShiftWork(null, this.localStorage.getPageSize(), this.formBaseSend);
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
    this.swSendList = [...this.swSendList].sort((a, b) => {
      let res: number;
      switch (column) {
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
        case 'swReason':
          res = compare(a.swReason, b.swReason);
          return direction === 'asc' ? res : -res;
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

  changePageSize(pageSize: number): void {
    this.screenPropsSend.pageSize = pageSize;
    this.localStorage.savePageSizeLocal(this.screenPropsSend.pageSize);
    this.formBaseSend.value.pageSize = pageSize;
    this.initDataShiftWork(null, this.localStorage.getPageSize(), this.formBaseSend);
  }

  pageChangeOutput(currentPage: number) {
    this.formBaseSend.value.pageNum = currentPage;
    this.screenPropsSend.pageNum = currentPage - 1;
    this.formBaseSend.value.pageSize = this.screenPropsSend.pageSize;
    this.initDataShiftWork(null, this.localStorage.getPageSize(), this.formBaseSend);
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

}
