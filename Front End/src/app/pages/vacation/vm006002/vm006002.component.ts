import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Title } from '@angular/platform-browser';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { TranslateService } from '@ngx-translate/core';
import { Subject } from 'rxjs';
import { mergeMap, takeUntil } from 'rxjs/operators';
import { LengthConstant } from '../../../common/constant/length';
import * as type from '../../../common/constant/type';
import { DateConfig, parseDate } from '../../../common/datepicker-config/datepicker-config';
import { DateValidator } from '../../../common/validator/date-validator';
import { StorageService } from '../../../services/storage/storage.service';
import { TranslationService } from '../../../services/translate/translation.service';
import { ModalConfirmComponent } from '../../../theme/shared/components/modal-confirm/modal-confirm.component';
import {
  ModalNotificationMaintenanceComponent
} from '../../../theme/shared/components/modal-notification-maintenance/modal-notification-maintenance.component';
import { ToastService } from '../../../theme/shared/components/toast-container/toast-service';
import { ScreenAction } from './../../../common/screen-action/screen-action';
import { CommonFunctionService } from './../../../services/common-function/common-function.service';
import { VM006002Service } from './vm006002.service';

@Component({
  selector: 'app-vm006002',
  templateUrl: './vm006002.component.html',
  styleUrls: ['./vm006002.component.scss'],
  entryComponents: [ModalConfirmComponent]
})
export class VM006002Component implements OnInit {

  showBreadcumb: boolean;
  submitted: boolean;
  isDataAvailable: boolean = false;

  accessEmployeeID: string;
  displayMessage: string;
  errorsCode: string;
  selectedEntry: string;

  formSubmit: FormGroup;

  listOption: any[] = [];
  listEmployee: any[] = [];
  selectedToAdd: any[] = [];
  selectedToRemove: any[] = [];
  selectedForward: any[] = [];
  selectedToOrigin: any[] = [];
  selectedLeader: any[] = [];
  selectedManager: any[] = [];

  config = new DateConfig();
  authButton = new ScreenAction();

  Type: any = type;

  maxLenghtInput = { value: LengthConstant.MAX_LENGTH_STRING };
  maxLenghtApprover3 = { value: LengthConstant.MAX_LENGTH_APPROVER };

  constructor(
    private localStorage: StorageService,
    private formBuilder: FormBuilder,
    private vm006002Service: VM006002Service,
    private router: Router,
    private route: ActivatedRoute,
    private modalService: NgbModal,
    private translate: TranslateService,
    private toastService: ToastService,
    private translationService: TranslationService,
    private datepipe: DatePipe,
    private commonFunctionService: CommonFunctionService,
    private titleService: Title,
  ) {
    this.readstorageData();
    this.authButton = this.commonFunctionService.initAuthAction(this.authButton);
    const id = this.route.snapshot.params.id;
    this.initDataLoad(id);
    this.initForm();
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

  get f() { return this.formSubmit.controls; }

  private unsubscribe$ = new Subject();

  /** Initialize a submit form **/
  initForm(): void {
    this.formSubmit = this.formBuilder.group({
      allowUpdate: true,
      employeeID: this.accessEmployeeID,
      swID: null,
      swOptionID: [-1, Validators.required],
      swStartDateAM: [null, Validators.required],
      swEndDateAM: [null, Validators.required],
      startDateCalculator: [this.getCurrentDate(), Validators.required],
      swStartDatePM: [null, Validators.required],
      swEndDatePM: [null, Validators.required],
      endDateCalculator: [this.getCurrentDate(), Validators.required],
      swReason: [null, [Validators.required, Validators.maxLength(LengthConstant.MAX_LENGTH_STRING)]],
      swAssignGroupOne: [null, Validators.required],
      swAssignGroupTwo: [null, Validators.required],
      swAssignGroupThree: [null, [Validators.required, Validators.maxLength(LengthConstant.MAX_LENGTH_APPROVER)]],
      swAssignGroupOneNote: null,
      swAssignGroupTwoNote: null,
      swAssignGroupThreeNote: null,
      statusNameGroupOne: null,
      statusNameGroupTwo: null,
      statusNameGroupThree: null,
      swUpdateGroupOneDate: null,
      swUpdateGroupTwoDate: null,
      swUpdateGroupThreeDate: null,
      swAssignGroup1Name: [null],
      swAssignGroup2Name: [null],
      selectedEmployeeID: null,
      filterEmployee: null,
    },
      {
        validator: Validators.compose([DateValidator.dateLessThan('startDateCalculator', 'endDateCalculator')])
      });
  }

  /** Check process and initialize data **/
  initDataLoad(id: number) {
    this.vm006002Service.vm006002InitList().pipe(takeUntil(this.unsubscribe$)).subscribe((data: any) => {
      if (data.error === null) {
        this.listOption = data.content.listShiftWorkOption;
        this.listEmployee = data.content.listEmployee;
        this.selectedToOrigin = data.content.listEmployee;
        // Check create/get detail process
        if (!id) {
          this.isDataAvailable = true;
          if (this.listOption != null) {
            this.formSubmit.patchValue({
              swOptionID: this.listOption[0]?.swOptionID,
            });
            this.selectedEntry = this.listOption[0]?.swOptionName.substring(6, 7);
          }
        }
        else {
          this.initDetailData(id);
        }
      }
      else {
        this.openPopupIsDataAvailable('notification.500');
      }
    });
  }

  /** Initialize data when process is get detail **/
  initDetailData(id: number) {
    let getNum: number = id;
    let isNum = Number(getNum);
    if (typeof isNum === 'number' && (isNum % 1) === 0) {
      this.vm006002Service.vm006002GetDetailShiftWork(id).pipe(takeUntil(this.unsubscribe$)).subscribe((value) => {
        if (value) {
          if (value.content == null) {
            this.router.navigate(['/404']);
          }
          else {
            this.isDataAvailable = true;

            this.selectedLeader = this.selectedToOrigin.filter(a => a.keyResponse.toString() == value.content.swAssignGroupOne);
            this.selectedManager = this.selectedToOrigin.filter(a => a.keyResponse.toString() == value.content.swAssignGroupTwo);
            var splitted = value.content.swAssignGroupThree.split(";");
            this.selectedForward = this.selectedToOrigin.filter(array => splitted.some(filter => filter === array.keyResponse.toString()));

            let getStartTime = new Date(value.content.swStartDateAM).toISOString().split('.')[0];
            let getEndTime = new Date(value.content.swEndDatePM).toISOString().split('.')[0];

            this.formSubmit.patchValue({
              allowUpdate: value.content.allowUpdate,
              employeeID: value.content.employeeID,
              swID: value.content.swID,
              swOptionID: value.content.swOptionID,
              swStartDateAM: value.content.swStartDateAM,
              swEndDateAM: value.content.swEndDateAM,
              startDateCalculator: this.datepipe.transform(getStartTime, 'dd-MM-yyyy'),
              swStartDatePM: value.content.swStartDatePM,
              swEndDatePM: value.content.swEndDatePM,
              endDateCalculator: this.datepipe.transform(getEndTime, 'dd-MM-yyyy'),
              swReason: value.content.swReason,
              swAssignGroupOne: value.content.swAssignGroupOne,
              swAssignGroupTwo: value.content.swAssignGroupTwo,
              swAssignGroupOneNote: value.content.swAssignGroupOneNote,
              swAssignGroupTwoNote: value.content.swAssignGroupTwoNote,
              swAssignGroupThreeNote: value.content.swAssignGroupThreeNote,
              statusNameGroupOne: value.content.statusNameGroupOne,
              statusNameGroupTwo: value.content.statusNameGroupTwo,
              statusNameGroupThree: value.content.statusNameGroupThree,
              swUpdateGroupOneDate: value.content.swUpdateGroupOneDate,
              swUpdateGroupTwoDate: value.content.swUpdateGroupTwoDate,
              swUpdateGroupThreeDate: value.content.swUpdateGroupThreeDate,
              swAssignGroup1Name: this.selectedLeader[0].valueResponse,
              swAssignGroup2Name: this.selectedManager[0].valueResponse,
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

  /** Submit process when click on Save button **/
  submitShiftWork() {
    this.submitted = true;
    this.setSubmitData();
    if (this.formSubmit.valid) {
      this.submitted = false;
      // Check update or create process
      if (this.f.swID.value) {
        this.vm006002Service.vm006002UpdateShiftWork(this.formSubmit.value).subscribe(
          (response) => {
            this.toastService.show('notification-message.save-success', { classname: 'bg-success text-light', delay: 3000 });
            this.router.navigate(['/shift-work']);
          },
          (errors) => {
            if (errors) {
              this.displayErrorMessage(errors);
            }
          }
        );
      }
      else {
        this.vm006002Service.vm006002Create(this.formSubmit.value).subscribe(
          (response) => {
            this.toastService.show('notification-message.save-success', { classname: 'bg-success text-light', delay: 3000 });
            this.router.navigate(['/shift-work']);
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

  onChangeValue() {
    this.setSubmitData();
  }

  /** Process date time, assgin group 3 before submit **/
  setSubmitData() {
    this.overideTime();
    if (this.formSubmit.controls.startDateCalculator.value) {
      let dateStartAM = new Date(parseDate(this.formSubmit.controls.startDateCalculator.value));
      dateStartAM.setHours(type.TimeDefault.startHourAM);
      dateStartAM.setMinutes(type.TimeDefault.startMinAM);
      let dateEndAM = new Date(parseDate(this.formSubmit.controls.startDateCalculator.value));
      dateEndAM.setHours(type.TimeDefault.endHourAM);
      dateEndAM.setMinutes(type.TimeDefault.endMinAM);
      if (dateStartAM.toString() != 'Invalid Date' && dateEndAM.toString() != 'Invalid Date') {
        this.formSubmit.controls.swStartDateAM.setValue(this.setDefaultTime(dateStartAM));
        this.formSubmit.controls.swEndDateAM.setValue(this.setDefaultTime(dateEndAM));
      }
      else {
        this.formSubmit.controls.swStartDateAM.setValue(this.formSubmit.controls.startDateCalculator.value);
        this.formSubmit.controls.swEndDateAM.setValue(this.formSubmit.controls.startDateCalculator.value);
      }
    }
    else {
      this.formSubmit.controls.swStartDateAM.setValue(this.formSubmit.controls.startDateCalculator.value);
      this.formSubmit.controls.swEndDateAM.setValue(this.formSubmit.controls.startDateCalculator.value);
    }

    if (this.formSubmit.controls.endDateCalculator.value) {
      let dateStartPM = new Date(parseDate(this.formSubmit.controls.endDateCalculator.value));
      dateStartPM.setHours(type.TimeDefault.startHourPM);
      dateStartPM.setMinutes(type.TimeDefault.startMinPM);
      let dateEndPM = new Date(parseDate(this.formSubmit.controls.endDateCalculator.value));
      dateEndPM.setHours(type.TimeDefault.endHourPM);
      dateEndPM.setMinutes(type.TimeDefault.endMinPM);
      if (dateStartPM.toString() != 'Invalid Date' && dateEndPM.toString() != 'Invalid Date') {
        this.formSubmit.controls.swStartDatePM.setValue(this.setDefaultTime(dateStartPM));
        this.formSubmit.controls.swEndDatePM.setValue(this.setDefaultTime(dateEndPM));
      }
      else {
        this.formSubmit.controls.swStartDatePM.setValue(this.formSubmit.controls.endDateCalculator.value);
        this.formSubmit.controls.swEndDatePM.setValue(this.formSubmit.controls.endDateCalculator.value);
      }
    }
    else {
      this.formSubmit.controls.swStartDatePM.setValue(this.formSubmit.controls.endDateCalculator.value);
      this.formSubmit.controls.swEndDatePM.setValue(this.formSubmit.controls.endDateCalculator.value);
    }
    // Set selected data
    if (this.selectedForward && this.selectedForward.length > 0) {
      var strIncrease = ";";
      for (let item of this.selectedForward) {
        strIncrease = strIncrease + item.keyResponse + ";";
      }
      this.formSubmit.controls.swAssignGroupThree.setValue(strIncrease);
    }
    else {
      this.formSubmit.controls.swAssignGroupThree.setValue(null);
    }
  }

  /** Overide start-end time with each shift work **/
  overideTime() {
    if (this.selectedEntry == type.ShiftOption.SHIFT1) {
      let indexStartHourAM = this.listOption[0].swOptionStartTimeAM.indexOf(":");
      let splittedStartHourAM = parseInt(this.listOption[0].swOptionStartTimeAM.slice(0, indexStartHourAM));
      let splittedStartMinAM = parseInt(this.listOption[0].swOptionStartTimeAM.slice(indexStartHourAM + 1));
      type.TimeDefault.startHourAM = splittedStartHourAM;
      type.TimeDefault.startMinAM = splittedStartMinAM;

      let indexEndHourAM = this.listOption[0].swOptionEndTimeAM.indexOf(":");
      let splittedEndHourAM = parseInt(this.listOption[0].swOptionEndTimeAM.slice(0, indexEndHourAM));
      let splittedEndMinAM = parseInt(this.listOption[0].swOptionEndTimeAM.slice(indexEndHourAM + 1));
      type.TimeDefault.endHourAM = splittedEndHourAM;
      type.TimeDefault.endMinAM = splittedEndMinAM;

      let indexStartHourPM = this.listOption[0].swOptionStartTimePM.indexOf(":");
      let splittedStartHourPM = parseInt(this.listOption[0].swOptionStartTimePM.slice(0, indexStartHourPM));
      let splittedStartMinPM = parseInt(this.listOption[0].swOptionStartTimePM.slice(indexStartHourPM + 1));
      type.TimeDefault.startHourPM = splittedStartHourPM;
      type.TimeDefault.startMinPM = splittedStartMinPM;

      let indexEndHourPM = this.listOption[0].swOptionEndTimePM.indexOf(":");
      let splittedEndHourPM = parseInt(this.listOption[0].swOptionEndTimePM.slice(0, indexEndHourPM));
      let splittedEndMinPM = parseInt(this.listOption[0].swOptionEndTimePM.slice(indexEndHourPM + 1));
      type.TimeDefault.endHourPM = splittedEndHourPM;
      type.TimeDefault.endMinPM = splittedEndMinPM;
    }
    if (this.selectedEntry == type.ShiftOption.SHIFT2) {
      let indexStartHourAM = this.listOption[1].swOptionStartTimeAM.indexOf(":");
      let splittedStartHourAM = parseInt(this.listOption[1].swOptionStartTimeAM.slice(0, indexStartHourAM));
      let splittedStartMinAM = parseInt(this.listOption[1].swOptionStartTimeAM.slice(indexStartHourAM + 1));
      type.TimeDefault.startHourAM = splittedStartHourAM;
      type.TimeDefault.startMinAM = splittedStartMinAM;

      let indexEndHourAM = this.listOption[1].swOptionEndTimeAM.indexOf(":");
      let splittedEndHourAM = parseInt(this.listOption[1].swOptionEndTimeAM.slice(0, indexEndHourAM));
      let splittedEndMinAM = parseInt(this.listOption[1].swOptionEndTimeAM.slice(indexEndHourAM + 1));
      type.TimeDefault.endHourAM = splittedEndHourAM;
      type.TimeDefault.endMinAM = splittedEndMinAM;

      let indexStartHourPM = this.listOption[1].swOptionStartTimePM.indexOf(":");
      let splittedStartHourPM = parseInt(this.listOption[1].swOptionStartTimePM.slice(0, indexStartHourPM));
      let splittedStartMinPM = parseInt(this.listOption[1].swOptionStartTimePM.slice(indexStartHourPM + 1));
      type.TimeDefault.startHourPM = splittedStartHourPM;
      type.TimeDefault.startMinPM = splittedStartMinPM;

      let indexEndHourPM = this.listOption[1].swOptionEndTimePM.indexOf(":");
      let splittedEndHourPM = parseInt(this.listOption[1].swOptionEndTimePM.slice(0, indexEndHourPM));
      let splittedEndMinPM = parseInt(this.listOption[1].swOptionEndTimePM.slice(indexEndHourPM + 1));
      type.TimeDefault.endHourPM = splittedEndHourPM;
      type.TimeDefault.endMinPM = splittedEndMinPM;
    }
  }

  onSelectionChange(entry: string) {
    this.selectedEntry = entry.substring(6, 7);
    this.submitted = false;
  }

  /** Select Project Leader button **/
  assignLeader() {
    if (this.selectedToAdd["keyResponse"]) {
      this.formSubmit.get('swAssignGroupOne').setValidators(null);
      this.formSubmit.get('swAssignGroupOne').setErrors(null);
      this.formSubmit.controls.swAssignGroupOne.setValue(this.selectedToAdd["keyResponse"]);
      this.formSubmit.controls.swAssignGroup1Name.setValue(this.selectedToAdd["valueResponse"]);
      this.selectedToAdd = [];
    }
  }

  /** Select Team Manager button **/
  assignManager() {
    if (this.selectedToAdd["keyResponse"]) {
      this.formSubmit.get('swAssignGroupTwo').setValidators(null);
      this.formSubmit.get('swAssignGroupTwo').setErrors(null);
      this.formSubmit.controls.swAssignGroupTwo.setValue(this.selectedToAdd["keyResponse"]);
      this.formSubmit.controls.swAssignGroup2Name.setValue(this.selectedToAdd["valueResponse"]);
      this.selectedToAdd = [];
    }
  }

  /** Unselect Team GA button **/
  unassignForward() {
    if (this.selectedToRemove && this.selectedToRemove["keyResponse"] != null) {
      this.selectedForward = this.selectedForward.filter(a => a.keyResponse !== this.selectedToRemove["keyResponse"]);
    }
  }

  /** Select Team GA button **/
  assignForward() {
    if (this.selectedToAdd) {
      let isDup = this.selectedForward.filter(a => a.keyResponse === this.selectedToAdd["keyResponse"]);
      if (this.selectedToAdd && isDup.length == 0) {
        this.selectedForward = this.selectedForward.concat(this.selectedToAdd);
        this.formSubmit.get('swAssignGroupThree').setValidators(Validators.maxLength(LengthConstant.MAX_LENGTH_APPROVER));
        this.formSubmit.get('swAssignGroupThree').setErrors(null);
        this.selectedToAdd = [];
      }
    }
  }

  /** Filter in list employee **/
  filterItem(event) {
    let isObject = typeof event;
    this.listEmployee = this.selectedToOrigin;
    if (isObject == 'string') {
      this.listEmployee = this.listEmployee.filter(a => a.valueResponse.toLowerCase().trim()
        .indexOf(event.toLowerCase().trim()) !== -1);
    }
  }

  /** Delete a shift work record **/
  deleteShiftWork() {
    if (this.f.swID.value) {
      this.openPopup(this.f.swID.value);
    }
  }

  /** Process when click on Back button **/
  back(event: any) {
    if (this.formSubmit.dirty) {
      this.openPopupIsDirty();
    } else {
      // this.localStorage.saveBackFlag(true);
      this.router.navigate(['/shift-work']);
    }
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
          this.submitShiftWork();
        }
      } else {
        if (result != 'Close') {
          this.localStorage.saveBackFlag(true);
          this.router.navigate(['/shift-work']);
        }
      }
    });
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
        this.vm006002Service.vm006002DeleteShiftWork(id).pipe(takeUntil(this.unsubscribe$)).subscribe(resp => {
          if (resp) {
            this.toastService.show('notification-message.delete-success', { classname: 'bg-success text-light', delay: 3000 });
            this.router.navigate(['/shift-work']);
          } else if (resp.error) {
            this.toastService.show('notification-message.delete-fail', { classname: 'bg-success text-light', delay: 3000 });
          }
        });
      }
    }, reason => {
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

  setDefaultTime(date): String {
    let checkDate = new Date(date);
    if (checkDate.toString() != 'Invalid Date') {
      let isoDate = new Date(date.getTime() - date.getTimezoneOffset() * 60000).toISOString();
      //let isoDate = new Date(date).toLocaleString();      
      return isoDate;
    }
    return date;
  }

  getCurrentDate() {
    let d = new Date,
      dformat = [d.getDate(),
      d.getMonth() + 1,
      d.getFullYear()].join('-');
    return dformat;
  }

}
