import { CommonFunctionService } from './../../../services/common-function/common-function.service';
import { ScreenAction } from './../../../common/screen-action/screen-action';
import { TranslationService } from './../../../services/translate/translation.service';
import { mergeMap, takeUntil } from 'rxjs/operators';
import { ChangeDetectorRef, Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription, Subject } from 'rxjs';
import { ToastService } from '../../../theme/shared/components/toast-container/toast-service';
import { MS005002DetailService } from './ms005002.service';
import { LengthConstant } from '../../../common/constant/length';
import { Regex } from '../../../common/constant/regex';
import { ValidatorForm } from '../../../common/validator/update-validity';
import * as _ from 'lodash';
import { ModalConfirmComponent } from '../../../theme/shared/components/modal-confirm/modal-confirm.component';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { TranslateService } from '@ngx-translate/core';
import { StorageService } from '../../../services/storage/storage.service';
import { Title } from '@angular/platform-browser';

@Component({
  selector: 'app-ms005002',
  templateUrl: './ms005002.component.html',
})
export class MS005002DetailComponent implements OnInit, OnDestroy {
  formData: FormGroup;
  showBreabCrumb: boolean;
  showId = true;
  isUpdate = false;
  private unsubscribe$ = new Subject();
  displayMessage: string;
  errorsCode: string;
  submitted = false;
  loading = true;
  authButton = new ScreenAction();
  validator = new ValidatorForm();
  valueOld = {
    languageID: '',
    languageName: '',
    languageDescription: '',
    languageCode: '',
  };
  maxLenghtName = { value: LengthConstant.MAX_LENGTH_NAME };
  maxLenghtCode = { value: LengthConstant.MAX_LENGTH_CODE };
  maxLenghtDescription = { value: LengthConstant.MAX_LENGTH_DESCRIPTION };

  constructor(
    private changDetector: ChangeDetectorRef,
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private languageService: MS005002DetailService,
    private toastService: ToastService,
    private commonFunctionService: CommonFunctionService,
    public translationService: TranslationService,
    private modalService: NgbModal,
    private translate: TranslateService,
    private localStorage: StorageService,
    private titleService: Title,
  ) {
    if (this.route.snapshot.params.id) {
      this.isUpdate = true;
    }
    this.authButton = this.commonFunctionService.initAuthAction(this.authButton);
    this.formData = this.fb.group({
      languageID: [''],
      languageName: [''],
      languageDescription: [''],
      languageCode: [''],
      languageCategoryID: [''],
    });
  }

  ngOnDestroy(): void {
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
  }

  get f() { return this.formData.controls; }

  ngOnInit(): void {
    // change title
    this.translate.get('title.master.language').subscribe((title: string) => {
      this.titleService.setTitle(title);
    });

    // change title when change language
    this.translationService.changeTranslatetion().pipe(takeUntil(this.unsubscribe$),
      mergeMap(lang => this.translate.get('title.master.language'))
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
    const id = this.route.snapshot.params.id;
    if (id) {
      this.languageService.getDetailLanguage(id).pipe(takeUntil(this.unsubscribe$)).subscribe((value) => {
        if (value) {
          // tells Angular that you are now ready for it to run change detection.
          this.loading = false;
          // trigger spiner
          // set value form
          this.formData.patchValue({
            languageID: value.content.languageID,
            languageName: value.content.languageName,
            languageDescription: value.content.languageDescription,
            languageCode: value.content.languageCode,
            languageCategoryID: value.content.languageCategoryID,
          });
          this.valueOld = JSON.parse(JSON.stringify(value.content));
        }
      },
        err => {
          // errors pending spiner
          if (err) {
            this.loading = false;
          }
        });
      this.showBreabCrumb = true;
    } else {
      this.loading = false;
      this.showBreabCrumb = false;
    }
  }

  submitForm() {
    this.submitted = true;
    this.formData.controls.languageName.setValue(this.formData.controls.languageName.value.trim());
    this.formData.controls.languageName.setValidators([Validators.required, Validators.maxLength(LengthConstant.MAX_LENGTH_NAME)]);
    this.formData.controls.languageCode.setValue(this.formData.controls.languageCode.value.trim());
    // tslint:disable-next-line:max-line-length
    this.formData.controls.languageCode.setValidators([Validators.required, Validators.maxLength(LengthConstant.MAX_LENGTH_CODE), Validators.pattern(Regex.fieldCode)]);
    this.formData.controls.languageCategoryID.setValue(this.formData.controls.languageCode.value);
    this.formData.controls.languageCategoryID.setValidators([Validators.required]);
    this.formData.controls.languageDescription.setValue(this.formData.controls.languageDescription.value.trim());
    this.formData.controls.languageDescription.setValue(this.formData.controls.languageDescription.value.trim());
    this.formData.controls.languageDescription.setValidators([Validators.maxLength(LengthConstant.MAX_LENGTH_DESCRIPTION)]);
    this.validator.updateTreeValidity(this.formData);
    if (!this.formData.invalid) {
      if (this.f.languageID.value) {
        this.languageService.updateLanguage(this.formData.value).pipe(takeUntil(this.unsubscribe$)).subscribe(
          (resp) => {
            if (resp.body.error === null) {
              this.toastService.show('notification-message.save-success', { classname: 'bg-success text-light', delay: 3000 });
              this.submitted = false;
              this.router.navigate(['/language']);
            }
          },
          (errors) => {
            if (errors) {
              this.displayErrorMessage(errors);
            }
          }
        );
      } else {
        this.languageService.createLanguage(this.formData.value).pipe(takeUntil(this.unsubscribe$)).subscribe(
          (resp) => {
            if (resp.body.error === null) {
              this.toastService.show('notification-message.save-success', { classname: 'bg-success text-light', delay: 3000 });
              this.formData.reset();
              this.submitted = false;
              this.router.navigate(['/language']);
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
      this.router.navigate(['/language']);
    } else {
      this.openPopup();
    }
  }
  // open popup confirm
  openPopup() {
    if (this.authButton.CREATE === true || this.authButton.UPDATE === true) {
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
          this.submitForm();
        } else {
          if (result === 'Unsave') {
            this.router.navigate(['/language']);
          }
        }
      });
    } else {
      this.router.navigate(['/language']);
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
