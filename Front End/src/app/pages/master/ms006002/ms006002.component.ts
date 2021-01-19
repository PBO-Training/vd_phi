import { CommonFunctionService } from './../../../services/common-function/common-function.service';
import { ScreenAction } from './../../../common/screen-action/screen-action';
import { mergeMap, takeUntil } from 'rxjs/operators';
import { Component, OnInit, OnDestroy, ChangeDetectorRef } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Subject } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';
import { MS006002DetailService } from './ms006002.service';
import { ToastService } from '../../../theme/shared/components/toast-container/toast-service';
import { TranslationService } from '../../../services/translate/translation.service';
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
  selector: 'app-ms006002',
  templateUrl: './ms006002.component.html',
})
export class MS006002DetailComponent implements OnInit, OnDestroy {
  formData: FormGroup;
  showBreadcumb: boolean;
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
    levelLanguageID: '',
    levelLanguageName: '',
    levelLanguageDescription: '',
    levelLanguageCode: '',
    levelLanguageValue: '',
  };
  maxLenghtName = { value: LengthConstant.MAX_LENGTH_NAME };
  maxLenghtCode = { value: LengthConstant.MAX_LENGTH_CODE };
  maxLenghtDescription = { value: LengthConstant.MAX_LENGTH_DESCRIPTION };

  constructor(
    private changDetector: ChangeDetectorRef,
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private levelLanguageService: MS006002DetailService,
    private localStorage: StorageService,
    // import toast in common components toast service
    public toastService: ToastService,
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
      levelLanguageID: [''],
      levelLanguageName: [''],
      levelLanguageDescription: [''],
      // tslint:disable-next-line:max-line-length
      levelLanguageCode: [''],
      levelLanguageValue: ['']
    });
  }

  ngOnDestroy(): void {
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
  }

  get f() { return this.formData.controls; }

  ngOnInit(): void {
    // change title
    this.translate.get('title.master.level-language').subscribe((title: string) => {
      this.titleService.setTitle(title);
    });

    // change title when change language
    this.translationService.changeTranslatetion().pipe(takeUntil(this.unsubscribe$),
      mergeMap(lang => this.translate.get('title.master.level-language'))
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
      this.levelLanguageService.getDetailLevelLanguage(id).pipe(takeUntil(this.unsubscribe$)).subscribe((value) => {
        if (value) {
          // tells Angular that you are now ready for it to run change detection.
          this.loading = false;
          // trigger spiner
          // set value form
          this.formData.patchValue({
            levelLanguageID: value.content.levelLanguageID,
            levelLanguageName: value.content.levelLanguageName,
            levelLanguageDescription: value.content.levelLanguageDescription,
            levelLanguageCode: value.content.levelLanguageCode,
            levelLanguageValue: value.content.levelLanguageValue
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
      this.showBreadcumb = true;
    } else {
      this.loading = false;
      this.showBreadcumb = false;
    }
  }

  submitForm() {
    this.submitted = true;
    this.formData.controls.levelLanguageName.setValue(this.formData.controls.levelLanguageName.value.trim());
    this.formData.controls.levelLanguageName.setValidators([Validators.required, Validators.maxLength(LengthConstant.MAX_LENGTH_NAME)]);
    this.formData.controls.levelLanguageCode.setValue(this.formData.controls.levelLanguageCode.value.trim());
    // tslint:disable-next-line:max-line-length
    this.formData.controls.levelLanguageCode.setValidators([Validators.required, Validators.maxLength(LengthConstant.MAX_LENGTH_CODE), Validators.pattern(Regex.fieldCode)]);
    this.formData.controls.levelLanguageDescription.setValue(this.formData.controls.levelLanguageDescription.value.trim());
    this.formData.controls.levelLanguageDescription.setValidators([Validators.maxLength(LengthConstant.MAX_LENGTH_DESCRIPTION)]);
    this.formData.controls.levelLanguageValue.setValue(this.formData.controls.levelLanguageValue.value);
    this.formData.controls.levelLanguageValue.setValidators([Validators.required, Validators.pattern(Regex.number)]);
    this.validator.updateTreeValidity(this.formData);
    if (!this.formData.invalid) {
      // if form have level language ID => Update else Create
      if (this.f.levelLanguageID.value) {
        this.levelLanguageService.updateLevelLanguage(this.formData.value).pipe(takeUntil(this.unsubscribe$)).subscribe(resp => {
          if (resp.body.error === null) {
            this.toastService.show('notification-message.save-success', { classname: 'bg-success text-light', delay: 3000 });
            this.submitted = false;
            this.router.navigate(['/levelLanguage']);
          }
        }, (errors) => {
          if (errors) {
            this.displayErrorMessage(errors);
          }
        });
      } else {
        this.levelLanguageService.createLevelLanguage(this.formData.value).pipe(takeUntil(this.unsubscribe$)).subscribe(
          (resp) => {
            if (resp.body.error === null) {
              this.toastService.show('notification-message.save-success', { classname: 'bg-success text-light', delay: 3000 });
              this.formData.reset();
              this.submitted = false;
              this.router.navigate(['/levelLanguage']);
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
      this.router.navigate(['/levelLanguage']);
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
            this.router.navigate(['/levelLanguage']);
          }
        }
      });
    } else {
      this.router.navigate(['/levelLanguage']);
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
