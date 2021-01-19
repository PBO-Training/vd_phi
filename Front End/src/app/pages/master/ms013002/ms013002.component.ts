import { DateConfig, formatDate, parseDate, parseNewDateJs } from './../../../common/datepicker-config/datepicker-config';
import { StoreService } from './../../employee/em001002/em001002-store-service';
import { CustomReactiveFormValidator } from './../../../common/validator/custom-validator';
import { TranslateService } from '@ngx-translate/core';
import { ModalConfirmComponent } from './../../../theme/shared/components/modal-confirm/modal-confirm.component';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { HolidayDetail } from './ms013002-detail-entity';
import { Holiday } from './ms013002-entity';
import { MS013002DetailService } from './ms013002.service';
import { mergeMap, takeUntil } from 'rxjs/operators';
import { LengthConstant } from './../../../common/constant/length';
import { TranslationService } from './../../../services/translate/translation.service';
import { CommonFunctionService } from './../../../services/common-function/common-function.service';
import { ToastService } from './../../../theme/shared/components/toast-container/toast-service';
import { ActivatedRoute, Router } from '@angular/router';
import { FormGroup, FormBuilder, Validators, FormArray } from '@angular/forms';
import { Subject } from 'rxjs';
import * as _ from 'lodash';
import { ScreenAction } from './../../../common/screen-action/screen-action';
import { Component, OnInit, OnDestroy, ChangeDetectorRef, Input, AfterViewInit } from '@angular/core';
import { ValidatorForm } from '../../../common/validator/update-validity';
import { StorageService } from '../../../services/storage/storage.service';
import { Title } from '@angular/platform-browser';

@Component({
  selector: 'app-ms013002',
  templateUrl: './ms013002.component.html',
  styleUrls: ['./ms013002.component.scss'],
})
export class MS013002DetailComponent implements OnInit, OnDestroy, AfterViewInit {
  formData: FormGroup;
  showBreadcumb: boolean;
  showId = true;
  isUpdate = false;
  response: Holiday;
  private unsubscribe$ = new Subject();
  displayMessage: string;
  errorsCode: string;
  submitted = false;
  loading = true;
  config = new DateConfig();
  authButton = new ScreenAction();
  validator = new ValidatorForm();
  checkRemove: boolean;
  listHolidayDetail: any[];
  listHoliday: HolidayDetail[] = [];
  date = new Date();
  maxLenghtName = { value: LengthConstant.MAX_LENGTH_NAME };
  maxLenghtYear = { value: LengthConstant.MAX_LENGTH_YEAR };
  maxLenghtDescription = { value: LengthConstant.MAX_LENGTH_DESCRIPTION };

  valueOld = {
    holidayID: '',
    holidayYear: '',
    holidayName: '',
    listHolidayDetails: [],
  };

  constructor(
    private changDetector: ChangeDetectorRef,
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private localStorage: StorageService,
    private holidayService: MS013002DetailService,
    // import toast in common components toast service
    public toastService: ToastService,
    private modalService: NgbModal,
    public commonFunctionService: CommonFunctionService,
    private translate: TranslateService,
    public translationService: TranslationService,
    private titleService: Title,
  ) {
    if (this.route.snapshot.params.id) {
      this.isUpdate = true;
    }
    this.authButton = this.commonFunctionService.initAuthAction(this.authButton);
    this.formData = this.fb.group({
      holidayID: [''],
      holidayName: ['', Validators.maxLength(LengthConstant.MAX_LENGTH_DESCRIPTION)],
      holidayYear: ['', [Validators.required, Validators.maxLength(LengthConstant.MAX_LENGTH_YEAR), Validators.pattern('^[0-9]*$')]],
      listHolidayDetails: this.fb.array([]),
    });
  }

  ngOnDestroy(): void {
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
  }

  ngAfterViewInit(): void {
    // set validator form dupplicate with param skillID
    this.formData.setValidators(CustomReactiveFormValidator.isDuplicate('holidayDetailsDate'));
  }

  get f() { return this.formData.controls; }

  get holidays() {
    return this.formData.controls.listHolidayDetails as FormArray;
  }

  ngOnInit(): void {
    // change title
    this.translate.get('title.master.holiday').subscribe((title: string) => {
      this.titleService.setTitle(title);
    });

    // change title when change language
    this.translationService.changeTranslatetion().pipe(takeUntil(this.unsubscribe$),
      mergeMap(lang => this.translate.get('title.master.holiday'))
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
      this.holidayService.getDetailHoliday(id).pipe(takeUntil(this.unsubscribe$)).subscribe((value) => {
        if (value) {
          // tells Angular that you are now ready for it to run change detection.
          this.loading = false;
          this.response = value.content;
          // trigger spiner
          // set value form
          this.formData.patchValue({
            holidayID: value.content.holidayID,
            holidayName: value.content.holidayName,
            holidayYear: value.content.holidayYear
          });
          this.createFormHolidate(value.content.listHolidayDetails);
          this.setOldValue(value);
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

  setOldValue(value: any) {
    this.valueOld = value.content;
    // tslint:disable-next-line:forin
    for (const index in value.content.listHolidayDetails) {
      const detailHoliday: HolidayDetail = {
        holidayDetailsName: value.content.listHolidayDetails[index]?.holidayDetailsName,
        // convert yyyy/mm/dd to dd/mm/yyy
        holidayDetailsDate: formatDate(value.content.listHolidayDetails[index]?.holidayDetailsDate.split('T')[0])
      };
      this.listHoliday.push(detailHoliday);
    }
    this.valueOld.listHolidayDetails = this.listHoliday;
  }

  createFormHolidate(detail: any[]) {
    // tslint:disable-next-line:forin
    for (const index in detail) {
      const group = this.fb.group({
        holidayDetailsName: ['' + detail[index]?.holidayDetailsName, [Validators.maxLength(LengthConstant.MAX_LENGTH_DESCRIPTION)]],
        // convert yyyy/mm/dd to dd/mm/yyy
        holidayDetailsDate: [formatDate(detail[index]?.holidayDetailsDate.split('T')[0]), [Validators.required]]
      });
      this.holidays.push(group);
    }
  }

  addHoliday() {
    this.submitted = false;
    const group = this.fb.group({
      holidayDetailsName: ['', [Validators.maxLength(LengthConstant.MAX_LENGTH_DESCRIPTION)]],
      // convert yyyy/mm/dd to dd/mm/yyy
      holidayDetailsDate: [formatDate(parseNewDateJs(this.date)), [Validators.required]]
    });
    this.holidays.insert(0, group);
  }

  deleteHoliday(index: number) {
    this.holidays.removeAt(index);
    if (this.holidays.length) {
      this.checkRemove = true;
    }
  }

  // compare do you have change data
  CheckValue(valueOld: any, valueNew: any): boolean {
    return _.isEqual(valueOld, valueNew);
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
        } else if (result === 'Unsave') {
          this.router.navigate(['/holiday']);
        }
      });
    } else {
      this.router.navigate(['/holiday']);
    }

  }

  back() {
    if (this.CheckValue(this.valueOld, this.formData.value) === true) {
      this.localStorage.saveBackFlag(true);
      this.router.navigate(['/holiday']);
    } else {
      this.openPopup();
    }
  }

  submitForm() {
    this.submitted = true;
    const id = this.route.snapshot.params.id;
    const holidayRequest = {
      ...this.formData.value,
    };
    // tslint:disable-next-line:forin
    for (const index in holidayRequest.listHolidayDetails) {
      // tslint:disable-next-line:max-line-length
      // convert dd/mm/yyy to yyyy/mm/dd
      holidayRequest.listHolidayDetails[index].holidayDetailsDate = parseDate(holidayRequest.listHolidayDetails[index].holidayDetailsDate);
    }
    CustomReactiveFormValidator.updateTreeValidity(this.formData);
    if (this.formData.valid) {
      if (this.f.holidayID.value) {
        this.holidayService.updateHoliday(holidayRequest).pipe(takeUntil(this.unsubscribe$)).subscribe(
          resp => {
            if (resp.body.error === null) {
              this.toastService.show('notification-message.update-success', { classname: 'bg-success text-light', delay: 3000 });
              this.submitted = false;
              this.router.navigate(['/holiday']);
            }
          },
          (errors) => {
            if (errors) {
              this.displayErrorMessage(errors);
            }
          });
      } else {
        this.holidayService.createHoliday(holidayRequest).pipe(takeUntil(this.unsubscribe$)).subscribe(
          (resp) => {
            if (resp.body.error === null) {
              this.toastService.show('notification-message.save-success', { classname: 'bg-success text-light', delay: 3000 });
              this.formData.reset();
              this.submitted = false;
              this.router.navigate(['/holiday']);
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
