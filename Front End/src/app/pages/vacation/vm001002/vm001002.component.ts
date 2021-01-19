import { DatePipe } from '@angular/common';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { TranslateService } from '@ngx-translate/core';
import { Subject } from 'rxjs';
import { mergeMap, takeUntil } from 'rxjs/operators';
import { LengthConstant } from '../../../common/constant/length';
import * as type from '../../../common/constant/type';
import { DateConfig, parseDate } from '../../../common/datepicker-config/datepicker-config';
import { Listprops } from '../../../common/page-options';
import { DateValidator } from '../../../common/validator/date-validator';
import { ValidatorForm } from '../../../common/validator/update-validity';
import { StorageService } from '../../../services/storage/storage.service';
import { TranslationService } from '../../../services/translate/translation.service';
import { ModalConfirmComponent } from '../../../theme/shared/components/modal-confirm/modal-confirm.component';
import { ToastService } from '../../../theme/shared/components/toast-container/toast-service';
import { ServerErrors } from './vm001002';
import { Vm001002Service } from './vm001002.service';
import {
  ModalNotificationMaintenanceComponent
} from '../../../theme/shared/components/modal-notification-maintenance/modal-notification-maintenance.component';
import { ScreenAction } from './../../../common/screen-action/screen-action';
import { CommonFunctionService } from './../../../services/common-function/common-function.service';
import { Title } from '@angular/platform-browser';

@Component({
  selector: 'app-vm001002',
  templateUrl: './vm001002.component.html',
  styleUrls: ['./vm001002.component.scss'],
  entryComponents: [ModalConfirmComponent]
})
export class Vm001002Component implements OnInit, OnDestroy {
  //@ViewChildren(SortTableDirective) headers: QueryList<SortTableDirective>;

  //Initial List
  selectSystemSetting: any[] = [];
  selectHolidayDetail: any[] = [];
  //selectBreakDate: any[] = [];
  selectTypes: any[] = [];
  selectOptions: any[] = [];
  selectEmployees: any[] = [];
  selectedToOrigin: any[] = [];
  selectedToAdd: any[] = [];
  selectedToRemove: any[] = [];
  selectedForward: any[] = [];
  selectedLeader: any[] = [];
  selectedManager: any[] = [];
  serverErrors = new Subject<ServerErrors>();
  hourList: any[] = [];
  baseHourList: any[] = [];
  minList: any[] = [];
  baseMinList: any[] = [];

  //Other
  formSubmit: FormGroup;
  validator = new ValidatorForm();
  Type: any = type;

  dateDiffInvalid: boolean;
  startDateInvalid: boolean = false;
  endDateInvalid: boolean = false;
  submitted: boolean;
  showBreadcumb: boolean;
  isCheck: boolean;
  isDataAvailable: boolean = false;

  screenProps = new Listprops();
  config = new DateConfig();
  accessEmployeeID: string;
  displayMessage: string;
  errorsCode: string;

  selectedEntry;
  counterTime = 0;
  endDateValue;
  maxLenghtInput = { value: LengthConstant.MAX_LENGTH_STRING };
  maxLenghtApprover3 = { value: LengthConstant.MAX_LENGTH_APPROVER };

  fullTimeConstant = type.TimeType.fullTime;
  haftTimeConstant = type.TimeType.haftTime;
  specialTimeConstant = type.TimeType.specialTime;

  authButton = new ScreenAction();

  constructor(
    private vm001002Service: Vm001002Service,
    private formBuilder: FormBuilder,
    private toastService: ToastService,
    private localStorage: StorageService,
    private translationService: TranslationService,
    private router: Router,
    private route: ActivatedRoute,
    private modalService: NgbModal,
    private translate: TranslateService,
    private datepipe: DatePipe,
    private commonFunctionService: CommonFunctionService,
    private titleService: Title,
  ) {
    this.readstorageData();
    this.authButton = this.commonFunctionService.initAuthAction(this.authButton);
    this.isCheck = true;
    this.submitted = false;
    const id = this.route.snapshot.params.id;
    this.screenProps.loading = true;
    this.initDataLoad(id);
    this.initForm();
  }

  get f() { return this.formSubmit.controls; }
  private unsubscribe$ = new Subject();

  setHourList() {
    this.baseHourList = Array<type.Hour>();
    for (let i = 0; i <= 23; i++) {
      this.baseHourList.push(new type.Hour(i, i + " "));
    }
    this.hourList = Array<type.Hour>();
    for (let i = type.TimeDefault.startHourAM; i <= type.TimeDefault.endHourAM; i++) {
      this.hourList.push(new type.Hour(i, i + " "));
    }
    for (let i = type.TimeDefault.startHourPM; i <= type.TimeDefault.endHourPM; i++) {
      this.hourList.push(new type.Hour(i, i + " "));
    }
  }

  filterHour(adding) {
    if (!adding) {
      this.hourList = this.baseHourList.filter(f => this.hourList.some(item => item.value === f.value));
    }
    else {
      this.hourList = this.baseHourList.filter(f => this.hourList.some(item => item.value === f.value || Number(f.value) == adding));
    }
  }

  setMinuteList() {
    this.baseMinList = Array<type.Minute>();
    for (let i = 0; i <= 60; i++) {
      this.baseMinList.push(new type.Minute(i, i + " "));
    }
    this.minList = Array<type.Minute>();
    for (let i = 0; i < 60; i += type.TimeDefault.stepBreakTime) {
      this.minList.push(new type.Minute(i, i + " "));
    }
  }

  filterMinute(adding) {
    if (!adding) {
      this.minList = this.baseMinList.filter(f => this.minList.some(item => item.value === f.value));
    }
    else {
      this.minList = this.baseMinList.filter(f => this.minList.some(item => item.value === f.value || Number(f.value) == adding));
    }
  }

  ngOnDestroy(): void {
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
  }

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

    /*const id = this.route.snapshot.params.id;
    this.screenProps.loading = true;
    this.initDataLoad(id);
    this.initForm();*/
  }

  getCurrentDate() {
    let d = new Date,
      dformat = [d.getDate(),
      d.getMonth() + 1,
      d.getFullYear()].join('-');
    return dformat;
  }

  initForm(): void {
    this.formSubmit = this.formBuilder.group({
      vacationID: null,
      vacationAssignGroup3: [null, [Validators.required, Validators.maxLength(LengthConstant.MAX_LENGTH_APPROVER)]],
      vacationAssignGroup1: [null, Validators.required],
      vacationAssignGroup2: [null, Validators.required],
      vacationAssignGroup1Name: [null],
      vacationAssignGroup2Name: [null],
      vacationTypeID: [[-1], Validators.required],
      vacationOptionID: [[-1], Validators.required],
      employeeID: this.accessEmployeeID,
      vacationReason: [null, [Validators.required, Validators.maxLength(LengthConstant.MAX_LENGTH_STRING)]],
      selectedEmployeeID: null,
      startDate: [null, Validators.required],
      startDateCalculator: [this.getCurrentDate(), Validators.required],
      endDate: [null, Validators.required],
      endDateCalculator: [this.getCurrentDate(), Validators.required],
      startTimeHour: type.TimeDefault.startHourAM,
      startTimeMin: type.TimeDefault.startMinAM,
      endTimeHour: type.TimeDefault.endHourPM,
      endTimeMin: type.TimeDefault.endMinPM,
      localeTime: 0,
      filterEmployee: null,
      allowUpdate: true,
      vacationGroupNote: null,
      vacationGroupOneNote: null,
      vacationGroupTwoNote: null,
      vacationGroupThreeNote: null,
      statusNameGroupOne: null,
      statusNameGroupTwo: null,
      statusNameGroupThree: null,
      vacationUpdateGroupOneDate: null,
      vacationUpdateGroupTwoDate: null,
      vacationUpdateGroupThreeDate: null,
      leaveHour: 0,
      timeWork: 0,
    },
      {
        validator: Validators.compose([DateValidator.dateLessThan('startDateCalculator', 'endDateCalculator'),
        DateValidator.compareToTime('startTimeHour', 'startTimeMin', 'endTimeHour', 'endTimeMin')])
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

    //Overide last weekend
    let splitted = this.selectSystemSetting[0].lastWeekend.split(";");
    type.TimeDefault.lastWeekend = splitted;

    this.setHourList();
    this.setMinuteList();
  }

  initDataLoad(id) {
    this.vm001002Service.vm001002InitList().pipe(takeUntil(this.unsubscribe$)).subscribe((data: any) => {
      if (data.error === null) {
        this.selectTypes = data.content.listVacationType;
        this.selectOptions = data.content.listVacationOption;
        this.selectEmployees = data.content.listEmployee;
        this.selectedToOrigin = data.content.listEmployee;
        this.selectHolidayDetail = data.content.listHolidayDetail;
        //this.selectBreakDate = type.TimeDefault.lastWeekend;
        this.selectSystemSetting = data.content.listSystemSetting;
        this.overideSetting();
        if (!id) {
          this.isDataAvailable = true;
          if (this.selectTypes != null && this.selectOptions != null) {
            this.formSubmit.patchValue({
              vacationTypeID: this.selectTypes[0]?.keyResponse,
              vacationOptionID: this.selectOptions[0]?.keyResponse,
            });
            this.selectedEntry = this.selectOptions[0]?.valueResponse.substring(0, 1);
          }
        }
        else {
          this.initDetailData(id);
        }
        this.filterHour(null);
        this.filterMinute(null);
      }
      else {
        this.openPopupIsDataAvailable('notification.500');
      }
    });
  }

  initDetailData(id) {
    let getNum: number = id;
    let isNum = Number(getNum);
    if (typeof isNum === 'number' && (isNum % 1) === 0) {
      this.vm001002Service.vm001002getDetailVacation(id).pipe(takeUntil(this.unsubscribe$)).subscribe((value) => {
        if (value) {
          if (value.content == null) {
            this.router.navigate(['/404']);
          }
          else {
            this.isDataAvailable = true;
            this.selectedEntry = value.content.isTime + "";
            let getlocaleDate = new Date(value.content.endDate);
            let getHourFromDate = getlocaleDate.getUTCHours();
            let islocaleTime = 0;
            if (getHourFromDate > 12) {
              islocaleTime = 1;
            }
            let getStartTime = new Date(value.content.startDate).toISOString().split('.')[0];
            let getEndTime = new Date(value.content.endDate).toISOString().split('.')[0];
            let getStartHour = new Date(getStartTime).getHours();
            let getStartMin = new Date(getStartTime).getMinutes();
            let getEndHour = new Date(getEndTime).getHours();
            let getEndMin = new Date(getEndTime).getMinutes();

            // Filter Hour & Minute
            this.filterHour(getStartHour);
            this.filterHour(getEndHour);
            this.filterMinute(getStartMin);
            this.filterMinute(getEndMin);

            this.selectedLeader = this.selectedToOrigin.filter(a => a.keyResponse.toString() == value.content.vacationAssignGroup1);
            this.selectedManager = this.selectedToOrigin.filter(a => a.keyResponse.toString() == value.content.vacationAssignGroup2);

            var splitted = value.content.vacationAssignGroup3.split(";");
            this.selectedForward = this.selectedToOrigin.filter(array => splitted.some(filter => filter === array.keyResponse.toString()));

            this.formSubmit.patchValue({
              vacationID: value.content.vacationID,
              vacationOptionID: value.content.vacationOptionID,
              vacationTypeID: value.content.vacationTypeID,
              employeeID: value.content.employeeID,
              vacationReason: value.content.vacationReason,
              selectedEmployeeID: value.content.selectedEmployeeID,
              startDate: value.content.startDate,
              endDate: value.content.endDate,
              startDateCalculator: this.datepipe.transform(getStartTime, 'dd-MM-yyyy'),
              endDateCalculator: this.datepipe.transform(getEndTime, 'dd-MM-yyyy'),
              startTimeHour: getStartHour,
              startTimeMin: getStartMin,
              endTimeHour: getEndHour,
              endTimeMin: getEndMin,
              localeTime: islocaleTime,
              allowUpdate: value.content.allowUpdate,
              vacationGroupOneNote: value.content.vacationGroupOneNote,
              vacationGroupTwoNote: value.content.vacationGroupTwoNote,
              vacationGroupThreeNote: value.content.vacationGroupThreeNote,
              statusNameGroupOne: value.content.statusNameGroupOne,
              statusNameGroupTwo: value.content.statusNameGroupTwo,
              statusNameGroupThree: value.content.statusNameGroupThree,
              vacationUpdateGroupOneDate: value.content.vacationUpdateGroupOneDate ? new Date(value.content.vacationUpdateGroupOneDate).toLocaleString() : null,
              vacationUpdateGroupTwoDate: value.content.vacationUpdateGroupTwoDate ? new Date(value.content.vacationUpdateGroupTwoDate).toLocaleString() : null,
              vacationUpdateGroupThreeDate: value.content.vacationUpdateGroupThreeDate ? new Date(value.content.vacationUpdateGroupThreeDate).toLocaleString() : null,
              vacationAssignGroup1: value.content.vacationAssignGroup1,
              vacationAssignGroup2: value.content.vacationAssignGroup2,
              vacationAssignGroup1Name: this.selectedLeader[0].valueResponse,
              vacationAssignGroup2Name: this.selectedManager[0].valueResponse,
            });
          }
        }
      },
        err => {
          if (err) {
            this.router.navigate(['/403']);
          }
        });
      this.showBreadcumb = true;
    } else {
      this.showBreadcumb = false;
      this.router.navigate(['/404']);
    }
  }

  setDefaultTime(date): String {
    let checkDate = new Date(date);
    if (checkDate.toString() != 'Invalid Date') {
      let isoDate = new Date(date.getTime() - date.getTimezoneOffset() * 60000).toISOString();
      //let isoDate = new Date(date).toLocaleString();      
      return isoDate;
    }
    return date;
  }

  onChangeValue() {
    this.setSubmitData();
  }

  setSubmitData() {
    if (this.selectedEntry == type.TimeType.fullTime) {
      if (this.formSubmit.controls.startDateCalculator.value) {
        let dateStart = new Date(parseDate(this.formSubmit.controls.startDateCalculator.value));
        dateStart.setHours(type.TimeDefault.startHourAM);
        dateStart.setMinutes(type.TimeDefault.startMinAM);
        if (dateStart.toString() != 'Invalid Date') {
          this.formSubmit.controls.startDate.setValue(this.setDefaultTime(dateStart));
          this.formSubmit.controls.startTimeHour.setValue(type.TimeDefault.startHourAM);
          this.formSubmit.controls.startTimeMin.setValue(type.TimeDefault.startMinAM);
          this.startDateInvalid = this.isHoliday(dateStart);
        }
        else {
          this.formSubmit.controls.startDate.setValue(this.formSubmit.controls.startDateCalculator.value);
        }
      }
      else {
        this.formSubmit.controls.startDate.setValue(this.formSubmit.controls.startDateCalculator.value);
      }
      if (this.formSubmit.controls.endDateCalculator.value) {
        let dateEnd = new Date(parseDate(this.formSubmit.controls.endDateCalculator.value));
        dateEnd.setHours(type.TimeDefault.endHourPM);
        dateEnd.setMinutes(type.TimeDefault.endMinPM);
        if (dateEnd.toString() != 'Invalid Date') {
          this.formSubmit.controls.endDate.setValue(this.setDefaultTime(dateEnd));
          this.formSubmit.controls.endTimeHour.setValue(type.TimeDefault.endHourPM);
          this.formSubmit.controls.endTimeMin.setValue(type.TimeDefault.endMinAM);
          this.endDateInvalid = this.isHoliday(dateEnd);
        }
        else {
          this.formSubmit.controls.endDate.setValue(this.formSubmit.controls.endDateCalculator.value);
        }
      }
      else {
        this.formSubmit.controls.endDate.setValue(this.formSubmit.controls.endDateCalculator.value);
      }
    }
    else if (this.selectedEntry == type.TimeType.haftTime) {
      if (this.formSubmit.controls.startDateCalculator.value) {
        this.formSubmit.controls.endDateCalculator.setValue(this.formSubmit.controls.endDateCalculator.value);
        let dateStart = new Date(parseDate(this.formSubmit.controls.startDateCalculator.value));
        if (dateStart.toString() != 'Invalid Date') {
          let dateEnd = new Date(parseDate(this.formSubmit.controls.startDateCalculator.value));
          this.startDateInvalid = this.isHoliday(dateStart);
          if (this.formSubmit.controls.localeTime.value == 0) {
            dateStart.setHours(type.TimeDefault.startHourAM);
            dateStart.setMinutes(type.TimeDefault.startMinAM);
            dateEnd.setHours(type.TimeDefault.endHourAM);
            dateEnd.setMinutes(type.TimeDefault.endMinAM);

            this.formSubmit.controls.startTimeHour.setValue(type.TimeDefault.startHourAM);
            this.formSubmit.controls.startTimeMin.setValue(type.TimeDefault.startMinAM);
            this.formSubmit.controls.endTimeHour.setValue(type.TimeDefault.endHourAM);
            this.formSubmit.controls.endTimeMin.setValue(type.TimeDefault.endMinAM);
          }
          else {
            dateStart.setHours(type.TimeDefault.startHourPM);
            dateStart.setMinutes(type.TimeDefault.startMinPM);
            dateEnd.setHours(type.TimeDefault.endHourPM);
            dateEnd.setMinutes(type.TimeDefault.endMinPM);

            this.formSubmit.controls.startTimeHour.setValue(type.TimeDefault.startHourPM);
            this.formSubmit.controls.startTimeMin.setValue(type.TimeDefault.startMinPM);
            this.formSubmit.controls.endTimeHour.setValue(type.TimeDefault.endHourPM);
            this.formSubmit.controls.endTimeMin.setValue(type.TimeDefault.endMinPM);
          }
          this.formSubmit.controls.startDate.setValue(this.setDefaultTime(dateStart));
          this.formSubmit.controls.endDate.setValue(this.setDefaultTime(dateEnd));
          this.formSubmit.controls.endDateCalculator.setValue(this.datepipe.transform(dateEnd, 'dd-MM-yyyy'));
        }
        else {
          this.formSubmit.controls.startDate.setValue(this.formSubmit.controls.startDateCalculator.value);
        }
      }
      else {
        this.formSubmit.controls.startDate.setValue(this.formSubmit.controls.startDateCalculator.value);
      }
    }
    else if (this.selectedEntry == type.TimeType.specialTime) {
      if (this.formSubmit.controls.startDateCalculator.value) {
        this.formSubmit.controls.endDateCalculator.setValue(this.formSubmit.controls.endDateCalculator.value);
        let dateStart = new Date(parseDate(this.formSubmit.controls.startDateCalculator.value));
        dateStart.setHours(this.formSubmit.controls.startTimeHour.value);
        dateStart.setMinutes(this.formSubmit.controls.startTimeMin.value);
        if (dateStart.toString() != 'Invalid Date') {
          this.startDateInvalid = this.isHoliday(dateStart);
          this.formSubmit.controls.startDate.setValue(this.setDefaultTime(dateStart));

          let dateEnd = dateStart;
          dateEnd.setHours(this.formSubmit.controls.endTimeHour.value);
          dateEnd.setMinutes(this.formSubmit.controls.endTimeMin.value);
          this.formSubmit.controls.endDate.setValue(this.setDefaultTime(dateEnd));
          this.formSubmit.controls.endDateCalculator.setValue(this.datepipe.transform(dateEnd, 'dd-MM-yyyy'));
        }
        else {
          this.formSubmit.controls.startDate.setValue(this.formSubmit.controls.startDateCalculator.value);
        }
      }
      else {
        this.formSubmit.controls.startDate.setValue(this.formSubmit.controls.startDateCalculator.value);
      }
    }
    this.formSubmit.controls.leaveHour.setValue(this.overideLeaveHour(this.formSubmit.controls.startDate.value, this.formSubmit.controls.endDate.value));
    this.formSubmit.controls.timeWork.setValue(type.TimeDefault.workTime);
    // Set selected data
    if (this.selectedForward && this.selectedForward.length > 0) {
      var strIncrease = ";";
      for (let item of this.selectedForward) {
        strIncrease = strIncrease + item.keyResponse + ";";
      }
      this.formSubmit.controls.vacationAssignGroup3.setValue(strIncrease);
    }
    else {
      this.formSubmit.controls.vacationAssignGroup3.setValue(null);
    }
  }

  isHoliday(date: Date) {
    for (let o of type.TimeDefault.lastWeekend.toString()) {
      if (date) {
        if (date.getDay() == Number(o)) {
          return true;
        }
      }
    }
    for (let p of this.selectHolidayDetail) {
      if (date) {
        let getDate = new Date(p.valueResponse);
        if (date.getDate() == getDate.getDate() && date.getMonth() == getDate.getMonth() && date.getFullYear() == getDate.getFullYear()) {
          return true;
        }
      }
    }
    return false;
  }

  overideLeaveHour(startDate, endDate) {
    let dt = new Date(startDate);
    let df = new Date(endDate);
    let diff = Math.abs(dt.getTime() - df.getTime());
    let diffHours = diff / (60 * 60 * 1000);
    let workDay = this.getBusinessDatesCount(dt, df, type.TimeDefault.lastWeekend);
    if (workDay > 1) {
      return workDay * type.TimeDefault.workTime;
    } else if (workDay == 1) {
      if (diffHours > type.TimeDefault.workTime) {
        return diffHours - type.TimeDefault.breakTime;
      } else {
        if (this.checkLocaleTime(dt) == false
          && this.checkLocaleTime(df)) {
          return diffHours - type.TimeDefault.breakTime;
        } else {
          return diffHours;
        }
      }
    } else {
      return 0;
    }
  }

  checkLocaleTime(date) {
    if ((date.getTime() % 86400000 / 3600000) > 12) {
      return true;
    }
    return false
  }

  getBusinessDatesCount(startDate, endDate, lastWeekend) {
    if (startDate && endDate && lastWeekend) {
      var count = 0;
      var curDate = startDate;
      while (curDate <= endDate) {
        var dayOfWeek = curDate.getDay();
        for (var val of lastWeekend) {
          if (dayOfWeek != val) {
            count++;
          }
          else {
            count--;
          }
          break;
        }
        curDate.setDate(curDate.getDate() + 1);
      }
      return count;
    }
    return 0;
  }

  submitVacation() {
    this.submitted = true;
    this.setSubmitData();
    if (this.formSubmit.valid && !this.startDateInvalid && !this.endDateInvalid) {
      this.submitted = false;
      if (this.f.vacationID.value) {
        this.vm001002Service.vm001002updateVacation(this.formSubmit.value).subscribe(
          (response) => {
            this.toastService.show('notification-message.save-success', { classname: 'bg-success text-light', delay: 3000 });
            this.router.navigate(['/vacation-send-list']);
          },
          (errors) => {
            if (errors) {
              this.displayErrorMessage(errors);
            }
          }
        );
      }
      else {
        this.vm001002Service.vm001002create(this.formSubmit.value).subscribe(
          (response) => {
            this.toastService.show('notification-message.save-success', { classname: 'bg-success text-light', delay: 3000 });
            this.router.navigate(['/vacation-send-list']);
          },
          (errors) => {
            if (errors) {
              this.displayErrorMessage(errors);
            }
          }
        );
      }
    }
  }

  assignLeader() {
    if (this.selectedToAdd["keyResponse"]) {
      this.formSubmit.get('vacationAssignGroup1').setValidators(null);
      this.formSubmit.get('vacationAssignGroup1').setErrors(null);
      this.formSubmit.controls.vacationAssignGroup1.setValue(this.selectedToAdd["keyResponse"]);
      this.formSubmit.controls.vacationAssignGroup1Name.setValue(this.selectedToAdd["valueResponse"]);
      this.selectedToAdd = [];
    }
  }

  assignManager() {
    if (this.selectedToAdd["keyResponse"]) {
      this.formSubmit.get('vacationAssignGroup2').setValidators(null);
      this.formSubmit.get('vacationAssignGroup2').setErrors(null);
      this.formSubmit.controls.vacationAssignGroup2.setValue(this.selectedToAdd["keyResponse"]);
      this.formSubmit.controls.vacationAssignGroup2Name.setValue(this.selectedToAdd["valueResponse"]);
      this.selectedToAdd = [];
    }
  }

  unassignForward() {
    if (this.selectedToRemove && this.selectedToRemove["keyResponse"] != null) {
      this.selectedForward = this.selectedForward.filter(a => a.keyResponse !== this.selectedToRemove["keyResponse"]);
    }
  }

  assignForward() {
    if (this.selectedToAdd) {
      let isDup = this.selectedForward.filter(a => a.keyResponse === this.selectedToAdd["keyResponse"]);
      if (this.selectedToAdd && isDup.length == 0) {
        this.selectedForward = this.selectedForward.concat(this.selectedToAdd);
        this.formSubmit.get('vacationAssignGroup3').setValidators(Validators.maxLength(LengthConstant.MAX_LENGTH_APPROVER));
        this.formSubmit.get('vacationAssignGroup3').setErrors(null);
        this.selectedToAdd = [];
      }
    }
  }

  readstorageData() {
    const storage: any = this.localStorage.getUser();
    this.accessEmployeeID = storage.employeeID;
    //this.accessEmployeeName = storage.employeeName;
  }

  onSelectionChange(entry) {
    this.selectedEntry = entry.substring(0, 1);
    this.submitted = false;
  }

  displayErrorMessage(errors) {
    const formControl = this.formSubmit.get(errors.error.error.itemName);
    if (formControl) {
      this.translationService.getTranslation(errors.error.error.code).pipe(takeUntil(this.unsubscribe$)).subscribe(errorMessage => {
        // set errors formcontrol
        this.errorsCode = errors.error.error.code;
        this.displayMessage = errorMessage;
        formControl.setErrors({
          serverError: errorMessage
        });
      });
    }
  }

  filterItem(event) {
    let isObject = typeof event;
    /*if (!event) {
      this.selectEmployees = this.selectedToOrigin;
    }*/
    this.selectEmployees = this.selectedToOrigin;
    if (isObject == 'string') {
      this.selectEmployees = this.selectEmployees.filter(a => a.valueResponse.toLowerCase().trim()
        .indexOf(event.toLowerCase().trim()) !== -1);
    }
  }

  deleteVacation() {
    if (this.f.vacationID.value) {
      this.openPopup(this.f.vacationID.value);
    }
  }

  openPopup(id: number) {
    const modalRef = this.modalService.open(ModalConfirmComponent, {centered: true});
    this.translate.get('confirm-message.delete').subscribe(
      (text: string) => {
        modalRef.componentInstance.title = text;
      }
    );
    modalRef.result.then(result => {
      if (result === 'delete') {
        this.vm001002Service.vm001002deleteVacation(id).pipe(takeUntil(this.unsubscribe$)).subscribe(resp => {
          if (resp) {
            this.toastService.show('notification-message.delete-success', { classname: 'bg-success text-light', delay: 3000 });
            this.router.navigate(['/vacation-send-list']);
          } else if (resp.error) {
            this.toastService.show('notification-message.delete-fail', { classname: 'bg-success text-light', delay: 3000 });
          }
        });
      }
    }, reason => {
    });
  }

  openPopupIsDirty() {
    // NgbModal bootstrap using abtract ViewContainerRef of angular create dynamic components
    // https://angular.io/api/core/ViewContainerRef;
    const modalRef = this.modalService.open(ModalConfirmComponent, {centered: true});
    modalRef.componentInstance.isDelete = false;
    modalRef.componentInstance.isBack = true;
    // COMUNICATION WITH DYNAMIC COMPONENTS USING @Input() or @Ouput() with componentInstance
    this.translate.get('confirm-message.update').subscribe(
      (text: string) => {
        modalRef.componentInstance.title = text;
      }
    );
    modalRef.result.then(result => {
      if (result === 'save tab') {
        if (this.formSubmit.dirty) {
          this.submitVacation();
        }
      } else {
        if (result != 'Close') {
          this.localStorage.saveBackFlag(true);
          this.router.navigate(['/vacation-send-list']);
        }
      }
    });
  }

  back(event: any) {
    if (this.formSubmit.dirty) {
      this.openPopupIsDirty();
    } else {
      // this.localStorage.saveBackFlag(true);
      this.router.navigate(['/vacation-send-list']);
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
