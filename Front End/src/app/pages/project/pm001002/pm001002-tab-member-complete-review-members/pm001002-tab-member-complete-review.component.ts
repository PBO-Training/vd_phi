import { Component, OnInit, Input, SimpleChanges, OnChanges, Output, EventEmitter, OnDestroy } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { ScreenAction } from '../../../../common/screen-action/screen-action';
import { Listprops } from '../../../../common/page-options';
import { DateValidator } from '../../../../common/validator/date-validator';
import { CommonFunctionService } from '../../../../services/common-function/common-function.service';
import { TranslationService } from '../../../../services/translate/translation.service';
import { ToastService } from '../../../../theme/shared/components/toast-container/toast-service';
import { Member, DeleteRequest } from './pm001002-tab-member-complete-review-entity';
import { DateConfig, formatDate, parseDate } from '../../../../common/datepicker-config/datepicker-config';
import { DropDownDataMember, Pm001002Response } from '../pm001002-entity';
import { LengthConstant } from '../../../../common/constant/length';

@Component({
  selector: 'app-pm001002-tab-member-complete-review',
  templateUrl: './pm001002-tab-member-complete-review.component.html',
  styleUrls: ['./pm001002-tab-member-complete-review.component.scss']
})
export class Pm001002TabMemberCompleteReviewComponent implements OnInit, OnChanges, OnDestroy {
  @Input() dropdownData: DropDownDataMember;
  @Input() selectedMembers: DeleteRequest;
  @Input() projectResponse: Pm001002Response;
  @Output() navigateActionTabMember = new EventEmitter<number>();
  @Output() request = new EventEmitter<DeleteRequest>();

  authButton = new ScreenAction();
  private unsubscribe$ = new Subject();
  submitted = false;
  date = new Date();
  errorsCode: string;
  displayMessage: string;
  formData: FormGroup;
  formDetails: FormGroup;
  projectID = this.route.snapshot.params.id;
  screenProps = new Listprops();
  submitedDetails = false;
  listMember: Member[];
  memberForm: FormGroup;
  config = new DateConfig();
  maxLenghtDescription = {value: LengthConstant.MAX_LENGTH_DESCRIPTION};

  constructor(
    private route: ActivatedRoute,
    private formBuilder: FormBuilder,
    public toastService: ToastService,
    private translateService: TranslationService,
    private commonFunctionService: CommonFunctionService
  ) {
    this.memberForm = this.formBuilder.group({
      listMember: this.formBuilder.array([])
    });
    this.authButton = this.commonFunctionService.initAuthAction(this.authButton);
  }

  get members() { return this.memberForm.controls.listMember as FormArray; }

  initData(): void {
    this.members.clear();
    this.selectedMembers?.listEmployee?.forEach(member => {
      const group = this.formBuilder.group({
        employeeID: ['' + member.employeeID],
        dateJoinProject: [formatDate(member.dateJoinProject), Validators.required],
        dateOutProject: [formatDate(member.dateOutProject), Validators.required],
        positionProjectID: [member.positionProjectID],
        evaluateEmployeeProjectID: [null, Validators.required],
        note: ['', Validators.maxLength(LengthConstant.MAX_LENGTH_DESCRIPTION)]
      }, {
        validator: Validators.compose([DateValidator.dateLessThan('dateJoinProject', 'dateOutProject')])
      });
      this.members.push(group);
    });
  }

  ngOnInit(): void {
    this.screenProps.loading = true;
    this.submitted = false;
    this.initData();
  }

  ngOnChanges(changes: SimpleChanges): void {
    this.initData();
  }

  ngOnDestroy(): void {
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
  }

  // Get form
  get getFormControl() {
    return this.formData.controls;
  }

  get formDetailsValidation() { return this.memberForm.controls; }

  // Check data, sent request to parent companent
  save() {
    this.submitted = true;
    if (!this.memberForm.invalid) {
      const valueMembers = this.members.value;
      valueMembers.forEach(member => {
        member.dateJoinProject = parseDate(member.dateJoinProject);
        member.dateOutProject = parseDate(member.dateOutProject);
      });
      this.selectedMembers = {
        ...this.selectedMembers,
        listEmployee: valueMembers
      };
      this.request.emit(this.selectedMembers);
    }
  }

  /*
    * Description: Set error to display
    * Param Object - errors
    */
  displayMessageError(errors: any) {
    const formControl = this.formDetails.get(errors.itemName);
    if (formControl) {
      this.translateService.getTranslation(errors.code).pipe(takeUntil(this.unsubscribe$)).subscribe(errorMessage => {
        // set errors formcontrol
        this.errorsCode = errors.code;
        this.displayMessage = errorMessage;
        formControl.setErrors({
          serverError: errorMessage
        });
      });
    }
  }

  backToList = (value: number) => {
    this.navigateActionTabMember.emit(value);
  }
}
