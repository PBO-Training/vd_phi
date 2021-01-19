import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { StorageService } from '../../../services/storage/storage.service';
import { EM002005Service } from './em002005.service';
import { ChangePasswordRequest } from './em002005-request';
import { ToastService } from '../../../theme/shared/components/toast-container/toast-service';
import { ActivatedRoute, Router } from '@angular/router';
import { TranslationService } from '../../../services/translate/translation.service';
import { takeUntil } from 'rxjs/operators';
import { Subject } from 'rxjs';

@Component({
  selector: 'app-em002005',
  templateUrl: './em002005.component.html'
})
export class EM002005Component implements OnInit {

  formData: FormGroup;
  submitted = false;
  displayMessage: string;
  request: ChangePasswordRequest;
  private unsubscribe$ = new Subject();
  errorsCode: string;
  fieldTextType = false;

  constructor(
    private fb: FormBuilder,
    private storageService: StorageService,
    private changePasswordService: EM002005Service,
    public toastService: ToastService,
    private route: ActivatedRoute,
    private router: Router,
    public translationService: TranslationService,
  ) {
    this.formData = this.fb.group({
      username: [''],
      currentPassword: ['', [Validators.required]],
      newPassword: ['', [Validators.required, Validators.minLength(8), Validators.pattern('^[a-zA-Z0-9@#$%^&+=]+$')]],
      confirmPassword: ['', [Validators.required]],
    }, {
      validator: this.checkPasswords('newPassword', 'confirmPassword')
    });
   }

  get f() { return this.formData.controls; }
  ngOnInit(): void {
    this.translationService.changeTranslatetion().pipe(takeUntil(this.unsubscribe$)).subscribe(event => {
      this.translationService.getErrorsChangeTranslation(this.errorsCode).pipe(takeUntil(this.unsubscribe$)).subscribe(errorMessage => {
        // set errors display after change language
        this.displayMessage = errorMessage;
      });
    });
  }

  checkPasswords(controlName: string, matchingControlName: string) {
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

  submitForm() {
    this.submitted = true;
    if (!this.formData.invalid) {
      const user: any = this.storageService.getUser();
      this.formData.value.username = user.username;
      this.changePasswordService.updatePassword(this.formData.value).subscribe(val => {
        if (val.body.error === null) {
          this.toastService.show('notification-message.update-success', { classname: 'bg-success text-light', delay: 3000 });
          this.submitted = false;
          this.router.navigate(['/employee']);
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

  toggleFieldTextType() {
    this.fieldTextType = !this.fieldTextType;
  }
}
