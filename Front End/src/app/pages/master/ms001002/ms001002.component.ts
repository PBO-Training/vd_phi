import { ChangeDetectorRef, Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Subject } from 'rxjs';
import { mergeMap, takeUntil } from 'rxjs/operators';
import { TranslationService } from '../../../services/translate/translation.service';
import { ToastService } from '../../../theme/shared/components/toast-container/toast-service';
import { ScreenAction } from './../../../common/screen-action/screen-action';
import { CommonFunctionService } from './../../../services/common-function/common-function.service';
import { Employee } from './ms001002-employee-entity';
import { MS001002Service } from './ms001002.service';
import { LengthConstant } from '../../../common/constant/length';
import { Regex } from '../../../common/constant/regex';
import { ValidatorForm } from '../../../common/validator/update-validity';
import * as _ from 'lodash';
import { ModalConfirmComponent } from '../../../theme/shared/components/modal-confirm/modal-confirm.component';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { TranslateService } from '@ngx-translate/core';
import { StorageService } from './../../../services/storage/storage.service';
import { Title } from '@angular/platform-browser';

@Component({
  selector: 'app-ms001002',
  templateUrl: './ms001002.component.html'
})
export class MS001002Component implements OnInit, OnDestroy {

  formData: FormGroup;
  showBreadcumb: boolean;
  showId = true;
  isUpdate = false;
  errorsCode: string;
  private unsubscribe$ = new Subject();
  displayMessage: string;
  submitted = false;
  toastTitle: string;
  isHidden = true;
  employee: Employee[];
  employeeTemp;
  id: number;
  authButton = new ScreenAction();
  listRole: any;
  passwordRequired = true;
  validator = new ValidatorForm();
  showBtnDefaultEmp = true;
  valueOld = {
    userID: '',
    username: '',
    password: '',
    confirmPassword: '',
    roleID: '',
    employeeID: '',
    employeeName: '',
    employeeSelectID: ''
  };
  maxLenghtName = { value: LengthConstant.MAX_LENGTH_NAME };
  minLenghtName = { value: LengthConstant.MIN_LENGTH_NAME };
  constructor(
    private changDetector: ChangeDetectorRef,
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private userService: MS001002Service,
    public toastService: ToastService,
    private localStorage: StorageService,
    private commonFunctionService: CommonFunctionService,
    public translationService: TranslationService,
    private modalService: NgbModal,
    private translate: TranslateService,
    private titleService: Title,
  ) {
    if (this.route.snapshot.params.id) {
      this.isUpdate = true;
    }
    this.authButton = this.commonFunctionService.initAuthAction(this.authButton);
    this.formData = this.fb.group({
      userID: [''],
      username: [''],
      password: [''],
      confirmPassword: [''],
      roleID: [null],
      employeeID: ['', [Validators.required]],  // assignToEmployeeID
      employeeName: [''],
      employeeSelectID: [''],
    }, {
      validator: this.CheckPasswords('password', 'confirmPassword')
    });
  }

  ngOnDestroy(): void {
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
  }

  get f() { return this.formData.controls; }
  ngOnInit(): void {
    // change title
    this.translate.get('title.master.user').subscribe((title: string) => {
      this.titleService.setTitle(title);
    });

    // change title when change language
    this.translationService.changeTranslatetion().pipe(takeUntil(this.unsubscribe$),
      mergeMap(lang => this.translate.get('title.master.user'))
    ).subscribe((title: string) => {
      this.titleService.setTitle(title);
    });

    this.translationService.changeTranslatetion().pipe(takeUntil(this.unsubscribe$)).subscribe(event => {
      this.translationService.getErrorsChangeTranslation(this.errorsCode).pipe(takeUntil(this.unsubscribe$)).subscribe(errorMessage => {
        // set errors display after change language
        this.displayMessage = errorMessage;
      });
    });
    this.initData();
  }

  initData() {
    this.userService.initDetail().subscribe(val => {
      if (val) {
        this.employee = val.content.listEmployee;
        this.listRole = val.content.listRole;
        this.employeeTemp = this.employee;

        if (this.route.snapshot.params.id !== undefined || this.route.snapshot.params.id != null) {
          this.passwordRequired = false;
          this.userService.getDetailUser(this.route.snapshot.params.id).pipe(takeUntil(this.unsubscribe$)).subscribe((value) => {
            if (value) {
              this.formData.patchValue({
                userID: value.content.userID,
                username: value.content.username,
                roleID: '' + value.content.role.roleID,
                employeeID: '' + value.content.employee.employeeID,
                employeeName: '' + value.content.employee.firstName
                  + ' ' + value.content.employee.lastName + ' '
                  + '(' + value.content.employee.employeeCode + ')'
              });
              this.valueOld.userID = this.formData.controls.userID.value;
              this.valueOld.username = this.formData.controls.username.value;
              this.valueOld.password = this.formData.controls.password.value;
              this.valueOld.confirmPassword = this.formData.controls.confirmPassword.value;
              this.valueOld.roleID = this.formData.controls.roleID.value;
              this.valueOld.employeeID = this.formData.controls.employeeID.value;
              this.valueOld.employeeName = this.formData.controls.employeeName.value;
              this.valueOld.employeeSelectID = this.formData.controls.employeeSelectID.value;
            }
          });
          this.showBreadcumb = true;
        } else {
          this.valueOld.roleID = '' + this.listRole[0]?.roleID;
          this.formData.patchValue({
            roleID: this.listRole[0]?.roleID + '',
          });
          this.showBreadcumb = false;
          this.showBtnDefaultEmp = false;
        }
      }
    });
  }

  submitForm() {
    this.submitted = true;
    this.formData.controls.username.setValue(this.formData.controls.username.value.trim());
    this.formData.controls.username.setValidators([Validators.required, Validators.maxLength(LengthConstant.MAX_LENGTH_NAME),
    Validators.minLength(LengthConstant.MIN_LENGTH_NAME), Validators.pattern(Regex.username)]);
    if (this.route.snapshot.params.id === undefined || this.route.snapshot.params.id == null) {
      this.formData.controls.password.setValue(this.formData.controls.password.value.trim());
      this.formData.controls.password.setValidators([Validators.required, Validators.maxLength(LengthConstant.MAX_LENGTH_NAME)]);
    } else {
      this.formData.controls.password.setValue(this.formData.controls.password.value.trim());
      this.formData.controls.password.setValidators([Validators.maxLength(LengthConstant.MAX_LENGTH_NAME)]);
    }
    this.validator.updateTreeValidity(this.formData);
    if (!this.formData.invalid) {
      if (this.f.userID.value) {
        this.userService.updateUser(this.formData.value).subscribe(resp => {
          if (resp.body.error === null) {
            this.toastService.show('notification-message.update-success', { classname: 'bg-success text-light', delay: 3000 });
            this.submitted = false;
            this.router.navigate(['/user']);
          }
        },
          (errors) => {
            if (errors) {
              this.displayErrorMessage(errors);
            }
          }
        );
      } else {
        this.userService.createUser(this.formData.value).subscribe(
          (resp) => {
            if (resp.body.error === null) {
              this.toastService.show('notification-message.save-success', { classname: 'bg-success text-light', delay: 3000 });
              this.formData.reset();
              this.submitted = false;
              this.router.navigate(['/user']);
            }
          },
          (errors) => {
            if (errors) {
              this.displayErrorMessage(errors);
            }
          }

        );
      }
    }
    this.formData.markAsPristine();
  }
  back() {
    if (this.CheckValue(this.valueOld, this.formData.value) === true) {
      this.localStorage.saveBackFlag(true);
      this.router.navigate(['/user']);
    } else {
      this.openPopup();
    }
  }
  // open popup confirm
  openPopup() {
    if (this.authButton.CREATE === true || this.authButton.UPDATE === true) {
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
          this.submitForm();
        } else {
          if (result === 'Unsave') {
            this.router.navigate(['/user']);
          }
        }
      });
    } else {
      this.router.navigate(['/user']);
    }
  }
  // compare do you have change data
  CheckValue(valueOld: any, valueNew: any): boolean {
    return _.isEqual(valueOld, valueNew);
  }
  // set form error, translate and display message
  displayErrorMessage(errors) {
    const formControl = this.formData.get(errors.error.error.itemName);
    if (formControl) {
      // get transate code
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

  CheckPasswords(controlName: string, matchingControlName: string) {
    return (formGroup: FormGroup) => {
      const control = formGroup.controls[controlName];
      const matchingControl = formGroup.controls[matchingControlName];

      if (matchingControl.errors && !matchingControl.errors.mustMatch) {
        // return if another validator has already found an error on the matchingControl
        return;
      }
      // set error on matchingControl if validation fails
      if (control.value !== matchingControl.value) {
        matchingControl.setErrors({ mustMatch: true });
      } else {
        matchingControl.setErrors(null);
      }
    };
  }

  toogle(): void {
    this.isHidden = !this.isHidden;
  }

  assignLeader() {
    this.id = this.formData.value.employeeSelectID;
    const obj = this.employee.find(item => item.employeeID === this.id);
    this.formData.patchValue({
      employeeID: '' + this.formData.value.employeeSelectID,
      employeeName: '' + obj.firstName + ' ' + obj.lastName + ' ' + '(' + obj.employeeCode + ')'
    });
  }

  onKey(event) {
    this.employee = this.employeeTemp;
    const keySearch = '' + event.target.value;
    // tslint:disable-next-line:max-line-length
    this.employee = this.employee.filter(item => (item.firstName + ' ' + item.lastName + ' ' + '(' + item.employeeCode + ')').toLowerCase().match(keySearch.toLowerCase()));
  }

  generatedEmp() {
    this.userService.generateEmp().pipe(takeUntil(this.unsubscribe$)).subscribe(
      (value) => {
        if (value.error === null) {
          this.toastService.show('notification-message.save-success', { classname: 'bg-success text-light', delay: 3000 });
          this.formData.patchValue({
            employeeID: '' + value.content.employeeID,
            employeeName: '' + value.content.firstName + ' ' + value.content.lastName + ' ' + '(' + value.content.employeeCode + ')'
          });
          this.submitted = false;
        }
      },
      (error) => {
        if (error) {
          this.toastService.show('notification-message.employee-unUse', { classname: 'bg-danger text-light', delay: 3000 });
        }
      }
    );
    this.userService.initDetail().subscribe(val => {
      if (val) {
        this.employee = val.content.listEmployee;
        this.employeeTemp = this.employee;
      }
    });
  }
}
