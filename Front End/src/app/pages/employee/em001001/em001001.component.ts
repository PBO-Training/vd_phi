import { DatePipe } from '@angular/common';
import { ChangeDetectorRef, Component, OnDestroy, OnInit, QueryList, ViewChild, ViewChildren } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Title } from '@angular/platform-browser';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { TranslateService } from '@ngx-translate/core';
import { Subject } from 'rxjs';
import { mergeMap, takeUntil } from 'rxjs/operators';
import { TranslationService } from './../../../services/translate/translation.service';
import { ExportType } from '../../../common/constant/type';
import { DateConfig, parseDate } from '../../../common/datepicker-config/datepicker-config';
import { Listprops } from '../../../common/page-options';
import { DateValidator } from '../../../common/validator/date-validator';
import { ExportExcelService } from '../../../services/export-excel/export-excel.service';
import { StorageService } from '../../../services/storage/storage.service';
import { ModalConfirmComponent } from '../../../theme/shared/components/modal-confirm/modal-confirm.component';
import { ToastService } from '../../../theme/shared/components/toast-container/toast-service';
import { compare, SortEvent, SortTableDirective } from '../../../theme/shared/directives/sort-table-header.directive';
import { ScreenAction } from './../../../common/screen-action/screen-action';
import { CommonFunctionService } from './../../../services/common-function/common-function.service';
import { Em001001ModalComponent } from './em001001-modal/em001001-modal.component';
import { EM001001SearchService } from './em001001.service';
import { Employee, EmployeeToExportHistory, EmployeeToExportProfile } from './employee-request';
import { NgSelectComponent } from '@ng-select/ng-select';

@Component({
  selector: 'app-em001001',
  templateUrl: './em001001.component.html',
  styleUrls: ['./em001001.component.scss'],
  entryComponents: [ModalConfirmComponent],
})
export class Em001001Component implements OnInit, OnDestroy {
  //
  @ViewChildren(SortTableDirective) headers: QueryList<SortTableDirective>;
  @ViewChild('select') select: NgSelectComponent;
  date = new Date();
  config = new DateConfig();
  authButton = new ScreenAction();
  typeSkillID = new FormControl();

  constructor(
    private changeDetector: ChangeDetectorRef,
    private employeeService: EM001001SearchService,
    private formBuilder: FormBuilder,
    private titleService: Title,
    private exportExcelService: ExportExcelService,
    public toastService: ToastService,
    private modalService: NgbModal,
    private localStorage: StorageService,
    private commonFunctionService: CommonFunctionService,
    private translateService: TranslateService,
    private datePipe: DatePipe,
    public translationService: TranslationService,
  ) {
    this.authButton = this.commonFunctionService.initAuthAction(this.authButton);
    const storage: any = this.localStorage.getUser();
    this.companyCodeDefault = storage.companyCode + '-';
    this.baseForm = this.formBuilder.group({
      employeeName: [null],
      employeeCode: [null],
      skillIDList: [[]],
      startDate: [null],
      endDate: [null],
      departmentID: [null],
      projectIDList: [[]],
      languageIDList: [[]],
      employeeGenderID: [null],
      contractTypeID: [null],
      pageNum: [1],
      pageSize: [this.localStorage.getPageSize() !== null ? this.localStorage.getPageSize() : 30],
      listSkillExp: [[{ skillID: -1, skillExperience: 0, skillName: 'All' }]]
    });
  }

  get f() { return this.formSearch.controls; }
  private unsubscribe$ = new Subject();
  checked: boolean;
  backFlag: boolean;
  indeterminate = false;
  // Create List GridView
  employeeList: Employee[];
  employeeListOld: Employee[] = [];
  // End
  public isCollapsed = false;
  skills: any[] = [];
  skillsNew: any[] = [];
  projects: any[] = [];
  departments: any[] = [];
  languages: any[] = [];
  contractTypes: any[] = [];
  skillTypes: any[] = [];
  skillExps: any[] = [];
  // form Search With Employee
  formSearch: FormGroup = new FormGroup({});
  // leak
  submitted: boolean;
  screenProps = new Listprops();
  direction: string;
  column: string;
  companyCodeDefault: string;
  valueOld = {
    employeeName: '',
    employeeCode: '',
    skillIDList: [],
    startDate: '',
    endDate: '',
    departmentID: null,
    projectIDList: [],
    languageIDList: [],
    employeeGenderID: null,
    contractTypeID: null,
    pageNum: 1,
    pageSize: 30,
    listSkillExp: [{ skillID: -1, skillExperience: 0, skillName: 'All' }]
  };
  baseForm: FormGroup;
  listEmpExportHistory: EmployeeToExportHistory[] = [];
  listEmpExportProfile: EmployeeToExportProfile[] = [];
  profileHeaders: string[];
  exportForm: FormGroup;
  ngOnInit(): void {
    // change title
    this.translateService.get('title.employee.employee').subscribe((title: string) => {
      this.titleService.setTitle(title);
    });
    // change title when change language
    this.translationService.changeTranslatetion().pipe(takeUntil(this.unsubscribe$),
      mergeMap(lang => this.translateService.get('title.employee.employee'))
    ).subscribe((title: string) => {
      this.titleService.setTitle(title);
    });

    this.screenProps.loading = true;
    this.submitted = false;
    this.initForm();
    const dataSearch = this.localStorage.getSearchData();
    this.backFlag = this.localStorage.getBackFlag();
    if (this.backFlag === false && dataSearch !== null) {
      this.localStorage.removeSearchData();
    }
    this.employeeService.em001001InitList().subscribe(data => {
      if (data.content) {
        this.skills = data.content.listSkill;
        this.departments = data.content.listDepartment;
        this.projects = data.content.listProject;
        this.languages = data.content.listLanguage;
        this.contractTypes = data.content.listContractType;
        this.skillTypes = data.content.listSkillType;
        this.skillExps = data.content.listSkillExperience;
        this.projects.shift();
        this.skills.shift();
        this.languages.shift();
        this.departments.shift();
        this.contractTypes.shift();
        this.skills.forEach(element => {
          this.skillsNew.push({
            skillID: element.skillID,
            skillName: element.skillName,
            skillExperience: 0
          });
        });
        if (this.backFlag === true && dataSearch !== null) {
          if (dataSearch.data === null) {
            this.initFormSearch(dataSearch, dataSearch.currentPage);
            this.initDataEmployee(this.formSearch);
            this.screenProps.page = dataSearch.currentPage;
          } else {
            this.formSearch.patchValue({
              skillIDList: dataSearch.data.skillIDList,
              projectIDList: dataSearch.data.projectIDList,
              languageIDList: dataSearch.data.languageIDList,
              employeeGenderID: dataSearch.data.employeeGenderID,
              contractTypeID: dataSearch.data.contractTypeID,
              listSkillExp: dataSearch.data.listSkillExp
            });
            this.initFormSearch(dataSearch, dataSearch.currentPage);
            this.initDataEmployee(this.formSearch);
            this.screenProps.page = dataSearch.currentPage;
          }
        } else {
          this.formSearch.patchValue({
            skillIDList: [],
            projectIDList: [],
            languageIDList: [],
            employeeGenderID: null,
            contractTypeID: null,
            departmentID: null,
            listSkillExp: [{ skillID: -1, skillExperience: 0, skillName: 'All' }]
          });
          this.initDataEmployee(this.baseForm);
        }
      }
    });
    this.localStorage.saveBackFlag(false);
  }

  initFormSearch(dataSearch: any, currentPage: number): void {
    if (dataSearch.data !== null) {
      this.formSearch = this.formBuilder.group({
        employeeName: dataSearch.data.employeeName,
        employeeCode: dataSearch.data.employeeCode,
        skillIDList: [dataSearch.data.skillIDList],
        startDate: dataSearch.data.startDate,
        endDate: dataSearch.data.endDate,
        departmentID: dataSearch.data.departmentID,
        projectIDList: [dataSearch.data.projectIDList],
        languageIDList: [dataSearch.data.languageIDList],
        employeeGenderID: dataSearch.data.employeeGenderID,
        contractTypeID: dataSearch.data.contractTypeID,
        pageSize: [this.localStorage.getPageSize() !== null ? this.localStorage.getPageSize() : 30],
        pageNum: currentPage,
        listSkillExp: [dataSearch.data.listSkillExp]
      }, { validator: Validators.compose([DateValidator.dateLessThan('startDate', 'endDate')]) });
      this.exportForm = this.formBuilder.group({
        exportType: [null],
        listID: [[]]
      });
      dataSearch.data.listSkillExp.forEach(element => {
        const index = this.skillsNew.findIndex(x => x.skillID === element.skillID);
        if (index > -1) {
          this.skillsNew[index].skillExperience = element.skillExperience;
        }
      });
    } else {
      this.formSearch = this.formBuilder.group({
        employeeName: [null],
        employeeCode: [null],
        skillIDList: [[]],
        startDate: [null, DateValidator.startDate],
        endDate: [null],
        departmentID: [null, Validators.min(-1)],
        projectIDList: [[]],
        languageIDList: [[]],
        employeeGenderID: [null, Validators.min(-1)],
        contractTypeID: [null, Validators.min(-1)],
        pageSize: [this.localStorage.getPageSize() !== null ? this.localStorage.getPageSize() : 30],
        pageNum: currentPage,
        listSkillExp: [[{ skillID: -1, skillExperience: 0, skillName: 'All' }]]
      }, { validator: Validators.compose([DateValidator.dateLessThan('startDate', 'endDate')]) });
      this.exportForm = this.formBuilder.group({
        exportType: [null],
        listID: [[]]
      });
    }
  }

  initForm(): void {
    this.formSearch = this.formBuilder.group({
      employeeName: [null],
      employeeCode: [null],
      skillIDList: [[]],
      startDate: [null, DateValidator.startDate],
      endDate: [null],
      departmentID: [null, Validators.min(-1)],
      projectIDList: [[]],
      languageIDList: [[]],
      employeeGenderID: [null, Validators.min(-1)],
      contractTypeID: [null, Validators.min(-1)],
      pageSize: [this.localStorage.getPageSize() !== null ? this.localStorage.getPageSize() : 30],
      listSkillExp: [[{ skillID: -1, skillExperience: 0, skillName: 'All' }]]
    }, { validator: Validators.compose([DateValidator.dateLessThan('startDate', 'endDate')]) });
    this.exportForm = this.formBuilder.group({
      exportType: [null],
      listID: [[]]
    });
  }

  ngOnDestroy(): void {
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
  }

  initDataEmployee(form: FormGroup) {
    this.resetSort();
    this.submitted = false;
    const formData = {
      ...form.value,
      startDate: parseDate(form.value.startDate),
      endDate: parseDate(form.value.endDate),
      departmentID: form.value.departmentID != null ? form.value.departmentID : -1,
      employeeGenderID: form.value.employeeGenderID != null ? form.value.employeeGenderID : -1,
      contractTypeID: form.value.contractTypeID != null ? form.value.contractTypeID : -1,
    };
    this.employeeService.em001001Search(formData).pipe(takeUntil(this.unsubscribe$)).subscribe(datacheck => {
      if (datacheck.error === null) {
        this.employeeList = datacheck.content.data;
        this.resetCheckedSet();
        this.employeeListOld = datacheck.content.data;
        this.employeeList.forEach(e => {
          e.listSkill = e.listSkill.sort((a, b) => {
            return +b.skillExperience - +a.skillExperience;
          });
          e.listLanguage = e.listLanguage.sort((a, b) => {
            return +b.levelLanguageValue - +a.levelLanguageValue;
          });
        });
        this.screenProps.pageNum = datacheck.content.pageNum ? datacheck.content.pageNum - 1 : 0;
        this.screenProps.pageSize = datacheck.content.pageSize ? datacheck.content.pageSize : 30;
        this.screenProps.totalRecord = datacheck.content.totalElements;
        this.screenProps.loading = false;
      }
    }, err => { });
  }

  searchEmployee() {
    if (this.formSearch.valid) {
      this.screenProps.loading = true;
      this.submitted = true;
      this.formSearch.value.pageNum = 1;
      this.screenProps.page = 1;
      this.formSearch.value.pageSize = this.localStorage.getPageSize() !== null ? this.localStorage.getPageSize() : 30;
      this.valueOld = JSON.parse(JSON.stringify(this.formSearch.value));
      this.baseForm.value.employeeName = this.valueOld.employeeName;
      this.baseForm.value.employeeCode = this.valueOld.employeeCode;
      this.baseForm.value.skillIDList = this.valueOld.skillIDList;
      if (this.baseForm.value.skillIDList.length > 1 && this.baseForm.value.skillIDList.includes(-1)) {
        const index = this.baseForm.value.skillIDList.indexOf(-1);
        this.baseForm.value.skillIDList.splice(index, 1);
      }
      this.baseForm.value.startDate = this.valueOld.startDate;
      this.baseForm.value.endDate = this.valueOld.endDate;
      this.baseForm.value.departmentID = this.valueOld.departmentID;
      this.baseForm.value.projectIDList = this.valueOld.projectIDList;
      if (this.baseForm.value.projectIDList.length > 1 && this.baseForm.value.projectIDList.includes(-1)) {
        const index = this.baseForm.value.projectIDList.indexOf(-1);
        this.baseForm.value.projectIDList.splice(index, 1);
      }
      this.baseForm.value.languageIDList = this.valueOld.languageIDList;
      if (this.baseForm.value.languageIDList.length > 1 && this.baseForm.value.languageIDList.includes(-1)) {
        const index = this.baseForm.value.languageIDList.indexOf(-1);
        this.baseForm.value.languageIDList.splice(index, 1);
      }
      this.baseForm.value.employeeGenderID = this.valueOld.employeeGenderID;
      this.baseForm.value.contractTypeID = this.valueOld.contractTypeID;
      this.baseForm.value.pageNum = this.valueOld.pageNum;
      this.baseForm.value.pageSize = this.valueOld.pageSize;
      this.baseForm.value.listSkillExp = this.valueOld.listSkillExp;
      this.initDataEmployee(this.baseForm);
      this.localStorage.saveSearchData(this.baseForm.value, this.screenProps.page);
    }
  }

  changePageSize(pageSize: number): void {
    this.resetCheckedSet();
    this.screenProps.pageSize = pageSize;
    // save pageSize
    this.localStorage.savePageSizeLocal(this.screenProps.pageSize);
    this.formSearch.value.pageSize = pageSize;
    this.baseForm.value.pageSize = pageSize;
    const dataSearch = this.localStorage.getSearchData();
    if (dataSearch !== null) {
      if (dataSearch.data === null) {
        const searchForm = this.formBuilder.group({
          employeeName: [null],
          employeeCode: [null],
          skillIDList: [[]],
          startDate: [null, DateValidator.startDate],
          endDate: [null],
          departmentID: [null, Validators.min(-1)],
          projectIDList: [[]],
          languageIDList: [[]],
          employeeGenderID: [null, Validators.min(-1)],
          contractTypeID: [null, Validators.min(-1)],
          pageSize,
          pageNum: dataSearch.currentPage,
          listSkillExp: [[{ skillID: -1, skillExperience: 0, skillName: 'All' }]]
        });
        this.initDataEmployee(searchForm);
      } else {
        const searchForm = this.formBuilder.group({
          employeeName: dataSearch.data.employeeName,
          employeeCode: dataSearch.data.employeeCode,
          skillIDList: [dataSearch.data.skillIDList],
          startDate: dataSearch.data.startDate,
          endDate: dataSearch.data.endDate,
          departmentID: dataSearch.data.departmentID,
          projectIDList: [dataSearch.data.projectIDList],
          languageIDList: [dataSearch.data.languageIDList],
          employeeGenderID: dataSearch.data.employeeGenderID,
          contractTypeID: dataSearch.data.contractTypeID,
          pageSize,
          pageNum: dataSearch.currentPage,
          listSkillExp: [dataSearch.data.listSkillExp]
        });
        this.initDataEmployee(searchForm);
      }
    } else {
      this.initDataEmployee(this.baseForm);
    }
  }

  pageChangeOutput(currentPage: number) {
    this.resetCheckedSet();
    this.baseForm.value.pageNum = currentPage;
    this.screenProps.pageNum = currentPage - 1;
    this.baseForm.value.pageSize = this.screenProps.pageSize;
    const dataSearch = this.localStorage.getSearchData();
    if (dataSearch !== null && dataSearch.data !== null) {
      const searchForm = this.formBuilder.group({
        employeeName: dataSearch.data.employeeName,
        employeeCode: dataSearch.data.employeeCode,
        skillIDList: [dataSearch.data.skillIDList],
        startDate: dataSearch.data.startDate,
        endDate: dataSearch.data.endDate,
        departmentID: dataSearch.data.departmentID,
        projectIDList: [dataSearch.data.projectIDList],
        languageIDList: [dataSearch.data.languageIDList],
        employeeGenderID: dataSearch.data.employeeGenderID,
        contractTypeID: dataSearch.data.contractTypeID,
        pageSize: this.localStorage.getPageSize(),
        pageNum: currentPage,
        listSkillExp: [dataSearch.data.listSkillExp]
      });
      this.localStorage.saveSearchData(dataSearch.data, currentPage);
      this.initDataEmployee(searchForm);
    } else {
      this.localStorage.saveSearchData(null, currentPage);
      this.initDataEmployee(this.baseForm);
    }
    this.exportForm = this.formBuilder.group({
      exportType: [null],
      listID: [[]]
    });
  }

  deleteEmployee() {
    if (this.screenProps.setOfCheckedId.size > 0) {
      this.screenProps.setOfCheckedId.forEach(x => {
        if (x) {
          this.screenProps.ids.push(x);
        }
      });
      this.openPopup(this.screenProps.ids);
    } else {
      this.translateService.get('notification-message.unchecked').subscribe((text: string) => {
        this.toastService.show(text, { classname: 'bg-warning text-light', delay: 3000 });
      });
    }
  }

  // open popup confirm
  openPopup(ids: number[]) {
    // NgbModal bootstrap using abtract ViewContainerRef of angular create dynamic components, https://angular.io/api/core/ViewContainerRef;
    const modalRef = this.modalService.open(ModalConfirmComponent, { centered: true });
    // COMUNICATION WITH DYNAMIC COMPONENTS USING @Input() or @Ouput() with componentInstance
    this.translateService.get('confirm-message.delete').subscribe(
      (text: string) => {
        modalRef.componentInstance.title = text;
      }
    );
    modalRef.result.then(result => {
      if (result === 'delete') {
        this.employeeService.em001001Delete(ids).pipe(takeUntil(this.unsubscribe$)).subscribe(resp => {
          if (resp.error === null) {
            // this.reset();
            this.initDataEmployee(this.baseForm);
            // show toast if delete success
            this.translateService.get('notification-message.delete-success').subscribe((message: string) => {
              this.toastService.show(message, { classname: 'bg-success text-light', delay: 3000 });
            });
            this.screenProps.ids = [];
            this.screenProps.setOfCheckedId.clear();
          } else {
            // show toast if delete fail
            this.translateService.get('notification-message.delete-fail').subscribe((message: string) => {
              this.toastService.show(message, { classname: 'bg-danger text-light', delay: 3000 });
            });
          }
        });
      } else {
        this.screenProps.ids = [];
        this.resetCheckedSet();
      }
    }, reason => {
    });
  }

  onAllChecked(event) {
    this.employeeList.forEach(item => this.updateCheckedSet(item.employeeID, event.target.checked));
    this.refreshCheckedStatus();
  }

  onItemChecked(id: number, event): void {
    this.updateCheckedSet(id, event.target.checked);
    this.refreshCheckedStatus();
  }

  onCurrentPageDataChange($event: Employee[]): void {
    this.employeeList = $event;
    this.refreshCheckedStatus();
  }

  refreshCheckedStatus(): void {
    this.checked = this.employeeList.every(item => this.screenProps.setOfCheckedId.has(item.employeeID));
    this.indeterminate = this.employeeList.some(item => this.screenProps.setOfCheckedId.has(item.employeeID)) && !this.checked;
  }

  updateCheckedSet(id: number, checked: boolean): void {
    if (checked) {
      this.screenProps.setOfCheckedId.add(id);
    } else {
      this.screenProps.setOfCheckedId.delete(id);
    }
  }

  resetCheckedSet(): void {
    this.checked = false;
    this.screenProps.setOfCheckedId.clear();
    this.refreshCheckedStatus();
    this.exportForm = this.formBuilder.group({
      exportType: [null],
      listID: [[]]
    });
  }

  createEmployeeHistoryExcelFiles() {
    const listID: number[] = [];
    if (this.screenProps.setOfCheckedId.size > 0) {
      this.screenProps.setOfCheckedId.forEach(x => {
        if (x) {
          listID.push(x);
        }
      });
    } else {
      this.translateService.get('notification-message.export-unchecked').subscribe((text: string) => {
        this.toastService.show(text, { classname: 'bg-warning text-light', delay: 3000 });
      });
      return;
    }
    this.exportForm.value.exportType = ExportType.HISTORY;
    this.exportForm.value.listID = listID;
    this.employeeService.em001001Export(this.exportForm.value).subscribe(data => {
      this.listEmpExportHistory = data.content;
      this.exportExcelService.createEmployeeHistoryExcelFiles('技術経歴書', this.listEmpExportHistory);
    });
  }

  createEmployeeProfileExcelFiles() {
    const listID: number[] = [];
    if (this.screenProps.setOfCheckedId.size > 0) {
      this.screenProps.setOfCheckedId.forEach(x => {
        if (x) {
          listID.push(x);
        }
      });
    } else {
      this.translateService.get('notification-message.export-unchecked').subscribe((text: string) => {
        this.toastService.show(text, { classname: 'bg-warning text-light', delay: 3000 });
      });
      return;
    }
    this.exportForm.value.exportType = ExportType.PROFILE;
    this.exportForm.value.listID = listID;
    this.employeeService.em001001Export(this.exportForm.value).subscribe(data => {
      this.listEmpExportProfile = data.content.profile;
      this.profileHeaders = data.content.listHeaders;
      const listToExport: any[] = [];
      const headers: string[] = [];
      this.profileHeaders.forEach(profileHeader => {
        this.translateService.get(profileHeader).subscribe((text: string) => {
          headers.push(text);
        });
      });
      this.listEmpExportProfile.forEach((e, index) => {
        const row: any[] = [];
        row.push(index + 1);
        row.push(e.employeeCode ? e.employeeCode : '');
        row.push(e.fullName ? e.fullName : '');
        row.push(e.biography ? e.biography : '');
        row.push(e.birthday ? this.datePipe.transform(e.birthday, 'dd-MM-yyyy') : '');
        this.translateService.get('employee.list.export.data.' + e.gender).subscribe((text: string) => {
          row.push(text);
        });
        row.push(e.email ? e.email : '');
        row.push(e.phone ? e.phone : '');
        row.push(e.address ? e.address : '');
        row.push(e.identityCard ? e.identityCard : '');
        row.push(e.graduateYear ? e.graduateYear : '');
        row.push(e.university ? e.university : '');
        row.push(e.skype ? e.skype : '');
        row.push(e.dateJoinCompany ? this.datePipe.transform(e.dateJoinCompany, 'dd-MM-yyyy') : '');
        row.push(e.numOfChild ? e.numOfChild : '');
        row.push(e.emergency ? e.emergency : '');
        row.push(e.dateOfIssueOfIDCard ? this.datePipe.transform(e.dateOfIssueOfIDCard, 'dd-MM-yyyy') : '');
        row.push(e.description ? e.description : '');
        this.translateService.get('employee.list.export.data.' + e.married).subscribe((text: string) => {
          row.push(text);
        });
        row.push(e.departmentName ? e.departmentName : '');
        row.push(e.statusEmployeeName ? e.statusEmployeeName : '');
        row.push(e.positionEmployeeName ? e.positionEmployeeName : '');
        row.push(e.nationalityName ? e.nationalityName : '');
        row.push(e.degreeName ? e.degreeName : '');
        row.push(e.listEmpSkillName ? e.listEmpSkillName : '');
        row.push(e.listEmpLanguageName ? e.listEmpLanguageName : '');
        row.push(e.listEmpProjectName ? e.listEmpProjectName : '');
        row.push(e.contractTypeName ? e.contractTypeName : '');
        listToExport.push(row);
      });
      this.exportExcelService.createEmployeeProfileExcelFile('List', headers, listToExport);
    });
  }

  resetForm() {
    this.screenProps.page = 1;
    this.reset();
  }

  reset() {
    this.baseForm = this.formBuilder.group({
      employeeName: [null],
      employeeCode: [null],
      skillIDList: [[]],
      startDate: [null, DateValidator.startDate],
      endDate: [null],
      departmentID: [-1, Validators.min(-1)],
      projectIDList: [[]],
      languageIDList: [[]],
      employeeGenderID: [-1, Validators.min(-1)],
      contractTypeID: [-1, Validators.min(-1)],
      pageSize: this.localStorage.getPageSize(),
      pageNum: this.screenProps.page,
      listSkillExp: [[{ skillID: -1, skillExperience: 0, skillName: 'All' }]]
    });

    this.formSearch = this.formBuilder.group({
      employeeName: [null],
      employeeCode: [null],
      skillIDList: [[]],
      startDate: [null, DateValidator.startDate],
      endDate: [null],
      departmentID: [null, Validators.min(-1)],
      projectIDList: [[]],
      languageIDList: [[]],
      employeeGenderID: [null, Validators.min(-1)],
      contractTypeID: [null, Validators.min(-1)],
      pageNum: [1],
      pageSize: [this.localStorage.getPageSize() !== null ? this.localStorage.getPageSize() : 30],
      listSkillExp: [[{ skillID: -1, skillExperience: 0, skillName: 'All' }]]
    }, { validator: Validators.compose([DateValidator.dateLessThan('startDate', 'endDate')]) });
    this.exportForm = this.formBuilder.group({
      listID: [[]]
    });
    this.initDataEmployee(this.formSearch);
    this.localStorage.removeSearchData();
    this.skillsNew.forEach(element => {
      element.skillExperience = 0;
    });
  }

  resetSort() {
    // reset sort
    this.column = 'employeeCode';
    this.direction = 'asc';
  }

  // sort common
  onSortNew(event) { }

  onSort({ column, direction }: SortEvent) {
    this.direction = direction;
    this.column = column;
    this.employeeList = [...this.employeeList].sort((a, b) => {
      let res: number;
      let nullableStringA = '';
      let nullableStringB = '';
      switch (column) {
        case 'employeeCode':
          res = compare(a.employeeCode, b.employeeCode);
          return direction === 'asc' ? res : -res;
        case 'employeeName':
          res = a.employeeName.localeCompare(b.employeeName);
          return direction === 'asc' ? res : -res;
        case 'employeePhone':
          res = compare(+a.employeePhone, +b.employeePhone);
          return direction === 'asc' ? res : -res;
        case 'employeeSkype':
          nullableStringA = a.employeeSkype === null ? '' : a.employeeSkype;
          nullableStringB = b.employeeSkype === null ? '' : b.employeeSkype;
          res = nullableStringA.localeCompare(nullableStringB);
          return direction === 'asc' ? res : -res;
        case 'employeeDepartment':
          nullableStringA = a.employeeDepartment === null ? '' : a.employeeDepartment.departmentName;
          nullableStringB = b.employeeDepartment === null ? '' : b.employeeDepartment.departmentName;
          res = nullableStringA.localeCompare(nullableStringB);
          return direction === 'asc' ? res : -res;
        case 'listProject':
          res = this.listProjectToString(a.listProject).localeCompare(this.listProjectToString(b.listProject));
          return direction === 'asc' ? res : -res;
        case 'listSkill':
          res = this.listSkillToString(a.listSkill).localeCompare(this.listSkillToString(b.listSkill));
          return direction === 'asc' ? res : -res;
        case 'listLanguage':
          res = this.listLanguageToString(a.listLanguage).localeCompare(this.listLanguageToString(b.listLanguage));
          return direction === 'asc' ? res : -res;
        case 'employeeContract':
          nullableStringA = a.employeeContract === null ? '' : a.employeeContract.contractTypeName;
          nullableStringB = b.employeeContract === null ? '' : b.employeeContract.contractTypeName;
          res = nullableStringA.localeCompare(nullableStringB);
          return direction === 'asc' ? res : -res;
      }
    });
  }

  listProjectToString(listProject: any): string {
    let result = '';
    listProject.forEach(element => {
      if (element?.projectID !== null && element?.projectID !== undefined) {
        result = result + ' ' + element?.projectName;
      }
    });
    return result;
  }

  listSkillToString(listSkill: any): string {
    let result = '';
    listSkill.forEach(element => {
      if (element?.skillID !== null && element?.skillID !== undefined) {
        result = result + ' ' + element?.skillName;
        if (element?.levelSkillID !== null && element?.levelSkillID !== undefined) {
          result = result + ' ' + element?.levelSkillName;
        }
      }
    });
    return result;
  }

  listLanguageToString(listLanguage: any): string {
    let result = '';
    listLanguage.forEach(element => {
      if (element?.languageID !== null && element?.languageID !== undefined) {
        result = result + ' ' + element?.languageName;
        if (element?.levelLanguageID !== null && element?.levelLanguageID !== undefined) {
          result = result + ' ' + element?.levelLanguageName;
        }
      }
    });
    return result;
  }

  onAddNewProjectID(event) {
    const itemValue: number = Number(event);
    this.valueOld = JSON.parse(JSON.stringify(this.formSearch.value));
    let newProjectIDList: number[] = this.valueOld.projectIDList;
    if (newProjectIDList.length > 1) {
      if (itemValue === -1) {
        newProjectIDList = [-1];
      } else {
        if (newProjectIDList.includes(-1)) {
          newProjectIDList = newProjectIDList.filter(item => item !== -1);
        }
      }
      if (itemValue === 0) {
        newProjectIDList = [0];
      } else {
        if (newProjectIDList.includes(0)) {
          newProjectIDList = newProjectIDList.filter(item => item !== 0);
        }
      }
    }
    this.formSearch.patchValue({
      projectIDList: newProjectIDList,
    });
  }

  onAddNewSkillID(event) {
    const itemValue: number = Number(event);
    this.valueOld = JSON.parse(JSON.stringify(this.formSearch.value));
    let newSkillIDList: number[] = this.valueOld.skillIDList;
    let newSkillExpList: any[] = this.valueOld.listSkillExp;
    if (newSkillIDList.length > 1) {
      if (itemValue === -1) {
        newSkillIDList = [-1];
      } else {
        if (newSkillIDList.includes(-1)) {
          newSkillIDList = newSkillIDList.filter(item => item !== -1);
        }
      }
      if (itemValue === 0) {
        newSkillIDList = [0];
      } else {
        if (newSkillIDList.includes(0)) {
          newSkillIDList = newSkillIDList.filter(item => item !== 0);
        }
      }
    }
    if (itemValue === 0) {
      newSkillExpList = [{ skillID: 0, skillExperience: 0, skillName: 'None' }];
      this.skillsNew.forEach(element => {
        element.skillExperience = 0;
      });
    } else {
      const skillName: string = this.skills[this.skills.findIndex(x => x.skillID === itemValue)].skillName;
      newSkillExpList = newSkillExpList.filter(skill => skill.skillID !== -1 && skill.skillID !== 0);
      newSkillExpList.push({ skillID: itemValue, skillExperience: 0, skillName: '' + skillName });
    }
    this.formSearch.patchValue({
      skillIDList: newSkillIDList,
      listSkillExp: newSkillExpList
    });
  }

  onAddNewLanguageID(event) {
    const itemValue: number = Number(event);
    this.valueOld = JSON.parse(JSON.stringify(this.formSearch.value));
    let newLanguageIDList: number[] = this.valueOld.languageIDList;
    if (newLanguageIDList.length > 1) {
      if (itemValue === -1) {
        newLanguageIDList = [-1];
      } else {
        if (newLanguageIDList.includes(-1)) {
          newLanguageIDList = newLanguageIDList.filter(item => item !== -1);
        }
      }
      if (itemValue === 0) {
        newLanguageIDList = [0];
      } else {
        if (newLanguageIDList.includes(0)) {
          newLanguageIDList = newLanguageIDList.filter(item => item !== 0);
        }
      }
    }
    this.formSearch.patchValue({
      languageIDList: newLanguageIDList,
    });
  }

  sortData(event) {
    // TODO : apply sort
  }

  onClickMoreBtn() {
    const listSkillID = [...this.formSearch.value.skillIDList.length === 0 ? [-1] : this.formSearch.value.skillIDList];
    const modalRef = this.modalService.open(Em001001ModalComponent, { size: 'md', scrollable: true, centered: true });
    this.translateService.get('employee.list.formsearch.skill-title').subscribe(
      (text: string) => {
        modalRef.componentInstance.title = text;
      }
    );
    modalRef.componentInstance.listSkillID = listSkillID;
    modalRef.componentInstance.listSkills = this.skills;
    modalRef.componentInstance.listOldSkills = this.formSearch.value.listSkillExp;
    modalRef.componentInstance.isOK.subscribe((emmitedValue) => {
      const oldSkillExpList = [];
      emmitedValue.forEach(element => {
        oldSkillExpList.push(
          {
            skillID: element.skillID,
            skillExperience: element.skillExperience,
            skillName: element.skillName
          }
        );
        const index = this.skillsNew.findIndex(x => x.skillID === element.skillID);
        if (index > -1) {
          this.skillsNew[index].skillExperience = element.skillExperience;
        }
      });
      this.formSearch.patchValue({
        listSkillExp: oldSkillExpList
      });
    });
  }

  onRemoveExistSkillID(event: any) {
    const itemValue: number = Number(event.value);
    let oldSkillExpList: any[] = this.formSearch.value.listSkillExp;
    oldSkillExpList.forEach((element, index) => {
      if (Number(element.skillID) === itemValue) {
        oldSkillExpList.splice(index, 1);
        if (oldSkillExpList.length === 0) {
          oldSkillExpList = [{ skillID: -1, skillExperience: 0, skillName: 'All' }];
        }
      }
    });
    this.formSearch.patchValue({
      listSkillExp: oldSkillExpList
    });
    const indexToRemove = this.skillsNew.findIndex(x => x.skillID === itemValue);
    if (indexToRemove > -1) {
      this.skillsNew[indexToRemove].skillExperience = 0;
    }
    // if dropdown is open, when remove on backspace, remove span year of experience in current option
    if (this.select.element.children[1]) {
      const selectedOption = this.select.element.children[1].children[0].children[1].children[event.index].children[0];
      const innerHTML = selectedOption.innerHTML;
      const indexOfChildSpan = innerHTML.indexOf('<span');
      if (indexOfChildSpan > -1) {
        selectedOption.innerHTML = innerHTML.substring(0, indexOfChildSpan);
      }
    }
  }

  onClearAllItem() {
    this.formSearch.patchValue({
      listSkillExp: [{ skillID: -1, skillExperience: 0, skillName: 'All' }]
    });
    this.skillsNew.forEach(element => {
      element.skillExperience = 0;
    });
    // if dropdown is open, when clear all, remove span year of experience in selected options
    if (this.select.element.children[1]) {
      const listSelectedOption = this.select.element.children[1].children[0].children[1].querySelectorAll('.ng-option-selected');
      listSelectedOption.forEach(element => {
        const selectedOption = element.children[0];
        const innerHTML = selectedOption.innerHTML;
        const indexOfChildSpan = innerHTML.indexOf('<span');
        if (indexOfChildSpan > -1) {
          selectedOption.innerHTML = innerHTML.substring(0, indexOfChildSpan);
        }
      });
    }
  }

  isSearchedElement(elementID: number, type: string) {
    switch (type) {
      case 'skill':
        if (this.formSearch.value.listSkillExp.findIndex(skill => skill.skillID === elementID) > -1) {
          return true;
        }
        break;
      case 'project':
        if (this.formSearch.value.projectIDList.findIndex(projectID => projectID === elementID) > -1) {
          return true;
        }
        break;
    }
    return false;
  }
}
