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
import { Vacation } from './vm004001-entity';
import { VM004001Service } from './vm004001.service';
import {
  ModalNotificationMaintenanceComponent
} from '../../../theme/shared/components/modal-notification-maintenance/modal-notification-maintenance.component';
import { Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Title } from '@angular/platform-browser';
import { TranslateService } from '@ngx-translate/core';

@Component({
  selector: 'app-vm004001',
  templateUrl: './vm004001.component.html',
  styleUrls: ['./vm004001.component.scss']
})
export class VM004001Component implements OnInit {

  accessEmployeeID: string;
  submitted: boolean;
  isCollapsed = false;
  displayMessage: string;
  errorsCode: string;
  formSearch: FormGroup;
  isDataAvailable: boolean = false;
  backFlag: boolean;

  direction: string;
  column: string;
  Type: any = type;

  screenPropsSummary = new Listprops();
  config = new DateConfig();

  formBaseSummary: FormGroup;
  vacationSummaryList: Vacation[];

  selectDepartments: any[] = [];
  selectTypes: any[] = [];
  yearList: any[] = [];
  selectSystemSetting: any[] = [];

  valueOldForSummary = {
    employeeName: [null],
    employeeCode: [null],
    accessEmployeeID: [null],
    departmentID: [null],
    vacationYear: new Date().getFullYear()
  };

  constructor(
    private formBuilder: FormBuilder,
    private localStorage: StorageService,
    private vm004001Service: VM004001Service,
    public translationService: TranslationService,
    private modalService: NgbModal,
    private router: Router,
    private titleService: Title,
    private translate: TranslateService,

  ) {
    this.readstorageData();
    this.initForm();
    this.initDataLoad();
    this.setYearList();
    this.formBaseSummary = this.formBuilder.group({
      employeeName: [null],
      employeeCode: [null],
      accessEmployeeID: this.accessEmployeeID,
      departmentID: [[], Validators.min(-1)],
      vacationYear: new Date().getFullYear(),
      pageNum: [1],
      pageSize: [this.localStorage.getPageSize() !== null ? this.localStorage.getPageSize() : 30],
    });

    const data = this.localStorage.getSearchDataWithKey('VACATION_SUMMARY');
    this.backFlag = this.localStorage.getBackFlagExpand();
    if (this.backFlag === true && data !== null) {
      let parseToJson = JSON.parse(JSON.stringify(data));
      let newDepartmentIDList: number[] = parseToJson.departmentID;

      this.formBaseSummary.patchValue({
        employeeName: data.employeeName,
        employeeCode: data.employeeCode,
        accessEmployeeID: data.accessEmployeeID,
        vacationYear: data.vacationYear,
        departmentID: newDepartmentIDList,
        pageNum: data.pageNum,
        pageSize: data.pageSize,
      });

      this.formSearch.patchValue({
        employeeName: data.employeeName,
        employeeCode: data.employeeCode,
        accessEmployeeID: data.accessEmployeeID,
        vacationYear: data.vacationYear,
        departmentID: newDepartmentIDList,
      });
    }
    else {
      this.formSearch.patchValue({
        employeeName: null,
        employeeCode: null,
        departmentID: [-1]
      });
      this.localStorage.removeSearchDataWithKey('VACATION_SUMMARY');
    }

    setTimeout(() => {
      this.initDataVacation(null, this.localStorage.getPageSize(), this.formBaseSummary);
    }, 1000);
    this.localStorage.saveBackFlagExpand(false);
  }

  get f() { return this.formSearch.controls; }
  private unsubscribe$ = new Subject();

  ngOnInit(): void {
    // change title
    this.translate.get('title.vacation.vacation-summary').subscribe((title: string) => {
      this.titleService.setTitle(title);
    });
    // change title when change language
    this.translationService.changeTranslatetion().pipe(takeUntil(this.unsubscribe$),
      mergeMap(lang => this.translate.get('title.vacation.vacation-summary'))
    ).subscribe((title: string) => {
      this.titleService.setTitle(title);
    });

  }

  setYearList() {
    this.yearList = Array<type.Year>();
    let end = new Date().getFullYear();
    for (let i = end; i >= 1900; i--) {
      this.yearList.push(new type.Year(i, i + " "));
    }
  }

  initForm(): void {
    this.formSearch = this.formBuilder.group({
      employeeName: [null],
      employeeCode: [null],
      accessEmployeeID: this.accessEmployeeID,
      departmentID: [[], Validators.min(-1)],
      vacationYear: new Date().getFullYear(),
      pageNum: [1],
      pageSize: [this.localStorage.getPageSize() !== null ? this.localStorage.getPageSize() : 30],
    });
  }

  readstorageData() {
    const storage: any = this.localStorage.getUser();
    this.accessEmployeeID = storage.employeeID;
  }

  initDataLoad() {
    this.vm004001Service.vm004001InitList().subscribe(data => {
      if (data.error === null) {
        this.selectDepartments = data.content.listDepartment;
        this.selectTypes = data.content.listVacationType;
        this.isDataAvailable = true;
        this.selectSystemSetting = data.content.listSystemSetting;
        this.overideSetting();
        const dataStorage = this.localStorage.getSearchDataWithKey('VACATION_SUMMARY');
        this.backFlag = this.localStorage.getBackFlagExpand();
        if (this.backFlag != true && dataStorage === null) {
          this.formSearch.patchValue({
            departmentID: [],
            vacationYear: new Date().getFullYear()
          });
        }
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
    this.formBaseSummary.value.accessEmployeeID = this.valueOldForSummary.accessEmployeeID;
    this.formBaseSummary.value.departmentID = this.valueOldForSummary.departmentID;
    this.formBaseSummary.value.vacationYear = this.valueOldForSummary.vacationYear;

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
      this.vm004001Service.vm004001searchVacation(formData).pipe(takeUntil(this.unsubscribe$)).subscribe(datacheck => {
        if (datacheck.error === null) {
          this.vacationSummaryList = datacheck.content.data;

          this.screenPropsSummary.pageNum = datacheck.content.pageNum ? datacheck.content.pageNum - 1 : 0;
          this.screenPropsSummary.pageSize = datacheck.content.pageSize ? datacheck.content.pageSize : 30;
          this.screenPropsSummary.totalRecord = datacheck.content.totalElements;
          this.screenPropsSummary.loading = false;
          this.vacationSummaryList = datacheck.content.data;
          //this.overideLeaveHour();
        }
      }, err => {
        const httpErr = err.error;
        this.vacationSummaryList = [];
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
    this.localStorage.removeSearchDataWithKey('VACATION_SUMMARY');
    this.initForm();
    this.initDataLoad();
    this.formBaseSummary.patchValue({
      employeeCode: null,
      employeeName: null,
      vacationYear: new Date().getFullYear(),
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
    this.vacationSummaryList = [...this.vacationSummaryList].sort((a, b) => {
      let nullableStringA = '';
      let nullableStringB = '';
      let res: number;
      switch (column) {
        case 'employeeCode':
          res = a.employeeCode.localeCompare(b.employeeCode);
          return direction === 'asc' ? res : -res;
        case 'employeeName':
          nullableStringA = a.firstName + a.lastName;
          nullableStringB = b.firstName + b.lastName;
          res = nullableStringA.localeCompare(nullableStringB);
          return direction === 'asc' ? res : -res;
        case 'departmentName':
          res = compare(a.departmentName, b.departmentName);
          return direction === 'asc' ? res : -res;
        case 'totalVacation':
          res = compare(+a.totalVacation, +b.totalVacation);
          return direction === 'asc' ? res : -res;
        case 'totalLeave':
          res = compare(+a.totalLeave, +b.totalLeave);
          return direction === 'asc' ? res : -res;
        case 'bonusVacation':
          res = compare(+a.bonusVacation, +b.bonusVacation);
          return direction === 'asc' ? res : -res;
        case 'remainVacation':
          res = compare(+a.remainVacation, +b.remainVacation);
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

  overideSetting() {
    //Overide start time AM
    let indexStartHourAM = this.selectSystemSetting[0].timeStartWorkAM.indexOf(":");
    let splittedStartHourAM = parseInt(this.selectSystemSetting[0].timeStartWorkAM.slice(0, indexStartHourAM));
    let splittedStartMinAM = parseInt(this.selectSystemSetting[0].timeStartWorkAM.slice(indexStartHourAM + 1));
    type.TimeDefault.startHourAM = splittedStartHourAM;
    type.TimeDefault.startMinAM = splittedStartMinAM;

    //Overide end time AM
    let indexEndHourAM = this.selectSystemSetting[0].timeEndWorkAM.indexOf(":");
    let splittedEndHourAM = parseInt(this.selectSystemSetting[0].timeEndWorkAM.slice(0, indexEndHourAM));
    let splittedEndMinAM = parseInt(this.selectSystemSetting[0].timeEndWorkAM.slice(indexEndHourAM + 1));
    type.TimeDefault.endHourAM = splittedEndHourAM;
    type.TimeDefault.endMinAM = splittedEndMinAM;

    //Overide start time PM
    let indexStartHourPM = this.selectSystemSetting[0].timeStartWorkPM.indexOf(":");
    let splittedStartHourPM = parseInt(this.selectSystemSetting[0].timeStartWorkPM.slice(0, indexStartHourPM));
    let splittedStartMinPM = parseInt(this.selectSystemSetting[0].timeStartWorkPM.slice(indexStartHourPM + 1));
    type.TimeDefault.startHourPM = splittedStartHourPM;
    type.TimeDefault.startMinPM = splittedStartMinPM;

    //Overide end time PM
    let indexEndHourPM = this.selectSystemSetting[0].timeEndWorkPM.indexOf(":");
    let splittedEndHourPM = parseInt(this.selectSystemSetting[0].timeEndWorkPM.slice(0, indexEndHourPM));
    let splittedEndMinPM = parseInt(this.selectSystemSetting[0].timeEndWorkPM.slice(indexEndHourPM + 1));
    type.TimeDefault.endHourPM = splittedEndHourPM;
    type.TimeDefault.endMinPM = splittedEndMinPM;

    //Overide step break time
    type.TimeDefault.stepBreakTime = parseInt(this.selectSystemSetting[0].stepBreakTime);

    //Overide Breaktime
    let dtBreak = new Date();
    dtBreak.setHours(type.TimeDefault.endHourAM);
    dtBreak.setMinutes(type.TimeDefault.endMinAM);
    let dfBreak = new Date();
    dfBreak.setHours(type.TimeDefault.startHourPM);
    dfBreak.setMinutes(type.TimeDefault.startMinPM);

    let diffBreak = Math.abs(dtBreak.getTime() - dfBreak.getTime());
    let diffHourBreak = diffBreak / (60 * 60 * 1000);
    type.TimeDefault.breakTime = diffHourBreak;

    //Overide Worktime
    let dtWork = new Date();
    dtWork.setHours(type.TimeDefault.startHourAM);
    dtWork.setMinutes(type.TimeDefault.startMinAM);
    let dfWork = new Date();
    dfWork.setHours(type.TimeDefault.endHourPM);
    dfWork.setMinutes(type.TimeDefault.endMinPM);

    let diffWork = Math.abs(dtWork.getTime() - dfWork.getTime());
    let diffHourWork = diffWork / (60 * 60 * 1000);
    type.TimeDefault.workTime = diffHourWork - diffHourBreak;
  }

}
