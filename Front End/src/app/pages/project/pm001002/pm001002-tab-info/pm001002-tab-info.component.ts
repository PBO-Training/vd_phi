import { Component, ElementRef, EventEmitter, Input, OnChanges, OnDestroy, OnInit, Output, SimpleChanges } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable, Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { StoreTabService } from '../../../../services/storage/storage-tab.service';
import { LengthConstant } from '../../../../common/constant/length';
import { DateConfig, formatDate, parseNewDateJs } from '../../../../common/datepicker-config/datepicker-config';
import { ScreenAction } from '../../../../common/screen-action/screen-action';
import { DateValidator } from '../../../../common/validator/date-validator';
import { ValidatorForm } from '../../../../common/validator/update-validity';
import { CommonFunctionService } from '../../../../services/common-function/common-function.service';
import { TranslationService } from '../../../../services/translate/translation.service';
import { ToastService } from '../../../../theme/shared/components/toast-container/toast-service';
import { DropDownDataProjectInfo, Pm001002Entity, Pm001002Response, ServerErrors, StatusProject } from '../pm001002-entity';
import { Pm001002Service } from '../pm001002.service';
import { Regex } from '../../../../common/constant/regex';

@Component({
  selector: 'app-project-tab-info',
  templateUrl: './pm001002-tab-info.component.html',
  styleUrls: ['./pm001002-tab-info.component.scss']
})
export class Pm001002TabInfoComponent implements OnInit, OnChanges, OnDestroy {
  @Input() dropdownData: DropDownDataProjectInfo;
  @Input() projectResponse: Pm001002Response;
  @Input() serverErrors$: Observable<ServerErrors>;
  @Output() saveProject = new EventEmitter<string>();
  @Output() back = new EventEmitter<boolean>();
  @Output() isEdit = new EventEmitter<boolean>();
  @Output() isCancel = new EventEmitter<boolean>();
  formDetails: FormGroup;
  projectID = this.route.snapshot.params.id;
  isUpdate = this.projectID !== undefined ? true : false;
  statusProject: StatusProject[] = [];
  date = new Date();
  errorsCode: string;
  requestProject: Pm001002Entity;
  private unsubscribe$ = new Subject();
  authButton = new ScreenAction();
  config = new DateConfig();
  // Html variable
  showId = true;
  loading = true;
  displayMessage: string;
  validator = new ValidatorForm();
  maxLenghtCode = {value: LengthConstant.MAX_LENGTH_CODE};
  maxLenghtName = {value: LengthConstant.MAX_LENGTH_NAME};
  maxLenghtDescription = {value: LengthConstant.MAX_LENGTH_DESCRIPTION};

  constructor(
    private route: ActivatedRoute,
    private formBuilder: FormBuilder,
    public toastService: ToastService,
    private el: ElementRef,
    public common: CommonFunctionService,
    private projectService: Pm001002Service,
    public storeService: StoreTabService,
    private translateService: TranslationService,
  ) {
    this.authButton = this.common.initAuthAction(this.authButton);
    this.formDetails = this.formBuilder.group({
      projectID: [this.projectID],
      projectCode: [''],
      projectName: [''],
      projectEffort: [null, [Validators.max(LengthConstant.MAX_NUMBER)]],
      projectDescription: ['', [Validators.maxLength(LengthConstant.MAX_LENGTH_DESCRIPTION)]],
      departmentID: [null],
      statusProjectID: [null],
      levelProjectID: [null],
      customerID: [null],
      evaluateProjectID: [null],
      projectStartDate: [formatDate(parseNewDateJs(this.date))],
      projectEndDate: [formatDate(parseNewDateJs(this.date))],
    }, { validator: Validators.compose([DateValidator.dateLessThan('projectStartDate', 'projectEndDate')]) });
  }
  ngOnDestroy(): void {
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
  }

  ngOnInit(): void {
    this.formDetails.controls.projectCode.valueChanges.pipe().subscribe(val => {
      if (val) {
        const control = this.formDetails.controls.projectCode;
        // tslint:disable-next-line: max-line-length
        control.setValidators([Validators.required, Validators.maxLength(LengthConstant.MAX_LENGTH_CODE), Validators.pattern(Regex.fieldCode)]);
        control.updateValueAndValidity({ onlySelf: true, emitEvent: false });
      }
    });
    this.formDetails.controls.projectName.valueChanges.pipe().subscribe(val => {
      if (val) {
        const control = this.formDetails.controls.projectName;
        control.setValidators([Validators.required, Validators.maxLength(LengthConstant.MAX_LENGTH_STRING)]);
        control.updateValueAndValidity({ onlySelf: true, emitEvent: false });
      }
    });
    // save store current data
    this.storeService.saveStore({ ...this.formDetails.value, isValid: this.formDetails.valid });
    this.storeService.saveValueChange({ ...this.formDetails.value, isValid: this.formDetails.valid });
    this.formDetails.valueChanges.pipe(takeUntil(this.unsubscribe$)).subscribe(val => {
      this.storeService.saveValueChange({ ...this.formDetails.value, isValid: this.formDetails.valid });
    });
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (this.route.snapshot.params.id === null || this.route.snapshot.params.id === undefined) {
      if (changes.dropdownData.currentValue) {
        this.initDataActionCreate();
      }
    } else {
      this.initDataActionUpdate();
      this.sortStatus();
    }
    this.storeService.saveStore({ ...this.formDetails.value, isValid: this.formDetails.valid });
    this.storeService.saveValueChange({ ...this.formDetails.value, isValid: this.formDetails.valid });
  }

  // Init data in screen create project
  initDataActionCreate(): void {
    this.statusProject = this.dropdownData?.listStatusProject.sort((a, b) => (a.statusProjectCode > b.statusProjectCode) ? 1 : -1);
    this.formDetails?.patchValue({
      departmentID: this.dropdownData?.listDepartment[0]?.departmentID,
      statusProjectID: this.statusProject[0]?.statusProjectID,
      customerID: this.dropdownData?.listCustomer[0]?.customerID
    });
  }

  initDataActionUpdate(): void {
    this.formDetails?.patchValue({
      departmentID: this.projectResponse?.objectDepartment?.departmentID,
      statusProjectID: this.projectResponse?.objectStatusProject?.statusProjectID,
      levelProjectID: this.projectResponse?.objectLevelProject?.levelProjectID,
      customerID: this.projectResponse?.objectCustomer?.customerID,
      evaluateProjectID: this.projectResponse?.objectEvaluateProject?.evaluateProjectID,
      projectCode: this.projectResponse?.projectCode,
      projectName: this.projectResponse?.projectName,
      projectEffort: this.projectResponse?.projectEffort,
      projectDescription: this.projectResponse?.projectDescription,
      projectStartDate: formatDate(this.projectResponse?.projectStartDate?.split('T')[0]),
      projectEndDate: formatDate(this.projectResponse?.projectEndDate?.split('T')[0]),
    });

  }

  // Get form
  get formDetailsValidation() { return this.formDetails.controls; }

  // Event onclick button Save
  submitForm() {
    this.formDetails.controls.projectCode.setValue(this.formDetails.controls.projectCode.value?.trim());
    this.formDetails.controls.projectCode.setValidators(
      [Validators.required, Validators.maxLength(LengthConstant.MAX_LENGTH_CODE), Validators.pattern(Regex.fieldCode)]
    );
    this.formDetails.controls.projectName.setValue(this.formDetails.controls.projectName.value?.trim());
    this.formDetails.controls.projectName.setValidators(
      [Validators.required, Validators.maxLength(LengthConstant.MAX_LENGTH_STRING)]
    );
    this.formDetails.controls.projectDescription.setValue(this.formDetails.controls.projectDescription.value?.trim());
    this.validator.updateTreeValidity(this.formDetails);
    this.requestProject = {
      ...this.formDetails.value
    };

    if (this.formDetails.valid) {
      if (this.projectID === null || this.projectID === undefined) {
        this.saveProject.emit('create');
      } else {
        this.saveProject.emit('update');
      }
    }
    // handle server Errors
    this.serverErrors$.pipe(takeUntil(this.unsubscribe$)).subscribe(
      value => {
        if (!value) {
          return;
        }
        this.displayMessageError(value);
      }
    );
  }

  /*
  * Description: Set error to display
  * Param Object - errors
  */
  displayMessageError(errors: ServerErrors) {
    const formControl = this.formDetails.get(errors.itemName);
    if (formControl) {
      this.translateService.getTranslation(errors.code).pipe(takeUntil(this.unsubscribe$)).subscribe(errorMessage => {
        // set errors formcontrol
        this.errorsCode = errors.code;
        this.displayMessage = this.translateService.convertToKeyJson(errors.code);
        formControl.setErrors({
          serverError: errorMessage
        });
      });
    }
  }

  sortStatus(): void {
    this.statusProject = this.dropdownData?.listStatusProject;
    if (this.projectResponse?.issuePercentDoneAverage < 100) {
      const statusFinish = this.statusProject?.findIndex(item => item.statusProjectCode === '40FINISH');
      this.statusProject?.splice(statusFinish, 1);
    }
    this.statusProject?.sort((a, b) => {
      return a.statusProjectCode > b.statusProjectCode ? 1 : -1;
    });
  }

  onBack(): void {
    this.back.emit(true);
  }

  onCancel(): void {
    this.isCancel.emit(true);
  }
}
