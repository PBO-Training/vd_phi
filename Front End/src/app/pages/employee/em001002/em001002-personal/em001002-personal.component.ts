import { Component, ElementRef, EventEmitter, Input, OnChanges, OnDestroy, OnInit, Output, SimpleChanges } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbProgressbarConfig } from '@ng-bootstrap/ng-bootstrap';
import { Observable, Subject } from 'rxjs';
import { delay, takeUntil } from 'rxjs/operators';
import { LengthConstant } from '../../../../common/constant/length';
import { Regex } from '../../../../common/constant/regex';
import { DateConfig, formatDate, parseNewDateJs } from '../../../../common/datepicker-config/datepicker-config';
import { ScreenAction } from '../../../../common/screen-action/screen-action';
import { CustomReactiveFormValidator } from '../../../../common/validator/custom-validator';
import { DateValidator } from '../../../../common/validator/date-validator';
import { TranslationService } from '../../../../services/translate/translation.service';
import { ToastService } from '../../../../theme/shared/components/toast-container/toast-service';
import { Degree, Department, Employee, Nationality, PositionEmployee, ServerErrors, StatusEmployee } from '../em001002-entity';
import { StoreService } from '../em001002-store-service';

@Component({
  selector: 'app-em001002-personal',
  templateUrl: './em001002-personal.component.html',
  styleUrls: ['./em001002-personal.component.scss'],
  providers: [NgbProgressbarConfig]

})
export class Em001002PersonalComponent implements OnInit, OnChanges, OnDestroy {
  // form
  formDetails: FormGroup;
  @Input() companyName: string;
  @Input() listPositionEmployee: PositionEmployee[];
  @Input() listStatusEmployee: StatusEmployee[];
  @Input() listNationality: Nationality[];
  @Input() listDepartment: Department[];
  @Input() listDegree: Degree[];
  @Input() employeeDetails: Employee;
  @Input() serverErrors$: Observable<ServerErrors>;
  @Input() authButton: ScreenAction;
  @Input() isProfile: boolean;
  @Output() submitForm: EventEmitter<any> = new EventEmitter();
  @Output() back: EventEmitter<any> = new EventEmitter();
  @Output() backOrCancel: EventEmitter<any> = new EventEmitter();
  @Output() backCreate: EventEmitter<any> = new EventEmitter();
  unsubscribe$ = new Subject();
  url = '';
  employeeName: string;
  displayMessage: string;
  errorsCode: string;
  submited: boolean;
  config = new DateConfig();
  idEmployee: number;
  isMarried = false;
  isAvatar: boolean;
  isHidden = true;
  totalYear: number;
  totalMonth: number;
  inCompanyYear: number;
  inCompanyMonth: number;
  get f() { return this.formDetails.controls; } // get Personal form controls
  // translate params
  maxLenghtCode = { value: LengthConstant.MAX_LENGTH_CODE };
  maxLenghtName = { value: LengthConstant.MAX_LENGTH_NAME };
  minLenghtName = { value: LengthConstant.MIN_LENGTH_NAME };
  maxLenghtString = { value: LengthConstant.MAX_LENGTH_STRING };
  maxLenghtNotes = { value: LengthConstant.MAX_LENGTH_NOTES };
  maxLenghtInput = { value: LengthConstant.MAX_LENGTH_INPUT };
  maxLenghtPhone = { value: LengthConstant.MAX_LENGTH_DATE_PHONE };
  minLenghtPhone = { value: LengthConstant.MIN_LENGTH_PHONE };
  minLenghtIDCard = { value: LengthConstant.MIN_LENGTH_IDCARD };
  maxLenghtIDCard = { value: LengthConstant.MAX_LENGTH_IDCARD };
  typeImageUpload = { value: LengthConstant.TYPE_IMG };

  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    public translationService: TranslationService,
    public toastService: ToastService,
    public storeService: StoreService,
    public el: ElementRef,
    config: NgbProgressbarConfig
  ) {
    // config progessbar max value
    config.max = LengthConstant.MAX_VALUE_PROGRESSBAR;
    this.formDetails = this.fb.group({
      // tslint:disable-next-line:max-line-length
      employeeCode: [null, [Validators.required, Validators.maxLength(LengthConstant.MAX_LENGTH_CODE), Validators.pattern(Regex.fieldCode)]],
      firstName: [null, [Validators.required, Validators.maxLength(LengthConstant.MAX_LENGTH_NAME), Validators.pattern('')]],
      lastName: [null, [Validators.required, Validators.maxLength(LengthConstant.MAX_LENGTH_NAME), Validators.pattern('')]],
      departmentID: [null],
      statusEmployeeID: [null],
      positionEmployeeID: [null],
      married: [true],
      avataUrl: [''],
      nationalityID: [null],
      gender: [null, Validators.required],
      numOfChild: [null, [Validators.maxLength(2), Validators.min(0), Validators.max(30), Validators.pattern(/^[.\d]+$/)]],
      birthday: [null, [DateValidator.startDate]],
      identityCard: ['',
        [Validators.required, Validators.minLength(LengthConstant.MIN_LENGTH_IDCARD),
        Validators.maxLength(LengthConstant.MAX_LENGTH_IDCARD)]],
      dateOfIssueOfIDCard: [, [Validators.required, DateValidator.startDate]],
      email: [null, [Validators.required, Validators.maxLength(LengthConstant.MAX_LENGTH_INPUT), Validators.email]],
      phone: [null, [Validators.required, Validators.minLength(10), Validators.maxLength(LengthConstant.MAX_LENGTH_DATE_PHONE)]],
      address: [null, [Validators.maxLength(LengthConstant.MAX_LENGTH_STRING)]],
      dateJoinCompany: [null, [Validators.required, DateValidator.startDate]],
      skype: [null, [Validators.maxLength(LengthConstant.MAX_LENGTH_INPUT)]],
      emergency: [null, [Validators.maxLength(LengthConstant.MAX_LENGTH_INPUT)]],
      degreeID: [null],
      // tslint:disable-next-line:max-line-length
      graduateYear: [null, [Validators.maxLength(4), Validators.pattern(/^[.\d]+$/), Validators.min(1950), DateValidator.checkYear]],
      university: [null, [Validators.maxLength(LengthConstant.MAX_LENGTH_INPUT)]],
      description: [null, [Validators.maxLength(LengthConstant.MAX_LENGTH_NOTES)]],
      biography: [null, [Validators.maxLength(LengthConstant.MAX_LENGTH_NOTES)]]
    });
  }
  ngOnDestroy(): void {
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
  }

  ngOnChanges(changes: SimpleChanges): void {
    this.idEmployee = this.route.snapshot.params.id;
    if (!this.idEmployee) {
      this.isMarried = true;
      this.formDetails.patchValue({
        gender: true,
        married: true,
        birthday: formatDate(parseNewDateJs(new Date())),
        numOfChild: 0,
        dateOfIssueOfIDCard: formatDate(parseNewDateJs(new Date())),
        graduateYear: '',
        departmentID: this.listDepartment[0]?.departmentID,
        positionEmployeeID: this.listPositionEmployee[0]?.positionEmployeeID,
        nationalityID: this.listNationality[0]?.nationalityID,
        statusEmployeeID: this.listStatusEmployee[0]?.statusEmployeeID,
        degreeID: this.listDegree[0]?.degreeID,
        dateJoinCompany: formatDate(parseNewDateJs(new Date())),
      }, { emitEvent: false });
      this.backCreate.emit(true);
    } else {
      this.initFormData();
      this.backCreate.emit(false);
    }
    this.storeService.saveStore({ ...this.formDetails.value, avataUrl: this.url });
    this.storeService.saveValueChange({ ...this.formDetails.value, avataUrl: this.url });
  }

  ngOnInit(): void {
    if (this.employeeDetails) {
      this.calculateExperience();
    }
    this.submited = false;
    this.isAvatar = true;
    // if is profile page, you canot change important information (code, department)
    if (this.router.url.slice(1, 8) === 'profile') {
      this.isHidden = true;
    } else {
      this.isHidden = false;
    }
    // save store current data
    this.formDetails.valueChanges.pipe(takeUntil(this.unsubscribe$)
    ).subscribe(val => {
      // update status form when submit on parent component
      this.storeService.updateStatusForm(this.formDetails);
      this.storeService.saveValueChange({ ...this.formDetails.value, avataUrl: this.url });
    });
    this.formDetails.controls.avataUrl.valueChanges.pipe(takeUntil(this.unsubscribe$), delay(300)).subscribe(val => {
      this.storeService.saveValueChange({ ...this.formDetails.value, avataUrl: this.url });
    });
    this.formDetails.controls.married.valueChanges.pipe(takeUntil(this.unsubscribe$)).subscribe((val: boolean) => {
      this.isMarried = val;
    });
    // call service to translate message error
    this.translationService.changeTranslatetion().pipe(takeUntil(this.unsubscribe$)).subscribe(event => {
      this.translationService.getErrorsChangeTranslation(this.errorsCode).pipe(takeUntil(this.unsubscribe$)).subscribe(errorMessage => {
        // set errors display after change language
        this.displayMessage = errorMessage;
      });
    });
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
  submit(form: any) {
    // this.submited = true;
    if (this.formDetails.value.graduateYear === '' || this.formDetails.value.graduateYear === null) {
      this.formDetails.value.graduateYear = 0;
    }
    // update tree validate
    CustomReactiveFormValidator.updateTreeValidity(this.formDetails);
    if (this.formDetails.valid) {
      this.submitForm.emit({
        ...form,
        avataUrl: this.url,
        numOfChild: form.married ? form.numOfChild : 0,
      });
    }
  }
  onSelectFile(event: any) {
    if (event.target.files && event.target.files.length > 0) {
      // Get the file first
      const file: File = event.target.files[0];
      const reader = new FileReader();
      // Ruun handler => set image to src
      if (file.type === 'image/png' || file.type === 'image/jpeg' || file.type === 'image/jpg' || file.type === 'image/gif') {
        reader.onload = this.handleReaderLoaded.bind(this);
        reader.readAsBinaryString(file);
        this.isAvatar = true;
      } else {
        this.isAvatar = false;
      }
    }
  }
  handleReaderLoaded(readerEvt: any) {
    const binaryString = readerEvt.target.result;
    // base64
    this.url = 'data:image/jpeg;base64,' + btoa(binaryString);
  }
  displayMessageError(erros: ServerErrors) {
    const formControl = this.formDetails.get(erros.itemName);
    if (formControl) {
      this.translationService.getTranslation(erros.code).pipe(takeUntil(this.unsubscribe$)).subscribe(errorMessage => {
        // set errors formcontrol
        this.errorsCode = erros.code;
        this.displayMessage = errorMessage;
        formControl.setErrors({
          serverError: errorMessage
        });
      });
    }
  }
  openPopup() {
    this.back.emit('back');
  }

  openPopupPersonal() {
    this.backOrCancel.emit('back');
  }

  initFormData() {
    if (this.employeeDetails) {
      if (this.employeeDetails.graduateYear === 0) {
        this.employeeDetails.graduateYear = '';
      }
      this.url = this.employeeDetails.avataUrl;
      this.isMarried = this.employeeDetails.married;
      this.employeeName = this.employeeDetails.firstName ? this.employeeDetails.firstName + ' ' : '';
      this.employeeName += this.employeeDetails.lastName ? this.employeeDetails.lastName : '';
      this.formDetails.patchValue({
        employeeCode: this.employeeDetails.employeeCode,
        firstName: this.employeeDetails.firstName,
        lastName: this.employeeDetails.lastName,
        avataUrl: '',
        departmentID: this.employeeDetails.departmentID,
        statusEmployeeID: this.employeeDetails.statusEmployeeID,
        positionEmployeeID: this.employeeDetails.positionEmployeeID,
        married: this.employeeDetails.married ? this.employeeDetails.married : false,
        nationalityID: this.employeeDetails.nationalityID,
        gender: this.employeeDetails.gender,
        birthday: formatDate(this.employeeDetails.birthday),
        identityCard: this.employeeDetails.identityCard,
        dateOfIssueOfIDCard: formatDate(this.employeeDetails.dateOfIssueOfIDCard),
        email: this.employeeDetails.email,
        phone: this.employeeDetails.phone,
        address: this.employeeDetails.address,
        dateJoinCompany: formatDate(this.employeeDetails.dateJoinCompany),
        skype: this.employeeDetails.skype,
        emergency: this.employeeDetails.emergency,
        degreeID: this.employeeDetails.degreeID,
        graduateYear: '' + this.employeeDetails.graduateYear,
        university: this.employeeDetails.university,
        description: this.employeeDetails.description,
        numOfChild: '' + this.employeeDetails.numOfChild,
        biography: this.employeeDetails.biography ? this.employeeDetails.biography : ''
      });
      CustomReactiveFormValidator.updateTreeValidity(this.formDetails);
    }
  }

  calculateExperience() {
    this.totalYear = Math.floor(this.employeeDetails.totalExperience / 12);
    this.totalMonth = this.employeeDetails.totalExperience % 12;
    this.inCompanyYear = Math.floor(this.employeeDetails.inCompanyExperience / 12);
    this.inCompanyMonth = this.employeeDetails.inCompanyExperience % 12;
  }
}
