import { Component, ElementRef, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';
import { Subject } from 'rxjs';
import { debounceTime, takeUntil } from 'rxjs/operators';
import { ValidatorForm } from '../../../../common/validator/update-validity';
import { LengthConstant } from '../../../../common/constant/length';
import { DateConfig, formatDate, parseDate, parseNewDateJs } from '../../../../common/datepicker-config/datepicker-config';
import { ScreenAction } from '../../../../common/screen-action/screen-action';
import { DateValidator } from '../../../../common/validator/date-validator';
import { CommonFunctionService } from '../../../../services/common-function/common-function.service';
import { ToastService } from '../../../../theme/shared/components/toast-container/toast-service';
import { DropDownIssueDetail } from '../pm001002-entity';
import { Pm001002Service } from '../pm001002.service';
import { TranslationService } from '../../../../services/translate/translation.service';
import { StorageService } from '../../../../services/storage/storage.service';

@Component({
    selector: 'app-issue-detail',
    templateUrl: './pm001002-tab-issue-detail.component.html',
    styleUrls: ['./pm001002-tab-issue-detail.component.scss']
})
export class Pm001002IssueDetailComponent implements OnInit, OnChanges {
    @Output() navigateToDetail = new EventEmitter<boolean>();
    @Output() reGetProjectDetail = new EventEmitter<boolean>();
    @Output() detailMember = new EventEmitter<any>();
    @Input() issueID: number;
    issueDetail: any;
    isEdit: boolean;
    formDetails: FormGroup;
    config = new DateConfig();
    issueStatusCode: string;
    isCreate = false;
    dropdownData: DropDownIssueDetail;
    validator = new ValidatorForm();
    date = new Date();
    errorsCode: string;
    private unsubscribe$ = new Subject();
    displayMessage: string;
    authButton = new ScreenAction();
    historyIssue = [];
    timeAddedIssue: any;
    employeeID = this.storageService.getUser()['employeeID'];

    maxLenghtCode = { value: LengthConstant.MAX_LENGTH_CODE };
    maxLenghtName = { value: LengthConstant.MAX_LENGTH_NAME };
    maxLenghtDescription = { value: LengthConstant.MAX_LENGTH_DESCRIPTION };

    constructor(
        private projectSerivce: Pm001002Service,
        private router: ActivatedRoute,
        private fb: FormBuilder,
        private el: ElementRef,
        public toastService: ToastService,
        private translateService: TranslateService,
        public common: CommonFunctionService,
        private commonTranslateService: TranslationService,
        private storageService: StorageService
    ) {
        this.authButton = this.common.initAuthAction(this.authButton);
        this.initForm();
    }

    ngOnChanges(changes: SimpleChanges): void {
    }

    ngOnInit(): void {
        this.initData();
        this.formDetails.controls.issueSubject.valueChanges.pipe(debounceTime(300)).subscribe(val => {
            if (val) {
                const control = this.formDetails.controls.issueSubject;
                control.setValidators([Validators.required, Validators.maxLength(LengthConstant.MAX_LENGTH_STRING)]);
                control.updateValueAndValidity({ onlySelf: true, emitEvent: false });
            }
        });
    }

    initData = () => {
        this.projectSerivce.pm001002InitIssueDetail().subscribe(
            (res: any) => {
                this.dropdownData = res.content;
                if (this.issueID === undefined || this.issueID === null) {
                    this.isCreate = true;
                    this.isEdit = true;
                    this.initDataCreate();
                } else {
                    this.initDataUpdate();
                }
            }
        );
    }

    initForm = () => {
        const listCoupDate = [
            { from: 'issueStartDatePlan', to: 'issueEndDatePlan' },
            { from: 'issueStartDateActual', to: 'issueEndDateActual' },
        ];
        this.formDetails = this.fb.group({
            issueID: [this.issueID],
            issueSubject: [''],
            issueDescription: ['', [Validators.maxLength(LengthConstant.MAX_LENGTH_DESCRIPTION)]],
            issueStartDatePlan: [formatDate(parseNewDateJs(this.date))],
            issueEndDatePlan: [formatDate(parseNewDateJs(this.date))],
            issueAmountPlan: [0, [Validators.max(LengthConstant.MAX_NUMBER)]],
            issueQualityPlan: [0, [Validators.max(LengthConstant.MAX_NUMBER)]],
            issueStartDateActual: [formatDate(parseNewDateJs(this.date))],
            issueEndDateActual: [formatDate(parseNewDateJs(this.date))],
            issueAmountActual: [0, [Validators.max(LengthConstant.MAX_NUMBER)]],
            issueQualityActual: [0, [Validators.max(LengthConstant.MAX_NUMBER)]],
            estimatedTime: [0, [Validators.max(LengthConstant.MAX_NUMBER)]],
            percentDone: [0, [Validators.min(LengthConstant.MIN_NUMBER), Validators.max(LengthConstant.MAX_PERCENT)]],
            priorityIssueID: [0],
            projectID: [this.router.snapshot.params.id],
            employeeID: [0],
            trackerID: [0],
            statusIssueID: [0],
        }, {
            validator: Validators.compose([DateValidator.listDateLessThan(listCoupDate)])
        });
    }

    get formDetailsValidation() { return this.formDetails.controls; }

    initDataCreate = () => {
        this.formDetails.patchValue({
            trackerID: this.dropdownData?.listTracker[0].keyResponse,
            statusIssueID: this.dropdownData?.listStatusIssue[0].keyResponse,
            priorityIssueID: this.dropdownData?.listPriorityIssue[1].keyResponse,
            employeeID: this.employeeID
        });
    }

    initDataUpdate = () => {
        this.projectSerivce.pm001002GetDetailIssue(this.issueID).subscribe(
            (res: any) => {
                this.issueDetail = res.content;
                this.issueStatusCode = res.content.statusIssue?.statusIssueCode;
                this.formDetails.patchValue({
                    issueID: this.issueID,
                    issueSubject: res.content.issueSubject,
                    issueDescription: res.content.issueDescription,
                    issueStartDatePlan: formatDate(res.content.issueStartDatePlan?.split('T')[0]),
                    issueEndDatePlan: formatDate(res.content.issueEndDatePlan?.split('T')[0]),
                    issueAmountPlan: res.content.issueAmountPlan,
                    issueQualityPlan: res.content.issueQualityPlan,
                    issueStartDateActual: formatDate(res.content.issueStartDateActual?.split('T')[0]),
                    issueEndDateActual: formatDate(res.content.issueEndDateActual?.split('T')[0]),
                    issueAmountActual: res.content.issueAmountActual,
                    issueQualityActual: res.content.issueQualityActual,
                    estimatedTime: res.content.estimatedTime,
                    percentDone: res.content.percentDone,
                    trackerID: res.content.tracker?.trackerID,
                    statusIssueID: res.content.statusIssue?.statusIssueID,
                    projectID: res.content.project?.projectID,
                    employeeID: res.content.employee?.employeeID,
                    priorityIssueID: res.content.priorityIssue?.priorityIssueID,
                });
                this.processTimeUpdate(res.content);
                this.processTimeAdded(res.content);
                this.isEdit = false;
            }
        );
    }

    saveData = () => {
        this.formDetails.controls.issueSubject.setValue(this.formDetails.controls.issueSubject.value?.trim());
        this.formDetails.controls.issueSubject.setValidators([Validators.required, Validators.maxLength(LengthConstant.MAX_LENGTH_NAME)]);
        this.validator.updateTreeValidity(this.formDetails);
        if (this.formDetails.valid) {
            this.formDetails.value.issueStartDatePlan = parseDate(this.formDetails.value.issueStartDatePlan);
            this.formDetails.value.issueEndDatePlan = parseDate(this.formDetails.value.issueEndDatePlan);
            this.formDetails.value.issueStartDateActual = parseDate(this.formDetails.value.issueStartDateActual);
            this.formDetails.value.issueEndDateActual = parseDate(this.formDetails.value.issueEndDateActual);
            if (this.issueID === undefined || this.issueID === null) {
                this.projectSerivce.pm001002CreateIssue(this.formDetails.value).subscribe(
                    (res: any) => {
                        if (res.error === null) {
                            this.translateService.get('notification-message.save-success').subscribe((text: string) => {
                                this.toastService.show(text, { classname: 'bg-success text-light', delay: 3000 });
                            });
                            this.onReGetProjectDetail(true);
                            this.backToList(false);
                        }
                    },
                    fail => {
                        this.displayMessageError(fail.error.error);
                    }
                );
            } else {
                this.projectSerivce.pm001002UpdateIssue(this.formDetails.value).subscribe(
                    (res: any) => {
                        if (res.error === null) {
                            this.translateService.get('notification-message.save-success').subscribe((text: string) => {
                                this.toastService.show(text, { classname: 'bg-success text-light', delay: 3000 });
                            });
                            this.onReGetProjectDetail(true);
                            this.initDataUpdate();
                        }
                    },
                    fail => {
                        this.displayMessageError(fail.error.error);
                    }
                );
            }
        }
    }

    /*
  * Description: Set error to display
  * Param Object - errors
  */
    displayMessageError(errors: any) {
        const formControl = this.formDetails.get(errors.itemName);
        if (formControl) {
            this.commonTranslateService.getTranslation(errors.code).pipe(takeUntil(this.unsubscribe$)).subscribe(errorMessage => {
                // set errors formcontrol
                this.errorsCode = errors.code;
                this.displayMessage = this.commonTranslateService.convertToKeyJson(this.errorsCode);
                formControl.setErrors({
                    serverError: errorMessage
                });
            });
        }
    }

    backToList = (value: boolean) => {
        this.navigateToDetail.emit(value);
    }

    onReGetProjectDetail(value: boolean): void {
        this.reGetProjectDetail.emit(value);
    }

    onCancel(): void {
        this.initData();
        this.isEdit = false;
    }

    onEdit(): void {
        this.isEdit = true;
    }

    calculatorTimeOver(time: Date): any {
        const currentTime = new Date();
        const originTime = new Date(time);
        const result = currentTime.getTime() - originTime.getTime();
        const m = Math.floor(result / 1000 / 60);
        const h = Math.floor(result / 1000 / 60 / 60);
        const d = Math.floor(result / 1000 / 60 / 60 / 24);
        if (m < 1) {
            return {
                time: result,
                unit: 's'
            };
        }
        if (h < 1) {
            return {
                time: m,
                unit: 'm'
            };
        }

        if (d < 1) {
            return {
                time: h,
                unit: 's'
            };
        }
        return {
            time: d,
            unit: 'd'
        };
    }

    processTimeUpdate(issueDetail: any): void {
        this.historyIssue = issueDetail?.historyIssueUpdate.sort((a: any, b: any) =>
            new Date(a.historyUpdateIssueTime).getTime() - new Date(b.historyUpdateIssueTime).getTime());
        const currentTime = new Date();
        this.historyIssue.forEach(item => {
            const originTime = new Date(item.historyUpdateIssueTime);
            const result = currentTime.getTime() - originTime.getTime();
            const m = Math.floor(result / 1000 / 60);
            const h = Math.floor(result / 1000 / 60 / 60);
            const d = Math.floor(result / 1000 / 60 / 60 / 24);
            if (m < 1) {
                item.historyUpdateIssueTime = Math.floor(result / 1000);
                item.unit = 's';
                return;
            }
            if (h < 1) {
                item.historyUpdateIssueTime = m;
                item.unit = 'm';
                return;
            }
            if (d < 1) {
                item.historyUpdateIssueTime = h;
                item.unit = 'h';
                return;
            }
            item.historyUpdateIssueTime = d;
            item.unit = 'd';
            return;
        });
    }

    processTimeAdded(issueDetail: any): void {
        const currentTime = new Date();
        const result = currentTime.getTime() - new Date(issueDetail?.issueTimeAdded).getTime();
        const m = Math.floor(result / 1000 / 60);
        const h = Math.floor(result / 1000 / 60 / 60);
        const d = Math.floor(result / 1000 / 60 / 60 / 24);
        if (m < 1) {
            issueDetail.issueTimeAdded = Math.floor(result / 1000);
            issueDetail.unit = 's';
            return;
        }
        if (h < 1) {
            issueDetail.issueTimeAdded = m;
            issueDetail.unit = 'm';
            return;
        }
        if (d < 1) {
            issueDetail.issueTimeAdded = h;
            issueDetail.unit = 'h';
            return;
        }
        issueDetail.issueTimeAdded = d;
        issueDetail.unit = 'd';
        return;
    }

    navigateMemberDetail(employeeID: number): void {
        this.detailMember.emit(employeeID);
      }
}
