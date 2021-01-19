import { Component, OnInit, Input, Output, EventEmitter, OnDestroy, OnChanges, SimpleChanges } from '@angular/core';
import { ScreenAction } from '../../../../common/screen-action/screen-action';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';
import { ToastService } from '../../../../theme/shared/components/toast-container/toast-service';
import { Member, RemoveMemberRequest } from './pm001002-tab-member-entity';
import { Pm001002Service } from '../pm001002.service';
import { CommonFunctionService } from '../../../../services/common-function/common-function.service';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { DateValidator } from '../../../../common/validator/date-validator';
import { TranslationService } from '../../../../services/translate/translation.service';
import { DateConfig, formatDate, parseDate } from '../../../../common/datepicker-config/datepicker-config';
import { DropDownDataMember, Pm001002Response } from '../pm001002-entity';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ModalConfirmComponent } from '../../../../theme/shared/components/modal-confirm/modal-confirm.component';
import { Pm001002StoreSerivce } from '../pm001002-store.service';
import { ModalPersonalAssessmentComponent } from './modal-personal-assessment/modal-personal-assessment.component';
import { ActionReviews } from '../../../../common/constant/type';

@Component({
  selector: 'app-pm001002-tab-member',
  templateUrl: './pm001002-tab-member.component.html',
  styleUrls: ['./pm001002-tab-member.component.scss']
})
export class Pm001002TabListMemberComponent implements OnInit, OnDestroy, OnChanges {
  authButton = new ScreenAction();
  @Input() dropdownData: DropDownDataMember;
  @Output() navigateActionTabMember = new EventEmitter<number>();
  @Input() projectResponse: Pm001002Response;
  @Output() selectedMember = new EventEmitter<any>();
  @Output() requestReviews = new EventEmitter<RemoveMemberRequest>();
  formListMemberShow: FormGroup;
  formListMemberOriginal: FormGroup;
  editIndex: number;
  formPage: FormGroup;
  request: Member;
  private unsubscribe$ = new Subject();
  submitted = false;
  isShowProject = this.route.snapshot.params.id !== undefined ? false : true;
  projectID = this.isShowProject !== false ? '-1' : this.route.snapshot.params.id;
  formChange: any;
  errorsCode: any;
  displayMessage: any;
  collapsed = true;
  config = new DateConfig();
  keyFilter = '';

  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private translateService: TranslateService,
    public toastService: ToastService,
    public translationService: TranslationService,
    private projectService: Pm001002Service,
    private commonFunctionService: CommonFunctionService,
    private modalService: NgbModal,
    private pm001002GlobalStore: Pm001002StoreSerivce
  ) {
    this.formListMemberShow = this.fb.group({
      listMemberShow: this.fb.array([])
    });
    this.formListMemberOriginal = this.fb.group({
      listMemberOriginal: this.fb.array([])
    });
    this.authButton = this.commonFunctionService.initAuthAction(this.authButton);
  }

  ngOnChanges(changes: SimpleChanges): void {
    this.initData();
  }

  get membersShow() { return this.formListMemberShow.controls.listMemberShow as FormArray; }

  get membersOriginal() { return this.formListMemberOriginal.controls.listMemberOriginal as FormArray; }

  ngOnInit(): void {
    this.initForm();
  }

  filterItem(event) {
    this.membersShow.clear();
    this.keyFilter = '' + event.target.value;
    this.membersOriginal.controls.forEach(element => {
      // tslint:disable-next-line: max-line-length
      if ((element.value.employeeName).toLowerCase().normalize('NFD').replace(/[\u0300-\u036f]/g, '').match(this.keyFilter.toLowerCase().normalize('NFD').replace(/[\u0300-\u036f]/g, '')) != null) {
        this.membersShow.push(element);
      }
    });
  }

  initForm(): void {
    this.formPage = this.fb.group({
      projectID: [this.projectID, Validators.min(-1)],
    });
  }

  toggleEdit(index: number) {
    this.editIndex = index;
  }

  cancelEdit() {
    this.initData();
    this.editIndex = null;
  }

  updateInfoMember(index: number) {
    this.submitted = true;
    if (this.membersShow.valid) {
      this.request = {
        ...this.membersShow.controls[index].value,
        projectID: this.projectID,
        dateJoinProject: parseDate(this.membersShow.controls[index].value.dateJoinProject),
        dateOutProject: parseDate(this.membersShow.controls[index].value.dateOutProject),
      };
      this.projectService.pm001002UpdateInfoMember(this.request).subscribe(
        (res: any) => {
          if (res.error === null) {
            this.projectResponse.listEmployee[index] = {
              ...this.projectResponse.listEmployee[index],
              employeeID: Number(this.request.employeeID),
              positionProjectID: this.request.positionProjectID,
              dateJoinProject: this.request.dateJoinProject,
              dateOutProject: this.request.dateOutProject
            };
            this.toastService.show('notification-message.save-success', { classname: 'bg-success text-light', delay: 3000 });
            this.editIndex = null;
          }
        },
        error => {
          this.displayMessageError(error.error);
        }
      );
    }
  }

  ngOnDestroy(): void {
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
  }

  /*
    * Description: Set error to display
    * Param Object - errors
    */
  displayMessageError(errors: any) {
    const formControl = this.formListMemberShow.get(errors.itemName);
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

  addMembers = (value: number) => {
    this.navigateActionTabMember.emit(value);
  }

  initData(): void {
    this.membersShow.clear();
    this.membersOriginal.clear();
    // tslint:disable-next-line: forin
    for (const index in this.projectResponse?.listEmployee) {
      const group = this.fb.group({
        employeeID: ['' + this.projectResponse?.listEmployee[index]?.employeeID],
        employeeCode: [this.projectResponse?.listEmployee[index]?.employeeCode],
        employeeName: [this.projectResponse?.listEmployee[index]?.employeeName],
        dateJoinProject: [formatDate(this.projectResponse?.listEmployee[index]?.dateJoinProject?.split('T')[0])],
        dateOutProject: [formatDate(this.projectResponse?.listEmployee[index]?.dateOutProject?.split('T')[0])],
        // tslint:disable-next-line: max-line-length
        positionProjectID: [this.projectResponse?.listEmployee[index]?.positionProjectID == null ? null : '' + this.projectResponse?.listEmployee[index]?.positionProjectID, Validators.required],
      }, {
        validator: Validators.compose([DateValidator.dateLessThan('dateJoinProject', 'dateOutProject')])
      });
      this.membersOriginal.push(group);
      this.membersShow.push(group);
    }
    if (this.keyFilter !== '') {
      this.membersShow.clear();
      this.membersOriginal.controls.forEach(element => {
        // tslint:disable-next-line: max-line-length
        if ((element.value.employeeName).toLowerCase().normalize('NFD').replace(/[\u0300-\u036f]/g, '').match(this.keyFilter.toLowerCase().normalize('NFD').replace(/[\u0300-\u036f]/g, '')) != null) {
          this.membersShow.push(element);
        }
      });
    }
  }

  // The process of displaying pop-ups, reviews member
  openPopupReviews = (index: number, member: any) => {
    const modalRef = this.modalService.open(ModalPersonalAssessmentComponent, { size: 'lg', centered: true});
    modalRef.componentInstance.member = member.value;
    modalRef.componentInstance.dropdownData = this.dropdownData;
    modalRef.result.then(result => {
      if (result !== 'close') {
        result.projectID = this.projectID;
        if (result.action !== ActionReviews.TEMPORARY) {
          this.membersShow.removeAt(index);
        }
        this.requestReviews.emit(result);
      }
    }, reason => {
    });
  }

  // The process of displaying pop-ups, handling remove
  openPopupQuestionRemove = (index: number, member: any) => {
    // NgbModal bootstrap using abtract ViewContainerRef of angular create dynamic components, https://angular.io/api/core/ViewContainerRef;
    const modalRef = this.modalService.open(ModalConfirmComponent, {centered: true});
    // COMUNICATION WITH DYNAMIC COMPONENTS USING @Input() or @Ouput() with componentInstance
    this.translateService.get('confirm-message.delete').subscribe(
      (text: string) => {
        modalRef.componentInstance.title = text;
        modalRef.result.then(result => {
          if (result === 'delete') {
            const listMemberRemove = Array<any>();
            const request = new RemoveMemberRequest();
            request.projectID = this.projectID;
            const memberRemove = {
              employeeID: member.value.employeeID,
              positionProjectID: member.value.positionProjectID,
              // tslint:disable-next-line: max-line-length
              evaluateEmployeeProjectID: member.value.evaluateEmployeeProjectID === undefined ? null : member.value.evaluateEmployeeProjectID,
              dateJoinProject: parseDate(member.value.dateJoinProject),
              dateOutProject: parseDate(member.value.dateOutProject),
              note: member.value.note === undefined ? null : member.value.note
            };
            listMemberRemove.push(memberRemove);
            request.listEmployee = listMemberRemove;
            request.action = ActionReviews.REMOVE;
            this.membersShow.removeAt(index);
            this.requestReviews.emit(request);
          }
        }, reason => {
        });
      }
    );
  }
  onShowDetailMember = (isShowDetailMember: boolean, employeeID: number) => {
    this.pm001002GlobalStore.onShowDetailMember({isShowDetailMember, employeeID});
  }
}
