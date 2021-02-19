import { ScreenAction } from './../../../common/screen-action/screen-action';
import { CommonFunctionService } from './../../../services/common-function/common-function.service';
import { ChangeDetectorRef, Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Subject } from 'rxjs';
import { mergeMap, takeUntil } from 'rxjs/operators';
import { TranslationService } from '../../../services/translate/translation.service';
import { ToastService } from '../../../theme/shared/components/toast-container/toast-service';
import { MS003002Service } from './ms003002.service';
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
  selector: 'app-skill-details',
  templateUrl: './ms003002.component.html',
  // changeDetection: ChangeDetectionStrategy.OnPush,

})
export class MS003002Component implements OnInit, OnDestroy {
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
  skillTypeList = [];
  valueOld = {
    skillID: '',
    skillName: '',
    skillDescription: '',
    skillCode: '',
    skillTypeID: ''
  };
  maxLenghtName = { value: LengthConstant.MAX_LENGTH_NAME };
  maxLenghtCode = { value: LengthConstant.MAX_LENGTH_CODE };
  maxLenghtDescription = { value: LengthConstant.MAX_LENGTH_DESCRIPTION };

  constructor(
    private changDetector: ChangeDetectorRef,
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private skillService: MS003002Service,
    private localStorage: StorageService,
    private titleService: Title,
    // import toast in common components toast service
    public toastService: ToastService,
    private commonFunctionService: CommonFunctionService,
    public translationService: TranslationService,
    private modalService: NgbModal,
    private translate: TranslateService
  ) {
    if (this.route.snapshot.params.id) {
      this.isUpdate = true;
    }
    this.authButton = this.commonFunctionService.initAuthAction(this.authButton);
    this.formData = this.fb.group({
      skillID: [''],
      skillName: [''],
      skillDescription: [''],
      skillCode: [''],
      skillTypeID: ['']
    });
  }
  ngOnDestroy(): void {
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
  }
  get f() { return this.formData.controls; }
  ngOnInit(): void {
    // change title
    this.translate.get('title.master.skill').subscribe((title: string) => {
      this.titleService.setTitle(title);
    });

    // change title when change language
    this.translationService.changeTranslatetion().pipe(takeUntil(this.unsubscribe$),
      mergeMap(lang => this.translate.get('title.master.skill'))
    ).subscribe((title: string) => {
      this.titleService.setTitle(title);
    });

    this.translationService.changeTranslatetion().pipe(takeUntil(this.unsubscribe$)).subscribe(event => {
      this.translationService.getErrorsChangeTranslation(this.errorsCode).pipe(takeUntil(this.unsubscribe$)).subscribe(errorMessage => {
        // set errors display after change language
        this.displayMessage = errorMessage;
      });
    });
    this.skillService.ms0030002Init().subscribe(
      data => {
        if (data.content) {
          this.skillTypeList = data.content;
          const id = this.route.snapshot.params.id;
          if (!id) {
            this.formData.patchValue({
              skillTypeID: '' + this.skillTypeList[0]?.skillTypeID,
            });
            this.valueOld.skillTypeID = '' + this.skillTypeList[0]?.skillTypeID;
          }
        }
      }
    );
    this.initData();
  }
  initData() {
    const id = this.route.snapshot.params.id;
    if (id) {
      this.skillService.getDetailSkill(id).pipe(takeUntil(this.unsubscribe$)).subscribe((value) => {
        if (value) {
          // tells Angular that you are now ready for it to run change detection.
          this.loading = false;
          this.formData.patchValue({
            skillID: '' + value.content.skillID,
            skillName: value.content.skillName,
            skillDescription: value.content.skillDescription,
            skillCode: value.content.skillCode,
            skillTypeID: '' + value.content.skillTypeID,
          });
          this.valueOld = JSON.parse(JSON.stringify(value.content));
          this.valueOld.skillID = '' + value.content.skillID,
            this.valueOld.skillTypeID = '' + value.content.skillTypeID;
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
    this.formData.controls.skillName.setValue(this.formData.controls.skillName.value.trim());
    this.formData.controls.skillName.setValidators([Validators.required, Validators.maxLength(LengthConstant.MAX_LENGTH_NAME)]);
    this.formData.controls.skillCode.setValue(this.formData.controls.skillCode.value.trim());
    // tslint:disable-next-line:max-line-length
    this.formData.controls.skillCode.setValidators([Validators.required, Validators.maxLength(LengthConstant.MAX_LENGTH_CODE), Validators.pattern(Regex.fieldCode)]);
    this.formData.controls.skillDescription.setValue(this.formData.controls.skillDescription.value.trim());
    this.formData.controls.skillDescription.setValidators([Validators.maxLength(LengthConstant.MAX_LENGTH_DESCRIPTION)]);
    this.formData.controls.skillTypeID.setValue(this.formData.controls.skillTypeID.value);
    this.validator.updateTreeValidity(this.formData);
    if (this.formData.invalid) {
      // if form have skill ID => Update else Create
      if (this.f.skillID.value) {
        this.skillService.updateSkill(this.formData.value).pipe(takeUntil(this.unsubscribe$)).subscribe(resp => {
          if (resp.body.error === null) {
            this.toastService.show('notification-message.save-success', { classname: 'bg-success text-light', delay: 3000 });
            this.submitted = false;
            this.router.navigate(['/skill']);
          }
        },
          (errors) => {
            if (errors) {
              this.displayErrorMessage(errors);
            }
          });
      } else {
        this.skillService.createSkill(this.formData.value).pipe(takeUntil(this.unsubscribe$)).subscribe(
          (resp) => {
            if (resp.body.error === null) {
              this.toastService.show('notification-message.save-success', { classname: 'bg-success text-light', delay: 3000 });
              this.formData.reset();
              this.submitted = false;
              this.router.navigate(['/skill']);
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
      this.router.navigate(['/skill']);
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
            this.router.navigate(['/skill']);
          }
        }
      });
    } else {
      this.router.navigate(['/skill']);
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

