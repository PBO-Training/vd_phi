import { Component, OnDestroy, OnInit, QueryList, ViewChildren } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ServerErrors } from './vm002002';
import { Vm002002Service } from './vm002002.service';
import { Listprops } from '../../../common/page-options';
import { ToastService } from '../../../theme/shared/components/toast-container/toast-service';
import { StorageService } from '../../../services/storage/storage.service';
import { Subject } from 'rxjs';
import { mergeMap, takeUntil } from 'rxjs/operators';
import { TranslationService } from '../../../services/translate/translation.service';
import { ValidatorForm } from '../../../common/validator/update-validity';
import { LengthConstant } from '../../../common/constant/length';
import { ActivatedRoute, Router } from '@angular/router';
import * as type from '../../../common/constant/type';
import { DatePipe } from '@angular/common';
import {
  ModalNotificationMaintenanceComponent
} from '../../../theme/shared/components/modal-notification-maintenance/modal-notification-maintenance.component';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ScreenAction } from './../../../common/screen-action/screen-action';
import { CommonFunctionService } from './../../../services/common-function/common-function.service';
import { TranslateService } from '@ngx-translate/core';
import { Title } from '@angular/platform-browser';

@Component({
  selector: 'app-Vm002002',
  templateUrl: './Vm002002.component.html',
  styleUrls: ['./Vm002002.component.scss']
})
export class Vm002002Component implements OnInit, OnDestroy {
  //Initial List
  selectSystemSetting: any[] = [];
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

  //Other
  formSubmit: FormGroup;
  validator = new ValidatorForm();
  Type: any = type;
  maxLenghtInput = { value: LengthConstant.MAX_LENGTH_STRING };
  hourList: any[] = [];
  baseHourList: any[] = [];
  minList: any[] = [];
  baseMinList: any[] = [];

  dateDiffInvalid: boolean;
  startDateInvalid: boolean;
  endDateInvalid: boolean;
  submitted: boolean;
  showBreadcumb: boolean;
  isCheck: boolean;
  isDataAvailable: boolean = false;
  accessEmployeeName: string;
  isApprove: boolean;

  screenProps = new Listprops();
  accessEmployeeID: string;
  displayMessage: string;
  errorsCode: string;

  selectedEntry;
  counterTime = 0;
  endDateValue;

  fullTimeConstant = type.TimeType.fullTime;
  haftTimeConstant = type.TimeType.haftTime;
  specialTimeConstant = type.TimeType.specialTime;
  authButton = new ScreenAction();

  constructor(
    private Vm002002Service: Vm002002Service,
    private formBuilder: FormBuilder,
    private toastService: ToastService,
    private localStorage: StorageService,
    private translationService: TranslationService,
    private router: Router,
    private route: ActivatedRoute,
    private datepipe: DatePipe,
    private modalService: NgbModal,
    private commonFunctionService: CommonFunctionService,
    private titleService: Title,
    private translate: TranslateService,

  ) {
    this.readstorageData();
    this.authButton = this.commonFunctionService.initAuthAction(this.authButton);
    this.isCheck = true;
    const id = this.route.snapshot.params.id;
    this.screenProps.loading = true;
    this.submitted = false;

    this.initForm();
    this.initDataLoad(id);
  }

  get f() { return this.formSubmit.controls; }
  private unsubscribe$ = new Subject();

  ngOnDestroy(): void {
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
  }

  ngOnInit(): void {
    // change title
    this.translate.get('title.vacation.receive-request').subscribe((title: string) => {
      this.titleService.setTitle(title);
    });

    // change title when change language
    this.translationService.changeTranslatetion().pipe(takeUntil(this.unsubscribe$),
      mergeMap(lang => this.translate.get('title.vacation.receive-request'))
    ).subscribe((title: string) => {
      this.titleService.setTitle(title);
    });

    /*const id = this.route.snapshot.params.id;
    this.screenProps.loading = true;
    this.submitted = false;

    this.initForm();
    this.initDataLoad(id);*/
  }

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
      this.baseMinList.push(new type.Hour(i, i + " "));
    }
    this.minList = Array<type.Minute>();
    for (let i = 0; i < 60; i += type.TimeDefault.stepBreakTime) {
      this.minList.push(new type.Hour(i, i + " "));
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

  setValidators() {
    this.formSubmit.controls.vacationGroupNote.setValidators([Validators.maxLength(LengthConstant.MAX_LENGTH_STRING)]);
    this.validator.updateTreeValidity(this.formSubmit);
  }

  initForm(): void {
    this.formSubmit = this.formBuilder.group({
      vacationID: null,
      vacationAssignGroup3: null,
      vacationAssignGroup1: null,
      vacationAssignGroup2: null,
      vacationTypeID: [-1],
      vacationOptionID: [-1],
      employeeID: this.accessEmployeeID,
      vacationReason: null,
      selectedEmployeeID: null,
      startDate: null,
      endDate: null,
      startTimeHour: null,
      endTimeHour: null,
      startTimeMin: null,
      endTimeMin: null,
      localeTime: 0,
      filterEmployee: null,
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
      accessEmployeeID: null,
      isApprove: null,
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

    type.TimeDefault.stepBreakTime = parseInt(this.selectSystemSetting[0].stepBreakTime);

    this.setHourList();
    this.setMinuteList();
  }

  initDataLoad(id) {
    this.Vm002002Service.Vm002002InitList().pipe(takeUntil(this.unsubscribe$)).subscribe((data: any) => {
      if (data.error === null) {
        this.selectTypes = data.content.listVacationType;
        this.selectOptions = data.content.listVacationOption;
        this.selectEmployees = data.content.listEmployee;
        this.selectedToOrigin = data.content.listEmployee;
        this.selectSystemSetting = data.content.listSystemSetting;
        this.overideSetting();
        if (!id) {
          if (this.selectTypes != null && this.selectOptions != null) {
            this.formSubmit.patchValue({
              vacationTypeID: this.selectTypes[0]?.keyResponse,
              vacationOptionID: this.selectOptions[0]?.keyResponse,
            });
            this.selectedEntry = this.selectOptions[0]?.valueResponse.substring(0, 1);
          }
        }
        this.initDetailData(id);
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
      this.Vm002002Service.Vm002002getDetailVacation(id, this.accessEmployeeID).pipe(takeUntil(this.unsubscribe$)).subscribe((value) => {
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
              startDate: this.datepipe.transform(getStartTime, 'dd-MM-yyyy'),
              endDate: this.datepipe.transform(getEndTime, 'dd-MM-yyyy'),
              startTimeHour: getStartHour,
              startTimeMin: getStartMin,
              endTimeHour: getEndHour,
              endTimeMin: getEndMin,
              localeTime: islocaleTime,
              vacationGroupOneNote: value.content.vacationGroupOneNote,
              vacationGroupTwoNote: value.content.vacationGroupTwoNote,
              vacationGroupThreeNote: value.content.vacationGroupThreeNote,
              statusNameGroupOne: value.content.statusNameGroupOne,
              statusNameGroupTwo: value.content.statusNameGroupTwo,
              statusNameGroupThree: value.content.statusNameGroupThree,
              vacationUpdateGroupOneDate: value.content.vacationUpdateGroupOneDate ? new Date(value.content.vacationUpdateGroupOneDate).toLocaleString() : null,
              vacationUpdateGroupTwoDate: value.content.vacationUpdateGroupTwoDate ? new Date(value.content.vacationUpdateGroupTwoDate).toLocaleString() : null,
              vacationUpdateGroupThreeDate: value.content.vacationUpdateGroupThreeDate ? new Date(value.content.vacationUpdateGroupThreeDate).toLocaleString() : null,
            });
          }
        }
      },
        err => {
          if (err) {
            this.router.navigate(['/403']);
          }
        });
      //this.showBreadcumb = true;
    } else {
      //this.showBreadcumb = false;
      this.router.navigate(['/404']);
    }
  }

  onSubmit(isApprove: boolean): void {
    if (isApprove === true) {
      this.isApprove = isApprove;
      this.formSubmit.controls.isApprove.setValue(isApprove);
    }
    if (isApprove === false) {
      this.isApprove = isApprove;
      this.formSubmit.controls.isApprove.setValue(isApprove);
    }
    this.submitVacation();
  }

  submitVacation() {
    this.submitted = true;
    this.setValidators();
    if (this.formSubmit.valid) {
      if (this.f.vacationID.value) {
        this.Vm002002Service.Vm002002approveVacation(this.formSubmit.value).pipe(takeUntil(this.unsubscribe$)).subscribe(
          (response) => {
            this.toastService.show('notification-message.save-success', { classname: 'bg-success text-light', delay: 3000 });
            this.router.navigate(['/vacation-receive-list']);
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

  readstorageData() {
    const storage: any = this.localStorage.getUser();
    this.accessEmployeeID = storage.employeeID;
    this.accessEmployeeName = storage.employeeName;
  }

  back(event: any) {
    // this.localStorage.saveBackFlag(true);
    this.router.navigate(['/vacation-receive-list']);
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
