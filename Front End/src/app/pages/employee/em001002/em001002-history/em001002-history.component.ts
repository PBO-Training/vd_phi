import {
  Component, ElementRef,
  EventEmitter, Input, OnChanges, OnDestroy, OnInit, Output, QueryList, SimpleChanges, ViewChildren
} from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Observable, Subject } from 'rxjs';
import { distinctUntilChanged, take, takeUntil } from 'rxjs/operators';
import { LengthConstant } from '../../../../common/constant/length';
import { DateConfig, formatDate } from '../../../../common/datepicker-config/datepicker-config';
import { ScreenAction } from '../../../../common/screen-action/screen-action';
import { CustomReactiveFormValidator } from '../../../../common/validator/custom-validator';
import { DateValidator } from '../../../../common/validator/date-validator';
// tslint:disable-next-line:max-line-length
import { Database, EvaluateEmployeeProject, HistoryWork, InitScopeWork, OperationSystem, PositionProject, ServerErrors, Skill } from '../em001002-entity';
import { StoreService } from '../em001002-store-service';

@Component({
  selector: 'app-em001002-history',
  templateUrl: './em001002-history.component.html',
  styleUrls: ['./em001002-history.component.scss']
})
export class Em001002HistoryComponent implements OnInit, OnChanges, OnDestroy {
  @ViewChildren('appHistory', { read: ElementRef }) appHistory: QueryList<ElementRef>;

  @Input() listHistory: HistoryWork[];
  @Input() companyName: string;
  @Input() listOperationSystem: OperationSystem[];
  @Input() listPositionProject: PositionProject[];
  @Input() listDatabase: Database[];
  @Input() listScopeWork: InitScopeWork[];
  @Input() listSkill: Skill[];
  @Input() listEvaluateEmployeeProject: EvaluateEmployeeProject[];
  @Input() authButton: ScreenAction;
  @Input() serverErrors$: Observable<ServerErrors>;
  @Input() isAddNewHistory: boolean;
  @Input() isProfile: boolean;
  @Output() submitForm: EventEmitter<any> = new EventEmitter();
  @Output() back: EventEmitter<any> = new EventEmitter();
  @Output() backOrCancel: EventEmitter<any> = new EventEmitter();
  submited: boolean;
  checkRemove: boolean;
  unsubscribe$ = new Subject();
  config = new DateConfig();

  formHistory: FormGroup;
  displayMessage: string;
  errorsCode: string;
  maxLenghtNotes = { value: LengthConstant.MAX_LENGTH_NOTES };
  maxLenghtInput = { value: LengthConstant.MAX_LENGTH_INPUT };
  maxLenghtPhone = { value: LengthConstant.MAX_LENGTH_DATE_PHONE };
  constructor(
    public fb: FormBuilder,
    public storeService: StoreService,

  ) {
    this.formHistory = this.fb.group({
      listHistory: this.fb.array([])
    });
  }
  get histories() {
    return this.formHistory.controls.listHistory as FormArray;
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes.listScopeWork) {
      this.listScopeWork.sort((a, b) => a.scopeWorkID - b.scopeWorkID);
    }
    this.storeService.saveValueChange(this.formHistory.value);
    this.storeService.saveStore(this.formHistory.value);
  }
  ngOnInit(): void {
    if (this.isAddNewHistory) {
      this.addNewHistory();
    } else {
      this.createFormHistory();
    }
    // form value changes
    this.formHistory.valueChanges.pipe(takeUntil(this.unsubscribe$), distinctUntilChanged()).subscribe(val => {
      // update status form when submit in parent component
      this.storeService.updateStatusForm(this.formHistory);
      this.storeService.saveValueChange(this.formHistory.value);
    });
    // handle errors
    this.serverErrors$.pipe(distinctUntilChanged()).subscribe(val => {
      if (!val) {
        return;
      }
      this.displayMessageError(val);
    });

  }
  setValidator() {
    const name = this.histories.controls.map(control => control.get('historyCompanyName'));
    const startDate = this.histories.controls.map(control => control.get('historyStartDate'));
    const endDate = this.histories.controls.map(control => control.get('historyEndDate'));
    const evaluateEmp = this.histories.controls.map(control => control.get('evaluateEmployeeProjectID'));

    name.map((abtractControl) => {
      abtractControl.setValidators(Validators.required);
      abtractControl.updateValueAndValidity({ onlySelf: false, emitEvent: false });
    });
    startDate.map(abtractControl => {
      abtractControl.setValidators(Validators.required);
    });
    endDate.map(abtractControl => {
      abtractControl.setValidators(Validators.required);
      abtractControl.updateValueAndValidity({ onlySelf: false, emitEvent: false });
    });
    evaluateEmp.map(abtractControl => {
      abtractControl.setValidators(Validators.required);
      abtractControl.updateValueAndValidity({ onlySelf: false, emitEvent: false });
    });

  }
  createFormHistory() {
    if (this.listHistory) {
      // tslint:disable-next-line:prefer-for-of
      for (let index = 0; index < this.listHistory.length; index++) {
        // if (this.listHistory[index].projectID == null) {
        const group = this.fb.group({
          projectID: [this.listHistory[index].projectID],
          historyWorkID: ['' + this.listHistory[index].historyWorkID],
          historyProjectName: [this.listHistory[index].historyProjectName, Validators.maxLength(LengthConstant.MAX_LENGTH_INPUT)],
          historyCompanyName: [
            this.listHistory[index].historyCompanyName, [Validators.required, Validators.maxLength(LengthConstant.MAX_LENGTH_INPUT)]
          ],
          positionProjectID: [this.listHistory[index].positionProjectID, Validators.required],
          evaluateEmployeeProjectID: [this.listHistory[index].evaluateEmployeeProjectID, Validators.required],
          historyInCompany: [this.listHistory[index].insideCompany],
          historyDescription: [this.listHistory[index].historyDescription, Validators.maxLength(LengthConstant.MAX_LENGTH_NOTES)],
          historyStartDate: [
            formatDate(this.listHistory[index].startDate),
            [Validators.required, Validators.maxLength(LengthConstant.MAX_LENGTH_DATE_PHONE)]
          ],
          historyEndDate: [
            formatDate(this.listHistory[index].endDate),
            [Validators.required, Validators.maxLength(LengthConstant.MAX_LENGTH_DATE_PHONE)]],
          operationSystemID: [this.listHistory[index].operationSystemID],
          databaseID: [this.listHistory[index].databaseID],
          listScopeWork: [this.listHistory[index].listScopeWork.map(item => item.scopeWorkID)],
          listSkill: [this.listHistory[index].listSkill.map(item => item.skillID)],
          tool: [this.listHistory[index].tool, Validators.maxLength(LengthConstant.MAX_LENGTH_INPUT)],
        },
          { validator: Validators.compose([DateValidator.dateLessThan('historyStartDate', 'historyEndDate')]) }
        );
        this.histories.push(group); // Push HistoryForms
        // }
      }
      this.formHistory.value.listHistory.map(control =>
        control.listScopeWork.sort((a, b) => {
          return a - b;
        }));
      this.storeService.saveValueChange(this.formHistory.value);
      this.storeService.saveStore(this.formHistory.value);
      CustomReactiveFormValidator.updateTreeValidity(this.formHistory);
    }
  }

  addHistory() {
    const group = this.fb.group({
      projectID: null,
      historyWorkID: [-1],
      historyProjectName: ['', { validators: Validators.maxLength(LengthConstant.MAX_LENGTH_INPUT) }],
      historyCompanyName: ['', { validators: [Validators.required, Validators.maxLength(LengthConstant.MAX_LENGTH_INPUT)] }],
      positionProjectID: [null, Validators.required],
      historyInCompany: [false],
      historyDescription: ['', Validators.maxLength(LengthConstant.MAX_LENGTH_NOTES)],
      historyStartDate: [, [Validators.required, Validators.maxLength(LengthConstant.MAX_LENGTH_DATE_PHONE)]],
      historyEndDate: [, [Validators.required, Validators.maxLength(LengthConstant.MAX_LENGTH_DATE_PHONE)]],
      operationSystemID: [null],
      evaluateEmployeeProjectID: [null, Validators.required],
      databaseID: [null],
      listScopeWork: [[]],
      listSkill: [[]],
      tool: ['', Validators.maxLength(LengthConstant.MAX_LENGTH_INPUT)],
    },
      { validator: Validators.compose([DateValidator.dateLessThan('historyStartDate', 'historyEndDate')]) });
    this.histories.insert(0, group); // Push HistoryForms
    // focus first element when create new language
    this.appHistory.changes.pipe(take(1)).subscribe({
      next: changes => changes.first.nativeElement.focus()
    });

  }
  submit(form: any) {
    this.submited = true;
    // set form validator
    CustomReactiveFormValidator.updateTreeValidity(this.formHistory);
    // this.setValidator();
    if (this.formHistory.valid) {
      form = this.formHistory.value;
      this.submitForm.emit(form);
    }
  }

  deleteHistory(index: number) {
    this.histories.removeAt(index);
    if (this.histories.length === 0) {
      this.checkRemove = true;
    }
  }

  ngOnDestroy(): void {
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
  }
  openPopup() {
    this.back.emit('back');
  }

  displayMessageError(errors) {

  }

  openPopupHistory() {
    this.backOrCancel.emit('back');
  }

  addNewHistory() {
    const group = this.fb.group({
      projectID: null,
      historyWorkID: [-1],
      historyProjectName: ['', { validators: Validators.maxLength(LengthConstant.MAX_LENGTH_INPUT) }],
      historyCompanyName: ['', { validators: [Validators.required, Validators.maxLength(LengthConstant.MAX_LENGTH_INPUT)] }],
      positionProjectID: [null, Validators.required],
      historyInCompany: [false],
      historyDescription: ['', Validators.maxLength(LengthConstant.MAX_LENGTH_NOTES)],
      historyStartDate: [, [Validators.required, Validators.maxLength(LengthConstant.MAX_LENGTH_DATE_PHONE)]],
      historyEndDate: [, [Validators.required, Validators.maxLength(LengthConstant.MAX_LENGTH_DATE_PHONE)]],
      operationSystemID: [null],
      evaluateEmployeeProjectID: [null, Validators.required],
      databaseID: [null],
      listScopeWork: [[]],
      listSkill: [[]],
      tool: ['', Validators.maxLength(LengthConstant.MAX_LENGTH_INPUT)],
    },
      { validator: Validators.compose([DateValidator.dateLessThan('historyStartDate', 'historyEndDate')]) });
    this.histories.insert(0, group);
    this.storeService.updateStatusForm(this.formHistory);
    this.storeService.saveValueChange(this.formHistory.value);
  }
}
