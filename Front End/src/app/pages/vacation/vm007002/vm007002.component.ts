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
import { Listprops } from '../../../common/page-options';
import { ValidatorForm } from '../../../common/validator/update-validity';
import { StorageService } from '../../../services/storage/storage.service';
import { TranslationService } from '../../../services/translate/translation.service';
import {
  ModalNotificationMaintenanceComponent
} from '../../../theme/shared/components/modal-notification-maintenance/modal-notification-maintenance.component';
import { ToastService } from '../../../theme/shared/components/toast-container/toast-service';
import { ScreenAction } from './../../../common/screen-action/screen-action';
import { CommonFunctionService } from './../../../services/common-function/common-function.service';
import { VM007002Service } from './vm007002.service';

@Component({
  selector: 'app-vm007002',
  templateUrl: './vm007002.component.html'
})
export class VM007002Component implements OnInit {

  isApprove: boolean;
  isCheck: boolean;
  submitted: boolean;
  isDataAvailable: boolean = false;

  accessEmployeeID: string;
  displayMessage: string;
  errorsCode: string;

  listOption: any[] = [];
  selectedLeader: any[] = [];
  selectedManager: any[] = [];
  selectedForward: any[] = [];
  selectedToOrigin: any[] = [];

  Type: any = type;

  validator = new ValidatorForm();
  screenProps = new Listprops();
  authButton = new ScreenAction();

  formSubmit: FormGroup;

  maxLenghtInput = { value: LengthConstant.MAX_LENGTH_STRING };

  constructor(
    private formBuilder: FormBuilder,
    private localStorage: StorageService,
    private vm007002Service: VM007002Service,
    private router: Router,
    private route: ActivatedRoute,
    private datepipe: DatePipe,
    private toastService: ToastService,
    private modalService: NgbModal,
    private translationService: TranslationService,
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

  get f() { return this.formSubmit.controls; }

  private unsubscribe$ = new Subject();

  /** Find user login **/
  readstorageData() {
    const storage: any = this.localStorage.getUser();
    this.accessEmployeeID = storage.employeeID;
  }

  /** Initialize a submit form **/
  initForm(): void {
    this.formSubmit = this.formBuilder.group({
      accessEmployeeID: null,
      swID: null,
      swOptionID: null,
      swStartDateAM: null,
      swEndDatePM: null,
      swReason: null,
      swGroupNote: null,
      isApprove: null,
      swAssignGroupOne: null,
      swAssignGroupTwo: null,
      swAssignGroupThree: null,
      statusNameGroupOne: null,
      statusNameGroupTwo: null,
      statusNameGroupThree: null,
      swAssignGroupOneNote: null,
      swAssignGroupTwoNote: null,
      swAssignGroupThreeNote: null,
      swUpdateGroupOneDate: null,
      swUpdateGroupTwoDate: null,
      swUpdateGroupThreeDate: null
    });
  }

  /** Check process and initialize data **/
  initDataLoad(id: number) {
    this.vm007002Service.vm007002InitList().pipe(takeUntil(this.unsubscribe$)).subscribe((data: any) => {
      if (data.error === null) {
        this.listOption = data.content.listShiftWorkOption;
        this.selectedToOrigin = data.content.listEmployee;
        this.initDetailData(id);
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
      this.vm007002Service.vm007002GetDetail(id, this.accessEmployeeID).pipe(takeUntil(this.unsubscribe$)).subscribe((value) => {
        if (value) {
          if (value.content == null) {
            this.router.navigate(['/404']);
          }
          else {
            this.isDataAvailable = true;

            let getStartTime = new Date(value.content.swStartDateAM).toISOString().split('.')[0];
            let getEndTime = new Date(value.content.swEndDatePM).toISOString().split('.')[0];

            this.selectedLeader = this.selectedToOrigin.filter(a => a.keyResponse.toString() == value.content.swAssignGroupOne);
            this.selectedManager = this.selectedToOrigin.filter(a => a.keyResponse.toString() == value.content.swAssignGroupTwo);

            var splitted = value.content.swAssignGroupThree.split(";");
            this.selectedForward = this.selectedToOrigin.filter(array => splitted.some(filter => filter === array.keyResponse.toString()));

            this.formSubmit.patchValue({
              swID: value.content.swID,
              swOptionID: value.content.swOptionID,
              swStartDateAM: this.datepipe.transform(getStartTime, 'dd-MM-yyyy'),
              swEndDatePM: this.datepipe.transform(getEndTime, 'dd-MM-yyyy'),
              swReason: value.content.swReason,
              statusNameGroupOne: value.content.statusNameGroupOne,
              statusNameGroupTwo: value.content.statusNameGroupTwo,
              statusNameGroupThree: value.content.statusNameGroupThree,
              swUpdateGroupOneDate: value.content.swUpdateGroupOneDate ? new Date(value.content.swUpdateGroupOneDate).toLocaleString() : null,
              swUpdateGroupTwoDate: value.content.swUpdateGroupTwoDate ? new Date(value.content.swUpdateGroupTwoDate).toLocaleString() : null,
              swUpdateGroupThreeDate: value.content.swUpdateGroupThreeDate ? new Date(value.content.swUpdateGroupThreeDate).toLocaleString() : null,
              swAssignGroupOneNote: value.content.swAssignGroupOneNote,
              swAssignGroupTwoNote: value.content.swAssignGroupTwoNote,
              swAssignGroupThreeNote: value.content.swAssignGroupThreeNote,
            });
          }
        }
      },
        err => {
          if (err) {
            this.router.navigate(['/403']);
          }
        });
    } else {
      this.router.navigate(['/404']);
    }
  }

  /** Handling request **/
  onSubmit(isApprove: boolean): void {
    if (isApprove === true) {
      this.isApprove = isApprove;
      this.formSubmit.controls.isApprove.setValue(isApprove);
    }
    if (isApprove === false) {
      this.isApprove = isApprove;
      this.formSubmit.controls.isApprove.setValue(isApprove);
    }
    this.submitShiftWork();
  }

  submitShiftWork() {
    this.submitted = true;
    this.setValidators();
    if (this.formSubmit.valid) {
      if (this.f.swID.value) {
        this.vm007002Service.vm007002Approve(this.formSubmit.value).pipe(takeUntil(this.unsubscribe$)).subscribe(
          (response) => {
            this.toastService.show('notification-message.save-success', { classname: 'bg-success text-light', delay: 3000 });
            this.router.navigate(['/shift-work-receive']);
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

  /** Validate for group note **/
  setValidators() {
    this.formSubmit.controls.swGroupNote.setValidators([Validators.maxLength(LengthConstant.MAX_LENGTH_STRING)]);
    this.validator.updateTreeValidity(this.formSubmit);
  }

  /** Process when click on Back button **/
  back(event: any) {
    this.router.navigate(['/shift-work-receive']);
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

}
