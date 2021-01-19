import { Component, OnInit, Input, SimpleChanges, OnChanges, Output,
  EventEmitter, ElementRef, ViewChild, AfterViewInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { DateConfig, formatDate, parseDate, parseNewDateJs } from '../../../../common/datepicker-config/datepicker-config';
import { ScreenAction } from '../../../../common/screen-action/screen-action';
import { Listprops } from '../../../../common/page-options';
import { DateValidator } from '../../../../common/validator/date-validator';
import { CommonFunctionService } from '../../../../services/common-function/common-function.service';
import { ToastService } from '../../../../theme/shared/components/toast-container/toast-service';
import { DropDownDataMember, Pm001002Response } from '../pm001002-entity';
import { Pm001002Service } from '../pm001002.service';
import { AddMembersRequest } from './pm001002-tab-member-add-members-entity';
import { TranslateService } from '@ngx-translate/core';
import { LengthConstant } from '../../../../common/constant/length';

@Component({
  selector: 'app-pm001002-tab-member-add-members',
  templateUrl: './pm001002-tab-member-add-members.component.html',
  styleUrls: ['./pm001002-tab-member-add-members.component.scss']
})
export class Pm001002TabMemberAddMembersComponent implements OnInit, OnChanges, AfterViewInit {
  @Input() dropdownData: DropDownDataMember;
  @Input() projectResponse: Pm001002Response;
  @Output() navigateActionTabMember = new EventEmitter<number>();
  @ViewChild('scrollframe', {static: false}) scrollFrame: ElementRef;
  authButton = new ScreenAction();
  formAddMembers: FormGroup;
  formGetListEmployee: FormGroup;
  request: AddMembersRequest;
  projectID = this.route.snapshot.params.id;
  private unsubscribe$ = new Subject();
  submitted = false;
  date = new Date();
  errorsCode: string;
  screenProps = new Listprops();
  config = new DateConfig();
  // Html variable
  showId = true;
  loading = true;
  isHidden = true;
  listEmployeeShow: any;
  listEmployeeOriginal: any;
  listMemberIDCurrent = [];
  submitedDetails = false;
  displayMessage: string;
  keyFilter: string;
  // Srcoll variable
  private scrollContainer: any;
  private isNearBottom = true;
  constructor(
    private route: ActivatedRoute,
    private formBuilder: FormBuilder,
    public toastService: ToastService,
    private projectService: Pm001002Service,
    private translateService: TranslateService,
    private commonFunctionService: CommonFunctionService
  ) {
    this.formAddMembers = this.formBuilder.group({
      projectID: [this.projectID],
      listEmployeeID: [[]],
      positionProjectID: [null, Validators.required],
      dateJoinProject: [formatDate(parseNewDateJs(this.date))],
      dateOutProject: [null],
    }, { validator: Validators.compose([DateValidator.dateLessThan('dateJoinProject', 'dateOutProject')]) });
    this.formGetListEmployee = this.formBuilder.group({
      employeeName: [null],
      employeeCode: [null],
      skillIDList: [[-1]],
      startDate: [null, DateValidator.startDate],
      endDate: [null],
      departmentID: [-1, Validators.min(-1)],
      projectIDList: [[-1]],
      languageIDList: [[-1]],
      employeeGenderID: [-1, Validators.min(-1)],
      contractTypeID: [-1, Validators.min(-1)],
      pageNum: [1],
      pageSize: [LengthConstant.INTEGER_MAX_VALUE],
      listSkillExp: [[{ skillID: -1, skillExperience: 0, skillName: 'All' }]]
    },
      { validator: Validators.compose([DateValidator.dateLessThan('startDate', 'endDate')]) });
    this.authButton = this.commonFunctionService.initAuthAction(this.authButton);
  }

  ngAfterViewInit() {
    this.scrollContainer = this.scrollFrame?.nativeElement;
    this.listEmployeeShow?.changes?.subscribe(_ => this.onItemElementsChanged());
  }

  private onItemElementsChanged(): void {
    if (this.isNearBottom) {
      this.scrollToBottom();
    }
  }

  private scrollToBottom(): void {
    this.scrollContainer.scroll({
      top: this.scrollContainer.scrollHeight,
      left: 0,
      behavior: 'smooth'
    });
  }

  private isUserNearBottom(): boolean {
    const threshold = 150;
    const position = this.scrollContainer.scrollTop + this.scrollContainer.offsetHeight;
    const height = this.scrollContainer.scrollHeight;
    return position > height - threshold;
  }

  scrolled(event: any): void {
    this.isNearBottom = this.isUserNearBottom();
  }
  ngOnInit(): void {
    this.screenProps.loading = true;
    this.submitted = false;
    // tslint:disable-next-line: max-line-length
    this.searchEmployee(Number(this.projectResponse?.objectDepartment == null ? -1 : this.projectResponse?.objectDepartment?.departmentID));
  }
  ngOnChanges(changes: SimpleChanges): void {
    if (this.projectResponse?.listEmployee !== undefined) {
      // tslint:disable-next-line: forin
      for (const index in this.projectResponse?.listEmployee) {
        this.listMemberIDCurrent.push(this.projectResponse?.listEmployee[index]?.employeeID);
      }
    }
  }

  // Call api get data of list employee follow condition
  searchEmployee(value: number): void {
    this.formValidation.listEmployeeID.setValue([]);
    const requestListEmployee = {
      ...this.formGetListEmployee.value,
      startDate: parseDate(this.formGetListEmployee.value.startDate),
      endDate: parseDate(this.formGetListEmployee.value.endDate)
    };
    requestListEmployee.departmentID = value;
    this.projectService.getListEmployee(requestListEmployee).subscribe(val => {
      if (val.content) {
        const listEmployeeFilter = Array<any>();
        val.content.data.forEach(element => {
          if (!this.listMemberIDCurrent.includes(element.employeeID)) {
            listEmployeeFilter.push(element);
          }
        });
        this.listEmployeeShow = listEmployeeFilter;
        this.listEmployeeOriginal = listEmployeeFilter;
      }
    });
    this.keyFilter = null;
  }

  filterItem(event) {
    this.listEmployeeShow = this.listEmployeeOriginal;
    this.keyFilter = '' + event.target.value;
    // tslint:disable-next-line:max-line-length
    this.listEmployeeShow = this.listEmployeeShow.filter(item => (item.employeeName).toLowerCase().normalize('NFD').replace(/[\u0300-\u036f]/g, '').match(this.keyFilter.toLowerCase().normalize('NFD').replace(/[\u0300-\u036f]/g, '')));
  }

  // Get form
  get formValidation() {
    return this.formAddMembers.controls;
  }

  // Event onclick button Save
  submitForm() {
    this.submitted = true;
    this.request = {
      ...this.formAddMembers.value,
      dateJoinProject: parseDate(this.formAddMembers.value.dateJoinProject),
      dateOutProject: parseDate(this.formAddMembers.value.dateOutProject),
    };
    if (this.formAddMembers.valid && this.request.listEmployeeID.length > 0) {
      this.projectService.pm001002AddMembers(this.request).subscribe(
        (res: any) => {
          if (res.error === null) {
            // tslint:disable-next-line: no-shadowed-variable
            this.listEmployeeShow.forEach(element => {
              if (this.request.listEmployeeID.includes(element.employeeID)) {
                const member = {
                  employeeID: element.employeeID,
                  employeeCode: element.employeeCode,
                  employeeName: element.employeeName,
                  positionProjectID: this.request.positionProjectID,
                  dateJoinProject: this.request.dateJoinProject,
                  dateOutProject: this.request.dateOutProject
                };
                this.projectResponse?.listEmployee.unshift(member);
                this.listMemberIDCurrent.push(element.employeeID);
              }
            });
            for (let i = this.listEmployeeShow.length; i > 0; i-- ) {
              if (this.request.listEmployeeID.includes(this.listEmployeeShow[i - 1].employeeID)) {
                this.listEmployeeShow.splice(this.listEmployeeShow.indexOf(this.listEmployeeShow[i - 1]), 1);
              }
            }
            this.listEmployeeOriginal = this.listEmployeeShow;
            this.formValidation.listEmployeeID.setValue([]);
            this.toastService.show('notification-message.save-success', { classname: 'bg-success text-light', delay: 3000 });
          }
         });
    } else if (this.request.listEmployeeID.length === 0) {
      this.translateService.get('notification-message.add-member-unchecked').subscribe((text: string) => {
        this.toastService.show(text, { classname: 'bg-warning text-light', delay: 3000 });
      });
      this.submitted = false;
    }
  }
  onCheckChange(event: any) {
    const value = this.formAddMembers.value.listEmployeeID;
    if (event.target.checked) {
      value.push(Number(event.target.value));
    } else {
      value.splice(value.indexOf(Number(event.target.value)), 1);
    }
  }
  /*
    * Description: Set error to display
    * Param Object - errors
    */
  displayMessageError(errors: any) {
    const formControl = this.formAddMembers.get(errors.itemName);
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
