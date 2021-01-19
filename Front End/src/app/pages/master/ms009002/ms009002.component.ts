import { ScreenAction } from './../../../common/screen-action/screen-action';
import { CommonFunctionService } from './../../../services/common-function/common-function.service';
import { mergeMap, takeUntil } from 'rxjs/operators';
import { TranslationService } from './../../../services/translate/translation.service';
import { ChangeDetectorRef, Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription, Subject } from 'rxjs';
import { ToastService } from '../../../theme/shared/components/toast-container/toast-service';
import { MS009002DetailService } from './ms009002.service';
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
    selector: 'app-customer-details',
    templateUrl: './ms009002.component.html'
})
export class MS009002Component implements OnInit, OnDestroy {
    formData: FormGroup;
    private unsubscribe$ = new Subject();
    // Display breadcrumb
    showBreadcrumb: boolean;
    // Variable for html file. Use to show customerID
    showId = true;
    // Check user clicked button submit
    submitted = false;
    displayMessage: string;
    isUpdate = false;
    errorsCode: string;
    loading = true;
    authButton = new ScreenAction();
    validator = new ValidatorForm();
    valueOld = {
        customerID: '',
        customerName: '',
        customerDescription: '',
        customerCode: '',
    };
    maxLenghtName = { value: LengthConstant.MAX_LENGTH_NAME };
    maxLenghtCode = { value: LengthConstant.MAX_LENGTH_CODE };
    maxLenghtDescription = { value: LengthConstant.MAX_LENGTH_DESCRIPTION };

    constructor(
        private changDetector: ChangeDetectorRef,
        private fb: FormBuilder,
        private route: ActivatedRoute,
        private router: Router,
        private localStorage: StorageService,
        private customerService: MS009002DetailService,
        // import toast in common components toast service
        public toastService: ToastService,
        private commonFunctionService: CommonFunctionService,
        public translationService: TranslationService,
        private modalService: NgbModal,
        private translate: TranslateService,
        private titleService: Title
    ) {
        if (this.route.snapshot.params.id) {
            this.isUpdate = true;
        }
        this.authButton = this.commonFunctionService.initAuthAction(this.authButton);
        this.formData = this.fb.group({
            customerID: [''],
            customerName: [''],
            customerDescription: [''],
            // tslint:disable-next-line:max-line-length
            customerCode: [''],
        });
    }
    ngOnDestroy(): void {
        this.unsubscribe$.next();
        this.unsubscribe$.complete();
    }
    get f() { return this.formData.controls; }
    ngOnInit(): void {
        // change title
        this.translate.get('title.master.customer').subscribe((title: string) => {
            this.titleService.setTitle(title);
        });

        // change title when change language
        this.translationService.changeTranslatetion().pipe(takeUntil(this.unsubscribe$),
            mergeMap(lang => this.translate.get('title.master.customer'))
        ).subscribe((title: string) => {
            this.titleService.setTitle(title);
        });

        this.translationService.changeTranslatetion().pipe(takeUntil(this.unsubscribe$)).subscribe(
            event => {
                this.translationService.getErrorsChangeTranslation(this.errorsCode).pipe(takeUntil(this.unsubscribe$)).subscribe(
                    errorMessage => {
                        // set errors display after change language
                        this.displayMessage = errorMessage;
                    });
            });
        this.initData();
    }

    // Init data to screen update
    initData() {
        const id = this.route.snapshot.params.id;
        if (id) {
            this.customerService.getDetailCustomer(id).subscribe((value) => {
                if (value) {
                    // tells Angular that you are now ready for it to run change detection.
                    this.loading = false;
                    // trigger spiner
                    // set value form
                    this.formData.patchValue({
                        customerID: value.content.customerID,
                        customerName: value.content.customerName,
                        customerDescription: value.content.customerDescription,
                        customerCode: value.content.customerCode
                    });
                    this.valueOld = JSON.parse(JSON.stringify(value.content));
                }
            }, err => {
                // errors pending spiner
                if (err) {
                    this.loading = false;
                }
            });
            this.showBreadcrumb = true;
        } else {
            this.loading = false;
            this.showBreadcrumb = false;
        }
    }

    // Active when user click to button submit
    submitForm() {
        this.submitted = true;
        this.formData.controls.customerName.setValue(this.formData.controls.customerName.value.trim());
        this.formData.controls.customerName.setValidators([Validators.required, Validators.maxLength(LengthConstant.MAX_LENGTH_NAME)]);
        this.formData.controls.customerCode.setValue(this.formData.controls.customerCode.value.trim());
        // tslint:disable-next-line:max-line-length
        this.formData.controls.customerCode.setValidators([Validators.required, Validators.maxLength(LengthConstant.MAX_LENGTH_CODE), Validators.pattern(Regex.fieldCode)]);
        this.formData.controls.customerDescription.setValue(this.formData.controls.customerDescription.value.trim());
        this.formData.controls.customerDescription.setValidators([Validators.maxLength(LengthConstant.MAX_LENGTH_DESCRIPTION)]);
        this.validator.updateTreeValidity(this.formData);
        if (!this.formData.invalid) {
            // if conditions are true then this is action update else this is action create
            if (this.f.customerID.value) {
                this.customerService.updateCustomer(this.formData.value).pipe(takeUntil(this.unsubscribe$)).subscribe(resp => {
                    if (resp.body.error === null) {
                        this.toastService.show('notification-message.save-success', { classname: 'bg-success text-light', delay: 3000 });
                        this.submitted = false;
                        this.router.navigate(['/customer']);
                    }
                },
                    (errors) => {
                        if (errors) {
                            this.displayErrorMessage(errors);
                        }
                    });
            } else {
                this.customerService.createCustomer(this.formData.value).pipe(takeUntil(this.unsubscribe$)).subscribe((resp) => {
                    if (resp.body.error === null) {
                        this.toastService.show('notification-message.save-success', { classname: 'bg-success text-light', delay: 3000 });
                        this.formData.reset();
                        this.submitted = false;
                        this.router.navigate(['/customer']);
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
            this.router.navigate(['/customer']);
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
                        this.router.navigate(['/customer']);
                    }
                }
            });
        } else {
            this.router.navigate(['/customer']);
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
