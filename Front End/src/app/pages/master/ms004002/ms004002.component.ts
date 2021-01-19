import { CommonFunctionService } from './../../../services/common-function/common-function.service';
import { ScreenAction } from './../../../common/screen-action/screen-action';
import { TranslationService } from './../../../services/translate/translation.service';
import { mergeMap, takeUntil } from 'rxjs/operators';
import { ChangeDetectorRef, Component, OnInit, OnDestroy } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription, Subject } from 'rxjs';
import { ToastService } from '../../../theme/shared/components/toast-container/toast-service';
import { MS004002Service } from './ms004002.service';
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
  selector: 'app-ms004002',
  templateUrl: './ms004002.component.html'
})
export class MS004002Component implements OnInit, OnDestroy {

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
    levelSkillID: '',
    levelSkillName: '',
    levelSkillDescription: '',
    levelSkillCode: '',
    levelSkillValue: ''
  };
  maxLenghtName = { value: LengthConstant.MAX_LENGTH_NAME };
  maxLenghtCode = { value: LengthConstant.MAX_LENGTH_CODE };
  maxLenghtDescription = { value: LengthConstant.MAX_LENGTH_DESCRIPTION };

  constructor(
    private changDetector: ChangeDetectorRef,
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private levelSkillService: MS004002Service,
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
      levelSkillID: [''],
      levelSkillName: [''],
      levelSkillDescription: [''],
      levelSkillCode: [''],
      levelSkillValue: ['']
    });
  }

  ngOnDestroy(): void {
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
  }

  get f() { return this.formData.controls; }
  ngOnInit(): void {
    // change title
    this.translate.get('title.master.level-skill').subscribe((title: string) => {
      this.titleService.setTitle(title);
    });

    // change title when change language
    this.translationService.changeTranslatetion().pipe(takeUntil(this.unsubscribe$),
      mergeMap(lang => this.translate.get('title.master.level-skill'))
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
      this.levelSkillService.getDetailLevelSkill(id).pipe(takeUntil(this.unsubscribe$)).subscribe((value) => {
        if (value) {
          // tells Angular that you are now ready for it to run change detection.
          this.loading = false;
          // trigger spiner
          // set value form
          this.formData.patchValue({
            levelSkillID: value.content.levelSkillID,
            levelSkillName: value.content.levelSkillName,
            levelSkillDescription: value.content.levelSkillDescription,
            levelSkillCode: value.content.levelSkillCode,
            levelSkillValue: value.content.levelSkillValue
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
    this.formData.controls.levelSkillName.setValue(this.formData.controls.levelSkillName.value.trim());
    this.formData.controls.levelSkillName.setValidators([Validators.required, Validators.maxLength(LengthConstant.MAX_LENGTH_NAME)]);
    this.formData.controls.levelSkillCode.setValue(this.formData.controls.levelSkillCode.value.trim());
    // tslint:disable-next-line:max-line-length
    this.formData.controls.levelSkillCode.setValidators([Validators.required, Validators.maxLength(LengthConstant.MAX_LENGTH_CODE), Validators.pattern(Regex.fieldCode)]);
    this.formData.controls.levelSkillDescription.setValue(this.formData.controls.levelSkillDescription.value.trim());
    this.formData.controls.levelSkillDescription.setValidators([Validators.maxLength(LengthConstant.MAX_LENGTH_DESCRIPTION)]);
    this.formData.controls.levelSkillValue.setValue(this.formData.controls.levelSkillValue.value);
    this.formData.controls.levelSkillValue.setValidators([Validators.required, Validators.pattern(Regex.number)]);
    this.validator.updateTreeValidity(this.formData);
    if (!this.formData.invalid) {
      if (this.f.levelSkillID.value) {
        this.levelSkillService.updateLevelSkill(this.formData.value).pipe(takeUntil(this.unsubscribe$)).subscribe(resp => {
          if (resp.body.error === null) {
            this.toastService.show('notification-message.save-success', { classname: 'bg-success text-light', delay: 3000 });
            this.submitted = false;
            this.router.navigate(['/skill-level']);
          }
        },
          (errors) => {
            if (errors) {
              this.displayErrorMessage(errors);
            }
          });
      } else {
        this.levelSkillService.createLevelSkill(this.formData.value).pipe(takeUntil(this.unsubscribe$)).subscribe(
          (resp) => {
            if (resp.body.error === null) {
              this.toastService.show('notification-message.save-success', { classname: 'bg-success text-light', delay: 3000 });
              this.formData.reset();
              this.submitted = false;
              this.router.navigate(['/skill-level']);
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
      this.router.navigate(['/skill-level']);
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
            this.router.navigate(['/skill-level']);
          }
        }
      });
    } else {
      this.router.navigate(['/skill-level']);
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
